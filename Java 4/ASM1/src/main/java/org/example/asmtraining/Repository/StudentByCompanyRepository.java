package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class StudentByCompanyRepository {
    public List<Student> getStudentsByCompany(String company) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Student> query = session.createQuery("SELECT s FROM Student s JOIN s.internships i WHERE i.companyName = :company", Student.class);
            query.setParameter("company", company);
            return query.getResultList();
        }
    }
}