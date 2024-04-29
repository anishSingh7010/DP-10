// TC: O(n ^ 3)
// SC: O(n ^ 2)

// Approach: Use a dp matrix to avoid repeated calculations. Start from
// arr of length 1, all the way to length n. At each arr, check all scenarios
// where you pop a specific balloon at the end. Take the max value.

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n][n];

        for (int length = 1; length <= n; length++) {
            for (int start = 0; start <= n - length; start++) {
                int end = start + length - 1;
                // burstable element one at a time (burst at end)
                int maxVal = 0;

                for (int i = start; i <= end; i++) {
                    int left = 0;
                    int right = 0;
                    int total = nums[i];

                    if (i != 0) {
                        left += dp[start][i - 1];
                    }

                    if (i != n - 1) {
                        right += dp[i + 1][end];
                    }

                    if (start - 1 >= 0) {
                        total *= nums[start - 1];
                    }

                    if (end + 1 < n) {
                        total *= nums[end + 1];
                    }

                    total += left + right;
                    maxVal = Math.max(total, maxVal);
                }

                dp[start][end] = maxVal;
            }
        }

        return dp[0][n - 1];
    }
}