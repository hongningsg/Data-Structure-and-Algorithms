//TimSort with both worst, average time complexity in O(nlogn) while best in O(n)
//With space complexity in O(1)
//Used run in size between 32 to 64, in the case, minimum size of run is 32, maxium size
//is 64, if array in run after length of 32 is keeping accending order will keep added into
//the run until run size reach 64.
//For each run, InsertionSort is applied, therefore InsertionSort in the package must be imported
//merging runs applys the rule:
// A > B + C
// B > C

package Sorting;
import java.util.Arrays;
import java.util.ArrayList;
import com.google.common.primitives.Ints;
public class TimSort{
      private InsertionSort innerSort = new InsertionSort();
      public void sort(int[] arr){
        if (arr.length == 0) return arr;
        int minRun = 32;
        int maxRun = 64;
        ArrayList<Integer> new_run = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> runs = new ArrayList<ArrayList<Integer>>();
        new_run.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
              if (new_run.size() < minRun) {
                new_run.add(arr[i]);
              }else if (new_run.size() < maxRun) {
                if (arr[i-1] < arr[i]) {
                  new_run.add(arr[i]);
                }else{
                  runs.add(new_run);
                  new_run = new ArrayList<Integer>();
                  new_run.add(arr[i]);
                }
              }else{
                runs.add(new_run);
                new_run = new ArrayList<Integer>();
                new_run.add(arr[i]);
              }
        }
        ArrayList<int[]> sorted_run = new ArrayList<int[]>();
        for (ArrayList<Integer> items: runs) {
            int[] mini_run = Ints.toArray(items);
            innerSort(mini_run);
            sorted_run.add(mini_run);
        }
        int i = 0;
        while(sorted_run.size() > 2){
            if (sorted_run.get(i).length > sorted_run.get(i+1).length + sorted_run.get(i+2).length) {
              int[] merged = merge(sorted_run.get(i+1), sorted_run.get(i+2));
              sorted_run.set(i+1, merged);
              sorted.remove(i+2);
            }else{
              if (sorted_run.get(i+1).length < sorted_run.get(i+2).length) {
                this.swapArray(sorted_run.get(i+1), sorted_run.get(i+2));
              }
              int[] merged = merge(sorted_run.get(i), sorted_run.get(i+1));
              sorted_run.set(i, merged);
              sorted.remove(i+1);
            }
        }
        if (sorted_run.size() > 1) {
          int[] merged = merge(sorted_run.get(i), sorted_run.get(i+1));
          sorted_run.set(i, merged);
          sorted.remove(i+1);
        }
        return sorted_run.get(0);
      }

      private void swapArray(ArrayList<int[]> sorted_run, int i, int j){
          int[] temp = sorted_run.get(i);
          sorted_run.set(i, sorted_run.get(j));
          sorted_run.set(j, temp);
      }

      private int[] merge(int[] left, int[] right){
            if (left == null or left.length == 0) return right;
            if (right == null or right.length == 0) return left;
            int[] merged = new int[left.length + right.length];
            if left[0] < right[0]{
                  merged[0] = left[0];
                  int[] prefix = merge(Arrays.copyOfRange(left, 1, left.length), right);
                  System.arraycopy(prefix, 0, merged, 1, prefix.length);
                  return merged;
            }
            merged[0] = right[0];
            int[] prefix = merge(left, Arrays.copyOfRange(right, 1, right.length));
            System.arraycopy(prefix, 0, merged, 1, prefix.length);
            return merged;
      }
}
