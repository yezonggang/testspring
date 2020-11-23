package com.example.testspring.model.userModel;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.List;
import com.example.testspring.utils.BaseEntity;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lt_sys_role")
@ApiModel(value = "LtSysRole对象", description = "角色表")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Role extends BaseEntity
{
    @ApiModelProperty(value = "角色id")
    @TableId(value = "role_id",type = IdType.UUID)
    private String roleId;
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色分类（1 超级管理员 2 系统管理员 3 普通用户）")
    private Integer roleType;
    @ApiModelProperty(value = "角色排序（升序）")
    private Integer roleSort;
    @ApiModelProperty(value = "数据范围设置（0未设置  1全部数据 2自定义数据）")
    private Integer dataScope;
    @ApiModelProperty(value = "适应业务范围（不同的功能，不同的数据权限支持）")
    private String bizScope;
    @ApiModelProperty(value = "备注信息")
    private String remarks;
    @ApiModelProperty(value = "租户代码")
    private String corpId;
    @TableField(exist = false)
    @ApiModelProperty(value = "用户列表")
    private List<User> users;

}
