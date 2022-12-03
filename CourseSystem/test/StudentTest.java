import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    AllCourse allCourse = new AllCourse();
    Course course1 = new Course();
    Course course2 = new Course();
    Student student = new Student();

    @Test
    void checkTime() throws IOException {
        // 設定課程
        allCourse.CourseInit();
        List<Integer> add = new ArrayList<>();
        // 學生吳課程，部會有衝堂問題
        assertEquals(false, student.checkTime(add));

        add.add(41);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1381")){
                course2 = course;
            }
        }
        // 家選課程1381 (時間為41 42 43)
        student.addCourse(course2);
        // System.out.println(student.time);
        // 因為1381課程41有課，所以衝堂
        assertEquals(true, student.checkTime(add));
    }

    @Test
    void printStudentCourse() throws IOException {
        // 設定課程
        allCourse.CourseInit();
        // 原始無課程
        assertEquals(0, student.credit);

        // 佳烜一堂課
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1381")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        // System.out.println(student.time);

        // 學分數上升，確認有加入學生課程內
        assertEquals(3, student.credit);

    }

    @Test
    void findCourseExist() throws IOException {
        // 設定課程
        allCourse.CourseInit();

        // 無加選課程，學生課程內存在1381課程
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1381")){
                course1 = course;
            }
        }
        // assertEquals(false, student.findCourseExist(course1));
        assertFalse(student.findCourseExist(course1));

        // 加選1384課程，學生課程內存在1384課程
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1384")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        // assertEquals(true, student.findCourseExist(course2));
        assertTrue(student.findCourseExist(course2));
        // System.out.println(course2.ID);
    }

    @Test
    void addCourse() throws IOException {
        // 設定課程
        allCourse.CourseInit();

        // 加選1384課程
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1384")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        // 確認加選成功
        assertEquals(2, student.credit);

        // 加選空課程(也就是沒有此課程)
        student.addCourse(null);
        // 學分數無改變
        assertEquals(2, student.credit);

        // 加選以加過的課程，學分數不會改變
        student.addCourse(course2);
        assertEquals(2, student.credit);

        for (Course course : allCourse.courseList){
            if(course.ID.equals("1381")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1385")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1386")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1387")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1393")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1394")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1401")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        assertEquals(18, student.credit);

        // 加選衝堂課程，學分數不會有改變
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1402")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        assertEquals(18, student.credit);

        for (Course course : allCourse.courseList){
            if(course.ID.equals("1406")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("2995")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("3018")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("0314")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("0320")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        assertEquals(30, student.credit);

        // 加選後學分數大於30，無法加選，學分不會改變
        for (Course course : allCourse.courseList){
            if(course.ID.equals("0321")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        assertEquals(30, student.credit);
    }

    @Test
    void removeCourse() throws IOException {
        // 設定課程
        allCourse.CourseInit();

        // 先加選一些課程
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1381")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1384")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1385")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1386")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1387")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1393")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1394")){
                course2 = course;
            }
        }
        student.addCourse(course2);
        assertEquals(15, student.credit);

        // 退選剛剛加選過的1381課程，退炫成功，學分下降
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1381")){
                course2 = course;
            }
        }
        student.removeCourse(course2);
        assertEquals(12, student.credit);

        // 退選空課程(也就是沒有此課程)，學分無變化
        student.removeCourse(null);
        assertEquals(12, student.credit);

        for (Course course : allCourse.courseList){
            if(course.ID.equals("1384")){
                course2 = course;
            }
        }
        student.removeCourse(course2);
        assertEquals(10, student.credit);

        // 退選後學份小於9，無法退選，學分不會改變
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1385")){
                course2 = course;
            }
        }
        student.removeCourse(course2);
        assertEquals(10, student.credit);
    }
}