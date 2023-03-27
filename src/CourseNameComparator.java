/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 6
 * Recitation: 01
 */

import java.util.Comparator;

/**
 * Write a fully-documented class named CourseNameComparator that allows us to compare two course names with the following priority: department and then number.
 */
public class CourseNameComparator implements Comparator <Course>{

    /**
     * This is a comparator method that allows us to compare two course names with the following priority: department and then number.
     * @param left          :
     *                      A Course object
     * @param right         :
     *                      The compared course object
     * @return              :
     *                      0 if left course is equal to right course
     *                      1 if left course should be after right course
     *                      -1 if left course should be before right course.
     */
    public int compare(Course left, Course right){
        if (left.getDepartment().compareTo(right.getDepartment()) == 0){
            return left.compareTo(right);
        }else
            return left.getDepartment().compareTo(right.getDepartment());
        }
}
