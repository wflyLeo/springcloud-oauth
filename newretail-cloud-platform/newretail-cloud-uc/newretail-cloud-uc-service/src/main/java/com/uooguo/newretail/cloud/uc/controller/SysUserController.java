package com.uooguo.newretail.cloud.uc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.uooguo.newretail.cloud.framework.base.controller.BaseController;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import com.uooguo.newretail.cloud.uc.service.SysUserService;
import io.swagger.annotations.Api;
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

    @PostMapping("/findAll")
    public Result findAll() {
        return this.success(this.sysUserService.selectList(new EntityWrapper<>()));
    }

}
