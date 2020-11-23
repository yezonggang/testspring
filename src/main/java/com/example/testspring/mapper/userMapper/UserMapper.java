package com.example.testspring.mapper.userMapper;
import com.example.testspring.model.userModel.Role;
import com.example.testspring.model.userModel.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testspring.model.userModel.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User>{
    User getByIdLazy(String userId);
    User findById(String userId);
    boolean deleteRoleByUserId(String userId);
    boolean insertRolesBatch(@Param("list") List<UserRole> list);}
