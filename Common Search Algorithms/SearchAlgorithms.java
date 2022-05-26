//Student Name: Joshua Guillot
//LSU ID: 893430281
//TA: Zachary Zaulkner
package project7;


class SearchAlgorithms {
    

    public static void LinearSearch(int[] arr, int target) 
    {     
        int count = 0;
        System.out.print("Linear search: ");
        for (int i = 0; i <= arr.length; i++)
        {
            count++; 
        
            if (arr[i] == target)
            {
                System.out.print("Target " + target + " found. ");
                System.out.println("Number of steps is " + count);
                break;
            }
            else if (count == (arr.length ))    
            {       
                System.out.print("Target " + target + " not found. ");
                System.out.println("Number of steps is " + count);
                break;
            }
        }
    }

    public static void BinarySearch(int[] sortedArray, int target) 
    {
        int low = 0;
        int high = (sortedArray.length - 1);
        int index = Integer.MAX_VALUE;   
        int count = 0;
        boolean found = false;
        
        System.out.print("Binear search: ");
        while (low <= high) 
        {
            count++;  
            int mid = (low + high) / 2;
            if (sortedArray[mid] < target) 
                low = mid + 1;
      
            else if (sortedArray[mid] > target) 
                high = mid - 1;
    
            else if (sortedArray[mid] == target) 
            {
                index = mid;
                System.out.print("Target " + target + " found. ");
                found = true;
                break;
            }
        }
        
        if (found == false)
            System.out.print("Target " + target + " not found. ");
            System.out.println("Number of steps is " + count);
    }

    public static int[] populateArray(int[] array) 
    {  
        for (int i = 0; i < array.length; i++) 
        {
            array[i] = (int)(Math.random()*1001);  
        }
        return array;
    }
    
}


