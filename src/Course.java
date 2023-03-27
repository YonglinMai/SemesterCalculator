/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 6
 * Recitation: 01
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A full-documented class named Course. Courses will be associated with the Students that are taking them. Each course will have a designated department (Ex. “CSE”), a three-digit course number (Ex. 214), and a semester associated with it (Ex. “F2021”).
 */
public class Course implements Serializable, Comparable<Course> {
    private String name;
    private String department;
    private int number;
    private String semester;
    private int year;
    private ArrayList<Student> students;

    /**
     * This is the constructor of the Course object.
     * @param nm            :
     *                      The name of the Course
     * @param dept          :
     *                      The department of the Course
     * @param num           :
     *                      The number of the Course
     * @param sem           :
     *                      The semester of enrollment
     * @param yr            :
     *                      THe year of enrollment
     */
    public Course(String nm, String dept, int num, String sem, int yr) {
        name = nm;
        department = dept;
        number = num;
        semester = sem;
        year = yr;
        students = new ArrayList<>();
    }

    /**
     * The getter method that gets the department of the Course.
     * @return          :
     *                  The department of the Course.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * The getter method that gets the number of the Course.
     * @return              :
     *                      The number of the course.
     */
    public int getNumber() {
        return number;
    }

    /**
     *  The getter method that gets the semester of the Course.
     * @return              :
     *                      The semester of the course.
     */
    public String getSemester() {
        return semester;
    }

    /**
     *  This is a comparing method that compares two Course number
     * @param o             :
     *                      Anoather Course object
     * @return              :
     *                      0 if two course numbers are equal
     *                      1 if this Course Number is bigger than the other Course number
     *                      -1 if this Course Number is smaller than the other Course number
     */
    @Override
    public int compareTo(Course o) {
        if (this.number == o.getNumber())
            return 0;
        else if (this.number > o.getNumber())
            return 1;
        else
            return -1;
    }

    /**
     * The getter method that gets the Student list of the Course.
     * @return          :
     *                  The Student list of the Course
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * The getter method that gets the year of the Course.
     * @return          :
     *                  The year of the Course enrollment
     */
    public int getYear() {
        return year;
    }

    /**
     * The getter method that gets the name of the Course.
     * @return          :
     *                  The name of teh Course
     */
    public String getName() {
        return name;
    }

    /**
     * This generates a string form of the Course object.
     * @return          :
     *                  The string form of the object.
     */
    public String toString(){
        return (department + "          " + number + "          " + semester) + "\n";
    }
}

