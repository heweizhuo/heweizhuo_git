package backTracking;

import java.util.*;

public class removeInvalidParentheses {
    public static void main(String[] args){
       List<String> res=removeInvalidParentheses1(")(((()(y((u()(z()()");
       System.out.println("-----------");
       for (String s:res){
           System.out.println(s);
       }
    }
    public static List<String> removeInvalidParentheses1(String s) {
        List<String> res=new LinkedList<>();
        if(isValid(s)) {
            res.add(s);
            return res;
        }
        Map<String,Integer> map=new HashMap<>();
        map.put(s,0);
        BTRemove(res,map,0);
        if(res.isEmpty())res.add("");
        return res;
    }
    public static void BTRemove(List<String>res,Map<String,Integer> next,int start){
        Map<String,Integer> nextString=new HashMap<>();
        for (String nextstr:next.keySet()) {
            if(nextstr.length()==1)break;
            for (int i = next.get(nextstr); i < nextstr.length(); i++) {
                if(nextstr.charAt(i)=='(' || nextstr.charAt(i)==')') {
                    String cur;
                    if (i + 1 < nextstr.length()) cur = nextstr.substring(start, i) + nextstr.substring(i + 1);
                    else cur = nextstr.substring(start, i);
                    if (!nextString.containsKey(cur)) {
                        if (isValid(cur) && !res.contains(cur)) res.add(cur);
                        nextString.put(cur,i);
                    }
                }
            }
        }
        if(res.isEmpty() && !nextString.isEmpty())
            BTRemove(res,nextString,0);
    }
    public static boolean isValid(String s){
        int valid=0;
        for (int i=0;i<s.length();i++){
            if(s.charAt(i)=='(')
                valid++;
            else if(s.charAt(i)==')')valid--;
            if(valid<0)return false;
        }
        if(valid==0)
            return true;
        return false;
    }
}
