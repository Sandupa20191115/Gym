package sample;

import java.io.Serializable;


public class DefaultMember implements Serializable {
    private String firstName;
    private String lastName;
    private int membershipNumber;
    private String relativeName;
    ///////////////////
    private String newdate;

    public String getNewdate() {
        return newdate;
    }

    public void setNewdate(String newdate) {
        this.newdate = newdate;
    }
    /////////////////////////

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }



}


