package sample;

import java.io.Serializable;


public class DefaultMember implements Serializable {
    private String firstName;
    private String lastName;
    private int membershipNumber;
    private String relativeName;
//    private Date defdate;

    public String getMemDate() {
        return memDate;
    }

    public void setMemDate(String memDate) {
        this.memDate = memDate;
    }

    private String memDate;

//    public Date getDefdate() {
//        return defdate;
//    }

//    public void setDefdate(Date defdate) {
//        this.defdate = defdate;
//    }

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


