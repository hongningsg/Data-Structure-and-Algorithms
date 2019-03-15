package Sorting;
public class InsertionSort{
      public void sort(int[] arr){
            for (int i = 0; i < arr.length; i++) {
                int insertVal = arr[i];
                int pos = binarySearch(arr, 0, i, arr[i]);
                this.shift(arr, pos, i);
                arr[pos] = insertVal;
            }
      }

      public void sort(int[] arr, boolean binary){
            if(!binary){
                for (int i = 0; i < arr.length; i++) {
                    int insertVal = arr[i];
                    int pos = backwardSearch(arr, i, arr[i]);
                    this.shift(arr, pos, i);
                    arr[pos] = insertVal;
                }
            }
            else this.sort(arr);
      }

      private int binarySearch(int[] arr, int start, int end, int val){
            if (start > end) return start;
            if (start == end) return arr[start] > val ? start : ++end;
            int half = (start + end) / 2;
            if (arr[half] > val) return binarySearch(arr, start, half - 1, val);
            else if (arr[half] < val) return binarySearch(arr, half + 1, end, val);
            return half;
      }

      private int backwardSearch(int[] arr, int curr, int val){
            while (curr > 0 && arr[curr] < arr[curr-1]) --curr;
            return curr;
      }

      private void shift(int[] arr, int start, int end){
            for (int i = end; i > start; --i) {
                arr[i-1] = arr[i];
            }
      }
}
