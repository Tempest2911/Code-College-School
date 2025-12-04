package org.example.j6backendjava.service;

import org.example.j6backendjava.enity.Student;
import org.example.j6backendjava.repository.IFStudent;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService implements IFStudent {
    // giả lập dữ liệu sinh viên


    Map<String, Student> db = new HashMap<>(Map.of(
            "SV01", new Student("SV01", "Nguyen Van A", true, 8.5),
            "SV02", new Student("SV02", "Tran Thi B", false, 7.0),
            "SV03", new Student("SV03", "Le Van C", true, 9.0)
    ));

    @Override
    public Collection<Student> findAll() {
        return db.values();
    }

    @Override
    public Student findById(String id) {
        return db.get(id);
    }

    @Override
    public Student create(Student student) {
            return db.put(student.getId(), student);
    }

    @Override
    public Student update(Student student) {
        return db.put(student.getId(), student);
    }

    @Override
    public void delete(String id) {
        db.remove(id);
    }


}
