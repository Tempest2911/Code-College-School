package Repository;

import Model.StudentAttendance;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Repository {
    public List<StudentAttendance> getStudentAttendance() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from StudentAttendance", StudentAttendance.class).getResultList();
        }
    }

    public StudentAttendance addStudent(StudentAttendance st) {
        try (Session s = HibernateUtil.getSession()) {
            s.beginTransaction();
            s.save(st);
            s.getTransaction().commit();
            return st;
        }
    }

    public void deleteStudent(Integer id) {
        try (Session s = HibernateUtil.getSession()) {
            s.beginTransaction();
            StudentAttendance st = s.get(StudentAttendance.class, id);
            if (st != null) s.delete(st);
            s.getTransaction().commit();
        }
    }

    public void updateStudent(StudentAttendance st) {
        try (Session s = HibernateUtil.getSession()) {
            s.beginTransaction();
            s.update(st); // Giả sử st đã có ID hợp lệ
            s.getTransaction().commit();
        }
    }


    public StudentAttendance getStudentById(Integer id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(StudentAttendance.class, id);
        }
    }

    public List<StudentAttendance> searchProducts(String keyword) {
        try (Session session = HibernateUtil.getSession()) {
            Query<StudentAttendance> query = session.createQuery("FROM StudentAttendance s WHERE s.studentName LIKE :kw", StudentAttendance.class);
            query.setParameter("kw", "%" + keyword + "%");
            return query.getResultList();
        }
    }
}