package Heap;
// maxHeap rank nodes from larger to smaller. i.e., root is the maxium number
public class maxHeap extends Heap{
      private int operation(int i, int j){
            return i - j;
      }
}
