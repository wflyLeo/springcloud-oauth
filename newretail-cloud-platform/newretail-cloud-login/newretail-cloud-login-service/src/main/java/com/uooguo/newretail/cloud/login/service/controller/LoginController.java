package com.uooguo.newretail.cloud.login.service.controller;


import com.uooguo.newretail.cloud.framework.base.controller.BaseController;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import com.uooguo.newretail.cloud.login.service.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户信息表 控制器
 * </p>
 *
 * @author wangqiang
 * @since 2018-09-11
 */
@RestController
@Api(tags = "用户信息表 接口")
public class LoginController extends BaseController {

    @Autowired
    private LoginService sysUserService;

    /**
     * 登陆
     */
    @PostMapping(value = "/login")
    @ApiOperation("用户登陆")
    public Result login(HttpServletRequest request, String username, String password) {
        return this.success(sysUserService.login(request, username, password));
    }


}

