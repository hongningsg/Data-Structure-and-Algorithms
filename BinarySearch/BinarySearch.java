//search a value in an sorted array, return its index value, -1 for not found.
package BinarySearch;
public class BinarySearch{
      public int search(int[] arr, int key){
            return this.binarySearch(arr, 0, arr.length, key);
      }

      private int binarySearch(int[] arr, int start, int end, int val){
            if (start > end) return -1;
            if (start == end) return arr[start] == val ? start : -1;
            int half = (start + end) / 2;
            if (arr[half] > val) return binarySearch(arr, start, half - 1, val);
            else if (arr[half] < val) return binarySearch(arr, half + 1, end, val);
            return half;
      }
}
