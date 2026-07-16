class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int maxSum = Arrays.stream(nums).sum();
        int minSum = -maxSum;
        if(target < minSum || maxSum < target) return 0;

        int k = maxSum - minSum;
        int offset = -minSum;
        int[] prev = new int[k + 1];

        prev[nums[0] + offset] = 1;
        prev[-nums[0] + offset] = 1;
        if(nums[0] == 0) prev[offset] = 2;
        else {
            prev[offset + nums[0]] = 1;
            prev[offset - nums[0]] = 1;
        }

        // System.out.println(Arrays.toString(dp[0]));
        for(int i = 1; i < n; i++) {
            int[] dp = new int[k + 1];
            for(int j = 0; j <= k; j++) {
                if(0 <= j - nums[i] && j - nums[i] <= k) {
                    dp[j] += prev[j - nums[i]];
                }

                if(0 <= j + nums[i] && j + nums[i] <= k) {
                    dp[j] += prev[j + nums[i]];
                }
            }

            prev = dp;
        }

        return prev[target + offset];
    }

    int getCount(int index, int[] nums, int result, int k) {
        if(index == 0) {
            if(result + nums[0] == k || result - nums[0] == k)  {
                if(nums[0] == 0) return 2;
                return 1;
            }
            return 0;
        }

        return getCount(index - 1, nums, result + nums[index], k) + getCount(index - 1, nums, result - nums[index], k);
    }
}