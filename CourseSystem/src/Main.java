import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        AllCourse allCourse = new AllCourse();
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        allCourse.CourseInit();

        while(true){
            System.out.println("請選擇操作並輸入操作代號");
            System.out.println("0 查看可以選的課程");
            System.out.println("1 查看已選課程");
            System.out.println("2 加選課程");
            System.out.println("3 刪除課程");
            System.out.println("4 退出");
            System.out.println("**********************分割線***********************\n");
            try{
                int choose = scanner.nextInt();
                if(choose == 4) {
                    break;
                }
                switch (choose) {
                    case 0 -> allCourse.printCourseList();
                    case 1 -> student.printStudentCourse(allCourse);
                    case 2 -> {
                        System.out.println("請輸入要加選的課程ID");
                        String addCourseId = scanner.next();
                        student.addCourse(allCourse.courseExist(addCourseId));
                    }
                    case 3 -> {
                        System.out.println("請輸入要退選的課程ID");
                        student.printStudentCourse(allCourse);
                        String removeCourseId = scanner.next();
                        student.removeCourse(allCourse.courseExist(removeCourseId));
                    }
                    default -> System.out.println("請輸入正確數值 0-4");
                }
            }catch(InputMismatchException exception){
                System.out.println("必須輸入0-4");
                scanner.next();
            }
        }
    }
}