//use insert to insert new word into trie
//search will return if this word is in the trie
//searchPrefix will return if any word in trie start with the word(prefix)
//startWith will return an ArrayList of string which contains all words start with input prefix
//delete will return true if a word is in the trie, false for word not in the tree and
//only delete nodes that will not affect on other words, e.g.,
// consider a trie like this:       Root
//                                    |
//                                    A
//                                 /    \
//                                B      C
//                               / \
//                              D   E
// if user call trie.delete("ABD");
// only node D will be deleted because other node like node "B" is still in use for word ABE
// then you call trie.delete("ABE");
// both node B and node E will be deleted becuase node B is not in use for any word in the trie anymore
package Trie;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LikedList;
import java.util.Queue;
import java.lang.StringBuilder;
public class Trie{
    private TrieNode root;
    Trie(){
        this.root = new TrieNode(null);
    }

    public TrieNode Root(){
        return this.root;
    }

    public void insert(String key){
        TrieNode curr = this.root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            }else{
                TrieNode child = new TrieNode(c);
                curr.children.put(c, child);
            }
        }
        curr.isLeaf = True;
    }

    public boolean search(String key){
        TrieNode curr = this.root;
        for (int i =0; i < key.length(); i++) {
            char c = key..charAt(i);
            if (curr.children.containsKey(c)){
                curr = curr.children.get(c);
            }else{
                return false;
            }
        }
        return curr.isLeaf;
    }

    public boolean searchPrefix(String key){
        TrieNode curr = this.root;
        for (int i =0; i < key.length(); i++) {
            char c = key..charAt(i);
            if (curr.children.containsKey(c)){
                curr = curr.children.get(c);
            }else{
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> startWith(String key){
        ArrayList<String> words = new ArrayList<String>();
        StringBuilder prefix = new StringBuilder();
        TrieNode curr = this.root;
        for (int i =0; i < key.length(); i++) {
            if (curr.children.containsKey(c)){
                curr = curr.children.get(key.charAt(i));
                prefix.append(key.charAt(i));
            }else{
                return words;
            }
        }
        addStartWith(words, curr, prefix);
        return words;
    }

    private void addStartWith(ArrayList<String> arr, TrieNode curr, StringBuilder prefix){
        if (curr.isLeaf) {
            arr.add(prefix);
        }
        if (curr.children.size() == 0) {
            return;
        }
        for (Character key: curr.children.keySet()) {
            prefix.append(key);
            this.addStartWith(arr, curr.children.get(key), prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public boolean delete(String key){
        if (!search(key)) {
            return false;
        }
        Queue<Character> word = new Queue<Character>();
        for (int i = 0; i < key.length(); i++) {
            word.offer(key.charAt(i));
        }
        this.Lonely(this.root, word);
        return true;
    }

    private boolean Lonely(TrieNode node, Queue<Character> word){
        if (node.children.size() == 0) {
            return true;
        }
        char c = word.poll();
        if (Lonely(node.children.get(c), word)) {
            node.children.remove(c);
        }
        return (this.children.size() == 0);
    }
}
