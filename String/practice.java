import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

    // 5. Longest Palindromic Substring ( Leetcode ) (Lag)
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

    // 14. Longest Common Prefix ( Leetcode )
    public String longestCommonPrefix(String[] strs) {

        StringBuilder ans = new StringBuilder();
        int minStrln = 300;
        String minStr = "";
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minStrln) {
                minStr = strs[i];
            }
        }
        for (int j = 0; j < minStr.length(); j++) {
            boolean isAllHaving = true;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i - 1].charAt(j) != strs[i].charAt(j)) {
                    isAllHaving = false;
                }
            }
            if (isAllHaving) {
                ans.append(minStr.charAt(j));
            }
        }

        return ans.toString();
    }

    // 20. Valid parantheses ( Leetcode )
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '(' || s.charAt(i) != '{' || s.charAt(i) != '[') {
                st.add(s.charAt(i));
            } else if (!st.empty()) {
                if (s.charAt(i) == ')' && st.pop() != '(') {
                    return false;
                } else if (s.charAt(i) == '}' && st.pop() != '{') {
                    return false;
                } else if (s.charAt(i) == ']' && st.pop() != '[') {
                    return false;
                }
            } else {
                st.add(s.charAt(i));
            }
        }
        return st.isEmpty() ? true : false;
    }

    // 49. Group Anagram ( Leetcode )
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> outer = new ArrayList<>();

        int k = 0;
        HashMap<String, Integer> m = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char curr[] = strs[i].toCharArray();
            Arrays.sort(curr);
            String s = new String(curr);
            boolean isAdded = false;

            if (m.containsKey(s)) {
                outer.get(m.get(s)).add(strs[i]);
                isAdded = true;
            }

            if (!isAdded) {
                List<String> inner = new ArrayList<>();
                inner.add(strs[i]);
                outer.add(inner);
                m.put(s, k);
                k++;
            }
        }

        return outer;
    }

    // 76. Minimum Window Substring ( Leetcode ) ( Not lag but Pratice to remeber
    // the method and also do the similar one )
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        boolean isStarted = false;
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int allContained = 0;
        String ans = "";
        int freq[] = new int[128];

        while (j < s.length()) {
            char ch = s.charAt(j);

            if (!isStarted) {
                if (map.containsKey(ch)) {
                    isStarted = true;
                } else {
                    i++;
                    j++;
                }
                continue;
            }

            if (map.containsKey(ch)) {
                if (map.get(ch) > freq[ch]) {
                    allContained++;
                }
                freq[ch]++;
            }

            if (allContained == t.length()) {
                while (i <= j && allContained == t.length()) {
                    char curr = s.charAt(i);
                    if ((j - i) + 1 < min) {
                        ans = s.substring(i, j + 1);
                        min = (j - i) + 1;
                    }

                    if (map.containsKey(curr)) {
                        freq[curr]--;
                        if (freq[curr] < map.get(curr)) {
                            allContained--;
                        }
                    }
                    i++;
                }
            }
            j++;
        }
        return ans;
    }

    // 125. Valid Palindrome ( Leetcode )
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        boolean ans = false;
        while (i < j) {
            int ASICCI = s.charAt(i);
            int ASICCJ = s.charAt(j);

            if ((ASICCI >= 32 && ASICCI <= 47) || (ASICCI >= 58 && ASICCI <= 96) || (ASICCI >= 123 && ASICCI <= 126)) {
                i++;
                continue;
            }

            if ((ASICCJ >= 32 && ASICCJ <= 47) || (ASICCJ >= 58 && ASICCJ <= 96) || (ASICCJ >= 123 && ASICCJ <= 126)) {
                j--;
                continue;
            }

            if (s.charAt(i) != s.charAt(j)) {
                System.out.println(i + " :- " + s.charAt(i) + " " + j + " :- " + s.charAt(j));
                return false;
            }
        }

        return true;
    }

    // 424. Longest Repeating Character Replacement ( Leetcode ) ( lag )
    public int characterReplacement(String s, int k) {
        int ans = -1;
        int highest = -1;
        HashMap<Character, Integer> map = new HashMap<>();

        int i = 0;
        int j = 0;

        while (j < s.length()) {
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
            highest = Math.max(highest, map.get(s.charAt(j)));
            int totalLength = (j - i) + 1;
            if ((totalLength - highest) > k) {
                char remove = s.charAt(i);
                map.put(remove, map.get(remove) - 1);
                i++;
            } else {
                ans = Math.max(ans, totalLength);
            }
            j++;
        }

        return ans;
    }

    // 567. Permutation in String ( Leetcode ) ( lag )
    public boolean zcheckInclusion(String s1, String s2) {

        if (s1.length() > s2.length())
            return false;

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int i = 0;
        int j = 0;
        boolean started = false;
        int freq[] = new int[26];
        int allContain = 0;
        int min = Integer.MAX_VALUE;

        while (j < s2.length()) {

            char ch = s2.charAt(j);
            if (!started) {
                if (map.containsKey(ch)) {
                    started = true;
                } else {
                    i++;
                    j++;
                }
            }

            if (map.containsKey(ch)) {
                if (freq[ch] < map.get(ch)) {
                    allContain++;
                }
                freq[ch]++;
            }

            if (allContain == s1.length()) {
                while (i <= j && allContain == s1.length()) {
                    char curr = s2.charAt(i);

                    if ((j - i) + 1 == s1.length()) {
                        return true;
                    }

                    if (map.containsKey(curr)) {
                        freq[curr]--;
                        if (freq[curr] < map.get(curr)) {
                            allContain--;
                        }
                    }
                    i++;
                }
            }
            j++;
        }

        return false;
    }

    // 438. Find All Anagrams in a String ( Leetcode )
    public List<Integer> findAnagrams(String s, String p) {

        int freq[] = new int[26];

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < p.length(); i++) {
            freq[p.charAt(i) - 'a']++;
        }

        int i = 0;
        int j = 0;

        while (j < s.length()) {
            char ch = s.charAt(j);

            freq[ch - 'a']--;

            if ((j - i) + 1 > p.length()) {
                freq[ch - 'a']++;
                i++;
            }

            if ((j - i) + 1 == p.length()) {
                boolean valid = true;
                for (int num : freq) {
                    if (num > 0) {
                        valid = false;
                    }
                }

                if (valid) {
                    ans.add(i);
                }
            }
            j++;
        }

        return ans;
    }

    // 1021. Remove Outermost Parentheses ( Leetcode ) ( repeat )
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int count = 0;

        for(char ch : s.toCharArray()){
            if(ch =='('){
                if(count > 0){
                    ans.append(ch);
                }
                count++;
            }
            else{
                count--;
                if(count > 0){
                    ans.append(ch);
                }
            }
        }

        return ans.toString(); 
    }

    public static void main(String[] args) {

    }
}