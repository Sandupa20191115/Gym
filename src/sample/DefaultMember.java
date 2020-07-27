package sample;

import java.util.Date;
import java.util.Scanner;

public class DefaultMember extends Users{
    private String firstName;
    private String lastName;
    private int membershipNumber;
    private Date StartMembershipDate;
//    private sample.Date
    private String relativeName;

    Scanner in = new Scanner(System.in);
//    public DefaultMember(){
//        int deMember = 0;
//        deMember += 1;
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

    public Date getStartMembershipDate() {
        return StartMembershipDate;
    }

    public void setStartMembershipDate(Date startMembershipDate) {
        StartMembershipDate = startMembershipDate;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

//    DefaultMember[] arrayOfDefMems = new DefaultMember[100]; //arr to store default members
//    public DefaultMember[] getArrayOfDefMems() {
//        return arrayOfDefMems;
//    }
//
//    public void setArrayOfDefMems(DefaultMember[] arrayOfDefMems) {
//        this.arrayOfDefMems = arrayOfDefMems;
//    }


}


