package com.example.testspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.testspring.model.userModel.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> getListByUserId(String userId);
}
