import java.util.Arrays;
import java.util.List;

public class practice {

    // 70. Climbing Stairs ( LeetCode )
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        int dp[] = new int[n];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }

    // 198. House Robber ( LeetCode )
    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(nums[i] + nums[i - 2], nums[i] + dp[i - 2]);
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
        return dp[nums.length - 1];
    }

    // 121. Best Time to Buy and Sell Stock ( LeetCode )
    public int maxProfit(int[] prices) {
        int minBuy = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            minBuy = Math.min(prices[i], minBuy);
            maxProfit = Math.max(maxProfit, prices[i] - minBuy);
        }

        return maxProfit;
    }

    // 300. Longest Increasing Subsequence ( Leetcode )
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 1;
        for (int j = 0; j < dp.length; j++) {
            ans = Math.max(ans, dp[j]);
        }
        return ans;
    }

    // 322. Coin Change ( Leetcode ) ( Lag ) ( Repeat )
    public static int coinChange(int[] coins, int amount) {
        int s = coinChangeHelper(coins, 0, amount);
        if (s >= 1000000000) {
            return -1;
        }
        return s;
    }

    public static int coinChangeHelper(int[] coins, int i, int amount) {
        if (i == 0) {
            if (amount % coins[i] == 0) {
                return amount / coins[i];
            }
            return 1000000000;
        }
        int take = Integer.MAX_VALUE;
        if (coins[i] <= amount) {
            take = 1 + coinChangeHelper(coins, i, amount - coins[i]);
        }
        int notTake = 0 + coinChangeHelper(coins, i + 1, amount);

        return Math.min(take, notTake);
    }

    // 0 - 1 Knapsack Problem ( geeksforgeeks ) ( Repeat )
    public int knapsack(int W, int val[], int wt[]) {
        int dp[][] = new int[wt.length + 1][W + 1];
        for (int i = 1; i <= wt.length; i++) {
            for (int j = 1; j <= (W); j++) {
                int include = -1;
                if (j - wt[i - 1] >= 0) {
                    include = val[i - 1] + dp[i - 1][j - wt[i - 1]];
                }
                int exclude = dp[i - 1][j];
                dp[i][j] = Math.max(include, exclude);
            }
        }
        return dp[wt.length][W];
    }

    // 518. Coin Change II ( Leetcode )
    public int change(int amount, int[] coins) {
        int dp[][] = new int[coins.length][amount + 1];
        Arrays.fill(dp, -1);
        return change(amount, coins, 0, 0, dp);
    }

    public int change(int amount, int[] coins, int val, int i, int dp[][]) {
        if (i == coins.length) {
            return 0;
        }

        if (dp[i][val] != -1) {
            return dp[i][val];
        }

        if (val == amount) {
            return 1;
        }
        if (val > amount) {
            return 0;
        }

        int include = change(amount, coins, val + coins[i], i, dp);
        int exclude = change(amount, coins, val, i + 1, dp);

        dp[i][val] = include + exclude;
        return dp[i][val];
    }

    public int changeTabulation(int amount, int[] coins) {
        int dp[][] = new int[coins.length][amount + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int currCoin = coins[i - 1];
                if (currCoin < j) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    if (currCoin == j || dp[i][j - currCoin] >= 1) {
                        dp[i][j] = 1 + dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

    // 139. Word Break ( Leetcode ) ( Lag ) ( Repeat )
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;

        int maxLength = 0;

        for (int i = 0; i < wordDict.size(); i++) {
            maxLength = Math.max(maxLength, wordDict.get(i).length());
        }

        for (int i = 1; i <= s.length(); i++) {
            int val = i - maxLength > 0 ? (i - maxLength) : 0;

            StringBuilder word = new StringBuilder();

            for (int j = i - 1; j >= val; j--) {

                word.insert(0, s.charAt(j));

                if (!dp[j]) {
                    continue;
                }

                if (wordDict.contains(word.toString())) {
                    dp[i] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
        }

        return dp[s.length()];
    }

    // 152. Maximum Product Subarray ( Leetcode ) ( Lag ) ( Repeat )
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] * dp[i - 1]) >= dp[i - 1] || nums[i] >= dp[i - 1]) {
                dp[i] = Math.max(nums[i] * dp[i - 1], nums[i]);
            } else {
                dp[i] = 1;
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 1143. Longest Common Subsequence ( Leetcode ) ( Repeat )
    public int longestCommonSubsequence(String text1, String text2) {
        int dp[][] = new int[text2.length() + 1][text1.length() + 1];
        for (int i = 1; i <= text2.length(); i++) {
            for (int j = 1; j <= text1.length(); j++) {
                if (text2.charAt(i - 1) == text1.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text2.length()][text1.length()];
    }

    // 416. Partition Equal Subset Sum ( Leetcode ) ( Repeat )
    public boolean canPartition(int[] nums) {
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        if (total % 2 != 0) {
            return false;
        }

        int target = total / 2;

        boolean dp[][] = new boolean[nums.length][target + 1];

        return canPartition(nums, 0, 0, target, dp);
    }

    public boolean canPartition(int[] nums, int i, int sum,
            int target, boolean dp[][]) {

        if (sum == target) {
            return true;
        }

        if (i == nums.length || sum > target) {
            return false;
        }

        if (dp[i][sum]) {
            return true;
        }

        boolean include = canPartition(
                nums, i + 1, sum + nums[i], target, dp);

        boolean exclude = canPartition(
                nums, i + 1, sum, target, dp);

        dp[i][sum] = include || exclude;

        return dp[i][sum];
    }

    public boolean canPartitionTabulation(int[] nums) {
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        if (total % 2 != 0) {
            return false;
        }

        int target = total / 2;

        boolean dp[][] = new boolean[nums.length + 1][target + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (nums[i - 1] <= j) {
                    if (dp[i - 1][j - nums[i - 1]]) {
                        dp[i][j] = true;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][target];
    }

    public static void main(String[] args) {

    }
}
