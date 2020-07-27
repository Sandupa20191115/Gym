package sample;

import java.util.Scanner;

public class Date {
    String newDate;
    public Scanner in = new Scanner(System.in);
    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        System.out.println("Enter the membership Year : ");
        String enteredYear = in.nextLine();

        System.out.println("Enter the membership Month : ");
        String enteredMonth = in.nextLine();

        System.out.println("Enter the membership Date : ");
        String enteredDate = in.nextLine();

        newDate = enteredYear + "/" + enteredMonth + "/" + enteredDate;

        this.newDate = newDate;
    }
}
