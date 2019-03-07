package Heap;
// minHeap rank nodes from smaller to larger. i.e., root is the minium number
public class minHeap extends Heap{
      private int operation(int i, int j){
            return j - i;
      }
}
