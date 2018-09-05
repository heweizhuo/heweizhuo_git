package Math;

import java.util.HashSet;
import java.util.Set;

public class isHappy {
    public boolean isHappy1(int n) {
        Set set=new HashSet();
        while(n!=1){
            if(set.contains(n))
                return false;
            set.add(n);
            int tmp=n;
            n=0;
            while(tmp!=0){
                n+=(int)Math.pow(tmp%10,2);
                tmp=tmp/10;
            }
        }
        return true;
    }
}
