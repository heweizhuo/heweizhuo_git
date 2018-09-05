package Math;

import java.util.*;

public class fourSumCount {
    public static void main(String[] args){
        int[] A=new int[]{1,2},B=new int[]{-2,-1},C=new int[]{-1,2},D=new int[]{0,2};
        fourSumCount fourSumCount=new fourSumCount();
        System.out.println(fourSumCount.fourSumCount1(A,B,C,D));
    }
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        int N=A.length,ans=0;
        HashMap<Integer,Integer> AB=new HashMap<>(),CD=new HashMap<>();
        for (int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int tmp1=A[i]+B[j],tmp2=C[i]+D[j];
                AB.put(tmp1,AB.getOrDefault(tmp1,0)+1);
                CD.put(tmp2,CD.getOrDefault(tmp2,0)+1);
            }
        }
        for (Map.Entry<Integer,Integer> e:AB.entrySet()){
            if(CD.containsKey(0-e.getKey())) {
                ans+=e.getValue()*CD.get(0-e.getKey());
            }
        }
        return ans;
    }
}
