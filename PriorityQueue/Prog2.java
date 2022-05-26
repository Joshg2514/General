
package prog2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Prog2 {

    public static void main(String[] args) throws FileNotFoundException 
    {
	Scanner scan = new Scanner(new File("input.txt"));
        String a = scan.next();
        PriorityQueue q = new PriorityQueue();
       
        char[] charactersList = a.toCharArray();

        for (int i = 0; i < charactersList.length; i++) {
            if (charactersList[i] == '!') {
                System.out.print(q.poll());               
            } else {               
                q.add(charactersList[i]);
            }
        }
        System.out.println();
    }

}

