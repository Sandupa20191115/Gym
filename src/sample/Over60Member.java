package sample;

import java.io.Serializable;

public class Over60Member extends DefaultMember implements Serializable {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
