package com.example.testspring.model.userModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightRoleEntity {
    private String roleId;
    private String roleName;
    private String roleCode;
    private Integer roleType;

}
