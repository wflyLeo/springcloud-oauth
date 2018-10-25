package com.uooguo.newretail.cloud.upm.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author zhangyu
 * @since 2018-10-10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("资源表")
@Accessors(chain = true)
@TableName("newretail_sys_resource")
public class SysResource extends Model<SysResource> {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("")
    private Long id;

    /**
     * 权限的标识
     */
    @ApiModelProperty("权限的标识")
    private String code;

    /**
     * 父id
     */
    @ApiModelProperty("父id")
    private Long parent;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String name;

    /**
     * 权限请求路径
     */
    @ApiModelProperty("权限请求路径")
    private String url;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 是否可用：Y-可用；N-不可用
     */
    @ApiModelProperty("是否可用：Y-可用；N-不可用")
    private String status;

    /**
     * 加载类型
     */
    @TableField("load_type")
    @ApiModelProperty("加载类型")
    private String loadType;

    @ApiModelProperty("")
    private Integer type;

    @ApiModelProperty("")
    private String description;

    @TableField("service_id")
    @ApiModelProperty("")
    private String serviceId;

    @ApiModelProperty("")
    private String route;

    /**
     * 是否父节点
     */
    @TableField("is_parents")
    @ApiModelProperty("是否父节点")
    private Boolean parents;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
