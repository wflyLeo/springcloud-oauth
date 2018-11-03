package com.uooguo.newretail.cloud.uc.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.uooguo.newretail.cloud.framework.base.pagination.PageInfo;
import com.uooguo.newretail.cloud.framework.base.service.impl.BaseServiceImpl;
import com.uooguo.newretail.cloud.framework.core.protocol.Result;
import com.uooguo.newretail.cloud.framework.util.BeanUtils;
import com.uooguo.newretail.cloud.uc.entity.SysUser;
import com.uooguo.newretail.cloud.uc.mapper.SysUserMapper;
import com.uooguo.newretail.cloud.uc.model.SysUserAuthentication;
import com.uooguo.newretail.cloud.uc.service.SysUserService;
import com.uooguo.newretail.cloud.upm.client.SysRoleClient;
import com.uooguo.newretail.cloud.upm.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleClient sysRoleClient;

    private SysUserAuthentication getSysUserAuthentication(SysUser sysUser) {
        if (sysUser == null) {
            return null;
        }
        SysUserAuthentication sysUserAuthentication = new SysUserAuthentication();
        BeanUtils.copy(sysUser, sysUserAuthentication);
        return sysUserAuthentication;
    }

    @Override
    public SysUserAuthentication findUserByUsername(String username) {
        SysUser sysUser = this.sysUserMapper.selectOne(new SysUser().setUsername(username).setDelete(false));
        return this.getSysUserAuthentication(sysUser);
    }

    @Override
    public Result findPage(String param, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Map<String, Object>> list = this.sysUserMapper.findPage(param);
        pageInfo.setCount(PageHelper.freeTotal());
        for (Map<String, Object> map : list) {
            List<SysRole> sysRoles = sysRoleClient.findRoleByUserId(Long.valueOf(String.valueOf(map.get("id"))));
            if (CollectionUtils.isEmpty(sysRoles)) {
                map.put("roleName", "");
                map.put("code", "");
            } else {
                List<String> stringList = sysRoles.stream().map(SysRole::getName).collect(Collectors.toList());
                map.put("roleName", String.join(",", stringList));
                map.put("code", sysRoles.get(0).getCode());
            }
            map.put("sysRoles", sysRoles);
        }
        return Result.buildSuccess("", list, pageInfo);
    }

}
