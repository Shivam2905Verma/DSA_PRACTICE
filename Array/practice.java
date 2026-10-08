import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class practice {

    // 1. Two sum ( Leetcode )
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        System.out.println(map);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { i, map.get(complement) };
            }
        }

        return new int[] {};
    }

    // 121 Best time to buy and sell stock (Leetcode)
    public int maxProfit(int[] prices) {
        int prev = prices[0];
        int profit[] = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prev > 0) {
                profit[i] = Math.max(profit[i - 1], prices[i] - prev);
            } else {
                profit[i] = Math.max(profit[i - 1], 0);
            }
            prev = Math.min(prev, prices[i]);
        }

        return profit[prices.length - 1];
    }

    // 217 contains Dubplicates (Leetcode)
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return false;
            }
            map.put(nums[i], 1);
        }
        return true;
    }

    // 238. Product of Array Except Self (Leetcode )
    public int[] productExceptSelf(int[] nums) {
        int prefix[] = new int[nums.length];
        int suffix[] = new int[nums.length];
        prefix[0] = nums[0];
        suffix[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }

        int ans[] = new int[nums.length];
        ans[0] = suffix[1];
        ans[nums.length - 1] = prefix[nums.length - 2];

        for (int i = 1; i < ans.length - 1; i++) {
            ans[i] = suffix[i + 1] * prefix[i - 1];
        }

        return ans;

    }

    // 53. maximum subarray (Leetcode)
    public int maxSubArray(int[] nums) {

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        int max = Integer.MIN_VALUE;

        int i = 0;
        int j = nums.length;
        while (i < j) {
            if (nums[i] >= nums[j]) {
                sum -= nums[j];
                j--;
            }
            if (nums[i] < nums[j]) {
                sum -= nums[i];
                i++;
            }
            max = Math.max(Math.max(nums[i], nums[j]), Math.max(max, sum));
        }

        return max;
    }

    // 56 Merge Intervals (Leet code)
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        ArrayList<int[]> ans = new ArrayList<>();

        int min = intervals[0][0];
        int max = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (min < intervals[i][0] && max > intervals[i][0]) {
                max = Math.max(max, intervals[i][0]);
            } else {
                ans.add(new int[] { min, max });
                min = intervals[i][0];
                max = intervals[i][0];
            }
        }
        ans.add(new int[] { min, max });

        int arr[][] = ans.toArray(new int[ans.size()][]);

        return arr;
    }

    // 152 Max Product (Leet code)
    public int maxProduct(int[] nums) {
        int maxProduct = 1;
        int minus = 0;

        for (int num : nums) {
            if (num < 0) {
                minus++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (minus % 2 == 0) {
                maxProduct *= nums[i];
            }
        }

        return maxProduct;
    }

    // 88 Merge sorted Array (Leet code)
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;

        int ans[] = new int[m + n];

        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                ans[k] = nums1[i];
                i++;
            } else {
                System.out.print("Run");
                ans[k] = nums2[j];
                j++;
            }
            k++;
        }

        while (i < m) {
            ans[k] = nums1[i];
            i++;
            k++;
        }
        while (j < n) {
            ans[k] = nums2[j];
            j++;
            k++;
        }
        return ans;
    }

    // 75 Sort colors (Leet code)
    public void sortColors(int[] nums) {
        int i = 1;
        int j = 1;

        while (i < nums.length) {
            while (j > 0 && nums[j - 1] > nums[j]) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j--;
            }
            i++;
            j = i;
        }
    }

    public static void main(String[] args) {

    }
}