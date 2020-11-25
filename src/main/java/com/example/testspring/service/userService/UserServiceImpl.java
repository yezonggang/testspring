package com.example.testspring.service.userService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testspring.mapper.userMapper.RoleMapper;
import com.example.testspring.mapper.userMapper.UserMapper;
import com.example.testspring.model.userModel.Role;
import com.example.testspring.model.userModel.User;
import com.example.testspring.model.userModel.UserRole;
import com.example.testspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper UserMapper;

    @Autowired
    private RoleMapper RoleMapper;

    @Override
    public User getUserAndRolesById(String userId) {
        User ltSysUser = UserMapper.findById(userId);
        List<Role> roles = RoleMapper.getListByUserId(userId);
        ltSysUser.setRoles(roles);
        return ltSysUser;
    }


    @Override
    public boolean saveRoleIdsByUserId(String userId, List<String> roleIds) {
        UserMapper.deleteRoleByUserId(userId);
        List<UserRole> list = roleIds.stream().map(roleId -> {
            return new UserRole(roleId, userId);
        }).collect(Collectors.toList());
        return UserMapper.insertRolesBatch(list);
    }
}
