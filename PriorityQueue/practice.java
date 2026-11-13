import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

    // 23. Merge k Sorted Lists ( Leetcode )
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode c = lists[i];
            ListNode temp = c;

            while (temp != null) {
                q.add(temp.val);
                temp = temp.next;
            }
        }

        ListNode ans = new ListNode(-1);
        ListNode temp = ans;

        while (!q.isEmpty()) {
            temp.next = new ListNode(q.poll());
            temp = temp.next;
        }

        return ans.next;
    }

    // 215. Kth Largest Element in an Array ( Leetcode )
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            q.add(nums[i]);
            if (q.size() > k) {
                q.poll();
            }
        }
        return q.poll();
    }

    // 295. Find median fom Data stream ( Leetcode ) ( Lag )
    public class MedianFinder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (!maxHeap.isEmpty() && maxHeap.peek() < num) {
                minHeap.add(num);
            }

            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            } else {
                maxHeap.add(num);
            }

        }

        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    // 373. Find median fom Data stream ( Leetcode ) ( Lag )
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (q.size() >= k) {
                    q.poll();
                }
                q.add(new int[] { nums1[i], nums2[j], nums1[i] + nums2[j] });
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int curr[] = q.poll();
            List<Integer> inner = new ArrayList<>();
            inner.add(curr[0]);
            inner.add(curr[1]);
            ans.add(inner);
        }
        return ans;
    }

    // 378. Kth Smallest Element in a Sorted Matrix ( Leetcode )
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                q.add(matrix[i][j]);
            }
        }

        int curr = 0;
        while (k > 0) {
            curr = q.poll();
            k--;
        }

        return curr;
    }

    public static void main(String[] args) {

    }
}
