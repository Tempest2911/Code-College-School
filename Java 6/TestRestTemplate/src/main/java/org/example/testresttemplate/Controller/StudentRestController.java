package org.example.testresttemplate.Controller;

import org.example.testresttemplate.Entity.Student;
import org.example.testresttemplate.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<Map<String, Student>> getAllStudents(){
        Map<String, Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{key}")
    public ResponseEntity<Student> getStudent(@PathVariable String key){
        Student student = studentService.getStudentByKey(key);
        if(student != null){
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        String key = studentService.createStudent(student);
        if(key != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Created student successfully: " + key);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create student");
        }
    }

    @PutMapping("/students/{key}")
    public ResponseEntity<String> updateStudent(@PathVariable String key, @RequestBody Student student) {
        try{
            studentService.updateStudent(student, key);
            return ResponseEntity.ok("Student updated successfully");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update student");
        }
    }
    @DeleteMapping("/students/{key}")
    public ResponseEntity<String> deleteStudent(@PathVariable String key) {
        try{
            studentService.deleteStudent(key);
            return ResponseEntity.ok("Student deleted successfully");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete student");
        }
    }
}
