package com.example.testspring.model.userModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightUserEntity implements Serializable {

    private String userId;
    private String loginName;
    private String token;
    // 过期时间
    private LocalDateTime expireTime;
    private String avatar;
    private String userName;
    private String organId;
    private String organName;
    private List<LightRoleEntity> roles;
    // 当前用户角色
    private LightRoleEntity role;
}
