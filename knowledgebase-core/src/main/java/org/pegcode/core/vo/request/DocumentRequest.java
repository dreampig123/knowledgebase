package org.pegcode.core.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel(value = "Document请求体", description = "Document请求体")
public class DocumentRequest {

    @ApiModelProperty(value = "文档主题")
    private String docTitle;

    @ApiModelProperty(value = "文档内容")
    private String docContent;

    @ApiModelProperty(value = "在线文档备注")
    private String remark;

    @NotEmpty(message = "catalogueId 不能为空")
    @ApiModelProperty(value = "目录id")
    private String catalogueId;

    @ApiModelProperty(value = "附属文件集")
    private List<FileRequest> fileList;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "修改人")
    private String updateId;
}
