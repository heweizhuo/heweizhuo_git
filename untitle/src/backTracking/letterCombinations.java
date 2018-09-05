package backTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class letterCombinations {
    public static void main(String[] args){
        letterCombinations letterCombinations=new letterCombinations();
        List<String> t=letterCombinations.letterCombinations1("");
        for (String s:t){
            System.out.println(s);
        }
    }

    String numMap2Str[]=new String[]{" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations1(String digits) {
        if (digits.length()==0)return new ArrayList<>();
        Set<String> stringSet=new HashSet<>();
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<digits.length();i++){
            stringList.add(numMap2Str[digits.charAt(i)-'0']);
        }
        letterCombinationsBackTracking(stringSet,stringList,"",0);
        List<String> ans=new ArrayList<>();
        ans.addAll(stringSet);
        return ans;
    }
    public void letterCombinationsBackTracking(Set<String> stringSet,List<String> stringList,String str,int index){
        if(index==stringList.size()){
            String tmp=new String(str);
            stringSet.add(tmp);
            return;
        }
        for (int i=0;i<stringList.get(index).length();i++){
            letterCombinationsBackTracking(stringSet,stringList,str+stringList.get(index).charAt(i),index+1);
        }
    }
}
