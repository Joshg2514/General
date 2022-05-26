//Student Name: Joshua Guillot
//LSU ID: 893430281
//TA: Zachary Zaulkner
package project7;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Project7 {

   
    public static void main(String[] args) {
       System.out.print("Enter array size: ");
       Scanner in = new Scanner(System.in);    
       int size = in.nextInt();
       int[] array = new int[size];
       SearchAlgorithms.populateArray(array);
       Random rand = new Random();
       int target = rand.nextInt(1001);
       SearchAlgorithms.LinearSearch(array, target);
       Arrays.sort(array);
       SearchAlgorithms.BinarySearch(array, target);
       System.out.println("");
    }
    
}
