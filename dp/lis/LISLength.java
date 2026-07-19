package dp.lis;

import java.util.Arrays;

class LISLength {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i - 1; j >= -1; j--) {
                int exclude = dp[i + 1][j + 1];
                int include = 0;
                if(j == -1 || nums[i] > nums[j]) {
                    include = 1 + dp[i + 1][i + 1];
                }

                dp[i][j + 1] = Math.max(include, exclude);
            }
        }

        return dp[0][0];
    }

    public int lengthOfLISMemo(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int[] arr : dp) Arrays.fill(arr, -1);
        return get(0, -1, nums, n, dp);
    }

    int get(int index, int prevIndex, int[] nums, int n, int[][] dp) {
        if(index == n ) return 0;

        if(dp[index][prevIndex + 1] != -1) return dp[index][prevIndex + 1];
        int exclude = get(index + 1, prevIndex, nums, n, dp);
        int include = 0;
        if(prevIndex == -1 || nums[index] > nums[prevIndex]) {
            include = 1 + get(index + 1, index, nums, n, dp);
        }

        return dp[index][prevIndex + 1] = Math.max(include, exclude);
    }
}