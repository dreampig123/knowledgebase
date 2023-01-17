package org.pegcode.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 在线编辑文档
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbOnlineDocument对象", description="在线编辑文档")
public class TbOnlineDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文档编号")
    private String docCode;

    @ApiModelProperty(value = "文档主题")
    private String docTitle;

    @ApiModelProperty(value = "文档内容")
    private String docContent;

    @ApiModelProperty(value = "文档目录id")
    private Long catalogueId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "创建时间")
    //@TableField(fill = FieldFill.INSERT)  issue:数据库时间自建，不传时间异常
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private String updateId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除标识")
    private Integer state;


}
