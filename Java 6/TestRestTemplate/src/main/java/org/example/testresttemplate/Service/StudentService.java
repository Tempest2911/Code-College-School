package org.example.testresttemplate.Service;

import lombok.RequiredArgsConstructor;
import org.example.testresttemplate.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student";

    public Map<String, Student> getAllStudent() {
        var url = BASE_URL + ".json";
        var student = restTemplate.getForObject(url, Map.class);
        return student != null ? student : new HashMap<>();
    }

    public Student getStudentByKey(String key) {
        try {
            var url = BASE_URL + "/" + key + ".json";
            return restTemplate.getForObject(url, Student.class);
        } catch (Exception e) {
            System.err.println("Lỗi khi gọi API: " + e.getMessage());
            return null;
        }
    }

    public String createStudent(Student student) {
        var url = BASE_URL + ".json";
        var response = restTemplate.postForObject(url, student, Map.class);
        if(response != null && response.containsKey("name")){
            return response.get("name").toString();
        }
        return null;
    }

    public void updateStudent(Student student, String key) {
        var detailUrl = BASE_URL + "/" + key + ".json";
        restTemplate.put(detailUrl, student);
    }

    public void deleteStudent(String key) { // Bỏ Student student
        var url = BASE_URL + "/" + key + ".json";
        restTemplate.delete(url);
    }
}
