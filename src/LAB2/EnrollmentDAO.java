package LAB2;

import LAB1.Enrollment;
import java.util.List;

public interface EnrollmentDAO {

    void addEnrollment(Enrollment enrollment);

    Enrollment getEnrollmentById(int enrollmentId);

    List<Enrollment> getAllEnrollments();

    List<Enrollment> getEnrollmentsByStudentId(String studentId);

    void updateEnrollment(Enrollment enrollment);

    void deleteEnrollment(int enrollmentId);
}