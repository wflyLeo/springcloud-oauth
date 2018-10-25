package com.uooguo.newretail.cloud.upm.controller;

import com.uooguo.newretail.cloud.framework.base.audit.EnableAudit;
import com.uooguo.newretail.cloud.framework.base.controller.BaseController;
import com.uooguo.newretail.cloud.upm.entity.SysRole;
import com.uooguo.newretail.cloud.upm.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统角色控制类
 *
 * @author Tiangel
 * @date 2017.12.10
 */
@EnableAudit
@RestController
@RequestMapping("/role")
@Api(value = "/role", tags = "系统角色管理")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/findRoleByUserId")
    @ApiOperation("获取角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", dataType = "Long"),
    })
    public List<SysRole> findRoleByUserId(@RequestParam("userId") Long userId) {
        return sysRoleService.findRoleByUserId(userId);
    }

}
