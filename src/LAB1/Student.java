package LAB1;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String StudentId;
    private String Fullname;
    private String Email;
    private String Gender;
    private String Address;
    private String   Phonenumber;
    //constructor

    // List of enrolled students
    private List<Student> enrolledStudents = new ArrayList<>();

    public Student(String studentId, String fullname, String email, String gender, String address, String  phonenumber) {
        StudentId = studentId;
        Fullname = fullname;
        Email = email;
        Gender = gender;
        Address = address;
        Phonenumber = phonenumber;
    }
    //getter and setter

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String  getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String  phonenumber) {
        Phonenumber = phonenumber;
    }
    //too string

    @Override
    public String toString() {
        return "Student{" +
                "StudentId='" + StudentId + '\'' +
                ", Fullname='" + Fullname + '\'' +
                ", Email='" + Email + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Address='" + Address + '\'' +
                ", Phonenumber=" + Phonenumber +
                '}';
    }

    public double calculateGPA() {
        return 0;
    }
}
