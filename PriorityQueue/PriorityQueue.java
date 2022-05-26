


import java.util.ArrayList;
import java.util.List;
import java.util.Collection;


public class PriorityQueue<T extends Comparable<T>> {
    private List<T> heap = null;
    private int heapSize = 0;
    private int heapCapacity = 0;

    public PriorityQueue() 
    {
        //constructs a PQ Heap if no initial size is given
        this(1);
    }    
    public PriorityQueue(int sz) 
    {
        // Constructs a PQ Heap with an initial size
        heap = new ArrayList<>(sz);
    }
    public PriorityQueue(T[] elems)
    {
        // Constructs a PQ using heapify; faster than typical construction 
        // ( O(n) vs O(nlog(n)) )
        // isn't really necessary given that we are reading elements from a doc
        // still wanted to add it this way anyway
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<T>(heapCapacity);
        
        for (int i = 0; i < heapSize; i++) heap.add(elems[1]);
        // heapify process
        for (int i = Math.max(0, (heapSize/2)- 1); i >= 0; i--) sink(i);
    }
    public int getSize() 
    {
        // returns the size of the heap 
	return heapSize;
    }
    public T peek() 
    {
        // Returns the highest priority element without removal
        if (isEmpty()) 
            return null;
        return heap.get(0);
    }
    public T poll() 
    {
        // Returns the highest priority element with removal
        return removeAt(0);
    }   
    public void add(T elem) 
    {
        // Adds an element to the queue, will not add if the elem is null, resizes array if capacity is reached (not needed here)
        if (elem == null) 
            throw new IllegalArgumentException();
        if (heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        } else {
        heap.add(elem);
            heapCapacity++;
        }
        swim(heapSize);
        heapSize++;
    }    
    private boolean less(int i, int j) 
    {
        //Compares two elements
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }    
    private void swim(int k) //Bubble up
    {
    // Grabs the index of the next parent node WRT to k
    int parent = (k - 1) / 2;
    // Keep swimming while we have not reached the
    // root and while we're less than our parent.
    while (k > 0 && less(k, parent)) 
    {
        // Exchange k with the parent
        swap(parent, k);
        k = parent;
        // Grab the index of the next parent node WRT to k
        parent = (k - 1) / 2;
    }
    }    
    private void sink(int k) //Bubble down
    {
        while (true) 
        {
            int left = 2 * k + 1; // Left  node
            int right = 2 * k + 2; // Right node
            int smallest = left; // Assume left is the smallest node of the two children

            // Find which is smaller left or right
            // If right is smaller set smallest to be right
            if (right < heapSize && less(right, left)) smallest = right;

            // Stop if we're outside the bounds of the tree
            // or stop early if we cannot sink k anymorre
            if (left >= heapSize || less(k, smallest)) break;

            // Move down the tree following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }
    private void swap(int i, int j) 
    {
        //swaps two given elements
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }       
    public boolean isMinHeap(int k) 
    {
        // Just used to check if heap was formed correctly
        // If we are outside the bounds of the heap return true
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // Make sure that the current node k is less than
        // both of its children left, and right if they exist
        // return false otherwise to indicate an invalid heap
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }
    public boolean isEmpty() 
    {
        return heapSize == 0;
    }
    private T removeAt(int i) 
    {
        if (isEmpty()) 
            return null;

        heapSize--;
        T removed_data = heap.get(i);
        swap(i, heapSize);

        // nullify the value
        heap.set(heapSize, null);

        // Check if the last element was removed
        if (i == heapSize) 
            return removed_data;
        T elem = heap.get(i);

        // Try sinking element
        sink(i);

        // If sinking did not work try swimming
        if (heap.get(i).equals(elem)) swim(i);
        return removed_data;
    }
}
