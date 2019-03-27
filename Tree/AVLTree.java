//AVL Balance tree with everything, I mean among search, insert, remove node in O(nlogn)

package Tree;
public class AVLTree{
    private AVLTreeNode root;
    private int TreeSize;

    private int height(AVLTreeNode node){
        if (node == null) return 0;
        return node.height;
    }

    private int max(int x, int y){
        return (x > y ? x : y);
    }

    private void reHeight(AVLTreeNode node){
        node.height = this.max(height(node.left), height(node.right)) + 1;
    }
// T1, T2, T3 and T4 are subtrees.
//          z                                      y
//         / \                                   /   \
//        y   T4      Right Rotate (z)          x      z
//       / \          - - - - - - - - ->      /  \    /  \
//      x   T3                               T1  T2  T3  T4
//     / \
//   T1   T2
    private AVLTreeNode rightRotate(AVLTree z){
        AVLTreeNode y = z.left;
        z.left = y.right;
        y.right = z;
        reHeight(z);
        reHeight(y);
        return y;
    }

//   z                                y
// /  \                            /   \
// T1   y     Left Rotate(z)     z      x
//   /  \   - - - - - - - ->    / \    / \
//  T2   x                     T1  T2 T3  T4
//      / \
//    T3  T4
    private AVLTreeNode leftRotate(AVLTree z){
        AVLTreeNode y = z.right;
        z.right = y.left;
        y.left = z;
        reHeight(z);
        reHeight(y);
        return y;
    }

    private int getBalance(AVLTreeNode node){
        if (node == 0) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public void insert(int val){
        this._insert(this.root, val);
    }

    private AVLTreeNode _insert(AVLTreeNode root, int v){
        if (root == null) {
            AVLTreeNode newNode = new AVLTreeNode(v);
            this.TreeSize++;
            return newNode;
        }
        if (v < root.val) {
            root.left = this._insert(root.left, v);
        }else if (v == root.val){
            return root;
        }else{
            root.right = this._insert(root.right, v);
        }
        reHeight(root);
        int balance = this.getBalance(root);
        //Left heavy
        if (balance > 1) {
            if (v < root.left.val) {
                return this.rightRotate(root);
            }else{
//      z                               z                           x
//     / \                            /   \                        /  \
//    y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
//   / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
// T1   x                          y    T3                    T1  T2 T3  T4
//     / \                        / \
//   T2   T3                    T1   T2
                root.left = this.leftRotate(root.left);
                return this.rightRotate(root);
            }
        }else if (balance < -1) { //right heavy
            if (v < root.right.val) {
  //    z                             z                             x
  //   / \                          /  \                          /  \
  //  T1   y   Right Rotate (y)    T1   x  Left Rotate(z)        z    y
  //      / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \   / \
  //     x   T4                      T2   y                  T1  T2 T3  T4
  //    / \                              / \
  //   T2 T3                           T3  T4
                root.right = this.rightRotate(root.right);
                return this.leftRotate(root);
            }else{
                return this.leftRotate(root);
            }
        }
        return root;
    }

    public boolean search(int v){
        AVLTreeNode curr = this.root;
        while(curr != null){
            if (v < curr.val) {
                root = curr.left;
            }else if(v > curr.val){
                root = curr.right;
            }else{
                return true;
            }
        }
        return false;
    }

    public boolean delete(int v){
        return (this.TreeSize == this.erase(this.root, v).TreeSize);
    }

    private AVLTreeNode erase(AVLTreeNode root, int v){
        if (root == null) return root;
        if (v < root.val) root.left = this.erase(root.left, v);
        else if (v > root.val) root.right = this.erase(root.right, v);
        else{
            this.TreeSize--;
            if (root.left == null && root.right == null){
                return null;
            }
            if (root.left == null) {
                return root.right;
            }else if (root.right == null) {
                return root.left;
            }else{
                root.val = leftMost(root.right).key;
                root.right = this.erase(root.right, root.val);
            }
        }
        reHeight(root);
        int balance = getBalance(root);
        if (balance > 1) {
            if (v < root.left.val) {
                root.left = this.leftRotate(root.left);
              }
            return this.rightRotate(root);
        }else if (balance < -1) {
            if (v > root.left.val){
                root.right = this.rightRotate(root.right);
            }
            return this.leftRotate(root);
        }
        return root;
    }

    private AVLTreeNode leftMost(AVLTreeNode root){
        while (root.left != null){
            root = root.left;
        }
        return root;
    }

}
