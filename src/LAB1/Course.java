package LAB1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course {
    private String courseid;
    private String coursename;
    private int coursecredit;

    // List of enrolled students
    private List<Student> enrolledStudents = new ArrayList<>();

    // Set of students without duplicate object entries
    private Set<Student> studentSet = new HashSet<>();

    public Course(String courseid, String coursename, int coursecredit) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.coursecredit = coursecredit;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getCoursecredit() {
        return coursecredit;
    }

    public void setCoursecredit(int coursecredit) {
        this.coursecredit = coursecredit;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
        studentSet.add(student);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseid='" + courseid + '\'' +
                ", coursename='" + coursename + '\'' +
                ", coursecredit=" + coursecredit +
                '}';
    }
}