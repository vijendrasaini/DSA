package dp.partition;

import java.util.Arrays;

public class MCM {
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int length = 2; length < n; length++) {
            for(int i = 0; i + length < n; i++) {
                int j = i + length;
                
                int min = Integer.MAX_VALUE;
                for(int k = i + 1; k < j; k++) {
                    int leftPart = dp[i][k];
                    int rightPart = dp[k][j];
                    int opsAtPart = arr[i] * arr[k] * arr[j];
                    min = Math.min(min, leftPart + rightPart + opsAtPart);
                }
                
                dp[i][j] = min;
            }
        }
        
        return dp[0][n - 1];
    }

    static int matrixMultiplicationTabulation(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 2; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for(int k = i + 1; k < j; k++) {
                    int leftPart = dp[i][k];
                    int rightPart = dp[k][j];
                    int opsAtPart = arr[i] * arr[k] * arr[j];
                    min = Math.min(min, leftPart + rightPart + opsAtPart);
                }
                
                dp[i][j] = min;
            }
        }
        
        return dp[0][n - 1];
    }

    static int matrixMultiplicationMemo(int arr[]) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        return get(0, n - 1, arr, dp);
    }
    
    
    static int get(int i, int j, int[] arr, int[][] dp) {
        if(i == j || i + 1 == j) return dp[i][j] = 0;
        
        if(dp[i][j] != -1) return dp[i][j];
        int min = Integer.MAX_VALUE;
        for(int k = i + 1; k < j; k++) {
            int leftPart = get(i, k, arr, dp);
            int rightPart = get(k, j, arr, dp);
            int opsAtPart = arr[i] * arr[k] * arr[j];
            min = Math.min(min, leftPart + rightPart + opsAtPart);
        }
        
        return dp[i][j] = min;
    }
}
