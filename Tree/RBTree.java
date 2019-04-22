package Tree;
public class RBTree<T extends Comparable<T>>{
  private RBTreeNode<T> root;
  private int size;
  private final boolean black = false;
  private final boolean red = true;

  public RBTree(){
    this.root = new RBTreeNode(null);
  }

  public T search(T value){
    RBTreeNode<T> curr = this.root;
    while (curr != null){
      int compare_val = curr.value.compareTo(value);
      if (compare_val < 0) {
        curr = curr.right;
      }else if(compare_val > 0){
        curr = curr.left;
      }else{
        return curr.value;
      }
    }
    return null;
  }

  private RBTreeNode add(RBTreeNode curr,T key){
    if (curr == null) {
      this.size++;
      RBTreeNode newNode = new RBTreeNode(key, red);
      return newNode;
    }
    int compare_val = curr.value.compareTo(value);
    if (compare_val < 0) {
      curr.right = this.add(curr.right, key);
    }else if (compare_val > 0){
      curr.left = this.add(curr.left, key);
    }else{
      return curr;
    }
  }
}
