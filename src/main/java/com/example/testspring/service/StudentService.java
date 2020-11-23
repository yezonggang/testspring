package com.example.testspring.service;

import com.example.testspring.mapper.StudentMapper;
import com.example.testspring.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService   {
    @Autowired
    StudentMapper studentMapper;
    public List<Students> SelectAll(){
        return studentMapper.SelectAll();
    }
    public Students SelectByID(int id){
        return studentMapper.SelectByID(id);
    }
}
