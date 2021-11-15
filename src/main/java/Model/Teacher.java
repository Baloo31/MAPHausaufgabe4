package Model;

import java.util.LinkedList;
import java.util.List;

public class Teacher extends Person{
    private long teacherId;
    private List<Course> courses;

    private static long teacherIdCounter = 0;


    /**
     * constructor for a teacher
     * @param firstName : first name
     * @param lastName : last name
     */
    public Teacher(String firstName, String lastName){
        super(firstName, lastName);
        this.teacherId = teacherIdCounter;
        teacherIdCounter++;
        this.courses = new LinkedList<>();
    }


    /**
     * string representation of a teacher
     * @return String
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", teacherId=" + teacherId +
                ", nrOfCourses=" + getNrOfCourses() +
                '}';
    }


    /**
     * adds a course to the list of courses that the teacher is teaching
     * @param course : a course that the teacher will teach
     */
    public void addCourse(Course course){
        courses.add(course);
    }


    /**
     * deletes a course from the list of courses of the teacher
     * @param course : a course that the teacher is teaching
     */
    public void deleteCourse(Course course) {
        courses.remove(course);
    }


    /**
     * getter for the number of courses a teacher is teaching
     * @return number of courses
     */
    public int getNrOfCourses() {
        return courses.size();
    }


    /**
     * getter for the id of a teacher
     * @return teacher id (long)
     */
    public long getTeacherId() {
        return teacherId;
    }


    /**
     * getter for the courses of a teacher
     * @return list of courses (List<Courses>)
     */
    public List<Course> getCourses() {
        return courses;
    }

}
