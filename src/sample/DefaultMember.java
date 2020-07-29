package sample;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class DefaultMember extends Users implements Serializable {
    private String firstName;
    private String lastName;
    private int membershipNumber;
    private Date StartMembershipDate;
    private String relativeName;

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


