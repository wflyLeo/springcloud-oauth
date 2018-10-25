package com.uooguo.newretail.cloud.uc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author zhangyu
 * @since 2018-09-30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息表")
@Accessors(chain = true)
@TableName("newretail_sys_user")
public class SysUser extends Model<SysUser> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 登录账号
     */
    @ApiModelProperty("登录账号")
    private String username;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 密码盐
     */
    @ApiModelProperty("密码盐")
    private String salt;

    /**
     * 电话号码
     */
    @TableField("phone_number")
    @ApiModelProperty("电话号码")
    private String phoneNumber;

    /**
     * 电子邮箱
     */
    @ApiModelProperty("电子邮箱")
    private String email;

    /**
     * 状态: 1-启用，0-禁用
     */
    @ApiModelProperty("状态: 1-启用，0-禁用")
    private Boolean status;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String portrait;

    /**
     * 用户类型：1-运营，2-商家，3-供应商
     */
    @ApiModelProperty("用户类型：1-运营，2-商家，3-供应商")
    private Boolean type;

    /**
     * 删除状态：1-已删除，0-未删除
     */
    @TableField("is_delete")
    @ApiModelProperty("删除状态：1-已删除，0-未删除")
    private Boolean delete;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("修改时间")
    private Date gmtModified;

    /**
     * 最后一次登录时间
     */
    @TableField("last_login_time")
    @ApiModelProperty("最后一次登录时间")
    private Date lastLoginTime;

    /**
     * 最后一次登录ip
     */
    @TableField("last_login_ip")
    @ApiModelProperty("最后一次登录ip")
    private String lastLoginIp;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String description;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
