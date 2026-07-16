class Coin {
    int INF = 100_000;
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);

        for(int k = 0; k <= amount; k++) {
            if(k % coins[0] == 0)
                dp[k] = k / coins[0];
        }

        for(int i = 1; i < n; i++) {
            for(int k = 0; k <= amount; k++) {
                int notTakeCount = dp[k];

                int takeCount = INF;
                if(k >= coins[i] && dp[k - coins[i]] != INF) {
                    takeCount = 1 + dp[k - coins[i]];
                }

                dp[k] = Math.min(notTakeCount, takeCount);
            }

            // System.out.println(Arrays.toString(dp[i]));
        }

        return dp[amount] == INF ? -1 : dp[amount];
        // int[][] dp = new int[coins.length][amount + 1];
        // for(int i = 0; i < coins.length; i++) Arrays.fill(dp[i], -2);;
        // return getCount(coins.length - 1, coins, amount, dp);
    }

    // TC without memoization : (number of coins : N) ^ Amount;
    int getCount(int index, int[] coins, int k, int[][] dp) {
        if(k == 0) return 0;

        if(index == 0) {
            if(k % coins[0] == 0) {
                return dp[index][k] = k / coins[0];
            } 

            return dp[index][k] = -1;
        }

        if(dp[index][k] != -2) return dp[index][k];
        int notTakeCount = getCount(index - 1, coins, k, dp);

        int takeCount = -1;
        if(k >= coins[index]) {
            takeCount = getCount(index, coins, k - coins[index], dp);
        }

        if(notTakeCount == -1 && takeCount == -1) return dp[index][k] = -1;
        else if(notTakeCount == -1) return dp[index][k] = 1 + takeCount;
        else if(takeCount == -1) return dp[index][k] = notTakeCount;
        else return dp[index][k] = Math.min(notTakeCount, 1 + takeCount);
    }
}