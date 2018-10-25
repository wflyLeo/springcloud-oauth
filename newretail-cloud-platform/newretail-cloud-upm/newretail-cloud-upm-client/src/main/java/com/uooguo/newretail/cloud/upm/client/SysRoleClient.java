package com.uooguo.newretail.cloud.upm.client;

import com.uooguo.newretail.cloud.upm.entity.SysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangyu
 * @date 2018-9-12
 */
@FeignClient(name = "upm-service")
public interface SysRoleClient {

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/upm/role/findRoleByUserId")
    List<SysRole> findRoleByUserId(@RequestParam("userId") Long userId);

}
