package backTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
//
//        例如，给出 n = 3，生成结果为：
//
//        [
//        "((()))",
//        "(()())",
//        "(())()",
//        "()(())",
//        "()()()"
//        ]

public class generateParenthesis {
    public static void main(String[] args){
        List<String > list=generateParenthesis1(3);

        for (String s:list){
            System.out.println(s);
        }
    }
    public static List<String> generateParenthesis1(int n) {
        Set<String> ansSet=new HashSet<String>();
        backTracking1(ansSet,n,n,"");
        List<String >ans=new ArrayList<>(ansSet);
        return ans;
    }
    public static void backTracking1(Set<String> result,int left,int right,String cur){
        if (left<0|| right<0)return;
        if(left==0 && right==0){
            String tmp=new String(cur);
            result.add(tmp);
            return;
        }
        if(left<=right) {
            backTracking1(result,left,right-1,cur+")");
            backTracking1(result,left-1,right,cur+"(");
        }
        else return;
    }
}
