package com.uooguo.newretail.cloud.upm.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 权限-资源中间表
 * </p>
 *
 * @author Tiangel
 * @date 2018-7-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("newretail_sys_authority")
public class SysAuthority extends Model<SysAuthority> {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("sys_role_id")
    @NotNull(message = "角色ID不能为空")
    private Long sysRoleId;

    @TableField("sys_resource_id")
    @NotNull(message = "资源ID不能为空")
    private Long sysResourceId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

