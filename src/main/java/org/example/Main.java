package org.example;

import java.util.Scanner;

public class Main {

    public void driver(){

    }

    public static void main(String[] args){
        PatientRecord patientRecord = new PatientRecord();
        ConsultQueue queue = new ConsultQueue(100,patientRecord);
        Scanner sc=new Scanner(System.in);
        int x ;

        // Add some patient records
        int id1 = patientRecord.addPatient("Alice", 40);
        queue.enqueue(id1);
        int id2 = patientRecord.addPatient("Bob", 80);
        queue.enqueue(id2);
        int id3 = patientRecord.addPatient("Charlie", 75);
        queue.enqueue(id3);

        while(true){
            System.out.println("1.Display");
            System.out.println("2.Insert Patient Data");
            System.out.println("3.Display Next Patient");
            System.out.println("4.Delete Patient");
            System.out.println("5.Exit");
            x= sc.nextInt();
            switch(x){
                case 1 : queue.display( queue.size());
                    break;
                case 2 :
                    sc.nextLine();      //consume new-line leftover
                    System.out.println("Enter Patient Name");
                    String name = sc.nextLine();
                    System.out.println("Enter Patient Age");
                    if(sc.hasNextInt()){                //validate if input is integer
                        int age = sc.nextInt();
                        boolean isValidAge = validateAge(age);
                        if(isValidAge) {
                            queue.enqueue(patientRecord.addPatient(name, age));
                            System.out.println("Added Successfully");
                        }
                    }else{
                        String invalidAge = sc.nextLine();
                        System.out.println(invalidAge + " is not a valid age. Please try again");
                    }
                    break;
                case 3 : System.out.println("The next patient is " + queue.next());
                    break;
                case 4 : //queue.dequeue();
                    System.out.println(patientRecord.getPatient(queue.dequeue()).name + " visit");
                    break;
                case 5 : System.exit(0);
                default:System.out.println("Invalid Entry");

            }
        }
    }

    //check if age is within a range (1 to 100)
    private static boolean validateAge(int age) {
        if(age < 1 || age > 100){
            System.out.println("Please enter a valid age");
            return false;
        }
        return true;
    }

}