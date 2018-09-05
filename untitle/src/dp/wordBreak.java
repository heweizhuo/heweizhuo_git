package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class wordBreak {
    public static void main(String[] args){
        System.out.println(wordBreak1("a",new ArrayList(Arrays.asList("a"))));
    }
    public static boolean wordBreak1(String s, List<String> wordDict) {
        int dp[]=new int[s.length()+1];
        dp[0]=1;
        for (int i=0;i<s.length();i++){
            for (int j=i;j>=0;j--){
                if(dp[j]==1 && wordDict.contains(s.substring(j,i+1))){
                    dp[i+1]=1;
                    break;
                }
            }
        }
        if(dp[s.length()]==1)return true;
        return false;
    }
    public int maxProduct(int[] nums) {
        int[] dpmax=new int[nums.length+1],dpmin=new int[nums.length+1];
        int max=Integer.MIN_VALUE;
        for (int i=0;i<=nums.length;i++){
            dpmax[i]=1;
            dpmin[i]=1;
        }
        for (int i=1;i<=nums.length;i++){
            int tmpMax=Math.max(dpmax[i-1]*nums[i-1],dpmin[i-1]*nums[i-1]);
            int tmpMin=Math.min(dpmax[i-1]*nums[i-1],dpmin[i-1]*nums[i-1]);
            dpmax[i]=Math.max(tmpMax,dpmax[i]*nums[i-1]);
            dpmin[i]=Math.min(tmpMin,dpmin[i]*nums[i-1]);
            max=Math.max(dpmax[i],max);
        }
        return max;
    }
}
