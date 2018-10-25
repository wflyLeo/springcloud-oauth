package com.uooguo.newretail.cloud.uc.client;

import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lengleng
 * @date 2017/10/31
 */
@FeignClient(name = "uc-service")
public interface SysUserClient {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return UserVo
     */
    @PostMapping("/uc/user/findUserByUsername")
    SysUserAuthentication findUserByUsername(@RequestParam("username") String username);

}
