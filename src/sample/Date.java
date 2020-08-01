package sample;

import java.util.Scanner;

import static sample.MyGymManager.validatingInts;

public class Date {
    private String fullDate;
    int date;
    int month;
    int year;

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate() {

        year = validatingInts("Enter Year : ");

        while (true){
            month = validatingInts("Enter month :");
            if(month>12 || month < 1)     //checks if its an invalid month
                System.out.println("Invalid Month");
            else
                break;

        }

        while (true){
            date = validatingInts("Enter date : ");
            if(date>31 || date <1)           //checks if its an invalid date
                System.out.println("Invalid Date");
            else
                break;
        }

        this.fullDate = year +"/"+ month +"/"+ date;
    }
}
