package org.example;

import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;

public class Main {

    public void driver(){

    }

    public static void main(String[] args){
        PatientRecord patientRecord = new PatientRecord();
        ConsultQueue queue = new ConsultQueue(100,patientRecord);
        Scanner sc=new Scanner(System.in);
        int x ;

        //Read from the test input and register them as
        File file = new File("input.txt");
 
        try {
            Scanner scan = new Scanner(file);
                 while(scan.hasNextLine()){
                    String[] line = scan.nextLine().split(",");
                    int id = patientRecord.addPatient(line[0], Integer.parseInt(line[1]));
                    queue.enqueue(id);
                 }
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        while(true){
            System.out.println("Menu Options:");
            System.out.println("1.Display");
            System.out.println("2.Insert Patient Data");
            System.out.println("3.Display Next Patient");
            System.out.println("4.Delete Patient");
            System.out.println("5.Exit");
            System.out.print("Enter your choice:");
            x= sc.nextInt();
            switch(x){
                case 1 : queue.display( queue.size());
                    break;
                case 2 :
                    sc.nextLine();      //consume new-line leftover
                    System.out.print("Enter Patient Name:");
                    String name = sc.nextLine();
                    System.out.print("Enter Patient Age:");
                    if(sc.hasNextInt()){                //validate if input is integer
                        int age = sc.nextInt();
                        boolean isValidAge = validateAge(age);
                        if(isValidAge) {
                            queue.enqueue(patientRecord.addPatient(name, age));
                            System.out.println(queue.size());
                            System.out.println("Added patient "+name+ " successfully" );
                            System.out.println();
                            queue.display( queue.size());
                        }
                    }else{
                        String invalidAge = sc.nextLine();
                        System.out.println(invalidAge + " is not a valid age. Please try again");
                        System.out.println();
                    }
                    break;
                case 3 : System.out.println("The next patient is " + queue.next());
                    System.out.println();
                    break;
                case 4 : //queue.dequeue();
                    System.out.println(patientRecord.getPatient(queue.dequeue()).name + " visited");
                    System.out.println();
                    break;
                case 5 : System.exit(0);
                default:System.out.println("Invalid Entry. Please try again");

            }
        }
    }

    //check if age is within a range (1 to 100)
    private static boolean validateAge(int age) {
        if(age < 1 || age > 100){
            System.out.println(age + " is not a valid age. Please try again");
            return false;
        }
        return true;
    }

}