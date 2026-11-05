package LAB3;

import LAB1.Student;
import LAB1.Course;
import LAB1.Enrollment;

import LAB2.StudentDAO;
import LAB2.StudentDAOImpl;
import LAB2.CourseDAO;
import LAB2.CourseDAOImpl;
import LAB2.EnrollmentDAO;
import LAB2.EnrollmentDAOImpl;
import LAB2.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

     static StudentDAO studentDAO = new StudentDAOImpl();
     static CourseDAO courseDAO = new CourseDAOImpl();
     static EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();

    public static void main(String[] args) {

        int choice = -1;

        do {
            System.out.println("UNIVERSITY MANAGEMENT SYSTEM ");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Add Course");
            System.out.println("6. View All Courses");
            System.out.println("7. Enroll Student in Course");
            System.out.println("8. View Student Enrollments");
            System.out.println("9. View Students with Their Courses");
            System.out.println("10. Update Enrollment");
            System.out.println("11. Delete Enrollment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    addCourse();
                    break;
                case 6:
                    viewAllCourses();
                    break;
                case 7:
                    enrollStudent();
                    break;
                case 8:
                    viewStudentEnrollments();
                    break;
                case 9:
                    viewStudentsWithCourses();
                    break;
                case 10:
                    updateEnrollment();
                    break;
                case 11:
                    deleteEnrollment();
                    break;
                case 0:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.nextLine();
        }

        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static void addStudent() {
        System.out.println("Add Student ");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Full Name: ");
        String fullname = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Student student = new Student(
                studentId, fullname, email,
                gender, address, phoneNumber
        );

        studentDAO.addStudent(student);
    }

    private static void viewAllStudents() {
        System.out.println(" All Students ");

        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.println("Update Student");

        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine();

        Student existing = studentDAO.getStudentById(studentId);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new Full Name: ");
        String fullname = scanner.nextLine();

        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter new Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter new Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Student updated = new Student(
                studentId, fullname, email,
                gender, address, phoneNumber
        );

        studentDAO.updateStudent(updated);
    }

    private static void deleteStudent() {
        System.out.println(" Delete Student ");

        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine();

        Student existing = studentDAO.getStudentById(studentId);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            studentDAO.deleteStudent(studentId);
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void addCourse() {
        System.out.println("Add Course ");

        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter Course Credits: ");
        int credits = readInt();

        if (credits <= 0) {
            System.out.println("Course credits must be greater than zero.");
            return;
        }

        Course course = new Course("", courseName, credits);
        courseDAO.addCourse(course);
    }

    private static void viewAllCourses() {
        System.out.println("All Courses ");

        List<Course> courses = courseDAO.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println("Course ID: " + course.getCourseid());
            System.out.println("Course Name: " + course.getCoursename());
            System.out.println("Credits: " + course.getCoursecredit());
            System.out.println();
        }
    }

    private static void enrollStudent() {
        System.out.println("Enroll Student in Course");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course ID: ");
        int courseId = readInt();

        Course course = courseDAO.getCourseById(courseId);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        Enrollment enrollment = new Enrollment(
                0, studentId, courseId, LocalDate.now()
        );

        enrollmentDAO.addEnrollment(enrollment);
    }

    private static void viewStudentEnrollments() {
        System.out.println("View Student Enrollments ");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        List<Enrollment> enrollments =
                enrollmentDAO.getEnrollmentsByStudentId(studentId);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println("Enrollment ID: "
                    + enrollment.getEnrollmentId());
            System.out.println("Student ID: "
                    + enrollment.getStudentId());
            System.out.println("Course ID: "
                    + enrollment.getCourseId());
            System.out.println("Enrollment Date: "
                    + enrollment.getEnrollmentDate());
            System.out.println();
        }
    }

    private static void viewStudentsWithCourses() {
        System.out.println("Students and Their Courses ");

        String sql = "SELECT s.student_id, s.fullname, s.email, "
                + "c.course_id, c.course_name, c.course_credit, "
                + "e.enrollment_date "
                + "FROM students s "
                + "JOIN enrollments e ON s.student_id = e.student_id "
                + "JOIN courses c ON e.course_id = c.course_id "
                + "ORDER BY s.student_id, c.course_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("Student ID: "
                        + rs.getString("student_id"));
                System.out.println("Student Name: "
                        + rs.getString("fullname"));
                System.out.println("Email: "
                        + rs.getString("email"));
                System.out.println("Course ID: "
                        + rs.getInt("course_id"));
                System.out.println("Course Name: "
                        + rs.getString("course_name"));
                System.out.println("Credits: "
                        + rs.getInt("course_credit"));
                System.out.println("Enrollment Date: "
                        + rs.getDate("enrollment_date"));
                System.out.println();
            }

            if (!found) {
                System.out.println("No enrolled students or courses found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve students and courses.");
            e.printStackTrace();
        }
    }

    private static void updateEnrollment() {
        System.out.println("Update Enrollment ");

        System.out.print("Enter Enrollment ID: ");
        int enrollmentId = readInt();

        Enrollment existing =
                enrollmentDAO.getEnrollmentById(enrollmentId);

        if (existing == null) {
            System.out.println("Enrollment not found.");
            return;
        }

        System.out.print("Enter new Student ID: ");
        String studentId = scanner.nextLine();

        if (studentDAO.getStudentById(studentId) == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new Course ID: ");
        int courseId = readInt();

        if (courseDAO.getCourseById(courseId) == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter enrollment date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateInput);

            Enrollment updated = new Enrollment(
                    enrollmentId, studentId, courseId, date
            );

            enrollmentDAO.updateEnrollment(updated);

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date. Use YYYY-MM-DD.");
        }
    }

    private static void deleteEnrollment() {
        System.out.println("Delete Enrollment ");

        System.out.print("Enter Enrollment ID to delete: ");
        int enrollmentId = readInt();

        Enrollment existing =
                enrollmentDAO.getEnrollmentById(enrollmentId);

        if (existing == null) {
            System.out.println("Enrollment not found.");
            return;
        }

        System.out.print("Are you sure you want to delete this enrollment? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            enrollmentDAO.deleteEnrollment(enrollmentId);
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}