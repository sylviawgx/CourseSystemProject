import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllCourse{
    List<Course> courseList = new ArrayList<>();

    public Course courseExist(String choose){//find the input name or id exist or not
        for (Course course : courseList) {
            if(course.ID.equals(choose) || course.name.equals(choose)){
                return course;
            }
        }
        return null;
    }

    public void changeString(int num){
        int week = (num - 1) / 13;
        int time = (num - 1) % 13 + 1;
        switch (week){
            case 0 -> System.out.printf("%s", "一");
            case 1 -> System.out.printf("%s", "二");
            case 2 -> System.out.printf("%s", "三");
            case 3 -> System.out.printf("%s", "四");
            case 4 -> System.out.printf("%s", "五");
        }
        System.out.printf("-%d ", time);
    }

    public void printCourseList(){// print all courses on window
        for(Course course: courseList){
            System.out.printf("%-10s\t%-10s\t%-3d\t%-3s",course.ID, course.name, course.credit, course.requireOrElective ? "選修":"必修");
            for(int num: course.time){
                changeString(num);
            }
            System.out.println();
        }
        System.out.println();
    }

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
                courseList.add(newCourse);
            }else {
                break;
            }
        }
        file.close();
    }
}
