package com.live.zhf.security.filter;
import com.live.zhf.exception.exception.NotFoundUserException;
import com.live.zhf.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("jwtAuthenticationTokenFilter")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
                parsingToken(request);
        }catch (NotFoundUserException e) {
            // 2. 捕获步骤1中校验出现异常，交给失败处理类进行进行处理
            filterChain.doFilter(request, response);
            return;
        }
        // 3. 校验通过，就放行
        filterChain.doFilter(request, response);
    }
    private void parsingToken(HttpServletRequest request) throws ServletRequestBindingException {
        // 1. 获取请求中的Token
        String token = request.getHeader("Access_Token");
        // 2. 校验空值情况
        if(StringUtils.isEmpty(token)) {
            throw new NotFoundUserException("not token");
        }
        // 5. token 解析
        try {
            String username = jwtTokenUtil.getSubject(token);
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            throw new NotFoundUserException(e.getMessage());
        }catch (Exception e){
            throw new NotFoundUserException(e.getMessage());
        }



    }
}
