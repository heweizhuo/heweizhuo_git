import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class test {

/** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    static int sticker(int[] score) {
        int sum=0,left=0,right=0,last=0,equal=0;
        List<Integer> peek=new LinkedList<>();
        for (int i=1;i<score.length;){
            while (i<score.length && score[i-1]<score[i])
                i++;
            left=i-last;
            last=i-1;
            while (i<score.length && score[i-1]==score[i]) {
                i++;
                equal++;
            }
            while (i<score.length && score[i-1]>score[i])
                i++;
            right=i-last;
            int max=Math.max(left,right),min=Math.min(left,right)-1;
            sum+=(1+max)*max/2+equal*max+(1+min)*min/2;
            left=0;right=0;last=i+1;
            if(last==score.length) {
                sum += min + 1;
                break;
            }
        }
        return sum;
    }
    public int LastRemaining_Solution2(int n, int m)
    {
        // invalid input
        if(n <= 0 || m < 0)
            return -1;

        // if there are only one integer in the circle initially,
        // of course the last remaining one is 0
        int lastinteger = 0;

        // find the last remaining one in the circle with n integers
        for (int i = 2; i <= n; i ++)
            lastinteger = (lastinteger + m) % i;

        return lastinteger;
    }
    public int LastRemaining_Solution1(int n, int m)
    {
        // invalid input
        if(n <= 0 || m < 0)
            return -1;

        // if there are only one integer in the circle initially,
        // of course the last remaining one is 0
        int index=0;
        List<Integer> list=new LinkedList<>();
        for (int i=0;i<n;i++){
            list.add(i);
        }
        while (list.size()>1){
            index=(index+m-1)%list.size();
            System.out.print(list.get(index)+" ");
            list.remove(index);
        }
        return list.get(0);
    }
    public static void main(String[] args) {
//        test test=new test();
//        System.out.println(test.LastRemaining_Solution2(50,3));
//        System.out.println(test.LastRemaining_Solution1(50,3));
    }
}
