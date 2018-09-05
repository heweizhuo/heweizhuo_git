package Other;

import java.util.*;

public class leastInterval {
    public static void main(String[] args){
        System.out.println(leastInterval1(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'},2));
    }
    public static int leastInterval1(char[] tasks, int n) {
        int[] cnt=new int[26];
        int max=0,maxcnt=0;
        for (int i=0;i<tasks.length;i++){
            cnt[tasks[i]-'A']++;
            if(cnt[tasks[i]-'A']>max){
                max=cnt[tasks[i]-'A'];
                maxcnt=1;
            }
            else if(cnt[tasks[i]-'A']==max)
                maxcnt++;
        }
        int part=max-1;
        int partLen=n+1-maxcnt;
        int leftTask=tasks.length-max*maxcnt;
        int emptySlot=part*partLen;
        int idel=Math.max(0,emptySlot-leftTask);
        int ans=tasks.length+idel;
        return ans;
    }
}
