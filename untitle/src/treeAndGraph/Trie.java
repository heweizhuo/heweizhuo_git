package treeAndGraph;

import java.util.*;

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        if(board.length==0 || words.length==0) return new LinkedList<>();
        Trie trie=new Trie();
        List<String> res=new LinkedList<>();
        for (int i=0;i<words.length;i++)
            trie.insert(words[i]);
        int row=board.length,col=board[0].length;
        trie=trie.Tries.get(' ');
        for(int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(trie.Tries.containsKey(board[i][j])){
                    String tmp="";
                    BTSearch(res,trie,board,i,j,tmp);
                }
            }
        }
        return res;
    }
    public void BTSearch(List<String> res,Trie trie,char[][] board,int i,int j,String tmp){
        if(board[i][j]=='#' || !trie.Tries.containsKey(board[i][j]))
            return;
        trie = trie.Tries.get(board[i][j]);
        if(trie.Tries.containsKey(' ')){
            res.add(tmp+board[i][j]);
            trie.Tries.remove(' ');
        }
        char c = board[i][j];
        tmp+=c;
        board[i][j] = '#';
        if (i + 1 < board.length) BTSearch(res,trie, board, i + 1, j,tmp);
        if (i - 1 >= 0) BTSearch(res,trie, board, i - 1, j,tmp);
        if (j + 1 < board[0].length) BTSearch(res,trie, board, i, j + 1,tmp);
        if (j - 1 >= 0) BTSearch(res,trie, board, i, j - 1,tmp);
        board[i][j] = c;
    }
}
class Trie {
    public static void main(String[] args){
        Solution solution=new Solution();
//[["s","e","e","n","e","w"],["t","m","r","i","v","a"],["o","b","s","i","b","d"],["w","m","y","s","e","n"],["l","t","s","n","s","a"],["i","e","z","l","g","n"]]
//["pluma","holm","lippen","trag","milla","bietle","upbind","waxy","knead","nickle","reem","skice","unde","hain","savant

//输出：["stow","stob","seer","seen","embow","neem","wadna","wave","wene","renew","reem","reest","rine","rive","riva","inerm","irene","vine","viner","vire","avener","avine","bowl","sise","besa","bena","bend","daven","wots","myst","send","teil","sang","sand","sane","anda","anes","anesis","nane"]
//预期：["anda","anes","anesis","avener","avine","bena","bend","benda","besa","besan","bowl","daven","embow","inerm","irene","myst","nane","nanes","neem","reem","reest","renew","rine","riva","rive","riven","sand","sane","sang","seen","seer","send","sise","stob","stow","teil","vine","viner","vire","wadna","wave","wene","wots"]

//        List<String> res=solution.findWords(new char[][]{{'a','b'},{'c','d'}},new String[]{"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"});
        List<String> res=solution.findWords(new char[][]{{'s','e','e','n','e','w'},{'t','m','r','i','v','a'},{'o','b','s','i','b','d'},{'w','m','y','s','e','n'},{'l','t','s','n','s','a'},{'i','e','z','l','g','n'}},new String[]{"bena","bend","benda","besa","besan","bowl"});
        for (String s:res){
            System.out.println(s);
        }
//        String[] t1=new String[]{"stow","stob","seer","seen","embow","neem","wadna","wave","wene","renew","reem","reest","rine","rive","riva","inerm","irene","vine","viner","vire","avener","avine","bowl","sise","besa","bena","bend","daven","wots","myst","send","teil","sang","sand","sane","anda","anes","anesis","nane"};
//        String[] t2=new String[]{"anda","anes","anesis","avener","avine","bena","bend","benda","besa","besan","bowl","daven","embow","inerm","irene","myst","nane","nanes","neem","reem","reest","renew","rine","riva","rive","riven","sand","sane","sang","seen","seer","send","sise","stob","stow","teil","vine","viner","vire","wadna","wave","wene","wots"};
//        Arrays.sort(t1);
//        Arrays.sort(t2);
//        for (String s:t1)
//            System.out.print(s+",");
//        System.out.println();
//        for (String s:t2)
//            System.out.print(s+",");

    }

    Map<Character,Trie> Tries=new HashMap<>();
    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Map<Character,Trie> tmp=this.Tries;
        if(tmp.size()==0)
            tmp.put(' ',new Trie());
        tmp=tmp.get(' ').Tries;
        for (int i=0;i<word.length();i++){
            if(!tmp.containsKey(word.charAt(i))){
                tmp.put(word.charAt(i),new Trie());
            }
            tmp=tmp.get(word.charAt(i)).Tries;
        }
        if(!tmp.containsKey(' '))
            tmp.put(' ',new Trie());
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Map<Character,Trie> tmp=this.Tries;
        tmp=tmp.getOrDefault(' ',new Trie()).Tries;
        for (int i=0;i<word.length();i++){
            if(!tmp.containsKey(word.charAt(i)))
                return false;
            tmp=tmp.get(word.charAt(i)).Tries;
        }
        if(!tmp.containsKey(' ')) return false;
        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Map<Character,Trie> tmp=this.Tries;
        tmp=tmp.getOrDefault(' ',new Trie()).Tries;
        for (int i=0;i<prefix.length();i++){
            if(!tmp.containsKey(prefix.charAt(i)))
                return false;
            tmp=tmp.get(prefix.charAt(i)).Tries;
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
