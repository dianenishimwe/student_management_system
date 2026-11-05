package LAB2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            String studentsTable = """
                    CREATE TABLE students (
                        student_id VARCHAR(50) PRIMARY KEY,
                        fullname VARCHAR(100) NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        gender VARCHAR(20),
                        address VARCHAR(150),
                        phonenumber VARCHAR(20)
                    )
                    """;

            String coursesTable = """
                    CREATE TABLE  courses (
                        course_id SERIAL PRIMARY KEY,
                        course_name VARCHAR(100) NOT NULL,
                        course_credit INT NOT NULL
                    )
                    """;

            String enrollmentsTable = """
                    CREATE TABLE enrollments (
                        enrollment_id SERIAL PRIMARY KEY,
                        student_id VARCHAR(50) REFERENCES students(student_id),
                        course_id INT REFERENCES courses(course_id),
                        enrollment_date DATE DEFAULT CURRENT_DATE,
                        UNIQUE (student_id, course_id)
                    )
                    """;

            st.executeUpdate(studentsTable);
            st.executeUpdate(coursesTable);
            st.executeUpdate(enrollmentsTable);

            System.out.println("Tables created successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to create tables.");
            e.printStackTrace();
        }
    }
}