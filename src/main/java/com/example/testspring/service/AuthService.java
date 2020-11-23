package com.example.testspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.testspring.model.userModel.Auth;

public interface AuthService extends IService<Auth> {
    Auth getByLoginName(String loginName);
    boolean updatePwdByUserId(String userId, String newPwd);
}
