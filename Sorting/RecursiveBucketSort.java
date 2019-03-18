//Came up with this bucket sort myself, I believe there might be someone already had this thought or implemention
//But I cannot find any sourse of this thought on algorithm books or internet
//algorithm:
//  create buckets for numbers separate by number of digits of the number:
//        i.e., 1 in bucket 0, 12 in bucket 1, 123 in bucket 2 etc.
//        use log_10 to determine with bukect will the number belong to
//        recursively sort the numbers in bucket:
//              base case: if sorting on ones digit, use bucket sort
//              Induction:
//                    Create 10 buckets and fill numbers into them regarding significant digits
//                    Sort significant digit recursively.
//                    (For instance, 123 and 321, first look at hundreds digits, put 123 in bucket 1, 321 in bucket 3
//                    Next level of recursion will look at tens digits put 123 in bucket 2, 321 in bucket 2 etc.)
//                    Merge all buckets level by level
//  This sort has best time in O(n), average and worst in O(nlogn), space complexity in O(n) 
package Sorting;
import java.lang.Math;
import java.util.ArrayList;

public class RecursiveBucketSort{
  public void sort(int[] arr){
      ArrayList<Integer> positive = new ArrayList<Integer>();
      ArrayList<Integer> negative = new ArrayList<Integer>();
      for (int i = 0; i < arr.length; i++) {
          if (arr[i] < 0) {
              negative.add(-1 * arr[i]);
          }else{
              positive.add(arr[i]);
          }
      }

    positive = this.sortPositive(positive);

    negative = this.sortNegative(negative);
    int i = 0;
    for (Integer num : negative) {
        arr[i++] = num;
    }
    for (Integer num : positive) {
        arr[i++] = num;
    }
  }

  private int HasDigits(int x){
      return (int)Math.log10(x);
  }

  private int maxDigits(ArrayList<Integer> arr){
      int maxNum = 0;
      for (int i = 0; i < arr.size(); i++) {
          if (arr.get(i) > maxNum) maxNum = arr.get(i);
      }
      return this.HasDigits(maxNum);
  }

  private ArrayList<Integer> sortPositive(ArrayList<Integer> arr){
      int BucketLen = this.maxDigits(arr) + 1;
      ArrayList<ArrayList<Integer>> DigitsBucket = new ArrayList<ArrayList<Integer>>();
      for (int i = 0; i < BucketLen; i++) {
          ArrayList<Integer> Bucket = new ArrayList<Integer>();
          DigitsBucket.add(Bucket);
      }
      for (int i = 0; i < arr.size(); i++) {
          int num = arr.get(i);
          int idx = this.HasDigits(num);
          DigitsBucket.get(idx).add(num);
      }
      ArrayList<Integer> sorted = new ArrayList<Integer>();
      for (int i = 0; i < BucketLen; i++) {
          ArrayList<Integer> bucket = this.RecursiveSort(DigitsBucket.get(i), i, i);
          for (Integer num : bucket) {
              sorted.add(num);
          }
      }
      return sorted;
  }

  private ArrayList<Integer> sortNegative(ArrayList<Integer> arr){
      ArrayList<Integer> sorted = this.sortPositive(arr);
      ArrayList<Integer> negSorted = new ArrayList<Integer>();
      for (int i = sorted.size() - 1; i > -1; i--) {
          negSorted.add(-1 * sorted.get(i));
      }
      return negSorted;
  }

  private ArrayList<Integer> RecursiveSort(ArrayList<Integer> arr, int level, int baseLevel){
      if (arr.size() == 0) return arr;
      if (level == 0){
          return this.BucketSort(arr, baseLevel);
      }
      ArrayList<ArrayList<Integer>> DigitsBucket = new ArrayList<ArrayList<Integer>>();
      for (int i = 0; i < 10; i++) {
          ArrayList<Integer> Bucket = new ArrayList<Integer>();
          DigitsBucket.add(Bucket);
      }
      for (Integer num : arr) {
          int base = num;
          for (int i = baseLevel; i > level; i--) {
              base = (int) (base % (int)(Math.pow(10, i)));
          }
          int idx = (int) (base / (int)(Math.pow(10, level)));
          DigitsBucket.get(idx).add(num);
      }

      for (int i = 1; i < 10; i++) {
          DigitsBucket.set(i, RecursiveSort(DigitsBucket.get(i), level - 1, baseLevel));
      }
      ArrayList<Integer> SortedBucket = new ArrayList<Integer>();
      for (int i = 1; i < 10; i++) {
          for (Integer num :  DigitsBucket.get(i)) {
              SortedBucket.add(num);
          }
      }
      return SortedBucket;
  }

  private ArrayList<Integer> BucketSort(ArrayList<Integer> arr, int baseLevel){
      if (arr.size() == 0) return arr;
      int[] buckets = new int[10];
      int basicNum = arr.get(0);
      int base = basicNum;
      for (int i = baseLevel; i > 0; i--) {
          base = (int) (base % (int)(Math.pow(10, i)));
      }
      basicNum -= base;
      for (Integer num : arr) {
          base = num;
          for (int i = baseLevel; i > 0; i--) {
              base = (int) (base % (int)(Math.pow(10, i)));
          }
          buckets[base]++;
      }
      ArrayList<Integer> sorted = new ArrayList<Integer>();
      for (int i = 0; i < 10; i++) {
          while (buckets[i]-- > 0) {
              sorted.add(basicNum + i);
          }
      }
      return sorted;
  }
}
