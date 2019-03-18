//Create Trie node by use TrieNode node = new TrieNode(c) where c is character stored in the node
package Trie;
import java.util.HashMap;
public class TrieNode{
    protected HashMap<Character, TrieNode> children;
    protected boolean isLeaf;
    protected char c;
    TrieNode(char c){
        this.c = c;
        isLeaf = False;
        children = new HashMap<Character, TrieNode>();
    }
}
