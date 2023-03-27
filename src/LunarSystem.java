/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 6
 * Recitation: 01
 */

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A fully-documented class named LunarSystem that will allow the user to interact with a database of Students. Provide the user with a menu-based interface that allows them to add, remove, and edit Students, as well as their courses.
 */
public class LunarSystem {

    static HashMap<String, Student> database;


    static final String mainMenu = """
            Menu:
                L)Login
                X)Save state and quit
                Q)Quit without saving state.""";

    static final String registrarMenu = """
            Options:
                 R) Register a student
                 D) De-register a student
                 E) View course enrollment
                 L) Logout""";

    static final String studentMenu = """
            Options:
                A)Add a class
                D)Drop a class
                C)View your classes sorted by course name/department
                S)View your courses sorted by semester
                L) Logout""";

    /**
     * This method setup the while loop for the user as a registrar, allowing the user to get access to the registrar menu.
     */
    static void registrarLoop() {
        Scanner input = new Scanner(System.in);
        String rOption;
        do {
            System.out.println(registrarMenu);
            System.out.println("Please select an option: ");
            rOption = input.nextLine();

            switch (rOption.toUpperCase()) {
                case "R" -> {
                    System.out.println("Please enter a webId for the new student: ");
                    String id = input.nextLine();

                    while(id.isEmpty()){
                        System.out.println("Please enter a valid webId for the new student: ");
                        id = input.nextLine();
                    }

                    if (database.containsKey(id))
                        System.out.println(id + " is already registered.");
                    else{
                        Student student = new Student(id);
                        database.put(id, student);
                        System.out.println(id + " registered.");
                    }
                }
                case "D" -> {
                    System.out.println("Please enter a webId for the student: ");
                    String id = input.nextLine();

                    while(id.isEmpty()){
                        System.out.println("Please enter a valid webId for the student: ");
                        id = input.nextLine();
                    }

                    if(database.containsKey(id))
                        database.remove(id);
                    else
                        System.out.println(id + " is not registered.");
                }
                case "E" -> {
                    System.out.println("Please enter course (ex: ABC 123): ");
                    String courseName = input.nextLine();
                    while (courseName.isEmpty()){
                        System.out.println("Please enter a valid course (ex: ABC 123): ");
                        courseName = input.nextLine();
                    }
                    HashMap<Student,String> studentList = checkEnrollment(courseName);
                    System.out.println("Student" + "            " + "Semester");
                    System.out.println("---------------------------------");
                    for (Student s: studentList.keySet()){
                        System.out.println(s.getWebID() + "                 " + studentList.get(s));
                    }
                }
                case "L" -> System.out.println("Registrar logged out.");
            }
        } while (!rOption.equalsIgnoreCase("L"));
    }

    /**
     * This method creates a Hashmap of Student to a string form of the course name
     * @param courseName            :
     *                              The name of the curse.
     * @return                      :
     *                              a Hashmap of Student to a string form of the course name
     */
    static HashMap<Student, String> checkEnrollment(String courseName){
        HashMap<Student, String> map = new HashMap<>();
        for (Student s: database.values()){
            for (Course c: s.getCourses()){
                if (c.getName().equals(courseName)){
                    map.put(s,c.getSemester());
                    break;
                }
            }
        }

        return map;
    }

    /**
     * This method prints out the list of Course for the student
     * @param student           :
     *                          The student object.
     */
    static void print(Student student){
        System.out.println("Course      Dept        Semester");
        System.out.println("---------------------------------");
        for (Course c: student.getCourses()){
            System.out.println(c);
        }
    }

    /**
     * This method setup the while loop for the user as a student, allowing the user to get access to the student menu.
     * @param student           :
     *                          The student object
     */
    static void studentLoop(Student student){
        Scanner input = new Scanner(System.in);
        String sOption;
        do {
            System.out.println(studentMenu);
            System.out.println("Please select an option: ");
            sOption = input.nextLine();

            switch (sOption.toUpperCase()) {
                case "A" -> {
                    Course course = newCourse();
                    addCourse(student, course);
                }
                case "D" -> removeCourse(student);
                case "C" -> {
                    Collections.sort(student.getCourses(), new CourseNameComparator());
                    print(student);
                }
                case "S" -> {
                    Collections.sort(student.getCourses(), new SemesterComparator());
                    print(student);
                }
                case "L" -> System.out.println(student.getWebID() + " logged out.");
            }
        }while(!sOption.equalsIgnoreCase("L"));
    }

    /**
     * This method creates a new Course object
     * @return          :
     *                  The course object
     * @throws NumberFormatException        :
     *                                      throws NumberFormatException if nubmer cant be parsed into int
     */
    static Course newCourse() throws NumberFormatException{
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter course name (ex: ABC 123): ");
        String courseName = input.nextLine();

        while(courseName.isEmpty()){
            System.out.println("Please enter a valid course name (ex: ABC 123): ");
            courseName = input.nextLine();
        }
        try{
            String[] clist = courseName.split(" ");
            String dept = clist[0];
            int n = Integer.parseInt(clist[1]);

            System.out.println("Please select a semester (ex: F2021): ");
            String semesterName = input.nextLine();
            int yr = Integer.parseInt(semesterName.substring(1));
            return new Course(courseName, dept, n, semesterName, yr);

        }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid input.");
            return null;
        }

    }
    /**
     * This method add course to the Student
     * @param student           :
     *                          Student object
     * @param course            :
     *                          Course object
     */
    static void addCourse(Student student, Course course){
        if (course != null){
            student.getCourses().add(course);
            course.getStudents().add(student);
            System.out.println(course.getName() + " has been added in " + course.getSemester());
        }
    }

    /**
     * This method remove course from the Student object
     * @param student           :
     *                          The student object
     */

    static void removeCourse(Student student){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter course name (ex: ABC 123): ");
        String courseName = input.nextLine();
        Course removingCourse = null;

        while (courseName.isEmpty()){
            System.out.println("Please enter a valid course name (ex: ABC 123): ");
            courseName = input.nextLine();
        }

        String id = student.getWebID();
        for (Course c: student.getCourses()){
            if (c.getName().equals(courseName)){
                removingCourse = c;
            }
        }

        student.getCourses().remove(removingCourse);

        if (removingCourse != null){
            removingCourse.getStudents().removeIf(s -> s.getWebID().equals(id));
            System.out.println(courseName + "has been dropped for " + removingCourse.getSemester());
        }

    }


    /**
     * This is hte main method that executes the program.
     * @param args
     */
    public static void main(String[] args) {
        String fileName = "Lunar.ser";
        File file = new File(fileName);
        HashMap<String, Student> dataBase;
        boolean exist = true;
        boolean quit = false;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            database = (HashMap<String, Student>) in.readObject();
            in.close();

        } catch (FileNotFoundException e) {
            database = new HashMap<>();
            exist = false;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        String option;
        do {
            System.out.println(mainMenu);
            Scanner input = new Scanner(System.in);
            System.out.println("Please select an option: ");
            option = input.nextLine();

            switch (option.toUpperCase()) {
                case "L" -> {
                    System.out.println("Please enter webId: ");
                    String id = input.nextLine();
                    if (id.equals("REGISTRAR")) {
                        Student newStudent = new Student(id);
                        database.put(id, newStudent);
                        registrarLoop();
                    } else {
                        if (database.containsKey(id)) {
                            studentLoop(database.get(id));
                        } else {
                            System.out.println("You are not registered.");
                        }
                    }
                }
                case "X" -> {
                    try {
                        dataBase = database;
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(dataBase);
                        out.close();
                        System.out.println("System state saved, system shut down for maintenance.\n");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    quit = true;
                }
                case "Q" -> {
                    if (exist) {
                        if (file.delete())
                            System.out.println("Good bye, please pick the right SUNY next time!");
                        else
                            System.out.println("File not deleted");
                    } else
                        System.out.println("Good bye, please pick the right SUNY next time!");
                    quit = true;
                }
            }
        } while (!quit);


    }
}