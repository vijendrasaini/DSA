package dp;

public class Knapsack {
    int getMax(int index, int total, int[] nums, int[] dp) {
        if (index < 0)
            return total;

        if (dp[index] > -1)
            return dp[index];
        int taken = nums[index] + getMax(index - 2, total, nums, dp);
        int notTaken = getMax(index - 1, total, nums, dp);

        return dp[index] = Math.max(taken, notTaken);
    }
}
