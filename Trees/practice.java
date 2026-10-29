import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

    // 543. Diameter of Binary Tree ( Leetcode )
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter(root);
        return maxDiameter;
    }

    public int diameter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = diameter(root.left);
        int right = diameter(root.right);

        maxDiameter = Math.max(maxDiameter, left + right);

        return Math.max(left, right) + 1;
    }

    // 110. Balanced Binary Tree ( Leetcode )
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = height(root.left);
        int right = height(root.right);

        if (Math.abs(right - left) > 1) {
            return false;
        }

        boolean l = isBalanced(root.left);
        boolean r = isBalanced(root.right);

        return l && r;

    }

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;

    }

    // 144. Binary Tree Preorder Traversal ( Leetcode )
    public void preorderTraversalHelper(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }

        ans.add(root.val);

        preorderTraversalHelper(root.left, ans);
        preorderTraversalHelper(root.right, ans);

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        preorderTraversalHelper(root, ans);
        return ans;
    }

    // 199. Binary Tree Right Side View ( Leetcode )
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashSet<Integer> s = new HashSet<>();

        rightSideViewHelper(root, maxDiameter, s, ans);

        return ans;
    }

    public void rightSideViewHelper(TreeNode root, int count, HashSet<Integer> isDone, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }
        if (!isDone.contains(count)) {
            ans.add(root.val);
            isDone.add(count);
        }
        rightSideViewHelper(root.right, count + 1, isDone, ans);
        rightSideViewHelper(root.left, count + 1, isDone, ans);
    }

    // 222. Count Complete Tree Nodes ( Leetcode )
    public int countNodes(TreeNode root) {
        int height = height(root);
        System.out.println(height);
        return (int) Math.pow(2, height) - 1;

    }

    // 105. Construct Binary Tree from Preorder and Inorder Traversal ( Leetcode ) (
    // Repeat )
    class Solution {
        int preIndex = 0;
        Map<Integer, Integer> inorderMap = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            preIndex = 0;
            inorderMap.clear();

            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }

            return build(preorder, 0, inorder.length - 1);
        }

        private TreeNode build(int[] preorder, int left, int right) {
            if (left > right) {
                return null;
            }

            int value = preorder[preIndex++];
            TreeNode root = new TreeNode(value);

            int mid = inorderMap.get(value);

            root.left = build(preorder, left, mid - 1);
            root.right = build(preorder, mid + 1, right);

            return root;
        }
    }

    // 98. Validate Binary Search Tree ( Leetcode )
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    public boolean isValidBSTHelper(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }

        if ((max != null && root.val >= max.val)) {
            return false;
        }
        if(min != null && root.val <= min.val){
            return false;
        }

        boolean left = isValidBSTHelper(root, min, root);
        boolean right = isValidBSTHelper(root, root, max);

        return left && right;
    }

    public static void main(String[] args) {

    }
}
