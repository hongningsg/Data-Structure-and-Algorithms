//Counting sort execute in O(n) time and O(n) extra space, actually worst case is O(2*n+2147483647) but still O(n)
//Only use Counting sort if all numbers in your array is natrual integer(i.e., 0, 1, 2 ... 2147483642)
//Yes, maxium number in this is 2147483642 due to java only support array length in math.MAX_VALUE(2147483647) - 5
package Sorting;
public class CountSort{
    public void sort(int[] arr){
        int large = this.findMax(arr);
        try{
            int[] count = new int[large];
        }catch (Exception e){
          System.out.println("Max number in array should less than 2147483643.")
        }
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            for (j = 0; j < count[i]; j++) {
                arr[index] = i;
                index++;
            }
        }
    }

    private int findMax(int[] arr){
        int large = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > large) {
                large = arr[i];
            }
        }
        return large;
    }
}
