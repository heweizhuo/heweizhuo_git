package dp;

import java.util.Arrays;

public class coinChange {
    public static void main(String[] args){
        int i=coinChange(new int[]{1},0);
        System.out.println(i);
    }
    public static int coinChange(int[] coins, int amount) {
        int dp[]=new int[amount+1];
        dp[0]=0;
        for (int i=1;i<=amount;i++){
            dp[i]=-1;
            for (int j=0;j<coins.length;j++){
                if(i-coins[j]>=0 && dp[i-coins[j]]!=-1){
                    if(dp[i]==-1 || dp[i-coins[j]]+1<dp[i]){
                        dp[i]=dp[i-coins[j]]+1;
                    }
                }
            }
        }
        return  dp[amount];
    }

    public int coinChange_jiuzhang(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int n = coins.length;

        dp[0] = 0;
        int i, j;
        for (i = 1; i <= amount; ++i) {
            dp[i] = -1;
            for (j = 0; j < n; ++j) {
                if (i >= coins[j] && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1 || dp[i - coins[j]] + 1 < dp[i]) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }
}
