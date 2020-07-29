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
import java.util.Scanner;

public class MyGymManager extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{



        Scanner input = new Scanner(System.in);    //scanner obj

        ArrayList<Over60Member> arrayOfOldMems  =  new ArrayList<>() ; //arr to store old members
        ArrayList<StudentMember> arrayOfStuMems =  new ArrayList<>(); //arr to store student members
        ArrayList<DefaultMember> arrayOfDefMems =  new ArrayList<>(); //arr to store old members

        FileInputStream defaultMemberFileInputStream = null;
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


        int mainAns = validatingInts("What Do u Want to do ?" +"\n"+
                "Enter 1 to add a new member "+"\n"+
                "Enter 2 to delete a member "+"\n"+
                "Enter 3 to print a the list of members"+"\n"+
                "Enter 4 to sort the list"+"\n"+
                "Enter 5 to save the list"+"\n"+
                "Enter 6 to open GUI :        ");

        if(mainAns==1){
            //adding new member
            int classChoose = validatingInts("Choose member type : " + "\n" +
                    "1 for student member , 2 for over 60 member ,3 for default member");
            if(classChoose == 1){

                StudentMember currentStuMem = new StudentMember();

                currentStuMem.setFirstName(validatingStrings("Enter first name :"));
                currentStuMem.setLastName(validatingStrings("Enter last name :"));
                currentStuMem.setMembershipNumber(validatingInts("Enter membership no : "));
                currentStuMem.setRelativeName(validatingStrings("Enter relative name : "));
                currentStuMem.setSchoolName(validatingStrings("Enter School name : "));

                arrayOfStuMems.add(currentStuMem);

                // Write objects to file

            }
            else if(classChoose == 2){
                Over60Member currentOver60Mem = new Over60Member();

                currentOver60Mem.setFirstName(validatingStrings("Enter first name :"));
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
                arrayOfOldMems.add(currentOver60Mem);

                // Write objects to file
            }
            else if(classChoose == 3){
                DefaultMember currentdefmem = new DefaultMember();

                currentdefmem.setFirstName(validatingStrings("Enter first name :"));
                currentdefmem.setLastName(validatingStrings("Enter last name :"));
                currentdefmem.setMembershipNumber(validatingInts("Enter membership no :"));
                currentdefmem.setRelativeName("Enter relative name :");

                arrayOfDefMems.add(currentdefmem);

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
            }
        }

        else if(mainAns==2){

            //delete a member
            System.out.println("Enter the membership no : ");
            int shoulddel = input.nextInt();

            for(StudentMember anyobj : arrayOfStuMems){   //iterate sthrough every objin the arraylist
                if(shoulddel != anyobj.getMembershipNumber()){
                    System.out.println("OOpz Looks like you have not entered an existing membership no!");
                }
                else if(shoulddel == anyobj.getMembershipNumber()){
                    System.out.println("The details of the member : ");
                    System.out.println("First name : "+anyobj.getFirstName());
                    System.out.println("Last name : "+anyobj.getLastName());

                    arrayOfStuMems.remove(anyobj);  //removes the selected obj

                }
            }

            for(Over60Member anyobj : arrayOfOldMems){   //iterate sthrough every objin the arraylist
                if(shoulddel != anyobj.getMembershipNumber()){
                    System.out.println("OOpz Looks like you have not entered an existing membership no!");
                }
                else if(shoulddel == anyobj.getMembershipNumber()){
                    System.out.println("The details of the member : ");
                    System.out.println("First name : "+anyobj.getFirstName());
                    System.out.println("Last name : "+anyobj.getLastName());

                    arrayOfOldMems.remove(anyobj);  //removes the selected obj

                }
            }


        }

        else if(mainAns==3){
            //print the list of members
            System.out.println("First Name   "+"Last Name   "+"Membership No   "+"Relative Name   "+"Membership Date");
            for(StudentMember anyobj : arrayOfStuMems){
                System.out.print(anyobj.getFirstName());
                System.out.print(anyobj.getLastName());
                System.out.print(anyobj.getMembershipNumber());
                System.out.print(anyobj.getRelativeName());
                //line to get the membership date after its all sorted out
            }
            for(Over60Member anyobj : arrayOfOldMems){
                System.out.print(anyobj.getFirstName());
                System.out.print(anyobj.getLastName());
                System.out.print(anyobj.getMembershipNumber());
                System.out.print(anyobj.getRelativeName());
                //line to get the membership date after its all sorted out
            }
            for(DefaultMember defaultMember : arrayOfDefMems){
                System.out.print(defaultMember.getFirstName());
                System.out.print(defaultMember.getLastName());
                System.out.print(defaultMember.getMembershipNumber());
                System.out.print(defaultMember.getRelativeName());
                //line to get the membership date after its all sorted out
            }

        }

        else if(mainAns==4){
            //sorting
            //creating new arraylist with just the names and then sorting it
            ArrayList<String> stuNames = new ArrayList<>();

            for(StudentMember anyonj : arrayOfStuMems){
                stuNames.add(anyonj.getFirstName());   //adds the names
            }
            Collections.sort(stuNames);  //sorts array of names
            ArrayList<StudentMember> sortedStuMems = new ArrayList<>();

            for(String oneName : stuNames){
                for(StudentMember anyonj : arrayOfStuMems){
                    if(anyonj.getFirstName().equals(oneName)){
                        sortedStuMems.add(anyonj);   //creates a new sorted list by first name of default members
                    }
                }
            }

            ArrayList<String> oldNames = new ArrayList<>();

            for(Over60Member anyonj : arrayOfOldMems){
                oldNames.add(anyonj.getFirstName());   //adds the names
            }
            Collections.sort(oldNames);  //sorts array of names
            ArrayList<Over60Member> sortedOldMems = new ArrayList<>();

            for(String oneName : oldNames){
                for(Over60Member anyonj : arrayOfOldMems){
                    if(anyonj.getFirstName().equals(oneName)){
                        sortedOldMems.add(anyonj);   //creates a new sorted list by first name of default members
                    }
                }
            }

            for(StudentMember anyobj : sortedStuMems){
                System.out.print(anyobj.getFirstName());
                System.out.print(anyobj.getLastName());
                System.out.print(anyobj.getMembershipNumber());
                System.out.print(anyobj.getRelativeName());
                //line to get the membership date after its all sorted out
            }

            for(Over60Member anyobj : sortedOldMems){
                System.out.print(anyobj.getFirstName());
                System.out.print(anyobj.getLastName());
                System.out.print(anyobj.getMembershipNumber());
                System.out.print(anyobj.getRelativeName());
                //line to get the membership date after its all sorted out
            }
        }

        else if(mainAns==5){
            //file writing

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

            //school column
            TableColumn sclColumn = new TableColumn("School");
            sclColumn.setCellValueFactory(new PropertyValueFactory("schoolName"));

            //id column
            TableColumn idColumn = new TableColumn("Membership ID");
            idColumn.setCellValueFactory(new PropertyValueFactory("membershipNumber"));

            //relative column
            TableColumn relNameColumn = new TableColumn("Relative Name");
            relNameColumn.setCellValueFactory(new PropertyValueFactory("relativeName"));

            //relative column
            TableColumn ageColumn = new TableColumn("Age");
            ageColumn.setCellValueFactory(new PropertyValueFactory("age"));


            ObservableList<DefaultMember> dataForTable = FXCollections.observableArrayList();
            dataForTable.addAll(arrayOfStuMems);
            dataForTable.addAll(arrayOfDefMems);
            dataForTable.addAll(arrayOfOldMems);

            table.setItems(dataForTable);
            table.getColumns().addAll(firstNameColumn,lastNameColumn,sclColumn,idColumn,relNameColumn);

            Label lblsearch = new Label("Search :");
            TextField tfsearch = new TextField();
            Button btnSearch = new Button("Search");

            ArrayList<StudentMember> finalArrayOfStuMems = arrayOfStuMems;
            btnSearch.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    ArrayList aftersearcharr = new ArrayList();
                    ObservableList<StudentMember> aftersearchobs = FXCollections.observableArrayList();

                    String searchValue = tfsearch.getText();
                    for (StudentMember anyobj : finalArrayOfStuMems){
                        if(anyobj.getFirstName().equals(searchValue)){
                            aftersearcharr.add(anyobj);
                        }
                    }

                    if(aftersearcharr.size() == 0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("No record!!!");
                        alert.setContentText("There is not a record with the keyword "+searchValue);
                        alert.showAndWait();
                        aftersearcharr = finalArrayOfStuMems;
                    }

                    aftersearchobs.addAll(aftersearcharr);

                    table.setItems(aftersearchobs);
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
        System.out.println("End");
        System.exit(0);
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

    public static void printArr(ArrayList<StudentMember> somearr){
        for(StudentMember anyobj : somearr){
            System.out.println(anyobj.getFirstName());
            System.out.println(anyobj.getLastName());
            System.out.println(anyobj.getMembershipNumber());
            System.out.println(anyobj.getRelativeName());
            //line to get the membership date after its all sorted out
        }
    }

    public static void searchBack(String searchName, ArrayList<StudentMember> someArr){
        for(StudentMember anyobj : someArr){

        }
    }

    public static void changeObList(){

    }
}
