//Student Name: Joshua Guillot
//LSU ID: 893430281

package javabonus;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;


public class JavaBonus {

    public static void main(String[] args) throws FileNotFoundException {
         
    {
        File inFile = new File("Mercury.txt"); 
        int[][] square = MagicSquareBuilder(inFile);
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                System.out.print(square[j][i] + " ");               
            }
            System.out.println("");
        }
        System.out.println((MagicSquare(square)));
        
        
        File inFile2 = new File("luna.txt"); 
        int[][] square2 = MagicSquareBuilder(inFile2);
        for (int i = 0; i < square2.length; i++) {
            for (int j = 0; j < square2.length; j++) {
                System.out.print(square2[j][i] + " ");                
            }
            System.out.println("");
        }
        System.out.println((MagicSquare(square2)));

    }
    }

    public static boolean MagicSquare(int[][] square) {
        int j = 0;
        int dsum = 0;
        int sum;
        int sum2;
        int sum3 = 0;
		int diag2 = 0;
        
        do{
            sum = 0;
            sum2 = 0;
        for (int i = 0; i < square.length; i++) {
            sum += square[i][j];
            sum2 += square[j][i];
            
            if(j == 0)
                dsum = sum;
        }
        sum3 += square[j][j];
        diag2 += square[j][square.length - j -1];
        j++;
        }while(sum == sum2 && dsum == sum && j < square.length);
        return(dsum == sum2 && dsum == sum && dsum == sum3 && dsum == diag2);
    }
    public static int[][] MagicSquareBuilder(File inFile) throws FileNotFoundException {
        Scanner in = new Scanner(inFile);
        int[][] square = null;
        int j = 0;
                while(in.hasNextLine())
        {           
            String line = in.nextLine(); 
            String[] fields = line.split("\\t");
            
            if(square == null)
                square = new int[fields.length][fields.length];
            for (int i = 0; i < fields.length; i++) {
                square[i][j] = Integer.parseInt(fields[i]);
            }
            j++;
        }
        return square;
    }
    
}
