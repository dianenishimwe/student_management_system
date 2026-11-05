package LAB2;

import LAB1.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(student_id, fullname, email, gender, address, phonenumber) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getStudentId());
            ps.setString(2, student.getFullname());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getPhonenumber());

            ps.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add student.");
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(String studentId) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Student(
                            rs.getString("student_id"),
                            rs.getString("fullname"),
                            rs.getString("email"),
                            rs.getString("gender"),
                            rs.getString("address"),
                            rs.getString("phonenumber")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve student.");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Student student = new Student(
                        rs.getString("student_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phonenumber")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve students.");
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET " +
                "fullname = ?, email = ?, gender = ?, " +
                "address = ?, phonenumber = ? " +
                "WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getFullname());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getPhonenumber());
            ps.setString(6, student.getStudentId());

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update student.");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String studentId) {

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete student.");
            e.printStackTrace();
        }
    }
}