package org.pegcode.core.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "OnlineDocument请求体", description = "OnlineDocument请求体")
public class OnlineDocumentRequest {

    @ApiModelProperty(value = "文档主题")
    private String docTitle;

    @ApiModelProperty(value = "文档内容")
    private String docContent;

    @ApiModelProperty(value = "目录ID")
    private String catalogueId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "修改人")
    private String updateId;
}
