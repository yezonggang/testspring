package com.example.testspring.service.userService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testspring.mapper.userMapper.RoleMapper;
import com.example.testspring.model.userModel.Role;
import com.example.testspring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getListByUserId(String userId) {
        return roleMapper.getListByUserId(userId);
    }

}
