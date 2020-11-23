package com.example.testspring.model.userModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightUserIdentityEntity {

    private String identityCode;
    private String identityName;
    private String identityType;
    private String organName;
    private String organCode;
    // private List<LtUapRole> ltUapRoleList;

}
