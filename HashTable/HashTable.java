import java.util.ArrayList;
import java.lang.math;
class Pair<K, V> {
      final K key;
      V value;
      Pair<K, V> next;

      public Pair(K key, V value, Entry<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
      }
}

public class HashMap<K, V> {
        private Pair<K, V>[] table;
        private int capacity = 4;
        private int len = 0;

        public HashMap(int capacity){
              int shifter = (int)((log((double)capacity) - 1)/log((double)2)) + 1;
              if (shifter < 4) shifter = this.capacity;
              this.capacity = 1 << shifter;
              this.table = new Pair[this.capacity];
        }

        public HashMap(){
              this.HashMap(this.capacity);
        }

        private int HashFunction(double d){
              return this.HashFunction(Double.toString(d));
        }

        private int HashFunction(int i){
              return this.HashFunction(Integer.toString(i));
        }

        private int HashFunction(String s){
              int sum = 0;
              for (int i = 0; i < s.length(); i++){
                    sum += (int)s.charAt(i);
              }
              int divider = s.length();
              if (divider > this.capacity){
                divider = 0;
              }
              return sum % divider;
        }

        public void put(K key, V value){
              int HashVal = this.HashFunction(key);
              Pair<K, V> pair = new Pair<>(key, value, null);
              Pair<K, V> currPair = this.table[HashVal];
              if(currPair == null){
                    this.table[HashVal] = pair;
                    this.len++;
              }else{
                    while(currPair.next != null){
                          if(currPair.key.equals(key)){
                                currPair.value = value;
                                return;
                          }
                          currPair = currPair.next;
                    }
                    if(currPair.key.equals(key)){
                          currPair.value = value;
                    }else{
                          currPair.next = pair;
                          this.len++;
                    }
              }
        }

        public V get(K key){
              int HashVal = this.HashFunction(key);
              Pair<K, V> currPair = this.table[HashVal];
              while(currPair != null){
                    if(currPair.key.equals(key)){
                          return currPair.value;
                    }
                    currPair = currPair.next;
              }
              return null;
        }

        public int size(){
              return this.len;
        }

        public boolean delKey(K key){
              int HashVal = this.HashFunction(key);
              Pair<K, V> currPair = this.table[HashVal];
              if(currPair != null && currPair.key.equals(key)){
                    this.table[HashVal] = currPair.next;
                    this.len--;
                    return true;
              }
              while(currPair.next != null){
                    if(currPair.next.key.equals(key)){
                          currPair.next = currPair.next.next;
                          this.len--;
                          return true;
                    }
              }
              return false;
        }

        public void free(){
              this.table = null;
        }
}
