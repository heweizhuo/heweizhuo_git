package Math;

import java.util.*;

public class largestNumber {
    public static void main(String[] args){
        System.out.println("ans:"+largestNumber1(new int[]{830,8308}));
    }
    public static String largestNumber1(int[] nums) {
        List<String> strings=new LinkedList<>();
        int flag=0;
        for (int i=0;i<nums.length;i++){
            if(nums[i]!=0)flag=1;
            strings.add(String.valueOf(nums[i]));
        }
        if(flag==0)return "0";
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.charAt(0)==o2.charAt(0)) {
                    int minlen=o1.length()<o2.length()?o1.length():o2.length();
                    for (int i=0;i<minlen;i++){
                        if(o1.charAt(i)<o2.charAt(i))return 1;
                        if(o1.charAt(i)>o2.charAt(i))return -1;
                    }
                    if(o1.length()!=o2.length()){
                        return -(o1+o2).compareTo(o2+o1);//也可以直接返回这一句，不区分o1,o2的开始值，但是效率没有当前的方法高
                    }
                    return 0;
                }
                else return -o1.compareTo(o2);
            }
        });
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<strings.size();i++){
            System.out.println(strings.get(i));
            sb.append(strings.get(i));
        }
        return sb.toString();
    }
}
