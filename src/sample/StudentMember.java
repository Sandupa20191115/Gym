package sample;

import java.io.Serializable;

public class StudentMember extends DefaultMember implements Serializable {
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
