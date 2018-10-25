package com.uooguo.newretail.cloud.upm.service;


import com.uooguo.newretail.cloud.framework.base.service.BaseService;
import com.uooguo.newretail.cloud.upm.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 获取角色
     *
     * @param userId
     * @return
     */
    List<SysRole> findRoleByUserId(Long userId);

}
