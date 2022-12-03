import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class AllCourseTest {
    AllCourse allCourse = new AllCourse();
    Course course1 = new Course();
    Course course2 = new Course();

    @Test
    void courseExist() throws IOException {
        // 設定課程
        allCourse.CourseInit();
        // String tmp = "1381";
        // course1 = allCourse.courseExist(tmp);
        // assertEquals(course1.ID,"1381");

        // 有1381課程，確認是否存在1381課程
        assertEquals(allCourse.courseList.get(0),allCourse.courseExist("1381"));

        /*
        for (Course course : allCourse.courseList){
            if(course.ID.equals("1381")){
                course1 = course;
            }
        }
        course2 = allCourse.courseExist("軟體工程開發實務");
        assertEquals(course1.ID, course2.ID);
        */

        // 無1382課程，確認是否存在1382課程
        assertEquals(null, allCourse.courseExist("1382"));




    }

    @org.junit.jupiter.api.Test
    void changeString() {
    }

    @org.junit.jupiter.api.Test
    void printCourseList() {
    }

    @org.junit.jupiter.api.Test
    void courseInit() {
    }
}