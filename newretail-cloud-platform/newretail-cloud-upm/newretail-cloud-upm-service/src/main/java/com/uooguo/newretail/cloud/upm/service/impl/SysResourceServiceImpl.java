package com.uooguo.newretail.cloud.upm.service.impl;

import com.uooguo.newretail.cloud.framework.base.service.impl.BaseServiceImpl;
import com.uooguo.newretail.cloud.upm.entity.SysResource;
import com.uooguo.newretail.cloud.upm.mapper.SysResourceMapper;
import com.uooguo.newretail.cloud.upm.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> findMenuByUserId(Long userId) {
        List<SysResource> sysResources = this.sysResourceMapper.findMenuByUserId(userId);
        return sysResources;
    }

}
