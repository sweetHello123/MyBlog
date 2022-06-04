package com.it.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.api.entity.SysLog;
import com.it.api.mapper.SysLogMapper;
import com.it.api.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService{
}
