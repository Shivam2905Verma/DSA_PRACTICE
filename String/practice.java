import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class practice {

    // 3. Longest Substring without Repeating Characters ( Leetcode )
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1)
            return 1;
        int max = 0;

        HashSet<Character> set = new HashSet<>();

        int i = 0;
        int j = 0;

        while (j < s.length()) {
            if (set.contains(s.charAt(j))) {
                max = Math.max(max, j - i);
                System.out.println("Max" + max);
                while (!set.contains(s.charAt(i))) {
                    set.remove(s.charAt(i));
                    i++;
                }
            }

            set.add(s.charAt(j));
            j++;
        }
        max = Math.max(max, j - i);

        return max;
    }

    // 1111. Maximum Nesting Depth of Two Valid Parentheses Strings (Leetcode)
    public int[] maxDepthAfterSplit(String seq) {
        Stack<Integer> st = new Stack<>();

        int ans[] = new int[seq.length()];

        boolean isZero = true;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                ans[i] = isZero ? 0 : 1;
                st.add(isZero ? 0 : 1);
            } else if (seq.charAt(i) == ')') {
                int curr = st.pop();
                ans[i] = curr;
            }
            isZero = !isZero;
        }

        return ans;
    }

    // 5. Longest Palindromic Substring ( Leetcode )
    public String longestPalindrome(String s) {
        String ans = "";
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.length() == 1) {
                return s;
            }
            // check for odd;
            String odd = HelperCheckLongestPalindrome(s, i, i);
            // check for even;
            String even = HelperCheckLongestPalindrome(s, i, i + 1);

            if (odd.length() > max) {
                max = odd.length();
                ans = odd;
            }
            if (even.length() > max) {
                max = even.length();
                ans = even;
            }

        }

        return ans;
    }

    public String HelperCheckLongestPalindrome(String s, int left, int right) {

        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {

    }
}