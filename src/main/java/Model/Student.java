package Model;

import java.util.LinkedList;
import java.util.List;

public class Student extends Person {
    private long studentId;
    private List<Course> enrolledCourses;

    private static long studentIdCounter = 0;


    /**
     * constructor for a student
     * @param firstname : first name
     * @param lastName : last name
     */
    public Student(String firstname, String lastName) {
        super(firstname, lastName);
        this.enrolledCourses = new LinkedList<>();
        this.studentId = studentIdCounter;
        studentIdCounter++;
    }


    /**
     * string representation for a student
     * @return String
     */
    @Override
    public String toString() {
        return "Student{" +
                "firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", studentId=" + studentId +
                ", totalCredits=" + getTotalCredits() +
                ", nrEnrolledCourses=" + getNumberOfCourses() +
                '}';
    }


    /**
     * adds a course to the list of courses of a student
     * @param course : course to add
     */
    public void addCourse(Course course){
        this.enrolledCourses.add(course);
    }


    /**
     * deletes a course from the list of courses of a student
     * @param course : course to delete
     */
    public void deleteCourse(Course course){
        this.enrolledCourses.remove(course);
    }


    /**
     * getter for the id of a student
     * @return student id (long)
     */
    public long getStudentId() {
        return studentId;
    }


    /**
     * returns the total credits of a student
     * @return total credits (int)
     */
    public int getTotalCredits() {
        int totalCredits = 0;
        for (Course course : enrolledCourses){
            totalCredits += course.getCredits();
        }
        return totalCredits;
    }


    /**
     * getter for the courses a student is enrolled to
     * @return list of courses (List<Course>)
     */
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }


    /**
     * getter for the number of courses a student is enrolled to
     * @return number of enrolled courses
     */
    public int getNumberOfCourses(){
        return enrolledCourses.size();
    }

}
