package com.example.testspring.controller;
import com.example.testspring.model.Students;
import com.example.testspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.*;

@RestController
@RequestMapping("/testspring")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/getStudent/{id}")
    public Students getStudent(@PathVariable int id ){
        return studentService.SelectByID(id);
    }

    @RequestMapping(value = {"/getStudentsAll"})
    @ResponseBody
    public List<Map<String,Object>> getStudents(){
        List<Students> listStudent=(List<Students>) studentService.SelectAll();
        List<Map<String, Object>> studentLists = new ArrayList<>();
        Iterator iterator=listStudent.iterator();
        while(iterator.hasNext()){
            Map<String, Object> map = new HashMap<>();
            Students st= (Students) iterator.next();
            map.put("id",st.getId());
            map.put("name",st.getName());
            map.put("sex",st.getSex());
            map.put("age",st.getAge());
            studentLists.add(map);
        }
        return studentLists;
    }
}
