
package LAB2;

import LAB1.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    // CREATE: Add a new course
    @Override
    public void addCourse(Course course) {

        String sql = "INSERT INTO courses (course_name, course_credit) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // PostgreSQL generates course_id automatically
            ps.setString(1, course.getCoursename());
            ps.setInt(2, course.getCoursecredit());

            ps.executeUpdate();

            System.out.println("Course added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add course.");
            e.printStackTrace();
        }
    }

    // READ: Find one course by ID
    @Override
    public Course getCourseById(int courseId) {

        String sql = "SELECT * FROM courses WHERE course_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Course(
                            String.valueOf(rs.getInt("course_id")),
                            rs.getString("course_name"),
                            rs.getInt("course_credit")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve course.");
            e.printStackTrace();
        }

        return null;
    }

    // READ: Retrieve all courses
    @Override
    public List<Course> getAllCourses() {

        List<Course> courses = new ArrayList<>();

        String sql = "SELECT * FROM courses ORDER BY course_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Course course = new Course(
                        String.valueOf(rs.getInt("course_id")),
                        rs.getString("course_name"),
                        rs.getInt("course_credit")
                );

                courses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve courses.");
            e.printStackTrace();
        }

        return courses;
    }

    // UPDATE: Modify an existing course
    @Override
    public void updateCourse(Course course) {

        String sql = "UPDATE courses SET course_name = ?, course_credit = ? WHERE course_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getCoursename());
            ps.setInt(2, course.getCoursecredit());
            ps.setInt(3, Integer.parseInt(course.getCourseid()));

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Course updated successfully!");
            } else {
                System.out.println("Course not found.");
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Failed to update course.");
            e.printStackTrace();
        }
    }

    // DELETE: Remove a course by ID
    @Override
    public void deleteCourse(int courseId) {

        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("Course not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete course.");
            e.printStackTrace();
        }
    }
}
