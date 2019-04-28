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

  private void rotateLeft(RBTreeNode<T> node){
    RBTreeNode<T> new_root = node.right;
    node.right = new_root.left;
    new_root.left = node;
    node.parent = new_root;
    if (node.right != null) {
      node.right.parent = node;
    }
  }

  private void rotateRight(RBTreeNode<T> node){
    RBTreeNode<T> new_root = node.left;
    node.left = new_root.right;
    new_root.right = node;
    node.parent = new_root;
    if (node.left != null) {
      node.left.parent = node;l
    }
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
        return curr;
      }
    }
    return null;
  }

  public void insert(T key){
    RBTreeNode<T> newnode = this.add(this.root, key);
    this.insertFix(newnode);
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
    return curr;
  }

  private void insertFix(RBTreeNode<T> node){
    if (node.parent == null){
      node.color = black;
    }else if(node.parent.color == black){
    }else if(this.uncle(node) != null && this.uncle(node).color == red){
      this.uncle(node).color = black;
      node.parent.color = black;
      this.grandParent(node).color = red;
      this.insertFix(this.grandParent(node));
    }else{
      RBTreeNode<T> grandParent = this.grandParent(node);
      RBTreeNode<T> parent = node.parent;
      if (parent.right == node && grandParent.left == parent){
        rotateLeft(parent);
        node = node.left;
      }else if (parent.left == node && grandParent.right == parent){
        rotateRight(node);
        node = node.right;
      }
      grandParent = this.grandParent(node);
      parent = node.parent;
      if (parent.left == node) {
        rotateRight(grandParent);
      }else{
        rotateLeft(grandParent);
      }
      parent.color = black;
      grandParent.color = red;
    }
  }

  public void remove(T key){
      RBTreeNode node = this.search(key);
      if (node != null){
        this._remove(node);
      }
  }

  private void _remove(RBTreeNode node){
    if (node.left != null && node.right != null) {
      RBTreeNode curr = node.right;
      while (curr.left != null){
        curr = curr.left;
      }
      if (node.parent != null) {
        RBTreeNode p = node.parent;
        if (p.left == node){
          p.left = curr;
        }else{
          p.right = curr;
        }
      }else{
        this.root = curr;
      }
      RBTreeNode successor = curr.right;
      RBTreeNode predecessor = curr.parent;
      if (node == predecessor) {
        predecessor = curr;
      }else{
        if (successor != null) {
          successor.parent = predecessor;
        }
        predecessor.left = successor;
        curr.right = node.right;
        node.right.parent = curr;
      }
      curr.parent = node.parent;
      boolean oldcolor = curr.color;
      curr.color = node.color;
      curr.left = node.left;
      node.left.parent = curr;
      if (oldcolor == black) {
        removeFix(successor, predecessor);
      }
    }
    else{
      RBTreeNode successor;
      RBTreeNode predecessor;
      if (node.left != null) {
        successor = node.left;
      }else{
        successor = node.right;
      }
      predecessor = node.parent;
      boolean oldcolor = node.color;
      if (successor != null) {
        successor.parent = predecessor;
      }
      if (predecessor != null){
          if (predecessor.left == node){
              predecessor.left = successor;
          } else {
              predecessor.right = successor;
          }
      } else {
          this.root = successor;
      }

      if (oldcolor == black){
          removeFix(successor, predecessor);
      }
    }
  }
}
