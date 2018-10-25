package com.uooguo.newretail.cloud.uc.service.impl;

import com.uooguo.newretail.cloud.framework.base.service.impl.BaseServiceImpl;
import com.uooguo.newretail.cloud.framework.util.BeanUtils;
import com.uooguo.newretail.cloud.uc.entity.SysUser;
import com.uooguo.newretail.cloud.uc.mapper.SysUserMapper;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import com.uooguo.newretail.cloud.uc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    private SysUserAuthentication getSysUserAuthentication(SysUser sysUser) {
        if (sysUser == null) {
            return null;
        }
        SysUserAuthentication sysUserAuthentication = new SysUserAuthentication();
        BeanUtils.copy(sysUser, sysUserAuthentication);
        return sysUserAuthentication;
    }

    @Override
    public SysUserAuthentication findUserByUsername(String username) {
        SysUser sysUser = this.sysUserMapper.selectOne(new SysUser().setUsername(username).setDelete(false));
        return this.getSysUserAuthentication(sysUser);
    }

}
