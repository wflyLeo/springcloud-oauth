package com.uooguo.newretail.cloud.upm.service;


import com.uooguo.newretail.cloud.framework.base.service.BaseService;
import com.uooguo.newretail.cloud.upm.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
public interface SysResourceService extends BaseService<SysResource> {

    /**
     * 根据用户查询菜单
     *
     * @param userId
     * @return
     */
    List<SysResource> findMenuByUserId(Long userId);


}
