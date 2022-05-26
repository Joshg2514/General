//Student Name: Joshua Guillot
//LSU ID: 893430281
//TA: Zachary Zaulkner
package project6;

import java.util.Arrays;
import java.util.Scanner;


public class Project6 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");  
        int arraysize = scan.nextInt();
        System.out.println("");
        int[] array = createArray(arraysize);
        arrayTest(array);
    }

    private static int[] createArray(int arraysize) 
    {
        int[] array = new int[arraysize];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*1001);  
        }
        return array;
    }

    private static void arrayTest(int[] array) 
    {  
        for (int i = 1; i <= 6; i++) {
            int[] testarray = Arrays.copyOf(array, array.length);
            long start = System.currentTimeMillis();
            switch(i){
                case 1:
                    SortingAlgorithms.BubbleSort(testarray);
                    System.out.print("Bubble sort: ");
                    break;
                case 2:
                    SortingAlgorithms.BubbleSortCS(testarray);
                    System.out.print("Bubble sort short circuit: ");
                    break;
                case 3:
                    SortingAlgorithms.SelectionSort(testarray);
                    System.out.print("Selection Sort: ");
                    break;
                case 4:
                    SortingAlgorithms.JavaSort(testarray);
                    System.out.print("Java Sort: ");
                    break;
                case 5:
                    SortingAlgorithms.InsertionSort(testarray);
                    System.out.print("Insertion Sort: ");
                    break;
                case 6:
                    SortingAlgorithms.CocktailSort(testarray);
                    System.out.print("Cocktail Sort: ");
                    break;
            }
            long finish = System.currentTimeMillis();
            long elapsed = finish - start;
            System.out.println(elapsed + " ms");
            testarray = array;
        }
    }   
}
