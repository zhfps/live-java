package com.live.zhf.config;
import com.live.zhf.exception.SysException;
import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;

/**
 * JWT的token，区分大小写
 */
@ConfigurationProperties(prefix = "config.jwt")
@Component
public class JwtConfig {
    private String secret;
    private long expire;
    private String header;

    /**
     * 生成token
     * @param subject
     * @return
     */
    public String createToken (String subject){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) throws ExpiredJwtException,UnsupportedJwtException,MalformedJwtException,SignatureException,IllegalArgumentException,SysException  {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims;
        }catch (ExpiredJwtException e){
            throw new ExpiredJwtException(null, null, e.getMessage());
        }catch (UnsupportedJwtException e){
            throw new UnsupportedJwtException(e.getMessage());
        }catch (MalformedJwtException e){
            throw new MalformedJwtException(e.getMessage());
        }catch (SignatureException e){
           throw new SignatureException(e.getMessage());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (Exception e){
            throw new SysException(e.getMessage());
        }
    }
    /**
     * 验证token是否过期失效
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) throws ExpiredJwtException,UnsupportedJwtException,MalformedJwtException,SignatureException,IllegalArgumentException,SysException  {
        return getTokenClaim(token).getExpiration();
    }
    /**
     * 获取用户名从token中
     */
    public String getSubject(String token) throws ExpiredJwtException,UnsupportedJwtException,MalformedJwtException,SignatureException,IllegalArgumentException,SysException  {
            return getTokenClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) throws ExpiredJwtException,UnsupportedJwtException,MalformedJwtException,SignatureException,IllegalArgumentException,SysException  {
        return getTokenClaim(token).getIssuedAt();
    }

    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public long getExpire() {
        return expire;
    }
    public void setExpire(long expire) {
        this.expire = expire;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
}
