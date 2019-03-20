//just a simple binary tree node
package Tree;
public class AVLTreeNode extends TreeNode{
    int height;

    TreeNode(int v){
        this.val = v;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
