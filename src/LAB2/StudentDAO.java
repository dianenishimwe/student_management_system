package LAB2;

import LAB1.Student;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);

    Student getStudentById(String studentId);

    List<Student> getAllStudents();

    void updateStudent(Student student);

    void deleteStudent(String studentId);
}