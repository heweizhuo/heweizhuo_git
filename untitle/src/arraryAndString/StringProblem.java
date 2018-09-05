package arraryAndString;

import java.util.*;

public class StringProblem {
    public static void main(String[] args){
        StringProblem stringProblem=new StringProblem();
        System.out.println(stringProblem.lengthOfLongestSubstring("abcabcbb"));//abcabcbb
    }

    public String longestPalindrome(String s) {
        int max=0;
        String ans="",s1="";
        for (int i=0;i<s.length();i++){
            s1=lengthOfPalindrome(s,i,i);
            if(s1.length()>max){
                max=s1.length();
                ans=s1;
            }
            s1=lengthOfPalindrome(s,i,i+1);
            if(s1.length()>max){
                max=s1.length();
                ans=s1;
            }
        }
        return ans;
    }

    public static String lengthOfPalindrome(String s,int left,int right){
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return s.substring(left+1,right);
    }


    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) return 0;
        int ans=0,start=0;
        Map<Character,Integer> map=new HashMap<>();
        for (int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                start=Math.max(start,1+map.get(s.charAt(i)));
            }
            map.put(s.charAt(i),i);
            ans=Math.max(ans,i-start+1);
        }
        return ans;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length==0)return new ArrayList<>();
        Map<List<Character>,List<String>> map=new HashMap<>();
        List<List<String>> res=new ArrayList<>();
        for (int i=0;i<strs.length;i++){
            List<Character> characters=new ArrayList<>();
            for (int j=0;j<strs[i].length();j++){
                characters.add(strs[i].charAt(j));
            }
            Collections.sort(characters);
            if(!map.containsKey(characters)){
                map.put(characters,new ArrayList(Arrays.asList(strs[i])));
            }
            else {
                List<String> tmp=map.get(characters);
                tmp.add(strs[i]);
                map.put(characters,tmp);
            }
        }
        for(Map.Entry<List<Character>,List<String>> entry:map.entrySet()){
            Collections.sort(entry.getValue());
            res.add(entry.getValue());
        }
        return res;
    }
}
