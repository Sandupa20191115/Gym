package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class MyGymManager extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Scanner input = new Scanner(System.in);    //scanner obj
        Scanner inputdate = new Scanner(System.in);

        ArrayList<Over60Member> arrayOfOldMems  =  new ArrayList<>() ; //arr to store old members
        ArrayList<StudentMember> arrayOfStuMems =  new ArrayList<>(); //arr to store student members
        ArrayList<DefaultMember> arrayOfDefMems =  new ArrayList<>(); //arr to store old members

        FileInputStream defaultMemberFileInputStream = null;                     //gets the existing values from the arrays
        ObjectInputStream defaultMemberObjectInputStream = null;
        try{
            defaultMemberFileInputStream = new FileInputStream(new File("DefaultArray.txt"));
            defaultMemberObjectInputStream = new ObjectInputStream(defaultMemberFileInputStream);
            arrayOfDefMems = (ArrayList<DefaultMember>) defaultMemberObjectInputStream.readObject();
        }catch (EOFException  e){
            // this is fine, it just says end of file reached.
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (defaultMemberFileInputStream != null) {
                defaultMemberFileInputStream.close();
            }
            if (defaultMemberObjectInputStream != null) {
                defaultMemberObjectInputStream.close();
            }
        }

        FileInputStream stuFileInputStream = null;           //gets the existing values from the arrays
        ObjectInputStream stuObjectInputStream = null;
        try{
            stuFileInputStream = new FileInputStream(new File("StudentArray.txt"));
            stuObjectInputStream = new ObjectInputStream(stuFileInputStream);
            arrayOfStuMems = (ArrayList<StudentMember>) stuObjectInputStream.readObject();
        }catch (EOFException  e){
            // this is fine, it just says end of file reached.
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (stuFileInputStream != null) {
                stuFileInputStream.close();
            }
            if (stuObjectInputStream != null) {
                stuObjectInputStream.close();
            }
        }

        FileInputStream oldFileInputStream = null;               //gets the existing values from the arrays
        ObjectInputStream oldObjectInputStream = null;
        try{
            oldFileInputStream = new FileInputStream(new File("OldArray.txt"));
            oldObjectInputStream = new ObjectInputStream(oldFileInputStream);
            arrayOfOldMems = (ArrayList<Over60Member>) oldObjectInputStream.readObject();
        }catch (EOFException  e){
            // this is fine, it just says end of file reached.
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (oldFileInputStream != null) {
                oldFileInputStream.close();
            }
            if (oldObjectInputStream != null) {
                oldObjectInputStream.close();
            }
        }

        int memcount = arrayOfDefMems.size() + arrayOfOldMems.size() + arrayOfStuMems.size();       //gets filled spaces

        int mainAns = validatingInts("What Do u Want to do ?" +"\n"+
                "Enter 1 to add a new member "+"\n"+
                "Enter 2 to delete a member "+"\n"+
                "Enter 3 to print a the list of members"+"\n"+
                "Enter 4 to sort the list"+"\n"+
                "Enter 5 to save the list"+"\n"+
                "Enter 6 to open GUI :        ");

        if(mainAns==1){
            //adding new member
            if(memcount==100){      //allowing only no of 100 people to be members
                System.out.println("The maximum no of members reached");
            }
            else{
                System.out.println("The no of spaces available is "+(100-memcount));    //showing remaining spaces
                int classChoose = validatingInts("Choose member type : " + "\n" +
                        "1 for student member , 2 for over 60 member ,3 for default member");
                if(classChoose == 1){

                    StudentMember currentStuMem = new StudentMember();

                    currentStuMem.setFirstName(validatingStrings("Enter first name :"));   //getting values
                    currentStuMem.setLastName(validatingStrings("Enter last name :"));
                    currentStuMem.setMembershipNumber(validatingInts("Enter membership no : "));
                    currentStuMem.setRelativeName(validatingStrings("Enter relative name : "));
                    currentStuMem.setSchoolName(validatingStrings("Enter School name : "));
                    currentStuMem.setMemDate(dateValidating());

                    arrayOfStuMems.add(currentStuMem);

                }
                else if(classChoose == 2){
                    Over60Member currentOver60Mem = new Over60Member();

                    currentOver60Mem.setFirstName(validatingStrings("Enter first name :"));      //getting values
                    currentOver60Mem.setLastName(validatingStrings("Enter last name :"));
                    currentOver60Mem.setMembershipNumber(validatingInts("Enter membership no : "));
                    while (true){    //validates age not to be impractical
                        currentOver60Mem.setAge(validatingInts("Enter age : "));
                        if (currentOver60Mem.getAge() > 99){
                            System.out.println("Impractical age! Try again");
                        }
                        else{
                            break;
                        }
                    }
                    currentOver60Mem.setRelativeName(validatingStrings("Enter relative name : "));
                    currentOver60Mem.setMemDate(dateValidating());
                    arrayOfOldMems.add(currentOver60Mem);

                }
                else if(classChoose == 3){
                    DefaultMember currentdefmem = new DefaultMember();

                    currentdefmem.setFirstName(validatingStrings("Enter first name :"));        //getting values
                    currentdefmem.setLastName(validatingStrings("Enter last name :"));
                    currentdefmem.setMembershipNumber(validatingInts("Enter membership no :"));
                    currentdefmem.setRelativeName(validatingStrings("Enter relative name :"));
                    currentdefmem.setMemDate(dateValidating());
                    arrayOfDefMems.add(currentdefmem);

                }
            }

        }

        else if(mainAns==2){

            System.out.println("The no of spaces available is "+(100-memcount));   //remaining

            //delete a member
            int shoulddel = validatingInts("Enter the membership no : ");

            Iterator<StudentMember> studentMemberIterator = arrayOfStuMems.iterator();    //had to use an iterator since remove will cause an exception using a for loop
            while (studentMemberIterator.hasNext()){
                if(studentMemberIterator.next().getMembershipNumber()==shoulddel){   //gets relevent obj
                    System.out.println("The member deleted is a "+studentMemberIterator.getClass().getSimpleName());
                    studentMemberIterator.remove();   //removes
                }
                else{
                    System.out.println("Membership no not found");
                }
            }
            Iterator<DefaultMember> defaultMemberIterator = arrayOfDefMems.iterator();
            while (defaultMemberIterator.hasNext()){
                if(defaultMemberIterator.next().getMembershipNumber()==shoulddel){
                    System.out.println("The member deleted is a "+defaultMemberIterator.getClass().getSimpleName());
                    defaultMemberIterator.remove();
                }
                else{
                    System.out.println("Membership no not found");
                }
            }
            Iterator<Over60Member> oldMemberIterator = arrayOfOldMems.iterator();
            while (oldMemberIterator.hasNext()){
                if(oldMemberIterator.next().getMembershipNumber()==shoulddel){
                    System.out.println("The member deleted is a "+oldMemberIterator.getClass().getSimpleName());
                    oldMemberIterator.remove();
                }
                else{
                    System.out.println("Membership no not found");
                }
            }

            int afterDelCount = arrayOfDefMems.size() + arrayOfOldMems.size() + arrayOfStuMems.size();
            System.out.println("The no of spaces available is "+(100-afterDelCount));

        }

        else if(mainAns==3){
            //print the list of members
            System.out.println("First Name   "+"Last Name   "+"Membership No   "+"Relative Name   "+"Membership date       "+"Type ");
            for(StudentMember anyobj : arrayOfStuMems){     //iterate through arraylist
                System.out.print(anyobj.getFirstName()+"        ");
                System.out.print(anyobj.getLastName()+"      ");
                System.out.print(anyobj.getMembershipNumber()+"             ");
                System.out.print(anyobj.getRelativeName()+"             ");
                System.out.print(anyobj.getMemDate()+"            ");
                System.out.println(anyobj.getClass().getSimpleName());
                //line to get the membership date after its all sorted out
            }
            for(Over60Member anyobj : arrayOfOldMems){
                System.out.print(anyobj.getFirstName()+"        ");
                System.out.print(anyobj.getLastName()+"      ");
                System.out.print(anyobj.getMembershipNumber()+"             ");
                System.out.print(anyobj.getRelativeName()+"             ");
                System.out.print(anyobj.getMemDate()+"            ");
                System.out.println(anyobj.getClass().getSimpleName());
                //line to get the membership date after its all sorted out
            }
            for(DefaultMember anyobj : arrayOfDefMems){
                System.out.print(anyobj.getFirstName()+"        ");
                System.out.print(anyobj.getLastName()+"      ");
                System.out.print(anyobj.getMembershipNumber()+"             ");
                System.out.print(anyobj.getRelativeName()+"             ");
                System.out.print(anyobj.getMemDate()+"            ");
                System.out.println(anyobj.getClass().getSimpleName());
                //line to get the membership date after its all sorted out
            }

        }

        else if(mainAns==4){
            //sorting
            //creating new arraylist with just the names and then sorting it
            ArrayList<String> allNames = new ArrayList<>();

            for(StudentMember anyonj : arrayOfStuMems){
                allNames.add(anyonj.getFirstName());   //adds the names
            }
            for(DefaultMember anyonj : arrayOfDefMems){
                allNames.add(anyonj.getFirstName());   //adds the names
            }
            for(Over60Member anyonj : arrayOfOldMems){
                allNames.add(anyonj.getFirstName());   //adds the names
            }
//            System.out.println(allNames);
            Collections.sort(allNames);  //sorts array of names
//            System.out.println(allNames);
            ArrayList<DefaultMember> sortedMems = new ArrayList<>();

            for(String oneName : allNames){
                for(DefaultMember anyonj : arrayOfDefMems){
                    if(anyonj.getFirstName().equals(oneName)){
                        sortedMems.add(anyonj);   // new sorted list by first name of default members
                    }
                }
                for(StudentMember anyonj : arrayOfStuMems){
                    if(anyonj.getFirstName().equals(oneName)){
                        sortedMems.add(anyonj);   // new sorted list by first name of default members
                    }
                }
                for(Over60Member anyonj : arrayOfOldMems){
                    if(anyonj.getFirstName().equals(oneName)){
                        sortedMems.add(anyonj);   // new sorted list by first name of default members
                    }
                }
            }
            System.out.println("First Name   "+"Last Name   "+"Membership No   "+"Relative Name   ");
            printArr(sortedMems);

        }

        else if(mainAns==5){
            //file writing
            //A special code for this section isnt needed since all the records are saved to a file anyway
        }

        else if(mainAns==6){

            //opens GUI
            TableView table = new TableView();   //creating tableview

            //first name column
            TableColumn firstNameColumn = new TableColumn("First Name");
            firstNameColumn.setCellValueFactory(new PropertyValueFactory("firstName"));

            //last name column
            TableColumn lastNameColumn = new TableColumn("Last Name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory("lastName"));

            //id column
            TableColumn idColumn = new TableColumn("Membership ID");
            idColumn.setCellValueFactory(new PropertyValueFactory("membershipNumber"));

            //relative column
            TableColumn relNameColumn = new TableColumn("Relative Name");
            relNameColumn.setCellValueFactory(new PropertyValueFactory("relativeName"));

            //date
            TableColumn dateColumn = new TableColumn("Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory("memDate"));

            ObservableList<DefaultMember> dataForTable = FXCollections.observableArrayList();
            dataForTable.addAll(arrayOfStuMems);   //adding arraylist
            dataForTable.addAll(arrayOfDefMems);
            dataForTable.addAll(arrayOfOldMems);

            table.setItems(dataForTable);
            table.getColumns().addAll(firstNameColumn,lastNameColumn,idColumn,relNameColumn,dateColumn);

            Label lblsearch = new Label("Search :");
            TextField tfsearch = new TextField();
            Button btnSearch = new Button("Search");

            ArrayList<StudentMember> finalStuMems = arrayOfStuMems;     //since the scoped variable problem had to create new arraylist to hold arraylist
            ArrayList<DefaultMember> finalDefMems = arrayOfDefMems;
            ArrayList<Over60Member>  finalOldMems = arrayOfOldMems;
            btnSearch.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    ArrayList aftersearcharr = new ArrayList();
                    ObservableList<StudentMember> aftersearchobs = FXCollections.observableArrayList();

                    String searchValue = tfsearch.getText();

                    for (StudentMember anyobj : finalStuMems){
                        if(anyobj.getFirstName().contains(searchValue)){     //adds the obj if the search value matches or contains
                            aftersearcharr.add(anyobj);
                        }
                    }

                    for (DefaultMember anyobj : finalDefMems){
                        if(anyobj.getFirstName().contains(searchValue)){   //adds the obj if the search value matches or contains
                            aftersearcharr.add(anyobj);
                        }
                    }

                    for (Over60Member anyobj : finalOldMems){
                        if(anyobj.getFirstName().contains(searchValue)){      //adds the obj if the search value matches or contains
                            aftersearcharr.add(anyobj);
                        }
                    }

                    if(aftersearcharr.size() == 0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("No record!!!");
                        alert.setContentText("There is not a record with the keyword "+searchValue);
                        alert.showAndWait();
                         table.setItems(dataForTable);
                    }
                    else{
                        aftersearchobs.addAll(aftersearcharr);
                        table.setItems(aftersearchobs);
                    }
                }
            });

            HBox searchStuff = new HBox(10,lblsearch,tfsearch,btnSearch);

            VBox root = new VBox(10,searchStuff,table);
            ///////////////////////**/////////////////////////////////
            Scene homeScene = new Scene(root,500,500);
            primaryStage.setTitle("Gym Manager");
            primaryStage.setScene(homeScene);
            primaryStage.show();
        }
        else{
            System.out.println("Invalid Input");
        }

        // Write objects to file
        FileOutputStream stuFileOutputStream = null;
        ObjectOutputStream stuObjectOutputStream = null;
        try{
            stuFileOutputStream = new FileOutputStream(new File("StudentArray.txt"));
            stuObjectOutputStream = new ObjectOutputStream(stuFileOutputStream);
            // writing updated array list.
            stuObjectOutputStream.writeObject(arrayOfStuMems);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (stuFileOutputStream != null) {
                stuFileOutputStream.close();
            }
            if (stuObjectOutputStream != null) {
                stuObjectOutputStream.close();
            }
        }
        ///////////////////**////////////////////

        // Write objects to file
        FileOutputStream oldFileOutputStream = null;
        ObjectOutputStream oldObjectOutputStream = null;
        try{
            oldFileOutputStream = new FileOutputStream(new File("OldArray.txt"));
            oldObjectOutputStream = new ObjectOutputStream(oldFileOutputStream);
            // writing updated array list.
            oldObjectOutputStream.writeObject(arrayOfOldMems);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (oldFileOutputStream != null) {
                oldFileOutputStream.close();
            }
            if (oldObjectOutputStream != null) {
                oldObjectOutputStream.close();
            }
        }
        ///////////////////**////////////////////

        FileOutputStream defaultMemberFileOutputStream = null;
        ObjectOutputStream defaultMemberObjectOutputStream = null;
        try{
            defaultMemberFileOutputStream = new FileOutputStream(new File("DefaultArray.txt"));
            defaultMemberObjectOutputStream = new ObjectOutputStream(defaultMemberFileOutputStream);
            // writing updated array list.
            defaultMemberObjectOutputStream.writeObject(arrayOfDefMems);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (defaultMemberFileOutputStream != null) {
                defaultMemberFileOutputStream.close();
            }
            if (defaultMemberObjectOutputStream != null) {
                defaultMemberObjectOutputStream.close();
            }
        }

        System.out.println("End");
//        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static String validatingStrings(String promptMsg){    ///validating input to be strings
        Scanner valIn = new Scanner(System.in);
        System.out.println(promptMsg);
        while (true){
            try {
                return valIn.nextLine();
            }
            catch (Exception e){
                System.out.println("Invalid Input Please try agian");
                valIn.nextLine();
            }
        }

    }


    public static int validatingInts(String promptMsg){
        Scanner valIn = new Scanner(System.in);
        System.out.println(promptMsg);
        while (true){
            try {
                return valIn.nextInt();
            }
            catch (Exception e){
                System.out.println("Invalid Input Please try agian");
                valIn.nextLine();
            }
        }

    }

    public static void printArr(ArrayList<DefaultMember> somearr){
        for(DefaultMember anyobj : somearr){
            System.out.print(anyobj.getFirstName()+"       ");
            System.out.print(anyobj.getLastName()+"      ");
            System.out.print(anyobj.getMembershipNumber()+"             ");
            System.out.println(anyobj.getRelativeName()+"             ");
            //line to get the membership date after its all sorted out
        }
    }

    public static String dateValidating(){
        System.out.println("--Getting Membership Date--");
        int year = validatingInts("Enter year : ");
        int month ;
        int date ;
        while (true){
            month = validatingInts("Enter month :");
            if(month>12 || month < 1)
                System.out.println("Invalid Month");
            else
                break;

        }
        while (true){
            date = validatingInts("Enter date : ");
            if(date>31 || date <1)
                System.out.println("Invalid Date");
            else
                break;
        }

        return year+"/"+month+"/"+date;
    }
}
