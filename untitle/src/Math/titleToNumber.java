package Math;

import java.util.HashMap;
import java.util.Map;

public class titleToNumber {
    public static void main(String[] args){
        System.out.println(fractionToDecimal(-7,12));
        //System.out.println(divide(Integer.MIN_VALUE,Integer.MIN_VALUE));
        //System.out.println(divide(-1,-1));
        //System.out.println(divide(Integer.MIN_VALUE,1));
        //System.out.println(divide(Integer.MIN_VALUE,-1));
        //System.out.println(divide(7,-3));

        //System.out.println(titleToNumber("ZY"));
        //System.out.println(mySqrt(2));
        //System.out.println(mySqrt(2147395599));
        //System.out.println(myPow(2.00000, -2147483648));
        //System.out.println("min:"+Integer.MIN_VALUE);
        //System.out.println("long:"+(-Long.valueOf(Integer.MIN_VALUE)));
        //System.out.println("long:"+(-Long.valueOf(Integer.MIN_VALUE)));
        //System.out.println("-2147483648");
        /*System.out.println(myPow(2,10));
        System.out.println(myPow(2,9));
        System.out.println(myPow(2,8));
        System.out.println(myPow(2,7));
        System.out.println(myPow(2,6));
        System.out.println(myPow(2,5));
        System.out.println(myPow(2,4));
        System.out.println(myPow(2,3));
        System.out.println(myPow(2,2));
        System.out.println("---------");
        System.out.println(myPow(3,10));
        System.out.println(myPow(3,9));
        System.out.println(myPow(3,8));
        System.out.println(myPow(3,7));
        System.out.println(myPow(3,6));
        System.out.println(myPow(3,5));
        System.out.println(myPow(3,4));
        System.out.println(myPow(3,3));
        System.out.println(myPow(3,2));
        System.out.println(myPow(3,1));*/
    }
    public static int titleToNumber(String s) {
        int ans=0;
        for (int i=0;i<s.length();i++){
            ans=ans*26+s.charAt(i)-'A'+1;
        }
        return ans;
    }
    public static double myPow(double x, int n) {
        if(n==0)return 1;
        double tmp=n>0?x:1/x,ans=tmp;
        long y=1,tmpn=n>0?n:-Long.valueOf(n);
        while (y < tmpn) {
            ans*=tmp;
            y=y*2;
            tmp*=tmp;
            if(y*2>tmpn){
                tmp=n>0?x:1/x;
                tmpn=tmpn-y+1;
                y=1;
            }
        }
        //System.out.println(ans);
        return  Double.valueOf(String.format("%.5f",ans));
    }
    public static int mySqrt(int x) {
        if(x==0 || x==1)return x;
        long low=0,hi=x;
        while(low<=hi){
            long mid=(low+hi)/2,tmp=x;
            if(mid*mid<tmp && (mid+1)*(mid+1)>tmp)
                return (int)mid;
            else if(mid*mid==tmp)return (int)mid;
            else if((mid+1)*(mid+1)==tmp)return (int)(mid+1);
            if(mid*mid>tmp)hi=mid;
            else low=mid;
        }
        return -1;
    }
    public static int divide(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE && divisor==-1)
            return Integer.MAX_VALUE;
        /*if(dividend==Integer.MIN_VALUE && divisor==1)
            return Integer.MIN_VALUE;*/
        int ans=0,flag=1;
        long ins=1,dividendLong=dividend,divisorLong=divisor;
        dividendLong=Math.abs(dividendLong);
        divisorLong=Math.abs(divisorLong);
        if((dividend>>>31 ^ divisor>>>31)==1)
            flag = -1;
        while(dividendLong-divisorLong>=0){
            dividendLong-=divisorLong;
            ans+=ins;
            divisorLong*=2;
            ins*=2;
            while(dividendLong-divisorLong<0 && divisorLong>Math.abs((long)divisor)){
                divisorLong/=2;
                ins/=2;
            }
        }
        return ans*flag;
    }
    public static String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0 || denominator==0)
            return String.valueOf(0);
        long num=numerator,den=denominator,remainder=0,len=0;
        StringBuilder integer=new StringBuilder();
        if((numerator>>>31 ^ denominator>>>31)==1){
            integer.insert(0,"-");
            num= Math.abs(num);
            den=Math.abs(den);
        }
        integer.append(num/den);
        remainder=num%den;
        if(remainder!=0) {
            StringBuilder stringBuilder = new StringBuilder();
            Map<Long, Integer> map = new HashMap<>(); //前面数字，后面位置
            map.put(remainder,0);
            int cur=1,pos=0;
            while (remainder != 0) {
                remainder *= 10;
                stringBuilder.append(remainder/den);
                if (!map.containsKey(remainder % den)) {
                    map.put(remainder%den, cur);
                }
                /**
                 * 余数重复的时候一定循环
                 * */
                else {
                    pos = map.get(remainder % den);
                    String pre=stringBuilder.substring(0,pos);
                    String loop="("+stringBuilder.substring(pos)+")";
                    return integer+"."+pre+loop;
                }
                remainder %= den;
                cur++;
            }
            return integer+"."+stringBuilder.toString();
        }
        else return String.valueOf(integer);
    }
}
