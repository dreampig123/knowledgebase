package org.pegcode.core.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Catalogue请求体", description = "Catalogue请求体")
public class CatalogueRequest {

    @NotEmpty(message = "catalogueName 不能为空")
    @ApiModelProperty(value = "目录名")
    private String catalogueName;

    @ApiModelProperty(value = "目录状态")
    private Integer catalogueStatus;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "修改人")
    private String updateId;
}
