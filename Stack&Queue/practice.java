import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class practice {

    // 20. Valid Parentheses ( LeetCode ) ( Repeat )
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                st.push(ch);
            } else {
                if (st.isEmpty())
                    return false;
                char curr = st.peek();
                if ((curr == '(' && ch == ')') ||
                        (curr == '[' && ch == ']') ||
                        (curr == '{' && ch == '}')) {
                    st.pop();
                } else {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    // 150. Evaluate Reverse Polish Notation ( LeetCode ) ( Repeat )
    public int evalRPN(String[] tokens) {
        Stack<String> st = new Stack<>();
        int num1;
        int num2;

        for (int i = 0; i < tokens.length; i++) {
            int curr = tokens[i].charAt(0);

            if (tokens[i].length() == 1 &&
                    (curr == 42 || curr == 43 || curr == 45 || curr == 47)) {

                num2 = Integer.parseInt(st.pop());
                num1 = Integer.parseInt(st.pop());

                if (curr == 42) {
                    num1 = num1 * num2;
                } else if (curr == 43) {
                    num1 = num1 + num2;
                } else if (curr == 45) {
                    num1 = num1 - num2;
                } else {
                    num1 = num1 / num2;
                }

                st.push(String.valueOf(num1));

            } else {
                st.push(tokens[i]);
            }
        }

        return Integer.parseInt(st.pop());
    }

    // 71. Simplify Path ( LeetCode )
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(".") || arr[i].equals("")) {
                continue;
            }
            if (arr[i].equals("..")) {
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else {
                st.add(arr[i]);
            }
        }

        return String.join("/", st);
    }

    // 85. Maximal Rectangle (LeetCode)
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
            return 0;

        int area = 0;
        int[] height = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            area = Math.max(area, maximalRectangle(height));
        }

        return area;
    }

    public int maximalRectangle(int[] height) {
        int area = 0;

        for (int i = 0; i < height.length; i++) {
            int left = i;
            int right = i;

            while (left > 0 && height[left - 1] >= height[i]) {
                left--;
            }

            while (right < height.length - 1 && height[right + 1] >= height[i]) {
                right++;
            }

            area = Math.max(area, height[i] * (right - left + 1));
        }

        return area;
    }

    // 85. Lognest Valid Parentheses (LeetCode)
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.add(i);
            } else {

                if (st.isEmpty()) {
                    st.add(i);
                } else if (s.charAt(st.peek()) == '(') {
                    st.pop();
                } else {
                    st.add(i);
                }
            }
        }

        int ans = 0;

        int start = -1;

        while (!st.isEmpty()) {
            int end = st.pop();
            ans = Math.max(ans, end - start - 1);
            start = end;
        }

        ans = Math.max(ans, s.length() - start - 1);

        return ans;
    }

    // 496. Next Greater Element I (LeetCode)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>();
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i++) {
            while (!st.isEmpty()) {
                if (st.peek() < nums2[i]) {
                    st.pop();
                } else {
                    m.put(nums2[i], st.peek());
                }
            }

            if (st.isEmpty()) {
                m.put(nums2[i], -1);
            }

            st.add(nums2[i]);
        }
        int arr[] = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            arr[i] = m.get(nums1[i]);
        }
        return arr;
    }

    // 155. Min Stack (LeetCode) ( Use linked list approach )
    class MinStack {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Stack<Integer> st = new Stack<>();

        void push(int el) {
            st.add(el);
            q.add(el);
        }

        void pop() {
            q.remove(st.pop());
        }

        int top() {
            return st.peek();
        }

        int getMin() {
            return q.peek();
        }

    }

    // 739. Daily Tempereatures (LeetCode)
    public int[] dailyTemperature(int[] tempereatures) {
        Stack<Integer> st = new Stack<>();
        int ans[] = new int[tempereatures.length];

        for (int i = tempereatures.length - 1; i >= 0; i--) {
            int count = 1;
            while (!st.isEmpty() && st.peek() < tempereatures[i]) {
                count++;
                st.pop();
            }

            if (st.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = count;
            }

            st.push(tempereatures[i]);
        }

        return ans;

    }

    // 946. Validate Stack Sequences (LeetCode)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0;
        int j = 0;

        Stack<Integer> st = new Stack<>();

        while (j < popped.length) {
            if (!st.isEmpty() && st.peek() == popped[j]) {
                st.pop();
                j++;
            } else {

                if (i >= pushed.length) {
                    return false;
                }

                st.add(pushed[i]);
                i++;
            }
        }

        System.out.println(st);

        return st.isEmpty();
    }

    class MyCircularDeque {

        Deque<Integer> q = new LinkedList<>();
        int size;
        int values;

        public MyCircularDeque(int k) {
            size = k;
        }

        public boolean insertFront(int value) {
            if (values == size) {
                return false;
            }
            q.addFirst(value);
            values++;
            return true;
        }

        public boolean insertLast(int value) {
            if (values == size) {
                return false;
            }
            q.addLast(value);
            values++;
            return true;

        }

        public boolean deleteFront() {
            if (values == 0) {
                return false;
            }
            q.removeFirst();
            values--;
            return true;
        }

        public boolean deleteLast() {
            if (values == 0) {
                return false;
            }
            q.removeLast();
            values--;
            return true;

        }

        public int getFront() {
            if (values == 0) {
                return -1;
            }
            return q.getFirst();
        }

        public int getRear() {
            if (values == 0) {
                return -1;
            }
            return q.getLast();

        }

        public boolean isEmpty() {
            return q.isEmpty();
        }

        public boolean isFull() {
            return values == size;
        }
    }

    public static void main(String[] args) {

    }
}
