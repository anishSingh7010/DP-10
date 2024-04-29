class SuperEggDrop {
    // TC: O(k * n^2)
    // SC: O(n * k)

    // Approach: For each floor; start with 1 egg
    // and find break no break scenario starting from 1 floor.
    // Take the min number of attemps

    public int superEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }

        int[][] dp = new int[k + 1][n + 1];
        // for 1 egg; need to throw it from all floors
        for (int i = 1; i <= n; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                // break/no break for each floor
                for (int f = 1; f <= j; f++) {
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i - 1][f - 1], dp[i][j - f]));
                }
            }
        }

        return dp[k][n];
    }

    // TC: O(n * k)
    // SC: O(n * k)

    // Approach: Starting with 1 egg, see how many
    // floors can be covered with 1 attempt. Keep increasing
    // the attemps and the eggs. When you see a value more than or
    // equal to the floors, that's the answer

    public int superEggDropOptimal(int k, int n) {
        if (k == 1) {
            return n;
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = 1 + dp[i - 1][j - 1] + dp[i - 1][j];
            }

            if (dp[i][k] >= n) {
                return i;
            }
        }

        return n;
    }
}