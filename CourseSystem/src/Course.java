import java.util.ArrayList;
import java.util.List;

public class Course {
    String name;//名稱
    String ID; //ID
    int credit;//學分數

    List<Integer> time = new ArrayList<Integer>();
    boolean  requireOrElective; // require or elective
    public Course(String name, String ID, int credit, boolean requireOrElective){
        this.name = name;
        this.ID = ID;
        this.credit = credit;
        this.requireOrElective = requireOrElective;
    }

    public Course(){}
}


