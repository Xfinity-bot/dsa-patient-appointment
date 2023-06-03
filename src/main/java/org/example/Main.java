package org.example;

import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;

public class Main {

    static PatientRecord patientRecord = new PatientRecord();
    static ConsultQueue queue = new ConsultQueue(100,patientRecord);

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        String x ;

        //Read from the test input and register them as
        registerPatient();

        while(true){
            System.out.println("Menu Options:");
            System.out.println("1.Display");
            System.out.println("2.Insert Patient Data");
            System.out.println("3.Display Next Patient");
            System.out.println("4.Delete Patient");
            System.out.println("5.Exit");
            System.out.print("Enter your choice:");
            x= sc.next();
            switch(x){
                case "1" : queue.display( queue.size());
                    break;
                case "2" :
                    sc.nextLine();      //consume new-line leftover
                    System.out.print("Enter Patient Name:");
                    String name = sc.nextLine();
                    System.out.print("Enter Patient Age:");
                    if(sc.hasNextInt()){                //validate if input is integer
                        int age = sc.nextInt();
                        boolean isValidAge = validateAge(age);
                        if(isValidAge) {
                            queue.enqueue(patientRecord.registerPatient(name, age));
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
                case "3" : System.out.println("The next patient is " + queue.next());
                    System.out.println();
                    break;
                case "4" : //queue.dequeue();
                    System.out.println(patientRecord.getPatient(queue.dequeue()).name + " visited");
                    System.out.println();
                    break;
                case "5" : System.exit(0);
                default  : System.out.println("Invalid Entry. Please try again");

            }
        }
    }

    private static void registerPatient(){
        File file = new File("input.txt");

        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String[] line = scan.nextLine().split(",");
                int id = patientRecord.registerPatient(line[0], Integer.parseInt(line[1]));
                queue.enqueue(id);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //check if age is within a range (1 to 100)
    private static boolean validateAge(int age) {
        if(age < 60 || age > 100){
            System.out.println(age + " is not a valid age. Please try again");
            return false;
        }
        return true;
    }

}