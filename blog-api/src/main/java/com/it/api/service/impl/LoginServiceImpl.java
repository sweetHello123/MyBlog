package com.it.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.it.api.common.ErrorCode;
import com.it.api.common.Result;
import com.it.api.entity.SysUser;
import com.it.api.service.LoginService;
import com.it.api.service.SysUserService;
import com.it.api.utils.JwtUtils;
import com.it.api.vo.param.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/14 18:11
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private static final String SALT = "wuchao!@###";

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Result login(LoginParam loginParam) {
        // 获取账户和密码
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        // 参数校验
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        // 密码加密
        password = DigestUtils.md5Hex(password + SALT);
        // 查询数据库
        SysUser user = userService.getUser(account, password);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        // 生成token
        String token = JwtUtils.createToken(user.getId());
        // 存入Redis
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        // 返回给前端
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        // 参数校验
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        // 查询该账户是否已存在
        SysUser user = userService.getUserByAccount(account);
        if (user != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        // 完成注册
        SysUser sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password + SALT));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(true);
        sysUser.setDeleted(false);
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        userService.save(sysUser);
        // 生成token
        String token = JwtUtils.createToken(sysUser.getId());
        // 存入Redis
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        // 返回给前端
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> map = JwtUtils.checkToken(token);
        if (map == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        // 将缓存中的json解析为user对象
        return JSON.parseObject(userJson, SysUser.class);
    }

    public static void main(String[] args) {
        String password = "admin";
        password = DigestUtils.md5Hex(password + SALT);
        System.out.println(password);
    }

}
