import java.util.ArrayList;
import java.util.List;

public class Student {
    int credit;
    List<Course> CourseSelected = new ArrayList<>();

    public Student(int credit){
        this.credit = credit;
    }
    public Student(){}
    List<Integer> time = new ArrayList<>();

    public Boolean checkTime(List<Integer> add){
        for(Integer addTime: add){
            if(time.contains(addTime)){
                return true;
            }
        }
        return false;
    }

    public void printStudentCourse(AllCourse allCourse){
        for (Course course : CourseSelected) {
            System.out.printf("%s %s ",course.ID, course.name);
            for(int times: course.time) {
                allCourse.changeString(times);
            }
            System.out.println();
        }
        System.out.println("目前學分數:" + credit);
        if(CourseSelected.size() == 0){
            System.out.println("沒有加選課程\n");
        }
    }
    public boolean findCourseExist(Course course){
        for(Course find: CourseSelected){
            if(find == course){
                return true;
            }
        }
        return false;
    }
    public void addCourse(Course course){
        if(course == null){
            System.out.println("沒有找到該課程，加選失敗\n");
            return;
        }
        if(findCourseExist(course)){
            System.out.println("功課表已有該課程，加選失敗\n");
            return;
        }
        if(credit + course.credit > 30){
            System.out.println("加選後已超過學分數30，加選失敗\n");
            return;
        }

        if(checkTime(course.time)){
            System.out.println("衝堂，加選失敗\n");
            return;
        }
        System.out.println("加選成功\n");
        CourseSelected.add(course);
        time.addAll(course.time);
        credit += course.credit;
    }
    public void removeCourse(Course course){
        if(course == null){
            System.out.println("沒有找到該課程\n");
            return;
        }
        if(credit - course.credit < 9){
            System.out.println("退選後學分數不足9學分，退選失敗\n");
            return;
        }
        if(!course.requireOrElective){
            System.out.print("你將要退選必修課程，");
        }
        System.out.println("退選成功\n");
        CourseSelected.remove(course);
        credit -= course.credit;
    }
}
