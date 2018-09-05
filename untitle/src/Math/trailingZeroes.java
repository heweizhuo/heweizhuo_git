package Math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class trailingZeroes {
    public static void main(String[] args){
        int i=trailingZeroes2(10);
        System.out.println(i);
    }
    public static int trailingZeroes1(int n) {
        int ans=0;
        while(n%5==0 && n>4){
            ans+=n/=5;
        }
        return ans;
    }
    public static int trailingZeroes2(int n){
        List<Integer> odd=new ArrayList<>();
        List<Integer> even=new ArrayList<>();
        int ans=0;
        for (int i=2;i<=n;i++){
            if(i%2==0)even.add(i);
            else odd.add(i);
            while(odd.size()!=0 && even.size()!=0){
                int tmp=odd.get(odd.size()-1)*even.get(even.size()-1);
                odd.remove(odd.size()-1);
                even.remove(even.size()-1);
                while(tmp%10==0 && tmp!=0){
                    ans++;
                    tmp=tmp/10;
                }
                if(tmp!=0){
                    if(tmp%2==0)even.add(tmp);
                    else odd.add(tmp);
                }
            }
        }
        for (int num:even) {

        }
        return ans;
    }
}
