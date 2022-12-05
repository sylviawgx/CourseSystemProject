import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class AllCourseTest {
    AllCourse allCourse = new AllCourse();

    @BeforeEach
    public void CourseInit() throws IOException{
        Path path = Paths.get("");
        String directoryName = path.toAbsolutePath().normalize().toString();

        FileReader file = new FileReader(directoryName+"\\CourseSystem\\src\\courseData.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            if(scanner.hasNextLine()){
                String id = scanner.next();
                String name = scanner.next();
                int credit = scanner.nextInt();
                boolean requireOrElective = scanner.nextBoolean();
                Course newCourse = new Course(name, id, credit, requireOrElective);
                int courseCount = scanner.nextInt();
                for(int i = 0 ; i < courseCount ; i++){
                    int time = scanner.nextInt();
                    newCourse.time.add(time);
                }
                allCourse.courseList.add(newCourse);
            }else {
                break;
            }
        }
        file.close();
    }

    @ParameterizedTest
    @CsvSource({
            /*
             * {allCourse中的資料位置}, {需查找課程}
             * */
            "0, 1381",//成功查找
            "null, 1382",// 無此課程
    })
    void courseExist(ArgumentsAccessor arguments){
        if(arguments.get(0).toString().equals("null")){
            assertNull(allCourse.courseExist(arguments.get(1).toString()));
        }else{
            assertEquals(allCourse.courseList.get(0), allCourse.courseExist(arguments.get(1).toString()));
        }
    }
//    /@Test
//    void courseExist() throws IOException {
//        // 設定課程
//        allCourse.CourseInit();
//        // String tmp = "1381";
//        // course1 = allCourse.courseExist(tmp);
//        // assertEquals(course1.ID,"1381");
//
//        // 有1381課程，確認是否存在1381課程
//        assertEquals(allCourse.courseList.get(0),allCourse.courseExist("1381"));
//
//        /*
//        for (Course course : allCourse.courseList){
//            if(course.ID.equals("1381")){
//                course1 = course;
//            }
//        }
//        course2 = allCourse.courseExist("軟體工程開發實務");
//        assertEquals(course1.ID, course2.ID);
//        */
//
//        // 無1382課程，確認是否存在1382課程
//        assertEquals(null, allCourse.courseExist("1382"));
//
//
//
//
//    }

    @org.junit.jupiter.api.Test
    void changeString() {
    }

    @org.junit.jupiter.api.Test
    void printCourseList() {

    }
}