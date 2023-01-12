package org.pegcode.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author dreampig123
 * @since 2023-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbUser对象", description="用户表")
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id;主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户编号;用户编号")
    private String code;

    @ApiModelProperty(value = "用户名;用户名")
    private String name;

    @ApiModelProperty(value = "性别;性别")
    private Integer gender;

    @ApiModelProperty(value = "年龄;年龄")
    private Integer age;

    @ApiModelProperty(value = "角色id;角色id")
    private Integer roleId;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "电话号码")
    private String tel;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像图片")
    private String icon;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "账号启动状态，0-未启动，1-已启动")
    private Integer accountStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间;创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间;更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除状态;0：未删除，1：已删除")
    private Integer state;


}
