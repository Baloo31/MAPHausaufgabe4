package Controller;

import Model.Course;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;

import java.util.LinkedList;
import java.util.List;


/**
 * Registration System
 */
public class RegistrationSystem {
    private CourseRepository courseRepo;
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;

    /**
     * Default constructor
     */
    public RegistrationSystem(){
        courseRepo = new CourseRepository();
        studentRepo = new StudentRepository();
        teacherRepo = new TeacherRepository();
    }


    /**
     * registration system constructor
     * @param courseRepo : course repository
     * @param studentRepo : student repository
     * @param teacherRepo : teacher repository
     */
    public RegistrationSystem(CourseRepository courseRepo, StudentRepository studentRepo, TeacherRepository teacherRepo){
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }


    /**
     * registers a student to a course with free places
     * @param course : the course
     * @param student : the student to register
     * @return true, if the registration was successful
     * else, false.
     */
    public boolean register(Course course, Student student){
        if ((course.getNumberOfStudents() < course.getMaxEnrollment()) &&
                (student.getTotalCredits() + course.getCredits() <= 30)){
            course.addStudent(student);
            student.addCourse(course);
            return true;
        }
        return false;
    }


    /**
     * retrieves all courses with free places
     * @return the courses (List<course>)
     */
    public List<Course> retrieveCoursesWithFreePlaces(){
        List<Course> coursesWithFreePlaces = new LinkedList<>();
        for (Course course : courseRepo.getAll()){
            if (course.getNumberOfStudents() < course.getMaxEnrollment()){
                coursesWithFreePlaces.add(course);
            }
        }
        return coursesWithFreePlaces;
    }


    /**
     * retrieves the students enrolled for a specific course
     * @param course : the course
     * @return the students enrolled for this course (List<Student>)
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course){
        return course.getStudentsEnrolled();
    }


    /**
     * @return all courses that exist, even the ones without free places
     */
    public List<Course> getAllCourses(){
        return courseRepo.getAll();
    }


    /**
     * a teacher deletes a course he is teaching
     * Pre: a teacher and a course he is teaching
     * Post: the course is deleted, and the credits of all enrolled students updated
     */
    public void deleteCourse(Teacher teacher, Course course){
        if (course.getTeacher().getTeacherId() == teacher.getTeacherId()) {
            teacher.deleteCourse(course);

            for (Student student : course.getStudentsEnrolled()) {
                student.deleteCourse(course);
            }

            course = null;
        }
    }


    /**
     * @return course repository
     */
    public CourseRepository getCourseRepo() {
        return courseRepo;
    }


    /**
     * @return student repository
     */
    public StudentRepository getStudentRepo() {
        return studentRepo;
    }


    /**
     * @return teacher repository
     */
    public TeacherRepository getTeacherRepo() {
        return teacherRepo;
    }
}
