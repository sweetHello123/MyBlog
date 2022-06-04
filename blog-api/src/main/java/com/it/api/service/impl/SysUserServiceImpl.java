package com.it.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.api.entity.SysUser;
import com.it.api.mapper.SysUserMapper;
import com.it.api.service.LoginService;
import com.it.api.service.SysUserService;
import com.it.api.vo.LoginUserVo;
import com.it.api.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser getUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account)
                .eq(SysUser::getPassword, password)
                .select(SysUser::getId, SysUser::getAccount, SysUser::getAvatar, SysUser::getNickname);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public LoginUserVo getUserByToken(String token) {
        // 先校验token
        SysUser user = loginService.checkToken(token);
        if (user == null) {
            return null;
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user, loginUserVo);
        return loginUserVo;
    }

    @Override
    public SysUser getUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public UserVo getUserVoById(Long id) {
        SysUser user = this.getById(id);
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
}
