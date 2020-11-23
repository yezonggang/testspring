package com.example.testspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.testspring.model.userModel.User;

import java.util.List;

public interface UserService extends IService<User> {
    User getUserAndRolesById(String userId);
    boolean saveRoleIdsByUserId(String userId, List<String> roleIds);
}
