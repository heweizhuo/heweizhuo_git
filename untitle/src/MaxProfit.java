public class MaxProfit {
    public static void main(String[] args){
        MaxProfit Solution = new MaxProfit();
        System.out.println("\n"+Solution.maxProfitII(new int[]{3,2,6,5,0,3}));
    }
    public int maxProfit(int[] prices) {
        int max=0;
        for (int i=0;i<prices.length-1;i++){
            for (int j=i+1;j<prices.length;j++){
                max=Math.max(max,prices[j]-prices[i]);
            }
        }
        return max;
    }
    public int maxProfitII(int[] prices) {
        if(prices.length==0) return 0;
        int len=prices.length;
        int []dp=new int[len+1];
        for (int i=1;i<=len;i++){
            for (int j=i-1;j>0;j--){
                dp[i]=Math.max(dp[i],dp[j]+Math.max(0,prices[i-1]-prices[j-1]));
            }
        }
//        for(int i=0;i<=len;i++){
//            System.out.print(dp[i]+" ");
//        }
        return dp[len];
    }
}
