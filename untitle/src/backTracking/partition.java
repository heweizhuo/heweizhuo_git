package backTracking;

import java.util.*;

public class partition {
    public static void main(String[] args){
        partition partition=new partition();
        List<List<String>> ans=partition.partition1("abbab");
        for (List<String> list:ans){
            for (String s:list){
                System.out.print(s+",");
            }
            System.out.println();
        }
    }
    public List<List<String>> partition2(String s) {
        List<List<String>> res=new LinkedList<>();
        bt2(res,0,s,new ArrayList<>());
        return res;
    }
    public static void bt2(List<List<String>> res,int start, String s,List<String> cur){
        if(start==s.length()){
            List<String> tmp=new LinkedList<>();
            tmp.addAll(cur);
            res.add(tmp);
            return;
        }
        for (int i=start;i<s.length();i++){
            if(isPalindromes(s,start,i)){
                cur.add(s.substring(start,i+1));
                bt2(res,i+1,s,cur);
                cur.remove(cur.size()-1);
            }
        }
    }
    public static boolean isPalindromes(String s,int i,int j){
        int low=i,hi=j;
        while (low<hi){
            if(s.charAt(low)!=s.charAt(hi)) return false;
            low++;hi--;
        }
        return true;
    }
    //超时
    public List<List<String>> partition1(String s) {
        List<String> Palindromes=new LinkedList<>();
        Set<List<String>> ans=new HashSet<>();
        List<List<String>> anslist=new LinkedList<>();
        for (int i=0;i<s.length();i++){
            Palindromes.addAll(curPosPalindromes(s,i,i));
            Palindromes.addAll(curPosPalindromes(s,i,i+1));
        }
        bt(s,ans,Palindromes,new LinkedList<>(),"",0);
        anslist.addAll(ans);
        return anslist;
    }
    public static List<String> curPosPalindromes(String s,int i,int j){
        List<String> Palindromes=new LinkedList<>();
        while (i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){
            Palindromes.add(s.substring(i,j+1));
            i--;j++;
        }
        return Palindromes;
    }
    public static void bt(String s, Set<List<String>> ans, List<String> Palindromes, List<String> cur,String curstr, int i){
        if(curstr.equals(s)){
            List<String > tmp=new LinkedList<>();
            tmp.addAll(cur);
            ans.add(tmp);
            return;
        }
        if(!s.contains(curstr)) return;
        for (int k=i;k<Palindromes.size();k++){
            cur.add(Palindromes.get(k));
            bt(s,ans,Palindromes,cur,curstr+Palindromes.get(k),k+1);
            cur.remove(cur.size()-1);
        }
    }
}
