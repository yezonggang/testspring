package com.example.testspring.mapper.userMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testspring.model.userModel.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getListByUserId(String userId);
    Role findById(String roleId);
}
