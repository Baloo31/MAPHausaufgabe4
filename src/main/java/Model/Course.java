package Model;

import java.util.LinkedList;
import java.util.List;

public class Course {
    private String name;
    private Teacher teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;
    private long courseId;
    private static long courseIdCounter = 0;


    /**
     * constructor for a course
     * @param name : name of a course
     * @param teacher : name of the teacher teaching the course
     * @param maxEnrollment : maximum number of students that can join the course
     * @param credits : course credits
     */
    public Course(String name, Teacher teacher, int maxEnrollment, int credits){
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = new LinkedList<>();
        this.credits = credits;
        this.courseId = courseIdCounter;
        courseIdCounter++;
        teacher.addCourse(this);
    }


    /**
     * string representation for a course
     * @return : String
     */
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher +
                ", maxEnrollment=" + maxEnrollment +
                ", nrEnrolledStudents=" + getNumberOfStudents() +
                ", credits=" + credits +
                ", courseId=" + courseId +
                '}';
    }


    /**
     * adds a student to the list of enrolled students
     * @param student : student to add
     */
    public void addStudent(Student student) {
        studentsEnrolled.add(student);
    }


    /**
     * deletes a student from the list of enrolled students
     * @param student : student to delete
     */
    public void deleteStudent(Student student) {
        studentsEnrolled.remove(student);
    }


    /**
     * getter for the number of enrolled students
     * @return number of enrolled students (int)
     */
    public int getNumberOfStudents(){
        return studentsEnrolled.size();
    }


    /**
     * getter for the name of a course
     * @return name of the course (String)
     */
    public String getName() {
        return name;
    }


    /**
     * setter for the name of a course
     * @param name : name of a course
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * getter for the teacher of a course
     * @return Teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }


    /**
     * setter for the teacher of a course
     * @param teacher : teacher that teaches the course
     */
    public void setTeacher(Teacher teacher) {
        this.teacher.deleteCourse(this);
        this.teacher = teacher;
        this.teacher.addCourse(this);
    }


    /**
     * getter for the maximum number of students enrolled to a course
     * @return max number (int)
     */
    public int getMaxEnrollment() {
        return maxEnrollment;
    }


    /**
     * setter for the maximum number of students enrolled to a course
     * @param maxEnrollment : max number of students
     */
    public void setMaxEnrollment(int maxEnrollment) {
        if (this.getNumberOfStudents() > maxEnrollment) {
            this.maxEnrollment = maxEnrollment;
        }
    }


    /**
     * getter for the number of credits of the course
     * @return credits (int)
     */
    public int getCredits() {
        return credits;
    }


    /**
     * setter for the number of credits of a course
     * @param credits : number of credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }


    /**
     * getter for the students enrolled to a course
     * @return the list of students (List<Student>)
     */
    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }


    /**
     * getter for the course id
     * @return course id (long)
     */
    public long getCourseId() {
        return courseId;
    }
}
