package com.uooguo.newretail.cloud.uc.service;


import com.uooguo.newretail.cloud.framework.base.pagination.PageInfo;
import com.uooguo.newretail.cloud.framework.base.service.BaseService;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import com.uooguo.newretail.cloud.uc.entity.SysUser;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return
     */
    SysUserAuthentication findUserByUsername(String username);

    /**
     * 获取用户列表
     *
     * @param param
     * @param pageInfo
     * @return
     */
    Result findPage(String param, PageInfo pageInfo);
}
