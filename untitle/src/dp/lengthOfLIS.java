package dp;

public class lengthOfLIS {
    public int lengthOfLIS1(int[] nums) {
        int len=nums.length,max=0;
        int dp[]=new int[len];
        for(int i=1;i<=len;i++){
            dp[i]=1;
            for (int j=1;j<i;j++){
                if(nums[j-1]<=nums[i-1]){
                    dp[i]=dp[j]+1>dp[i]?dp[j]+1:dp[i];
                }
            }
            if(dp[i]>max){
                max=dp[i];
            }
        }
        return max;
    }
}
