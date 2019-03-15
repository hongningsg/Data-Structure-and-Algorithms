package Sorting;
//HeapSort takes O(nlogn) time and O(1) space
//Build max heap and extract root element one by one with keeping it max heap!
public class HeapSort{
      public void sort(int[] arr){
            buildMaxHeap(arr);
            extract(arr);
      }

      private int parentOf(int i) { return (i - 1) / 2;}
      private int left(int i) { return i * 2 + 1;}
      private int right(int i) { return i * 2 + 2;}
      private void swap(int[] arr, int i, int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
      }
      private void maxHeapify(int[] arr, int i){
            int l = left(i);
            int r = right(i);
            int root = i;
            if (l < arr.length && arr[l] > arr[root]){
                  root = l;
            }
            if (r < arr.length && arr[r] > arr[root]){
                  root = r;
            }
            if (root != i){
                swap(arr, i, root);
                maxHeapify(arr, root);
            }
      }
      private void buildMaxHeap(int arr[]){
            for ( int i = parentOf(arr.length - 1); i > -1; i--){
                  maxHeapify(i);
            }
      }
      private void extract(int arr[]){
            for (int i = arr.length - 1; i > 0; i--) {
                  swap(arr, i, 0);
                  maxHeapify(arr, 0);
            }
      }
}
