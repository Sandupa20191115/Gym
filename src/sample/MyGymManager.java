package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MyGymManager extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

//        //mongo shit
//        MongoClient mClient = new MongoClient("localhost",27017);
//        MongoDatabase mdatabase = mClient.getDatabase("cwdata");
//
//        //creating a collection
//        mdatabase.createCollection("gym",null);
//
//        //create a collection`
////        if(mdatabase.getCollection("gym")==null){
////            mdatabase.createCollection("gym");
////        }
//
//        //accessing the collection
//        MongoCollection collection = mdatabase.getCollection("student");
//
//        //create a document in the collection
//        //Create a document in the collection
//        Document document1 = new Document();
//        document1.put("id", new DefaultMember());
//        document1.put("firstName", "Hirushi");
//        document1.put("lastName", "Perera");
//        document1.put("age", 25);
//
//        //insert record as a document
//        collection.insertOne(document1);



        Scanner input = new Scanner(System.in);    //scanner obj

        ArrayList<StudentMember> arrayOfStuMems = new ArrayList<>(); //arr to store student members
        ArrayList<Over60Member> arrayOfOldMems = new ArrayList<>(); //arr to store student members

        StudentMember sandu1 = new StudentMember();
        sandu1.setFirstName("Sandu");
        arrayOfStuMems.add(sandu1);

        int mainAns = validatingInts("What Do u Want to do ?" +"\n"+
                "Enter 1 to add a new member "+"\n"+
                "Enter 2 to delete a member "+"\n"+
                "Enter 3 to print a the list of members"+"\n"+
                "Enter 4 to sort the list"+"\n"+
                "Enter 5 to save the list"+"\n"+
                "Enter 6 to open GUI :        ");

        if(mainAns==1){
            //adding new member
            int classChoose = validatingInts("Choose member type : " +
                    "1 for student member , 2 for over 60 member ");
            if(classChoose == 1){

                StudentMember currentStuMem = new StudentMember();

                currentStuMem.setFirstName(validatingStrings("Enter first name :"));
                currentStuMem.setLastName(validatingStrings("Enter last name :"));
                currentStuMem.setMembershipNumber(validatingInts("Enter membership no : "));
                currentStuMem.setRelativeName(validatingStrings("Enter relative name : "));
                currentStuMem.setSchoolName(validatingStrings("Enter School name : "));

                arrayOfStuMems.add(currentStuMem);

                //opens GUI
                TableView<StudentMember> table;   //creating tableview

                //first name column
                TableColumn<StudentMember, String> firstNameColumn = new TableColumn<>("First Name");
                firstNameColumn.setMinWidth(200);
                firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

                ObservableList<StudentMember> dataForTable = FXCollections.observableArrayList();
                dataForTable.addAll(arrayOfStuMems);

                table = new TableView<>();
                table.setItems(dataForTable);
                table.getColumns().addAll(firstNameColumn);

                VBox root = new VBox();
                ///////////////////////**/////////////////////////////////
                Scene homeScene = new Scene(root,500,500);
                primaryStage.setTitle("Gym Manager");
                primaryStage.setScene(homeScene);
                primaryStage.show();

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
            //saving to file
        }

        else if(mainAns==6){
//            //opens GUI
//            TableView<StudentMember> table;   //creating tableview
//
//            //first name column
//            TableColumn<StudentMember, String> firstNameColumn = new TableColumn<>("First Name");
////            firstNameColumn.setMinWidth(200);
//            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
////            firstNameColumn.setCellValueFactory();Factory(new PropertyValueFactory<StudentMember,String>("First Name"));
//
//            ObservableList<StudentMember> dataForTable = FXCollections.observableArrayList();
//            dataForTable.addAll(arrayOfStuMems);
//
//            table = new TableView<>();
//            table.setItems(dataForTable);
//            table.getColumns().add(firstNameColumn);



            //opens GUI
            TableView table = new TableView();   //creating tableview

            //first name column
            TableColumn firstNameColumn = new TableColumn("First Name");
//            firstNameColumn.setMinWidth(200);
            firstNameColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
//            firstNameColumn.setCellValueFactory();Factory(new PropertyValueFactory<StudentMember,String>("First Name"));

            ObservableList<StudentMember> dataForTable = FXCollections.observableArrayList();
            dataForTable.addAll(arrayOfStuMems);

            table.setItems(dataForTable);
            table.getColumns().add(firstNameColumn);

            VBox root = new VBox(table);
            ///////////////////////**/////////////////////////////////
            Scene homeScene = new Scene(root,500,500);
            primaryStage.setTitle("Gym Manager");
            primaryStage.setScene(homeScene);
            primaryStage.show();
        }

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
            System.out.print(anyobj.getFirstName());
            System.out.print(anyobj.getLastName());
            System.out.print(anyobj.getMembershipNumber());
            System.out.print(anyobj.getRelativeName());
            //line to get the membership date after its all sorted out
        }
    }


}
