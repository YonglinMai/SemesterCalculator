/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 6
 * Recitation: 01
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A fully-documented class named Student that will serve as the stored element of the Lunar System database.
 */
public class Student implements Serializable {
    private String webID;
    private ArrayList<Course> courses;

    /**
     * The constructor for the class that initializes the variables.
     * @param id        :
     *                  The webId of the Student
     */
    public Student(String id) {
        webID = id;
        courses = new ArrayList<>();
    }

    /**
     * This is a getter method that gets the webId of the student.
     * @return          :
     *                  The webId of the Student
     */
    public String getWebID() {
        return webID;
    }

    /**
     * This is a getter method that gets the list of Course of the Student.
     * @return          :
     *                  The list of Course
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

}
