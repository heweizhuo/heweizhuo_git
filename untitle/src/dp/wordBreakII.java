package dp;

import java.util.*;

public class wordBreakII {
    public static void main(String[] args){
        List<String> test=wordBreak("a",new ArrayList(Arrays.asList("a")));
        for (String s:test){
            System.out.println(s+" ");
        }
    }
    public static List<String> wordBreak(String s, List<String> wordDict) {
        int len=s.length();
        int dp[][]=new int[len+1][len+1];
        dp[0][0]=1;
        for (int i=0;i<len;i++){
            for (int j=i+1;j<=len;j++){
                String tmp=s.substring(i,j);
                if (wordDict.contains(tmp))
                    for (int k=i;k>=0;k--) {
                        if(dp[k][i]==1) {
                            dp[i][j] = 1;
                            break;
                        }
                    }
            }
        }
        for (int i=0;i<=len;i++){
            for (int j=0;j<=len;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        List<String> res=new LinkedList<>();
        Queue<String> queue=new LinkedList<>();
        for (int i=0;i<len;i++){
            if(dp[i][len]==1)
                queue.add(s.substring(i,len));
            while (!queue.isEmpty()){
                String top=queue.poll();
                int end=s.length()-top.replaceAll(" ","").length();
                for (int k=end;k>=0;k--){
                    if(dp[k][end]==1){
                        String cur=s.substring(k,end)+" "+top;
                        if(k==0) res.add(cur.trim());
                        else queue.add(cur);
                    }
                }
            }
        }
        return res;
    }
}
class Solution {
    private Map<String, List<String>> map = new HashMap<String, List<String>>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(map.containsKey(s)) return map.get(s);
        List<String> res = new ArrayList<String>();
        if(s.length()==0){
            res.add("");
            return res;
        }

        for(String word : wordDict){
            if(s.startsWith(word)){
                List<String> subWords = wordBreak(s.substring(word.length()), wordDict);
                for(String subWord : subWords){
                    res.add(word + (subWord.isEmpty() ? "" :" ") + subWord);
                }


            }
        }
        map.put(s, res);
        return res;
    }
}