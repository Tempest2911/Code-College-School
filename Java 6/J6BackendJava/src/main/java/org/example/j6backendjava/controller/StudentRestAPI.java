package org.example.j6backendjava.controller;

import org.example.j6backendjava.enity.Student;
import org.example.j6backendjava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
public class StudentRestAPI {
    @Autowired
    private StudentService studentService;
    @GetMapping("/student")
    public Collection<Student> getAllStudent(){
        return studentService.findAll();
    }
    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable String id){
        return studentService.findById(id);
    }
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student){
        return studentService.create(student);
    }
    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student){
        return studentService.update(student);
    }
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable String id){
        studentService.delete(id);
    }
}
