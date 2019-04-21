package Tree;
public class RBTree<T extends Comparable<T>>{
  private RBTreeNode<T> root;
  private int size;

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

  
}
