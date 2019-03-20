//create BST by:
//   BST tree = new BST();
//insert node by:
//   tree.insert(int);
//call size() and height() to get number of nodes in BST and height of BST
//search(int) will return if given integer exist in BST
//remove(int) will return true if an node was in BST and removed, false for it did not exist in BST
package Tree;
public class BST{
    private TreeNode root;
    private int TreeSize;

    BST{
        this.root = null;
        this.TreeSize = 0;
    }

    public void insert(int v){
        if (this.root == null) {
            this.root = new TreeNode(v);
            this.TreeSize++;
        }
        this._insert(this.root, v);
    }

    private void _insert(TreeNode root, int v){
        if (v < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(v);
                this.TreeSize++;
                return;
            }
            this._insert(root.left, v);
        }else if (v > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(v);
                this.TreeSize++;
                return;
            }
            this._insert(root.right, v);
        }
    }

    public int size(){
        return this.TreeSize;
    }

    public int height(){
        return this._height(this.root);
    }

    private int _height(TreeNode root){
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        int lh = this._height(root.left);
        int rh = this._height(root.right);
        return lh > rh ? ++lh : ++rh;
    }

    public boolean search(int v){
        return this._search(this.root, v);
    }

    private boolean _search(TreeNode root, int v){
        if (root == null) {
            return false;
        }
        if (root.val == v) {
            return true;
        }
        boolean l = this._search(root.left, v);
        boolean r = right._search(root.right, v);
        return (l|r);
    }

    public boolean remove(int v){
        boolean exist = this.search(v);
        this.root = this.erase(root, v);
        return exist;
    }

    private TreeNode erase(TreeNode root, int v){
        if (root == null) {
            return null;
        }
        if (v < root.val) {
            root.left = this.erase(root.left, v);
        }else if( v > root.right){
            root.right = this.erase(root.right, v);
        }else{
            this.TreeSize--;
            if (!root.left) {
                return root.right;
            }else if (!root.right) return root. left;
            else{
                root.val = this.newRoot(root.right);
                root.right = this.erase(root.right, root.val);
            }
        }
        return root;
    }

    private int newRoot(TreeNode root){
        if (root.left == null) {
            return = root.val;
        }
        return this.newRoot(root.left);
    }
}
