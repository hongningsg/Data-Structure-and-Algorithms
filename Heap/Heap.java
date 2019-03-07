package Heap;
//Heap implemented by array, default as maxheap, use Heap h = new minHeap() to get a min heap.
//usage: top() returns the root node, insert(int) will insert an integer to the heap
//       extract() will pop out the root, isEmpty() to check if the heap is empty, size() to get size
//       maxLen() to get the maxium capacity of the heap.
public class Heap{
      private int[] heap;
      private int size;
      private int capacity;

      public Heap(int capacity){
            this.capacity = capacity;
            this.size = 0;
            heap = new int[this.capacity];
      }

      private int parent(int pos){
            return (pos - 1) / 2;
      }

      private int left(int pos){
            return pos * 2 + 1;
      }

      private int right(int pos){
            return pos * 2 + 2;
      }

      private void swap(int i, int j){
            int temp = this.heap[i];
            this.heap[i] = this.heap[j];
            this.heap[j] = temp;
      }

      private int operation(int i, int j){
            return i - j;
      }

      private void heapify(int pos){
            int l = left(pos);
            int r = right(pos);
            int root = pos;
            if (l < this.size && operation(this.heap[l], this.heap[root]) > 0){
                  root = l;
            }
            if (r < this.size && operation(this.heap[r], this.heap[root]) > 0){
                  root = r;
            }
            if (root != pos){
                  swap(root, pos);
                  heapify(root);
            }
      }

      public void insert(int val){
            heap[this.size++] = val;
            int pos = size - 1;
            while (parent(pos) >= 0 && operation(heap[pos], heap[parent(pos)]) > 0){
                  swap(pos, parent(pos));
                  pos = parent(pos);
            }
      }

      public int extract(){
            int pop = heap[0];
            heap[0] = heap[this.size--];
            heapify(0);
            return pop;
      }

      public int top(){
            return heap[0];
      }

      public int size(){
            return this.size;
      }

      public boolean isEmpty(){
            return (this.size == 0);
      }

      public int maxLen(){
            return this.capacity;
      }
}
