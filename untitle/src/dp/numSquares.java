package dp;

public class numSquares {
    public static void main(String[] args){
        System.out.println(numSquares1(12));
        System.out.println(numSquares1(13));
    }
    public static int numSquares1(int n) {
        int dp[]=new int[n+1];
        dp[1]=1;
        for (int i=2;i<=n;i++){
            int min=Integer.MAX_VALUE;
            for (int j=(int)Math.sqrt(i);j>=1;j--){
                int tmp=1+dp[i-j*j];
                min=min<tmp?min:tmp;
            }
            dp[i]=min;
        }
        return dp[n];
    }
}
