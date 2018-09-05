package dp;

public class maxProfit {
    public static void main(String[] args){
        System.out.println(maxProfit1(new int[]{1,2,3,0,2}));
    }
    public static int maxProfit1(int[] prices) {
        int len=prices.length,ans=0;
        int dp[]=new int[len+1];
        for (int i=1;i<=len;i++){
            int max=0;
            for (int k=i-1;k>=0;k--){
                int tmp;
                if(k>=1)
                    tmp=prices[i-1]-prices[k]+dp[k-1];
                else tmp=prices[i-1]-prices[k];
                max=tmp>max?tmp:max;
                max=max>dp[k]?max:dp[k];
            }
            dp[i]=max;
            ans=ans>max?ans:max;
        }
        return ans;
    }
}
