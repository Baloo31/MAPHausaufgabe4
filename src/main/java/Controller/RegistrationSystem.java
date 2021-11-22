package Controller;

import Exceptions.*;
import Model.Course;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RegistrationSystem {
    private CourseRepository courseRepo;
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;

    public RegistrationSystem(String coursesFile, String studentsFile, String teacherFile){
        studentRepo = new StudentRepository(studentsFile);
        teacherRepo = new TeacherRepository(teacherFile);
        courseRepo = new CourseRepository(coursesFile);
    }


    public void register(long courseId, long studentId) throws ElementDoesNotExistException, MaxCreditsSurpassedException, MaxEnrollmentSurpassedException, AlreadyExistsException {
        int studentIndex = -1;
        for (int idx = 0; idx < studentRepo.getAll().size(); idx++){
            if (studentRepo.getAll().get(idx).getStudentId() == studentId) {
                studentIndex = idx;
                break;
            }
        }

        int courseIndex = -1;
        for (int idx = 0; idx < courseRepo.getAll().size(); idx++){
            if (courseRepo.getAll().get(idx).getCourseId() == courseId) {
                courseIndex = idx;
                break;
            }
        }

        if ((courseIndex == -1) || (studentIndex == -1)){
            throw new ElementDoesNotExistException("The Course or the Student could not be found !");
        }

        Course course = courseRepo.getAll().get(courseIndex);
        Student student = studentRepo.getAll().get(studentIndex);

        if (course.getStudentsEnrolled().contains(studentId)){
            throw new AlreadyExistsException("Student was already registered to this course !");
        }

        if (calculateStudentCredits(student) + course.getCredits() > 30){
            throw new MaxCreditsSurpassedException("The credits will be over 30 by adding this course !");
        }

        if (course.getNumberOfStudents() >= course.getMaxEnrollment()) {
            throw new MaxEnrollmentSurpassedException("The course is full !");
        }

        course.addStudent(studentId);
        student.addCourse(courseId);

        courseRepo.update(course);
        studentRepo.update(student);
    }


    public List<Course> retrieveCoursesWithFreePlaces(){
        List<Course> freePlacesCourses = new LinkedList<>();
        for (Course course : courseRepo.getAll()){
            if (course.getMaxEnrollment() > course.getNumberOfStudents()){
                freePlacesCourses.add(course);
            }
        }
        return freePlacesCourses;
    }


    public List<Student> retrieveStudentsEnrolledForACourse(long courseId){
        List<Student> studentsEnrolledForTheCourse = new LinkedList<>();
        for (Student student : studentRepo.getAll()){
            if (student.getEnrolledCourses().contains(courseId)){
                studentsEnrolledForTheCourse.add(student);
            }
        }
        return studentsEnrolledForTheCourse;
    }


    public List<Course> getAllCourses() {
        return courseRepo.getAll();
    }


    public void deleteTeacherCourse(long courseId, long teacherId) throws ElementDoesNotExistException, NotTeachingTheCourseException {
        int teacherIndex = -1;
        for (int idx = 0; idx < teacherRepo.getAll().size(); idx++){
            if (teacherRepo.getAll().get(idx).getTeacherId() == teacherId) {
                teacherIndex = idx;
                break;
            }
        }

        int courseIndex = -1;
        for (int idx = 0; idx < courseRepo.getAll().size(); idx++){
            if (courseRepo.getAll().get(idx).getCourseId() == courseId) {
                courseIndex = idx;
                break;
            }
        }

        if ((courseIndex == -1) || (teacherIndex == -1)){
            throw new ElementDoesNotExistException("The Course or the Teacher could not be found !");
        }

        Course course = courseRepo.getAll().get(courseIndex);
        Teacher teacher = teacherRepo.getAll().get(teacherIndex);

        if (course.getTeacher() != teacherId) {
            throw new NotTeachingTheCourseException("Course is not teached by this teacher !");
        }


        for (long studentId : course.getStudentsEnrolled()){
            for (Student student : studentRepo.getAll()){
                if (student.getStudentId() == studentId) {
                    student.deleteCourse(courseId);
                    studentRepo.update(student);
                }
            }
        }

        teacher.deleteCourse(courseId);
        teacherRepo.update(teacher);

        courseRepo.delete(course);

    }


    public void addTeacher(){
    }


    public int calculateStudentCredits(Student student){
        int nrCredits = 0;
        for (long courseId : student.getEnrolledCourses()){
            for (Course course : courseRepo.getAll()) {
                if (course.getCourseId() == courseId) {
                    nrCredits += course.getCredits();
                }
            }
        }
        return nrCredits;
    }


    public void readAllData(){
        try {
            studentRepo.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            teacherRepo.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            courseRepo.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void writeAllData(){
        try {
            studentRepo.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            teacherRepo.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            courseRepo.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
