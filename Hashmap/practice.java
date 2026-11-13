import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class practice {

    // 1. Two Sum ( Leetcode )
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> s = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            s.put(nums[i], i);
            if (s.containsKey(target - nums[i])) {
                return new int[] { i, s.get(target - nums[i]) };
            }
        }

        return new int[] { -1, -1 };
    }

    // 13. Roman to Integer ( Leetcode )
    HashMap<Character, Integer> map = new HashMap<>();

    public int romanToInt(String s) {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = map.get(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            if (map.get(s.charAt(i - 1)) < map.get(s.charAt(i))) {
                sum += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
            } else {
                sum += map.get(s.charAt(i));
            }
        }

        return sum;
    }

    // 36. Valid Sudoku ( Leetcode )
    public static boolean isValidSudoku(char[][] board, int row, int col) {

        for (int i = 0; i < board.length; i++) {
            if (i != row && board[i][col] == board[row][col]) {
                return false;
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (i != col && board[row][i] == board[row][col]) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if ((i != row || j != col) && board[i][j] == board[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!isValidSudoku(board, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    // 49. Group Anagrams ( Leetcode )
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> m = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String s = new String(c);

            if (m.containsKey(s)) {
                m.get(s).add(strs[i]);
            } else {
                m.put(s, new ArrayList<>(Arrays.asList(strs[i])));
            }
        }

        List<List<String>> ans = new ArrayList<>();

        for (String s : m.keySet()) {
            ans.add(m.get(s));
        }

        return ans;
    }

    // 76. Minimum Window Substring ( Leetcode )
    public String minWindow(String s, String t) {

        if (t.length() > s.length()) {
            return "";
        }

        HashMap<Character, Integer> tc = new HashMap<>();
        int i = 0;
        int j = 0;

        boolean allTrue = false;
        String ans = "";

        for (int k = 0; k < t.length(); k++) {
            tc.put(t.charAt(k), tc.getOrDefault(t.charAt(k), 0) + 1);
        }

        while (j < s.length()) {
            if (tc.containsKey(s.charAt(j))) {
                tc.put(s.charAt(j), tc.get(s.charAt(j)) - 1);
                allTrue = true;
                for (Character c : tc.keySet()) {
                    if (tc.get(c) > 0) {
                        allTrue = false;
                    }
                }
            }

            while (i <= j && allTrue) {
                if (ans.length() > (j - i + 1) || ans.length() == 0) {
                    ans = s.substring(i, j + 1);
                }

                if (tc.containsKey(s.charAt(i))) {
                    tc.put(s.charAt(i), tc.get(s.charAt(i)) + 1);
                    for (Character c : tc.keySet()) {
                        if (tc.get(c) > 0) {
                            allTrue = false;
                        }
                    }
                }

                i++;
            }

            j++;
        }

        return ans;
    }

    // 128. Longest Consecutive Sequence ( Leetcode )
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = 0;
        int curr = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 1) {
                ans = Math.max(curr, ans);
                curr = 0;
            }
            if (nums[i] - nums[i - 1] == 1) {
                curr++;
            }
        }
        return ans;
    }

    // 202. Happy Number ( Leetcode )
    public boolean isHappy(int n) {
        HashSet<Integer> s = new HashSet<>();
        s.add(n);
        while (true) {
            int k = 0;

            while (n != 0) {
                int lastDig = n % 10;
                k += lastDig * lastDig;
                n /= 10;
            }

            if (k == 1) {
                return true;

            } else if (s.contains(k)) {
                return false;
            }

            n = k;
            s.add(k);
        }
    }

    // 383. Ransom Note ( Leetcode )
    public boolean canConstruct(String ransomNote, String magazine) {
        int freq[] = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            freq[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (freq[ransomNote.charAt(i) - 'a'] <= 0) {
                return false;
            }
            freq[ransomNote.charAt(i) - 'a']--;
        }

        return true;
    }

    // 387. First Unique Character in a String ( Leetcode )
    public int firstUniqChar(String s) {
        int freq[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    // 347. Top K Frequent Elements ( Leetcode )
    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> m = new HashMap<>();

        for (int j = 0; j < nums.length; j++) {
            m.put(nums[j], m.getOrDefault(nums[j], 0) + 1);
        }

        int ans[] = new int[k];
        for (int i = 0; i < k; i++) {

            int temp = Integer.MIN_VALUE;
            int val = -1;

            for (int key : m.keySet()) {
                if (m.get(key) > temp) {
                    temp = m.get(key);
                    val = key;
                }
            }

            ans[i] = val;
            m.remove(val);
        }

        return ans;
    }



    public static void main(String[] args) {

    }
}
