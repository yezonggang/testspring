package com.example.testspring.model.userModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.testspring.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lt_sys_user")
@ApiModel(value = "LtSysUser实体对象", description = "员工表")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编码")
    @TableId(value = "user_id", type = IdType.UUID)
    private String userId;

    @ApiModelProperty(value = "员工编号")
    private String userCode;

    @ApiModelProperty(value = "员工姓名")
    private String userName;

    @ApiModelProperty(value = "英文名")
    private String userNameEn;

    @ApiModelProperty(value = "组织id")
    private String organId;

    @ApiModelProperty(value = "机构编码")
    private String organCode;

    @ApiModelProperty(value = "机构名称")
    private String organName;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "办公电话")
    private String phone;

    @ApiModelProperty(value = "用户性别")
    private Integer sex;

    @ApiModelProperty(value = "头像路径")
    private String avatar;

    @ApiModelProperty(value = "个性签名")
    private String sign;

    @ApiModelProperty(value = "用户排序")
    private Integer userSort;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime regDate;

    @ApiModelProperty(value = "状态（0在职 1离职）")
    private Integer status;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "租户代码")
    private String corpId;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色列表")
    private List<Role> roles;

}
