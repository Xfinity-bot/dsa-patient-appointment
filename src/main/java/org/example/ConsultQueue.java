package org.example;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ConsultQueue {
    private int[] heap;
    private int size;
    private int capacity;
    private static PatientRecord record;

    //The constructor initializes the heap array and assigns values to the instance variables. It has a constant time complexity of O(1)
    //@Params capacity which is size and patient record node
    public ConsultQueue(int capacity, PatientRecord record) {
        heap = new int[capacity];
        size = 0;
        this.capacity = capacity;
        this.record = record;
    }

    //Insertion method to priority queue
    //@Params patientId
    public  void enqueue(int patientId) {
        if (size == capacity) {
            System.out.println("Consult queue is full");
        }
        heap[size] = patientId;
        shiftUp(size);
        size++;
    }

    //Returns the name of the next person in the queue
    public String next(){
        if(size==0){
            return "Consult queue is empty";
        }
        return record.getPatient(heap[0]).name;
    }

    //Removes the first patient from the heap
    public int dequeue() {
        if (size == 0) {
            System.out.println( "Consult queue is empty");
        }
        int max = heap[0];
        size--;
        heap[0] = heap[size];
        shiftDown(0,heap);
        return max;
    }
//Returns the size of the list
    public int size() {
        return size;
    }


    //Display  patient record in sequence as per the age the priority queue
    public void display( int size) {
        int tmpSize=size;
        if (tmpSize == 0) {
            System.out.println("Consult queue is empty");
        }
        int[] tempHeap = Arrays.copyOf(heap, size);
        int[] teemp = heapSort(tempHeap,size);
        System.out.println("----------------------------------------------------");
        System.out.printf("%10s %8s %13s %11s", "Sequence No.", "ID", "Name", "Age");
        System.out.println();
        for(int i=size-1 ;i>=0;i--){
            String sequence= Integer.toString(size-i);
            String id =Integer.toString(record.getPatient(teemp[i]).id);
            String name=  record.getPatient(teemp[i]).name;
            String age =Integer.toString(record.getPatient(teemp[i]).age);
            System.out.printf("%10s %10s %13s %10s", sequence, id, name, age);
            System.out.println();
        }
        System.out.println("----------------------------------------------------");
    }
    private void shiftUp(int index) {
        int parent = (index - 1) / 2;
        if (index > 0 && record.getPatient(heap[index]).age > record.getPatient(heap[parent]).age) {
            swap(index, parent);
            shiftUp(parent);
        }
    }

    private int[] shiftDown(int index, int[] heap) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int max = index;
        if (left < size && record.getPatient(heap[left]).age > record.getPatient(heap[max]).age) {
            max = left;
        }
        if (right < size && record.getPatient(heap[right]).age > record.getPatient(heap[max]).age) {
            max = right;
        }
        if (max != index) {
            swap(index, max);
            shiftDown(max,heap);
        }
        return heap;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    public int[] heapSort(int[] arr, int size) {

        int n = arr.length;
        // Heap sort
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child is smaller than root node
        if (left < n && record.getPatient(arr[left]).age > record.getPatient(arr[largest]).age) {
            largest = left;
        }

        // Check if right child is smaller than root node
        if (right < n && record.getPatient(arr[right]).age > record.getPatient(arr[largest]).age) {
            largest = right;
        }

        // If largest is not root, swap root with largest and recursively heapify the affected subtree
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }
}