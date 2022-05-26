
package binarytreeex;

import java.util.Iterator;


public class BinaryTreeDriver {

   
    public static void main(String[] args) {
     /*
       prints current, then travels left before right
       preorder(node):
        if node == null: return
        print(node.value)
        preorder(node.left)
        preorder(node.right)
        
       prints bottom left most, then right (in binary tree it print the value in order (increasing))
       inorder(node):
        if node == null: return
        inorder(node.left)
        print(node.value)
        inorder(node.right)
       
       prints leftmost, then rightmost of left most
       postorder(node): 
        if node == null: return
        postorder(node.left)
        postorder(node.right)
        print(node.value)
        
       level order traversal; print the nodes as the appear at the top, left to right, (breadth first search) you can do this by using a queue we add the root, then the left child, them the right child the poll the first up in the queue
        levelorder cant be done recursively, it must be itterative
  
        */
     BinaryTree tree = new BinaryTree();
     tree.insert(5);
     tree.insert(20);
     tree.insert(3);
     tree.insert(4);
     tree.insert(9);
     tree.remove(4);
     tree.insert(7);
     
     
//     Iterator level = tree.traverse(TreeTraversalOrder.LEVEL_ORDER);
//        int[] intArray = new int[tree.size()];
//        int i = 0;
//         while(level.hasNext()) {
//         Object element = level.next();
//         intArray[i] = (int)element;
//             System.out.println(intArray[i] + " new ");
//         i++;
//      }
//      System.out.println(); 
//     
//         Iterator inorder = tree.traverse(TreeTraversalOrder.IN_ORDER);
//        
//         while(inorder.hasNext()) {
//         Object element = inorder.next();
//         System.out.print(element + " ");
//      }
//      System.out.println();   
     
    tree.printLevelOrder();
    tree.printInOrder(); 
      
        System.out.println((1 == 2 || 1 == 1));
    }
    
}
