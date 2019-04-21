package Tree;
public class RBTreeNode<T extends Comparable<T>>{
  protected T value;
  protected RBTreeNode<T> left;
  protected RBTreeNode<T> right;
  protected RBTreeNode<T> parent;
  protected boolean color;

  public RBTreeNode(T value){
    this.value = value;
  }

  public RBTreeNode(T value, boolean color){
    this.value = value;
    this.color = color;
  }

  public isLeaf(){
    return this.left == null && this.right == null;
  }
}
