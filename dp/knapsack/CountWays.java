package dp.knapsack;

public class CountWays {
    public int climbStairs(int n) {

        // Tabulation with space optimization
        int prev2 = 1, prev1 = 1, current = 0;
        for(int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1; // (or current)

    
        // // Memorization
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // return waysByMemo(n, dp);
        
        // // recursion
        // return ways(n);
    }

    int waysByTabulation(int n) {
        
        // // Tabulation
        int[] ways = new int[n + 1];
        ways[0] = ways[1] = 1;
        for(int i = 2; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n];
    }

    
    int ways(int n) {
        if(n == 0 || n == 1) return 1;

        return ways(n - 1) + ways(n - 2);
    }

    int waysByMemo(int n, int[] dp) {
        if(n == 0 || n == 1) {
            return dp[n] = 1;
        }

        if(dp[n] != -1) return dp[n];
        return dp[n] = waysByMemo(n - 1, dp) + waysByMemo(n - 2, dp);
    }
}
