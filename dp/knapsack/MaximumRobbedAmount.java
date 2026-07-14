package dp.knapsack;

import java.util.Arrays;

public class MaximumRobbedAmount {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return knapsack(nums.length - 1, nums, dp);
    }

    int knapsack(int index, int[] arr, int[] dp) {
        if(index == 0) {
            return dp[index] = arr[0];
        }

        if(index == 1) {
            return dp[index] = Math.max(arr[0], arr[1]);
        }

        if(dp[index] != -1) return dp[index];
        return dp[index] = Math.max(knapsack(index - 1, arr, dp), arr[index] + knapsack(index - 2, arr, dp));
    }
}