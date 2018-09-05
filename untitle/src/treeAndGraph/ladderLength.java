package treeAndGraph;

import java.util.*;

public class ladderLength {
    public static void main(String[] args){
        ladderLength ladderLength=new ladderLength();
        System.out.println(ladderLength.ladderLength1("hit","cog",new ArrayList(Arrays.asList("hot","dot","dog","lot","log","cog"))));
    }
//    Set<String> visited=new HashSet<>();
    class walk{
        String key;
        int value;
        public walk(String key,int value){
            this.key=key;
            this.value=value;
        }
    }
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) return 0;
        if (!wordList.contains(endWord)) return 0;
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        HashSet<String> hashSet = new HashSet<>(wordList);
        q1.add(beginWord);
        q2.add(endWord);
        hashSet.remove(endWord);
        return twoEndBFS(q1, q2, hashSet, 2);
    }

    private int twoEndBFS(Set<String> q1, Set<String> q2, Set<String> dic, int len) {
        if (q1.isEmpty() || q2.isEmpty())
            return 0;
        if (q1.size() > q2.size())
            return twoEndBFS(q2, q1, dic, len);
        Set<String> temp = new HashSet<>();
        for (String word : q1) {
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                for (char newC = 'a'; newC <= 'z'; newC++) {
                    arr[i] = newC;
                    String newS = String.valueOf(arr);
                    if (q2.contains(newS))
                        return len;
                    if (dic.contains(newS)) {
                        temp.add(newS);
                        dic.remove(newS);
                    }
                }
                arr[i] = c;
            }
        }
        return twoEndBFS(temp, q2, dic, len + 1);
    }
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        int len=wordList.size();
        if(!wordList.contains(endWord) || beginWord.equals(endWord)) return 0;
        Queue<walk> queue=new LinkedList<>();
        queue.add(new walk(beginWord,1));
        while(!queue.isEmpty()){
            walk top=queue.poll();
            if(top.value>len) return 0;
            for (int i=wordList.size()-1;i>=0;i--){
                String s=wordList.get(i);
                if(compare(s,top.key)==1){
                    if(s.equals(endWord))
                        return top.value+1;
                    queue.add(new walk(s,top.value+1));
                    wordList.remove(i);
                }
            }
        }
        return 0;
    }
    public static int compare(String s1,String s2){
        int ans=0;
        for (int i=0;i<s1.length() && ans<=1;i++){
            if(s1.charAt(i)!=s2.charAt(i))
                ans++;
        }
        return ans;
    }
}
