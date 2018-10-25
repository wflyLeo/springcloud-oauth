package com.uooguo.newretail.cloud.framework.base.pagination;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PageInfo {

    @ApiModelProperty("总页数")
    private Integer total;
    @ApiModelProperty("页数")
    private Integer pageNum = 1;
    @ApiModelProperty("每页显示条数")
    private Integer pageSize = 20;
    @ApiModelProperty("总记录数")
    private long count;

    public Integer getTotal() {
        try {
            return total = ((int) this.count + pageSize - 1) / pageSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

}
