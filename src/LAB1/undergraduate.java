package LAB1;

public class undergraduate extends Student {
    private int marks;

    public undergraduate(String studentId, String fullname, String email, String gender, String address, String  phonenumber, int marks) {
        super(studentId, fullname, email, gender, address, phonenumber);

        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    @Override
    public double calculateGPA() {
        return marks / 20;
    }
}