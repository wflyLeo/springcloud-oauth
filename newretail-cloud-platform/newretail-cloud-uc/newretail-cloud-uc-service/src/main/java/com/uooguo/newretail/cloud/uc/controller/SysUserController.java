package com.uooguo.newretail.cloud.uc.controller;

import com.uooguo.newretail.cloud.framework.base.controller.BaseController;
import com.uooguo.newretail.cloud.framework.base.pagination.PageInfo;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import com.uooguo.newretail.cloud.uc.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户控制类
 *
 * @author Tiangel
 * @date 2017.12.10
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "系统用户管理")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param username 用户名
     * @return UseVo 对象
     */
    @PostMapping("/findUserByUsername")
    public SysUserAuthentication findUserByUsername(@RequestParam("username") String username) {
        return this.sysUserService.findUserByUsername(username);
    }

    @PostMapping("findPage")
    @ApiOperation("获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", value = "会员账号/名称/手机号", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页数", dataType = "String"),
    })
    public Result findPage(String param, PageInfo pageInfo) {
        return sysUserService.findPage(param, pageInfo);
    }

}
