
package binarytreeex;

import java.util.Iterator;


public class BinaryTree <T extends Comparable <T>>{

   private int nodeCount = 0;
   private Node root = null;

    public BinaryTree() {
    }

    void printInOrder() {
       Iterator inorder = this.traverse(TreeTraversalOrder.IN_ORDER);
        while(inorder.hasNext()) {
        Object element = inorder.next();
        System.out.print(element + " ");
      }
        System.out.println("");
    }
   
   private class Node {
       
       T data;
       Node left, right; 

       public Node(Node left, Node right, T data) 
       {
           this.data = data;
           this.left = left;
           this.right = right;
       }
   }
   
/* Function to line by line print level order traversal a tree*/
 void printLevelOrder()
{
    
    int h = height(root);
    int i;
    for (i=1; i<=h; i++)
    {
        printGivenLevel(root, i);
        System.out.println();
    }
}
/* Print nodes at a given level */
void printGivenLevel(Node root, int level)
{
    if (root == null)
        return;
    if (level == 1)
        System.out.print(root.data + " ");
    else if (level > 1)
    {
        printGivenLevel(root.left, level-1);
        printGivenLevel(root.right, level-1);
    }
}
   private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
   private Node findMax(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }
   private Node insert(Node node, T elem){
       if (node == null){
           node = new Node(null, null, elem);
       } else {
           if (elem.compareTo(node.data) < 0) {
              node.left = insert(node.left, elem); 
           } else {
              node.right = insert(node.right, elem);
           }
       }
       return node;
    }
   private Node remove(Node node, T elem){
        if (node == null)
            return null;
        
        int comp = elem.compareTo(node.data);
        
        if (comp < 0)
            node.left = remove(node.left, elem);
        else if (comp > 0)
            node.right = remove(node.right, elem);
        else {
           if (node.left == null){
              Node rightChild = node.right;
              
              node.data = null;
              node = null;
              
              return rightChild;
           } else if (node.right == null){
               Node leftChild = node.left;
               
               node.data = null;
               node = null; 
               
               return leftChild;
           } else {      
               Node temp = findMin(node.right);
               
               node.data = temp.data;
               
               node.right = remove(node.right, temp.data);
           }
        }
       return node;
   }
   private boolean contains(Node node, T elem) {
       if (node == null) return false;
       
       int comp = elem.compareTo(node.data);
       
       if (comp < 0)
           return contains(node.left, elem);
       
       else if (comp > 0)
           return contains(node.right, elem);
       
       else
           return true;  
   }
   
   public boolean insert(T elem){
       if(contains(elem)){
           return false;
       }
       else {
           root = insert(root, elem);
           nodeCount++;
           return true;
       }
   }
   public boolean remove(T elem){
       if(contains(elem)){
           root = remove(root, elem);
           nodeCount--;
           return true;
        }
      return false;
   }
   public boolean contains(T elem) {
       return contains(root, elem);
   }
   public boolean isEmpty() {
    return size() == 0;
    }
   public int size() {
    return nodeCount;
    }
   public int height() {
    return height(root);
    }
  // Recursive helper method to compute the height of the tree
  private int height(Node node) {
    if (node == null) return 0;
    return Math.max(height(node.left), height(node.right)) + 1;
  }

 
   
//iterators (preorder, inorder, postorder, levelorder)
   public java.util.Iterator<T> traverse(TreeTraversalOrder order) {
       return switch (order) { // this is an 'enhanced switch statement'  looks much cleaner than the switch statement I had before
           case PRE_ORDER -> preOrderTraversal();
           case IN_ORDER -> inOrderTraversal();
           case POST_ORDER -> postOrderTraversal();
           case LEVEL_ORDER -> levelOrderTraversal();
           default -> null;
       };
  } 
   private java.util.Iterator<T> preOrderTraversal() {

    final int expectedNodeCount = nodeCount;
    final java.util.Stack<Node> stack = new java.util.Stack<>();
    stack.push(root);

    return new java.util.Iterator<T>() {
      @Override
      public boolean hasNext() {
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        Node node = stack.pop();
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
   private java.util.Iterator<T> inOrderTraversal() {

    final int expectedNodeCount = nodeCount;
    final java.util.Stack<Node> stack = new java.util.Stack<>();
    stack.push(root);

    return new java.util.Iterator<T>() {
      Node trav = root;

      @Override
      public boolean hasNext() {
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {

        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();

        // Dig left
        while (trav != null && trav.left != null) {
          stack.push(trav.left);
          trav = trav.left;
        }

        Node node = stack.pop();

        // Try moving down right once
        if (node.right != null) {
          stack.push(node.right);
          trav = node.right;
        }

        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }   
   private java.util.Iterator<T> postOrderTraversal() {
    final int expectedNodeCount = nodeCount;
    final java.util.Stack<Node> stack1 = new java.util.Stack<>();
    final java.util.Stack<Node> stack2 = new java.util.Stack<>();
    stack1.push(root);
    while (!stack1.isEmpty()) {
      Node node = stack1.pop();
      if (node != null) {
        stack2.push(node);
        if (node.left != null) stack1.push(node.left);
        if (node.right != null) stack1.push(node.right);
      }
    }
    return new java.util.Iterator<T>() {
      @Override
      public boolean hasNext() {
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        return root != null && !stack2.isEmpty();
      }

      @Override
      public T next() {
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        return stack2.pop().data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  } 
   private java.util.Iterator<T> levelOrderTraversal() {

    final int expectedNodeCount = nodeCount;
    final java.util.Queue<Node> queue = new java.util.LinkedList<>();
    queue.offer(root);

    return new java.util.Iterator<T>() {
      @Override
      public boolean hasNext() {
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        return root != null && !queue.isEmpty();
      }

      @Override
      public T next() {
          
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        Node node = queue.poll();
        if (node.left != null) queue.offer(node.left);       
        if (node.right != null){ queue.offer(node.right);
        }   
        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
      
    };
       
  } 
  
}
