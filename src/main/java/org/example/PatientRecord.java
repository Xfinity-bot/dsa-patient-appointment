package org.example;

public class PatientRecord {
    public class Patient {

        String name;
        int age;
        int id;

        Patient prev;
        Patient next;

        public Patient(String name, int age, int id) {
            this.name = name;
            this.age = age;
            this.id = id;
            this.prev = null;
            this.next = null;
        }
    }

    private Patient head;
    private Patient tail;

    public PatientRecord() {
        head = null;
        tail = null;
    }

    public int registerPatient(String name, int age) {
        int id = (sizeDLL() + 1)*100;
        Patient patient = new Patient(name, age, id);
        if (head == null) {
            head = patient;
            tail = patient;
        } else {
            patient.prev = tail;
            tail.next = patient;
            tail = patient;
        }
        return id;
    }

    public Patient getPatient(int id) {
        Patient patient = head;
        while (patient != null) {
            if (patient.id == id) {
                return patient;
            }
            patient = patient.next;
        }
        return null;
    }

    public void deletePatient(int id) {
        Patient patient = getPatient(id);
        if (patient != null) {
            if (patient.prev != null) {
                patient.prev.next = patient.next;
            } else {
                head = patient.next;
            }
            if (patient.next != null) {
                patient.next.prev = patient.prev;
            } else {
                tail = patient.prev;
            }
        }
    }

    public int sizeDLL() {
        int count = 0;
        Patient patient = head;
        while (patient != null) {
            count++;
            patient = patient.next;
        }
        return count;
    }

    public void displayPatients() {
        Patient patient = head;
        while (patient != null) {
            System.out.println("Patient ID: " + patient.id);
            System.out.println("Name: " + patient.name);
            System.out.println("Age: " + patient.age);
            System.out.println();
            patient = patient.next;
        }
    }


}
