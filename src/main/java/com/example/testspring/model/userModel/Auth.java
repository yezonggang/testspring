package com.example.testspring.model.userModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.testspring.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lt_sys_user_auth")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "LtSysUserAuth对象", description = "用户认证信息表")
public class Auth extends BaseEntity {

    public static final String DEFAULT_PASSWORD = "123456";
    @ApiModelProperty(value = "账户id")
    @TableId(value = "auth_id",type = IdType.UUID)
    private String authId;

    @ApiModelProperty(value = "用户编码")
    private String userId;

    @ApiModelProperty(value = "登录账号")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    private String passwd;

    @ApiModelProperty(value = "令牌值")
    private String token;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;

    @ApiModelProperty(value = "绑定的钉钉号")
    private String dingtalkOpenid;

    @ApiModelProperty(value = "绑定的welink号")
    private String welinkOpenid;

    @ApiModelProperty(value = "绑定的微信号")
    private String wxOpenid;

    @ApiModelProperty(value = "绑定的手机串号")
    private String mobileImei;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "密码安全级别（0初始 1很弱 2弱 3安全 4很安全）")
    private Integer pwdSecurityLevel;

    @ApiModelProperty(value = "密码最后更新时间")
    private LocalDateTime pwdUpdateDate;

    @ApiModelProperty(value = "密码修改记录")
    private String pwdUpdateRecord;

    @ApiModelProperty(value = "密码问题修改时间")
    private LocalDateTime pwdQuestUpdateDate;

    @ApiModelProperty(value = "最后登陆IP")
    private String lastLoginIp;

    @ApiModelProperty(value = "最后登陆时间")
    private LocalDateTime lastLoginDate;

    @ApiModelProperty(value = "冻结时间")
    private LocalDateTime freezeDate;

    @ApiModelProperty(value = "冻结原因")
    private String freezeCause;

    @ApiModelProperty(value = "用户权重（降序）")
    private Integer userWeight;

    @ApiModelProperty(value = "状态（0正常 1冻结）")
    private Integer status;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

}
