package dp.strings;

public class LongCommSubstr {
    public int longCommSubstr(String s1, String s2) {
        // code here
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        int max = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                max = Math.max(dp[i][j], max);
            }
        }

        return max;
    }
}