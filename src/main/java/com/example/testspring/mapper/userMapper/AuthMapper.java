package com.example.testspring.mapper.userMapper;
import com.example.testspring.model.userModel.Auth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper extends  BaseMapper<Auth>{
   // Auth getByLoginName(String loginName);
}
