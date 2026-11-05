package LAB2;

import LAB1.Enrollment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    // 1. Add a new enrollment
    @Override
    public void addEnrollment(Enrollment enrollment) {

        String sql = "INSERT INTO enrollments "
                + "(student_id, course_id, enrollment_date) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));

            ps.executeUpdate();

            System.out.println("Enrollment added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add enrollment.");
            e.printStackTrace();
        }
    }

    // 2. Get an enrollment by its ID
    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {

        String sql = "SELECT * FROM enrollments WHERE enrollment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Enrollment(
                            rs.getInt("enrollment_id"),
                            rs.getString("student_id"),
                            rs.getInt("course_id"),
                            rs.getDate("enrollment_date").toLocalDate()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve enrollment.");
            e.printStackTrace();
        }

        return null;
    }

    // 3. Get all enrollments
    @Override
    public List<Enrollment> getAllEnrollments() {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = "SELECT * FROM enrollments ORDER BY enrollment_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Enrollment enrollment = new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getString("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date").toLocalDate()
                );

                enrollments.add(enrollment);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve enrollments.");
            e.printStackTrace();
        }

        return enrollments;
    }

    // 4. Get enrollments belonging to a specific student
    @Override
    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = "SELECT * FROM enrollments WHERE student_id = ? "
                + "ORDER BY enrollment_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Enrollment enrollment = new Enrollment(
                            rs.getInt("enrollment_id"),
                            rs.getString("student_id"),
                            rs.getInt("course_id"),
                            rs.getDate("enrollment_date").toLocalDate()
                    );

                    enrollments.add(enrollment);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve student enrollments.");
            e.printStackTrace();
        }

        return enrollments;
    }

    // 5. Update an existing enrollment
    @Override
    public void updateEnrollment(Enrollment enrollment) {

        String sql = "UPDATE enrollments "
                + "SET student_id = ?, course_id = ?, enrollment_date = ? "
                + "WHERE enrollment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            ps.setInt(4, enrollment.getEnrollmentId());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Enrollment updated successfully!");
            } else {
                System.out.println("Enrollment not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update enrollment.");
            e.printStackTrace();
        }
    }

    // 6. Delete an enrollment
    @Override
    public void deleteEnrollment(int enrollmentId) {

        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, enrollmentId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Enrollment deleted successfully!");
            } else {
                System.out.println("Enrollment not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete enrollment.");
            e.printStackTrace();
        }
    }
}