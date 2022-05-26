//Student Name: Joshua Guillot
//LSU ID: 893430281
//TA: Zachary Zaulkner
package project6;

import java.util.Arrays;

public class SortingAlgorithms {

    public static void BubbleSort(int[] array){
        for(int i = 0; i < array.length-1; i++)
        {
            for(int j = 0; j < array.length-i-1; j++)
                if(array[j] > array[j+1])
                {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;  
                }
        }    
    }
    
    public static void BubbleSortCS(int[] array){
        for(int i = 0; i < array.length-1; i++)
        {
            boolean swap = false;
            for(int j = 0; j < array.length-i-1; j++)
            {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    swap = true;}
            }
            if(!swap)
            break;
        }
    }
    
    public static void SelectionSort(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            int index = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[index])
                    index = j;
                int smallerNumber = array[index];
                array[index] = array[i];
                array[i] = smallerNumber;
        }

    }   
    
    public static void JavaSort(int[] array){
        Arrays.sort(array);
}  
    
    public static void InsertionSort(int[] array){
        int i, key, j;
        for (i = 1; i < array.length; i++)
        {
            key = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > key)
            {
                array[j + 1] = array[j];
                j = j - 1;
            }
        array[j + 1] = key;
        }  
    }   
   
    public static void CocktailSort(int[] array){
        boolean swapped = true;
        int start = 0;
        int end = array.length;
 
        while (swapped)
        {
            // reset the swapped flag on entering the
            // loop, because it might be true from a
            // previous iteration.
            swapped = false;
 
            // loop from bottom to top same as
            // the bubble sort
            for (int i = start; i < end - 1; ++i) 
            {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
 
            // if nothing moved, then array is sorted.
            if (!swapped)
                break;
 
            // otherwise, reset the swapped flag so that it
            // can be used in the next stage
            swapped = false;
 
            // move the end point back by one, because
            // item at the end is in its rightful spot
            end = end - 1;
 
            // from top to bottom, doing the
            // same comparison as in the previous stage
            for (int i = end - 1; i >= start; i--) 
            {
                if (array[i] > array[i + 1]) 
                {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
 
            // increase the starting point, because
            // the last stage would have moved the next
            // smallest number to its rightful spot.
            start = start + 1;
        }
}   
  
}
