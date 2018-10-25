package com.uooguo.newretail.cloud.upm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.uooguo.newretail.cloud.framework.base.service.impl.BaseServiceImpl;
import com.uooguo.newretail.cloud.upm.entity.SysRole;
import com.uooguo.newretail.cloud.upm.entity.UserRole;
import com.uooguo.newretail.cloud.upm.mapper.SysRoleMapper;
import com.uooguo.newretail.cloud.upm.mapper.UserRoleMapper;
import com.uooguo.newretail.cloud.upm.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户权限吧 服务实现类
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> findRoleByUserId(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectList(new EntityWrapper<SysRole>().in("id", userRoleMapper.selectObjs(new EntityWrapper<UserRole>().setSqlSelect("sys_role_id").eq("sys_user_id", userId))));
        return sysRoles;
    }

}
