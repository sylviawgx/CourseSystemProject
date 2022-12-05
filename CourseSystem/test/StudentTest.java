import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    AllCourse allCourse = new AllCourse();
    //    Course course1 = new Course();
//    Course course2 = new Course();
    Student student = new Student();

    Course course = new Course();

    @BeforeEach
    public void CourseInit() throws IOException {
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().normalize().toString();
        FileReader file = new FileReader(directoryName + "\\CourseSystem\\src\\courseData.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            if (scanner.hasNextLine()) {
                String id = scanner.next();
                String name = scanner.next();
                int credit = scanner.nextInt();
                boolean requireOrElective = scanner.nextBoolean();
                Course newCourse = new Course(name, id, credit, requireOrElective);
                int courseCount = scanner.nextInt();
                for (int i = 0; i < courseCount; i++) {
                    int time = scanner.nextInt();
                    newCourse.time.add(time);
                }
                allCourse.courseList.add(newCourse);
            } else {
                break;
            }
        }
        file.close();
    }

    @ParameterizedTest
    @CsvSource({
            /* 加選需判斷有無衝堂
             * {加選成功: false, 加選失敗: true},{原始課程}, { 加選時間 }
             * */
            "false, 1393, 21",// 無衝堂，加選成功
            "true, 1393, 19",// 衝堂，導致無法加選
    })
    void checkTime(ArgumentsAccessor arguments) {
        List<Integer> addTime = new ArrayList<>();//建立需加選課程時間
        addTime.add(arguments.getInteger(2));//加入時間
        course = allCourse.courseExist(arguments.getString(1));
        student.addCourse(course);//加選指定課程
        assertEquals(arguments.getBoolean(0), student.checkTime(addTime));
    }

    //    @Test
//    void checkTime() throws IOException {
//        // 設定課程
//        allCourse.CourseInit();
//        List<Integer> add = new ArrayList<>();
//        // 學生吳課程，部會有衝堂問題
//        assertEquals(false, student.checkTime(add));
//
//        add.add(41);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1381")){
//                course2 = course;
//            }
//        }
//        // 家選課程1381 (時間為41 42 43)
//        student.addCourse(course2);
//        // System.out.println(student.time);
//        // 因為1381課程41有課，所以衝堂
//        assertEquals(true, student.checkTime(add));
//    }
//
    @ParameterizedTest
    @CsvSource({
            "2, 1393",
            "0, null"
    })
    void printStudentCourse(ArgumentsAccessor arguments) {
        course = allCourse.courseExist(arguments.getString(1));
        student.addCourse(course);
        assertEquals(arguments.getInteger(0), student.credit);
    }
//    @Test
//    void printStudentCourse() throws IOException {
//        // 設定課程
//        allCourse.CourseInit();
//        // 原始無課程
//        assertEquals(0, student.credit);
//
//        // 佳烜一堂課
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1381")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        // System.out.println(student.time);
//
//        // 學分數上升，確認有加入學生課程內
//        assertEquals(3, student.credit);
//
//    }

    @ParameterizedTest
    @CsvSource({
            /* 加選需先查找課程有無存在
             * {課程存在: true 課程不存在: false}, {需查找課程ID}
             * */
            "true, 1381",// 課程存在
            "false, 1382"// 課程不存在
    })
    void findCourseExist(ArgumentsAccessor arguments) {
        course = allCourse.courseExist("1381");
        student.addCourse(course);
        course = allCourse.courseExist(arguments.getString(1));
        assertEquals(arguments.getBoolean(0), student.findCourseExist(course));
    }
//@Test
//void findCourseExist() throws IOException {
//    // 設定課程
//    allCourse.CourseInit();
//
//    // 無加選課程，學生課程內存在1381課程
//    for (Course course : allCourse.courseList){
//        if(course.ID.equals("1381")){
//            course1 = course;
//        }
//    }
//    // assertEquals(false, student.findCourseExist(course1));
//    assertFalse(student.findCourseExist(course1));
//
//    // 加選1384課程，學生課程內存在1384課程
//    for (Course course : allCourse.courseList){
//        if(course.ID.equals("1384")){
//            course2 = course;
//        }
//    }
//    student.addCourse(course2);
//    // assertEquals(true, student.findCourseExist(course2));
//    assertTrue(student.findCourseExist(course2));
//    // System.out.println(course2.ID);
//}

    @ParameterizedTest
    @CsvSource({
            /*
             * {執行後學分數}, {原有課程}, {需加選課程}, {原始學分數}
             * */
            "4, 1393, 1384, 0",// 正常加選
            "2, 1393, 1405, 0",// 衝堂無法加選
            "2, 1393, 1393, 0",// 已有該課程無法加選
            "2, 1393, 1383, 0",// 無法找到該課程無法加選
            "29, 1393, 1400, 29",// 加選後學分數超過30無法加選
    })
    void addCourse(ArgumentsAccessor arguments) {
        student.credit = arguments.getInteger(3);
        course = allCourse.courseExist(arguments.getString(1));
        student.addCourse(course);
        course = allCourse.courseExist(arguments.getString(2));
        student.addCourse(course);
        assertEquals(arguments.getInteger(0), student.credit);
    }

//    @Test
//    void addCourse() throws IOException {
//        // 設定課程
//        allCourse.CourseInit();
//
//        // 加選1384課程
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1384")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        // 確認加選成功
//        assertEquals(2, student.credit);
//
//        // 加選空課程(也就是沒有此課程)
//        student.addCourse(null);
//        // 學分數無改變
//        assertEquals(2, student.credit);
//
//        // 加選以加過的課程，學分數不會改變
//        student.addCourse(course2);
//        assertEquals(2, student.credit);
//
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1381")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1385")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1386")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1387")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1393")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1394")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1401")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        assertEquals(18, student.credit);
//
//        // 加選衝堂課程，學分數不會有改變
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1402")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        assertEquals(18, student.credit);
//
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1406")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("2995")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("3018")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("0314")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("0320")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        assertEquals(30, student.credit);
//
//        // 加選後學分數大於30，無法加選，學分不會改變
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("0321")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        assertEquals(30, student.credit);
//    }

    @ParameterizedTest
    @CsvSource({
            /*
             * {執行後學分數}, {原有課程}, {需退選選課程}, {原始學分數}
             * */
            "20, 1393, 1393, 20",// 正常退選
            "22, 1393, 1383, 20",// 沒有該課程無法退選
            "10, 1393, 1400, 8",// 退選後學分數不足9學分，退選失敗
            "20, 1384, 1384, 20"// 退選必修課程
    })
    void removeCourse(ArgumentsAccessor arguments) {
        student.credit = arguments.getInteger(3);
        course = allCourse.courseExist(arguments.getString(1));
        student.addCourse(course);
        course = allCourse.courseExist(arguments.getString(2));
        student.removeCourse(course);
        assertEquals(arguments.getInteger(0), student.credit);
    }

//    @Test
//    void removeCourse() throws IOException {
//        // 設定課程
//        allCourse.CourseInit();
//
//        // 先加選一些課程
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1381")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1384")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1385")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1386")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1387")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1393")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1394")){
//                course2 = course;
//            }
//        }
//        student.addCourse(course2);
//        assertEquals(15, student.credit);
//
//        // 退選剛剛加選過的1381課程，退炫成功，學分下降
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1381")){
//                course2 = course;
//            }
//        }
//        student.removeCourse(course2);
//        assertEquals(12, student.credit);
//
//        // 退選空課程(也就是沒有此課程)，學分無變化
//        student.removeCourse(null);
//        assertEquals(12, student.credit);
//
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1384")){
//                course2 = course;
//            }
//        }
//        student.removeCourse(course2);
//        assertEquals(10, student.credit);
//
//        // 退選後學份小於9，無法退選，學分不會改變
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1385")){
//                course2 = course;
//            }
//        }
//        student.removeCourse(course2);
//        assertEquals(10, student.credit);
//    }
}