import java.util.Map;

public class knightProbability{
    public static void main(String[] args){
        //System.out.println(knightProbability1(3,1,1,1));
        System.out.println(knightProbability1(8,30,6,4));
        System.out.println(0.00019*Math.pow(8,30));
    }
    public static void printarr(double [][]arr){
        for (int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    //要注意double[][][] dp=new double[K+1][N][N];多次记录后会溢出，要用double，不能用int和long
    public static double knightProbability1(int N, int K, int r, int c) {
        if(K==0) return 1;
        int[][] dir=new int[][]{{1,-2},{1,2},{-1,-2},{-1,2},{-2,-1},{2,1},{2,-1},{-2,1}};
        double[][][] dp=new double[K+1][N][N];
        double ans=0;
        dp[0][r][c]=1;
        for (int i=1;i<=K;i++){
            for (int xi=0;xi<N;xi++){
                for(int yi=0;yi<N;yi++) {
                    if(dp[i-1][xi][yi]>=1) {
                        for (int[] p : dir) {
                            int x = xi + p[0], y = yi + p[1];
                            if (x >= 0 && x < N && y >= 0 && y < N) {
                                dp[i][x][y]+=dp[i-1][xi][yi];
                                if(i==K)
                                    ans+=dp[i-1][xi][yi];
                            }
                        }
                    }
                }
            }
        }
        return ans/Math.pow(8,K);
    }
}
