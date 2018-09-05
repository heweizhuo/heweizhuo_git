public class Candy {
    public static void main(String[] args){
        Candy Solution=new Candy();
        System.out.println(Solution.candy(new int[]{1,2,2}));
    }
    public int candy(int[] score) {
        int len=score.length,sum=0;
        int []candy=new int[len];
        candy[0]=1;
        for (int i=1;i<len;i++){
            if(score[i]>score[i-1])
                candy[i]=candy[i-1]+1;
            else if(score[i]<score[i-1])
                candy[i]=Math.max(1,candy[i]-1);
            else candy[i]=1;
        }
        for (int i=len-2;i>=0;i--){
            if(score[i]>score[i+1] && candy[i]<=candy[i+1])
                candy[i]=candy[i+1]+1;
            else if(score[i]<score[i+1] && candy[i]>=candy[i+1])
                candy[i]=Math.max(1,candy[i+1]-1);
            sum+=candy[i+1];
        }
        return sum+candy[0];
    }
}
