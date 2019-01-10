package attendance.com.schoolmaster.model;

import java.io.Serializable;

public class ClasslstModel implements Serializable {

    private String name, rewards, studNo;

    public ClasslstModel(String className, String rewards, String noOfStudents)
    {
        this.name = className;
        this.rewards = rewards;
        this.studNo = noOfStudents;
    }

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public String getRewards() {
        return rewards;
    }

    /*public void setRewards(String rewards) {
        this.rewards = rewards;
    }*/

    public String getStudNo() {
        return studNo;
    }

    /*public void setStudNo(int studNo) {
        this.studNo = studNo;
    }*/
}
