/**
 * Name: Yonglin Mai
 * Stony ID: 113299531
 * Homework 6
 * Recitation: 01
 */

import java.util.Comparator;

/**
 * Write a fully-documented class named Semester that allows us to compare two courses based on the semester they were offered.
 */
public class SemesterComparator implements Comparator <Course> {

    /**
     * The method compares the two Course by semester and year
     * @param left          :
     *                      A Course object
     * @param right         :
     *                      The other course object
     * @return              :
     *                      0 if left course is equal to right course
     *                      1 if left course should be after right course
     *                      -1 if left course should be before right course.
     */
    public int compare(Course left, Course right){
        if (left.getSemester().compareTo(right.getSemester()) == 0)
            return Integer.compare(left.getYear(), right.getYear());
        else
            return left.getSemester().compareTo(right.getSemester());
    }

}
