package org.example.testresttemplate.Service;

import org.example.testresttemplate.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    RestTemplate restTemplate;

    public Map<String, Student> getAllStudent() {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student.json";
        var student = restTemplate.getForObject(url, StudentMap.class);
        return student != null ? student : new HashMap<>();
    }

    public Student getStudentByKey(String key) {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student/" + key + ".json";
        return restTemplate.getForObject(url, Student.class);
    }

    public String createStudent(Student student) {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student.json";
        var response = restTemplate.postForObject(url, student, Map.class);
        if(response != null && response.containsKey("name")){
            return response.get("name").toString();
        }
        return null;
    }

    public void updateStudent(Student student, String key) {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student/" + key + ".json";
        restTemplate.put(url, student);
    }

    public void deleteStudent(Student student, String key) {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student/" + key + ".json";
        restTemplate.delete(url, student);
    }
}
