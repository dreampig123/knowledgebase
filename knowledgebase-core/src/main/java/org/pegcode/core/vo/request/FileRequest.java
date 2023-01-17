package org.pegcode.core.vo.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 文件
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-16
 */
@Data
@ApiModel(value="File请求体", description="File请求体")
public class FileRequest{

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件类型")
    private Integer fileType;

    @ApiModelProperty(value = "文件大小;KB")
    private BigDecimal fileSize;

    @ApiModelProperty(value = "文件后缀名")
    private String fileSuffix;

    @ApiModelProperty(value = "文件存储路径")
    private String filePath;

    @ApiModelProperty(value = "文件状态")
    private Integer fileStatus;

    @ApiModelProperty(value = "关联在线文件code")
    private String onlineDocCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "修改人")
    private String updateId;
}
