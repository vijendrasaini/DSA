package dp.strings.transformation;

import java.util.Arrays;

public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        int n1 = s.length(), n2 = p.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for(int[] arr : dp) Arrays.fill(arr, -1);
        dp[0][0] = 1;
        boolean isStar = true;
        for(int j = 1; j <= n2; j++) {
            if(p.charAt(j - 1) != '*') isStar = false;
            dp[0][j] = isStar ? 1 : 0;
        }

        return isValid(s.length(), p.length(), s, p, dp) == 1;
    }

    public int isValid(int i, int j, String si, String sj, int[][] dp) {
        if(i == 0 && j == 0) return 1;
        if(i == 0) return dp[0][j];
        if(j == 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];
        if(si.charAt(i - 1) == sj.charAt(j - 1) || sj.charAt(j - 1) == '?') {
            return dp[i][j] = isValid(i - 1, j - 1, si, sj, dp);
        }

        if(sj.charAt(j - 1) == '*') {
            // * can play 3 roles
            boolean eatAll = isValid(i - 1, j, si, sj, dp) == 1;
            boolean eatOne = isValid(i - 1, j - 1, si, sj, dp) == 1;
            boolean noEating = isValid(i, j - 1, si, sj, dp) == 1;
            return dp[i][j] = (eatAll || eatOne || noEating) ? 1 : 0;
        }

        return dp[i][j] = 0;
    }

    boolean isStartPattern(int n, String b) {
        for(int j = 0; j < n; j++) if(b.charAt(j) != '*') return false;
        return true;
    }
}
