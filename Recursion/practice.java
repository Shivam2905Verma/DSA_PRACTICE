import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class practice {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode node) {
            this.val = val;
            this.next = node;
        }

    }

    class TreeNode {
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

    // 23. Merge k Sorted Lists ( Leetcode )
    public ListNode mergeKLists(ListNode[] lists) {

        ArrayList<Integer> allVal = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode temp = lists[i];

            while (temp != null) {
                allVal.add(temp.val);
                temp = temp.next;
            }
        }

        if (allVal.size() == 0) {
            return null;
        }

        Collections.sort(allVal);

        ListNode ans = new ListNode(allVal.get(0));
        ListNode temp = ans;

        for (int i = 1; i < allVal.size(); i++) {
            temp.next = new ListNode(allVal.get(i));
            temp = temp.next;
        }

        return ans;
    }

    // 50. Pow(x, n) ( Leetcode )
    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        if (n == -1) {
            return 1 / x;
        }

        double ans = myPow(x, n / 2);
        if (n % 2 == 0) {
            return ans * ans;
        } else {
            if (n < 0) {
                return ans * ans * (1 / x);
            }
            return ans * ans * x;
        }
    }

    // 77. Combinations ( Leetcode ) ( Lag )
    public List<List<Integer>> combine(int n, int k) {
        if (n == 1) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(List.of(1));
            return ans;
        }
        List<List<Integer>> ans = new ArrayList<>();
        combine(1, n, k, ans, new ArrayList<>());
        return ans;
    }

    public void combine(int i, int n, int k, List<List<Integer>> ans, List<Integer> inner) {
        if (inner.size() == k) {
            ans.add(new ArrayList<>(inner));
            return;
        }

        if (i > n) {
            return;
        }

        for (int j = i; j <= n; j++) {
            inner.add(j);
            combine(j + 1, n, k, ans, inner);
            inner.remove(inner.size() - 1);
        }
    }

    // 78. Subsets ( Leetcode )
    public void subsets(int[] nums, int i, List<List<Integer>> outer, List<Integer> inner) {

        if (i > nums.length - 1) {
            return;
        }

        for (int j = i; j < nums.length; j++) {
            inner.add(nums[j]);
            outer.add(new ArrayList<>(inner));
            subsets(nums, j + 1, outer, inner);
            inner.remove(inner.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        subsets(nums, 0, outer, new ArrayList<>());
        return outer;
    }

    // 79. Word Search ( Leetcode ) ( Lag )
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j]
                        && exist(board, word, 1, i, j, new boolean[board.length][board[0].length])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean exist(char[][] board, String word, int index, int row, int col, boolean[][] isTracked) {
        if (index == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(index) || isTracked[row][col]) {
            return false;
        }

        isTracked[row][col] = true;

        boolean found = exist(board, word, index + 1, row + 1, col, isTracked)
                || exist(board, word, index + 1, row - 1, col, isTracked)
                || exist(board, word, index + 1, row, col + 1, isTracked)
                || exist(board, word, index + 1, row, col - 1, isTracked);

        isTracked[row][col] = false;

        return found;
    }

    // 98. Validate Binary Search Tree ( Leetcode ) ( Lag )
    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);

    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    // 104 Maximum depth of Binary tree ( Leetcode )
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    // 110 Balanced Binary tree ( Leetcode ) ( Lag )
    public boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }

        int right = maxDepth(root.right);
        int left = maxDepth(root.left);

        if (Math.abs(left - right) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    // 236. Lowest Common Ancestor of a Binary Tree ( Leetcode )
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (p == root || q == root) {
            return root;
        }

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return root;
    }

    // 206. Reverse Linked List ( Leetcode )
    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode pre = null;

        while (temp.next != null) {
            ListNode curr = temp;
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
            temp = next;
        }

        return pre;
    }

    public static void main(String[] args) {

    }
}
