import java.util.ArrayList;
import java.util.List;

public class practice {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;

        }

    }

    // 94. Binary Tree Inorder Traversal ( LeetCode )
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        inorderTraversalHelper(root, l);
        return l;
    }

    public static void inorderTraversalHelper(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }

        inorderTraversalHelper(root.left, l);
        l.add(root.val);
        inorderTraversalHelper(root.right, l);

    }

    // 100. Same Tree ( LeetCode )
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (q == null && p == null) {
            return true;
        }

        if (q == null && p != null) {
            return false;
        } else if (q != null && p == null) {
            return false;
        } else if (q.val != p.val) {
            return false;
        }

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);

        return left && right;
    }

    // 226. Invert Binary Tree ( LeetCode )
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        TreeNode temp = left;
        root.left = right;
        root.right = temp;

        return root;
    }

    public static void main(String[] args) {

    }
}
