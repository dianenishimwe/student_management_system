package LAB1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instructor {
    private String instructorid;
    private String instructorname;
    private String instructoremail;
    private String instructorgender;

    // List of assigned courses
    private List<Course> assignedCourses = new ArrayList<>();

    // Map of course ID to course
    private Map<String, Course> courseAssignments = new HashMap<>();

    public Instructor(String instructorid, String instructorname,
                      String instructoremail, String instructorgender) {
        this.instructorid = instructorid;
        this.instructorname = instructorname;
        this.instructoremail = instructoremail;
        this.instructorgender = instructorgender;
    }

    public String getInstructorid() {
        return instructorid;
    }

    public void setInstructorid(String instructorid) {
        this.instructorid = instructorid;
    }

    public String getInstructorname() {
        return instructorname;
    }

    public void setInstructorname(String instructorname) {
        this.instructorname = instructorname;
    }

    public String getInstructoremail() {
        return instructoremail;
    }

    public void setInstructoremail(String instructoremail) {
        this.instructoremail = instructoremail;
    }

    public String getInstructorgender() {
        return instructorgender;
    }

    public void setInstructorgender(String instructorgender) {
        this.instructorgender = instructorgender;
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
        courseAssignments.put(course.getCourseid(), course);
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public Map<String, Course> getCourseAssignments() {
        return courseAssignments;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorid='" + instructorid + '\'' +
                ", instructorname='" + instructorname + '\'' +
                ", instructoremail='" + instructoremail + '\'' +
                ", instructorgender='" + instructorgender + '\'' +
                '}';
    }
}