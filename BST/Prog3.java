
import java.io.*;
import java.util.Scanner;


public class Prog3 {
public static void main(String[] args) throws FileNotFoundException, Exception {
    
    FileReader r = new FileReader(new File("input.txt"));
    Scanner input = new Scanner(r);
    BST tree = new BST();
    
    while (input.hasNextLine()) {
		String s = input.nextLine();
                if(s.length() == 0){
                    System.out.println("Tree Done");
                    break;
                }
		String[] p = s.split("\\s+");
		String p0 = p[0]; // insert or delete
		int p1 = Integer.parseInt(p[1]); // integer
		
                if(p0.equals("insert"))
                    tree.insert(p1);
		else
                    tree.remove(p1);
                    
    }

//    tree.insert(39);
//    tree.insert(51);
//    tree.insert(33);
//    tree.insert(65);
//    tree.insert(58);
//    tree.insert(36);
//    tree.insert(20);
//    tree.insert(24);
//    tree.insert(11);
//    tree.remove(20);
//    tree.remove(33);
   tree.printLevelOrder();
   tree.printInOrder();

}
}
