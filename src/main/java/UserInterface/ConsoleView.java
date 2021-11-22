package UserInterface;

import Controller.RegistrationSystem;
import Exceptions.*;
import Model.Course;

import java.util.Scanner;

public class ConsoleView {
    private RegistrationSystem registrationSystem;
    private Scanner inputScanner;

    public ConsoleView() {
        registrationSystem = new RegistrationSystem("course.json", "student.json", "teacher.json");
        inputScanner = new Scanner(System.in);
    }

    public void start(){
        registrationSystem.readAllData();
        int option = -1;
        while (option != 0) {
            this.showMenu();
            System.out.print("Please choose an Option : ");
            option = inputScanner.nextInt();
            if (option > 7 || option < 0){
                System.out.println("This Option does not exist, please try again !");
            }

            if (option == 1) {
                this.addStudent();
            } else if (option == 2) {
                this.addTeacher();
            } else if (option == 3) {
                this.addCourse();
            } else if (option == 4) {
                this.register();
            } else if (option == 5) {
                this.retrieveFree();
            } else if (option == 6) {
                this.retrieveAll();
            } else if (option == 7) {
                this.teacherDeleteCourse();
            }
        }
        registrationSystem.writeAllData();
        System.out.println("The Application was closed !\s");
    }

    public void addStudent() {
        inputScanner.nextLine();

        System.out.print("Enter first name : ");
        String firstName = inputScanner.nextLine();

        System.out.print("Enter last name : ");
        String lastName = inputScanner.nextLine();

        System.out.print("Enter id : ");
        long id = inputScanner.nextLong();

        try {
            registrationSystem.addStudent(firstName, lastName, id);
            System.out.println("Student added successfully !");
        } catch (AlreadyExistsException e) {
            System.out.println("Student already exists !");
        }
    }

    public void addTeacher(){
        inputScanner.nextLine();

        System.out.print("Enter first name : ");
        String firstName = inputScanner.nextLine();

        System.out.print("Enter last name : ");
        String lastName = inputScanner.nextLine();

        System.out.print("Enter id : ");
        long id = inputScanner.nextLong();

        try {
            registrationSystem.addTeacher(firstName, lastName, id);
            System.out.println("Teacher added successfully !");
        } catch (AlreadyExistsException e) {
            System.out.println("Teacher already exists !");
        }
    }

    public void addCourse(){
        inputScanner.nextLine();

        System.out.print("Enter course name : ");
        String name = inputScanner.nextLine();

        System.out.print("Enter a teacher id (that actually exists) : ");
        long teacherId = inputScanner.nextLong();

        System.out.print("Enter max enrollment : ");
        int maxEnrollment = inputScanner.nextInt();

        System.out.print("Enter number of credits : ");
        int credits = inputScanner.nextInt();

        System.out.print("Enter course id : ");
        long courseId = inputScanner.nextLong();


        try {
            registrationSystem.addCourse(name, teacherId, maxEnrollment, credits, courseId);
            System.out.println("Course added successfully !");
        } catch (AlreadyExistsException e) {
            System.out.println("Course already exists !");
        } catch (ElementDoesNotExistException e) {
            System.out.println("That teacher does not exist !");
        }
    }

    public void register(){
        inputScanner.nextLine();

        System.out.print("Enter course id : ");
        long courseId = inputScanner.nextLong();

        System.out.print("Enter student id : ");
        long studentId = inputScanner.nextLong();

        try {
            registrationSystem.register(courseId, studentId);
            System.out.println("Successfully registered to the course !");
        } catch (ElementDoesNotExistException e) {
            System.out.println("The Course or the Student could not be found !");
        } catch (MaxCreditsSurpassedException e) {
            System.out.println("Can't perform the operation ! The student will have more than 30 credits !");
        } catch (MaxEnrollmentSurpassedException e) {
            System.out.println("This course has no available places !");
        } catch (AlreadyExistsException e) {
            System.out.println("Student is already registered to this course !");
        }
    }

    public void retrieveFree(){
        for (Course course : registrationSystem.retrieveCoursesWithFreePlaces()){
            System.out.println(course);
        }
    }

    public void retrieveAll(){
        for (Course course : registrationSystem.getAllCourses()){
            System.out.println(course);
        }
    }

    public void teacherDeleteCourse(){
        inputScanner.nextLine();

        System.out.print("Enter teacher id : ");
        long teacherId = inputScanner.nextLong();

        System.out.print("Enter course id : ");
        long courseId = inputScanner.nextLong();

        try {
            registrationSystem.deleteTeacherCourse(courseId, teacherId);
            System.out.println("Course successfully deleted !");
        } catch (ElementDoesNotExistException e) {
            System.out.println("Course or teacher does not exist !");
        } catch (NotTeachingTheCourseException e) {
            System.out.println("The specified teacher is not teaching this course !");
        }
    }

    public void showMenu(){
        System.out.print("""
                0. Exit\s
                1. Add student\s
                2. Add teacher\s
                3. Add course\s
                4. Register a student to a course\s
                5. Retrieve courses with free places\s
                6. Retrieve all available courses\s
                7. Delete a Teachers course
                """);
    }
}
