package dp.stocks;

import java.util.Arrays;

public class OneStockManyOrders {
    public int maxProfit2(int[] prices) {
        int n = prices.length;

        int prevBuy = 0, prevSell = 0, currBuy = 0, currSell = 0;
        for(int i = n - 1; i >= 0; i--) {
            currSell = Math.max(  prices[i] + prevBuy, prevSell);
            currBuy = Math.max(- prices[i] + prevSell, prevBuy);
            
            prevBuy = currBuy;
            prevSell = currSell;
        }

        return prevBuy;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int k = Arrays.stream(prices).max().orElse(0);
        int[][] dp = new int[n][2];

        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
            
        int profit = getMax(0, prices, 1, dp);
        if( profit > 0) return profit;
        return 0;
    }

    int getMax(int i, int[] prices, int canBuy, int[][] dp) {
        if(i == prices.length) {
            return 0;
        }

        if(dp[i][canBuy] != -1) return dp[i][canBuy];
        if(canBuy == 1) {
            int buy = -prices[i] + getMax(i + 1, prices, 0, dp);
            int skip = getMax(i + 1, prices, 1, dp);
            return dp[i][canBuy] = Math.max(buy, skip);
        }

        int sell = prices[i] + getMax(i + 1, prices, 1, dp);
        int skip = getMax(i + 1, prices, 0, dp);
        return dp[i][canBuy] = Math.max(sell, skip);
    }
}