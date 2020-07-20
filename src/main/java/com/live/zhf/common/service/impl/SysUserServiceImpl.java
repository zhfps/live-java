package com.live.zhf.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.live.zhf.common.entity.LoginUser;
import com.live.zhf.common.entity.SysPermission;
import com.live.zhf.common.entity.SysRole;
import com.live.zhf.common.entity.SysUser;
import com.live.zhf.common.dao.SysUserDao;
import com.live.zhf.common.service.SysUserMenuService;
import com.live.zhf.common.service.SysUserService;
import com.live.zhf.exception.exception.NotFoundUserException;
import com.live.zhf.exception.exception.SysException;
import com.live.zhf.utils.JwtTokenUtil;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import lombok.extern.java.Log;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-05-14 20:39:42
 */
@Log
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService, UserDetailsService {
    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserMenuService sysUserMenuService;

    @Resource
    private ResultBuilder resultBuilder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    StringRedisTemplate redisTemplate;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result<SysUser> get(Integer id) {
        Result<SysUser> result = resultBuilder.success(this.sysUserDao.get(id), ResultCode.SUCCESS);
        return result;
    }

    /**
     * 查询多条数据
     *
     * @param currentPage 查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public Result<PageInfo> queryPage(String userName,String status, Integer currentPage, Integer pageSize, String order, Integer sortType){
        String orderBy = order;
        if(sortType == 1){
            orderBy+=" desc";
        }else {
            orderBy+=" asc";
        }
        PageHelper.startPage(currentPage, pageSize,orderBy);
        List<SysUser> users = this.sysUserDao.queryPage(userName,status);
        PageInfo pageInfo = new PageInfo(users);
        Result<PageInfo> result = this.resultBuilder.success(pageInfo, ResultCode.SUCCESS);
        return result;
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public Result<Boolean> insert(SysUser sysUser) throws SysException {
        Result<Boolean> result;
        SysUser user= this.sysUserDao.getUserByName(sysUser.getUsername());
        if(user !=null){
            throw new SysException("用户名已存在");
        }
        Date createTime = new Date();
        String password = BCrypt.hashpw(sysUser.getPassword(),BCrypt.gensalt());
        sysUser.setPassword(password);
        sysUser.setCreateTime(createTime);
        Integer cell =  this.sysUserDao.insert(sysUser);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
        }
        return result;

    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public Result<Boolean> update(SysUser sysUser) {
        Result<Boolean> result;
        Integer cell = this.sysUserDao.update(sysUser);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(true,ResultCode.CREATE_ERROE);
        }
        return result;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result<Boolean> delete(Integer id) {
        Result<Boolean> result;
        SysUser user = this.sysUserDao.get(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(user.getUsername() == authentication.getName()){
            result = this.resultBuilder.error(false,ResultCode.CREATE_ERROE);
        }
        Integer cell = this.sysUserDao.delete(id);
        if(cell > 0){
            result = this.resultBuilder.success(true,ResultCode.SUCCESS);
        }else {
            result = this.resultBuilder.error(false,ResultCode.CREATE_ERROE);
        }
        return result;
    }

    @Override
    public List<SysPermission> getUserPermission(Integer userId) {
        return this.sysUserDao.getUserPermission(userId);
    }

    @Override
    public String login(Authentication authentication) {
            String AccessToken =jwtTokenUtil.createToken(authentication.getName());
            return AccessToken;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserDao.getUserByName(username);
        if (user == null)
        {
            log.info("用户"+username+"不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        Gson gson = new Gson();
        String json = redisTemplate.opsForValue().get(user.getUsername());
        if(StringUtils.isEmpty(json)){
            LoginUser loginUser = new LoginUser(user, this.sysUserDao.getLoginUserPermission(user.getId()));
            loginUser.setMenu(this.sysUserMenuService.getUserMenu(user.getId()));
            String newJson = gson.toJson(loginUser);
            redisTemplate.opsForValue().set(loginUser.getUsername(),newJson);
            return loginUser;
        }else {
            return gson.fromJson(json,LoginUser.class);
        }

    }
}