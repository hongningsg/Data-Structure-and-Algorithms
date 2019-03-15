package Sorting;
//QuickSort in O(nlogn) avg time (O(n) in best, O(n^2) in worst case) and O(1) space
//choose pivot randomly
import java.util.Arrays;
import java.util.Random;
public class QuickSort{
      public void sort(int[] arr){
          quicksort(arr, 0, arr.length - 1);
      }

      private void quicksort(int[] arr, int low, int high){
            if (low < high){
                  int p = partition(arr, low, high);
                  quicksort(arr, low, --p);
                  quicksort(arr, ++p, high);
            }
      }

      private void swap(int[] arr, int i, int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
      }

      private int partition(int[] arr, int low, int high){
            int pivot = Random().nextInt(high - low + 1) + low;
            swap(arr, low, pivot);
            pivot = low++;
            for (int i = low; i <= high; i++) {
                  if (arr[i] < arr[pivot]) swap(arr, i, low++);
            }
            swap(arr, pivot, --low);
            return low;
      }
}
