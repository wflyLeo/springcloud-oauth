package com.uooguo.newretail.cloud.upm.controller;

import com.uooguo.newretail.cloud.framework.base.controller.BaseController;
import com.uooguo.newretail.cloud.upm.entity.SysResource;
import com.uooguo.newretail.cloud.upm.service.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统资源控制类
 *
 * @author Tiangel
 * @date 2017-12-11
 */
@RestController
@RequestMapping("/resource")
@Api(value = "/resource", tags = "系统资源管理")
public class SysResourceController extends BaseController {


    @Autowired
    private SysResourceService sysResourceService;

    @PostMapping("/findMenuByUserId")
    @ApiOperation("根据用户查询菜单")
    public List<SysResource> findMenuByUserId(Long userId) {
        List<SysResource> sysResources = this.sysResourceService.findMenuByUserId(userId);
        return sysResources;
    }

}
