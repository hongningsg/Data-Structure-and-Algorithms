package Tree;
public class RBTree<T extends Comparable<T>>{
  private RBTreeNode<T> root;
  private int size;
  private final boolean black = false;
  private final boolean red = true;

  public RBTree(){
    this.root = new RBTreeNode(null);
  }

  private RBTreeNode<T> grandParent(RBTreeNode<T> node){
    RBTreeNode<T> parent = node.parent;
    if (parent != null){
      return parent.parent;
    }
    return null;
  }

  private RBTreeNode<T> sibling(RBTreeNode<T> node){
    RBTreeNode<T> parent = node.parent;
    if (parent == null) return null;
    return node == parent.left? parent.right: parent.left;
  }

  private RBTreeNode<T> uncle(RBTreeNode<T> node){
    RBTreeNode<T> grandParent = this.grandParent(node);
    if (grandParent == null) {
      return null;
    }
    return this.sibling(node.parent);
  }

  private RBTreeNode<T> rotateLeft(RBTreeNode<T> node){
    RBTreeNode<T> new_root = node.right;
    node.right = new_root.left;
    new_root.left = node;
    node.parent = new_root;
    if (node.right != null) {
      node.right.parent = node;
    }
    return new_root;
  }

  private RBTreeNode<T> rotateRight(RBTreeNode<T> node){
    RBTreeNode<T> new_root = node.left;
    node.left = new_root.right;
    new_root.right = node;
    node.parent = new_root;
    if (node.left != null) {
      node.left.parent = node;l
    }
    return new_root;
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

  public void insert(T key){
    this.add(this.root, key);
  }

  private RBTreeNode<T> add(RBTreeNode curr,T key){
    if (curr == null) {
      this.size++;
      RBTreeNode newNode = new RBTreeNode(key, red);
      return newNode;
    }
    int compare_val = curr.value.compareTo(value);
    if (compare_val < 0) {
      curr.right = this.add(curr.right, key);
      curr.right.parent = curr;
    }else if (compare_val > 0){
      curr.left = this.add(curr.left, key);
      curr.left.parent = curr;
    }else{
      return curr;
    }
    insertFix(curr);
  }

  private insertFix(RBTreeNode<T> node){

  }
}
