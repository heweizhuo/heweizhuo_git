package dp;

public class canJump {
    //[2,3,1,1,4]
    public static void main(String[] args) {
        //Scanner scanner=new Scanner(System.in);
        int[] nums=new int[]{2,3,1,1,4};
        canJump(nums);
    }
    public static boolean canJump(int[] nums) {
        int dp[]=new int[nums.length];
        dp[0]=1;
        for (int i=0;i<nums.length;i++){
            if(dp[i]==1){
                for (int j=1;j<=nums[i] && (i+j)<nums.length;j++)
                    dp[i+j]=1;
            }
        }
        for (int i=0;i<dp.length;i++)
            System.out.println(dp[i]);
        if(dp[nums.length-1]==1)return true;
        else return false;
    }
}
