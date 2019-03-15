package Sorting;
//MergeSort with O(n) space complexity and 0(nlogn) time complexity
import java.util.Arrays;
public void MergeSort(int[] arr){
      if (arr.length > 1){
            int midpoint = (int)(arr.length/2);
            int[] LeftArray = Arrays.copyOfRange(arr, 0, midpoint);
            int[] RightArray = Arrays.copyOfRange(arr, midpoint, arr.length);
            MergeSort(LeftArray);
            MergeSort(RightArray);
            int i = 0, j = 0, index = 0;
            while (i < LeftArray.length && j < RightArray.length){
                  if (LeftArray[i] < RightArray[j]){
                        arr[index] = LeftArray[i];
                        i++;
                  }else{
                        arr[index] = RightArray[j];
                        j++;
                  }
                  index++;
            }
            while (i < arr.length){
                  arr[index] = LeftArray[i];
                  i++;
                  index++;
            }
            while (j < arr.length){
                  arr[index] = RightArray[j];
                  j++;
                  index++;
            }
      }
}
