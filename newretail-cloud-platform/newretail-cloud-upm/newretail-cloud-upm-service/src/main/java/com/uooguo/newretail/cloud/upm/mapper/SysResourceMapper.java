package com.uooguo.newretail.cloud.upm.mapper;


import com.uooguo.newretail.cloud.framework.base.mapper.BaseMapper;
import com.uooguo.newretail.cloud.upm.entity.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> findMenuByUserId(@Param("userId") Long userId);
}