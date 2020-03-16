package com.example.testspring.mapper;


import com.example.testspring.model.Students;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper {
    List<Students> SelectAll();
    Students SelectByID(int id);

}

