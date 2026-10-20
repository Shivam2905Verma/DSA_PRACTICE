import java.util.ArrayList;
import java.util.Collections;
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

    // 141. Linked List Cycle ( Leetcode )
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            if (fast.next == null) {
                return false;
            }
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            if (slow == null) {
                return false;
            }
            fast = fast.next.next;
        }
    }

    // 21. Merge Two Sorted Lists ( Leetcode )
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode top = list1;
        ListNode bottom = list2;

        ListNode ans = new ListNode(-1);
        ListNode temp = ans;

        while (top != null && bottom != null) {
            if (top.val <= bottom.val) {
                temp.next = new ListNode(top.val);
                top = top.next;
            } else {
                temp.next = new ListNode(bottom.val);
                bottom = bottom.next;
            }

            temp = temp.next;
        }

        while (top != null) {
            temp.next = new ListNode(top.val);
            top = top.next;
            temp = temp.next;
        }

        while (bottom != null) {
            temp.next = new ListNode(bottom.val);
            bottom = bottom.next;
            temp = temp.next;
        }

        return ans.next;
    }

    // 203. Remove Linked List Elements ( Leetcode )
    public ListNode removeElements(ListNode head, int val) {
        ListNode temp = head;
        ListNode newHead = new ListNode(-1);
        ListNode newHeadTemp = newHead;

        while (temp != null) {
            System.out.println(temp.val);
            if (temp.val != val) {
                newHeadTemp.next = new ListNode(temp.val);
            }
            temp = temp.next;
            newHeadTemp = newHeadTemp.next;
        }

        return newHead.next;
    }

    // 2. Add Two Numbers ( Leetcode ) ( Lag )
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode lt = l1;
        ListNode l2t = l2;
        ListNode ans = new ListNode(-1);
        ListNode temp = ans;

        int carry = 0;

        while (lt != null && l2t != null) {
            int sum = lt.val + l2t.val + carry;

            carry = sum / 10;
            sum = sum % 10;

            temp.next = new ListNode(sum);
            temp = temp.next;

            lt = lt.next;
            l2t = l2t.next;
        }

        while (lt != null) {
            int sum = lt.val + carry;

            carry = sum / 10;
            sum = sum % 10;

            temp.next = new ListNode(sum);
            temp = temp.next;

            lt = lt.next;
        }

        while (l2t != null) {
            int sum = l2t.val + carry;

            carry = sum / 10;
            sum = sum % 10;

            temp.next = new ListNode(sum);
            temp = temp.next;

            l2t = l2t.next;
        }

        if (carry > 0) {
            temp.next = new ListNode(carry);
        }

        return ans.next;
    }

    // 19. Remove Nth Node From End of List ( Leetcode ) ( Repeat )
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode count = head;
        int size = 0;

        while (count != null) {
            size++;
            count = count.next;
        }

        if (size == 1) {
            return head;
        }

        int k = size - n;

        if (k == 0) {
            return head.next;
        }

        ListNode temp = head;
        int i = 0;

        while (temp != null) {
            if (i == k - 1) {
                temp.next = temp.next.next;
            }
            i++;
            temp = temp.next;
        }

        return head;
    }

    // 234. Palindrome Linked List ( Leetcode ) ( Learn other approach like slow
    // fast reverse half linkded list )
    public static boolean isPalindrome(ListNode head) {
        ListNode temp = head;

        StringBuilder str = new StringBuilder();

        while (temp != null) {
            str.append(temp.val);
            temp = temp.next;
        }

        int i = 0;
        int j = str.length() - 1;

        while (i <= j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    // 148. Sort ( Leetcode ) ( approach like slow fast )
    public ListNode sortList(ListNode head) {

        List<Integer> nums = new ArrayList<>();
        ListNode temp = head;

        while (temp != null) {
            nums.add(temp.val);
            temp = temp.next;
        }

        Collections.sort(nums);
        ListNode sortTemp = head;

        for (int num : nums) {
            sortTemp.val = num;
            sortTemp = sortTemp.next;
        }

        return head;

    }

    // 23. Merge k Sorted Lists ( Leetcode )
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < lists.length; i++) {
            ListNode temp = lists[0];
            System.out.println(temp.val);

            while (temp != null) {
                q.add(temp.val);
                temp = temp.next;
            }
        }

        ListNode ans = new ListNode(-1);

        while (!q.isEmpty()) {
            int curr = q.poll();
            ans.next = new ListNode(curr);
        }

        return ans.next;
    }

    public static void main(String[] args) {

    }
}
