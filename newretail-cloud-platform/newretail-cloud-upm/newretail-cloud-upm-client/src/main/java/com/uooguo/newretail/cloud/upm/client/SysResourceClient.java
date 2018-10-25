package com.uooguo.newretail.cloud.upm.client;

import com.uooguo.newretail.cloud.upm.entity.SysResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangyu
 * @date 2018-9-12
 */
@FeignClient(name = "upm-service")
public interface SysResourceClient {

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/upm/resource/findMenuByUserId")
    List<SysResource> findMenuByUserId(@RequestParam("userId") Long userId);
}
