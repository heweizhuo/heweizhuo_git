package Design;


import java.util.*;
import java.util.stream.Stream;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Codec {
    public static void main(String[] args){
//        StringBuilder sb=new StringBuilder("[1, 2, 3, null, null, 4, 5, null, null, null, null]");
//        String s=sb.toString().replaceAll("((, null)+)]","]");
//        System.out.println(s);
        TreeNode root=deserialize("[1,2,3,null,null,4,5]");
        String string=serialize(root);
        System.out.println(string);
    }
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root==null) return "null";
        Queue<TreeNode> queue=new LinkedList<>();
        List<StringBuilder> sb=new LinkedList<>();
        queue.offer(root);
        int cursize=1;
        while(!queue.isEmpty()){
            cursize=queue.size();
            for (int i=0;i<cursize;i++){
                TreeNode tmp=queue.poll();
                if(tmp!=null) {
                    sb.add(new StringBuilder(""+tmp.val));
                    queue.offer(tmp.left);
                    queue.offer(tmp.right);
                }
                else sb.add(new StringBuilder("null"));
            }
        }
        StringBuilder nullstr=new StringBuilder("null");
        return sb.toString().replaceAll("((, null)+)]","]");
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data.length()==0) return null;
        String[] dataStr=data.replaceAll("[\\[|\\] ]","").split(",");
        List<TreeNode> queue=new LinkedList<>();
        if(dataStr[0].equals("null")) return null;
        TreeNode root=new TreeNode(Integer.valueOf(dataStr[0]));
        queue.add(root);
        for (int i=1;i<dataStr.length;i++){
            if(!dataStr[i].equals("null")){
                TreeNode cur=queue.get((i-1)/2);
                if(i%2==0){
                    cur.right=new TreeNode(Integer.valueOf(dataStr[i]));
                    queue.add(cur.right);
                }
                else {
                    cur.left=new TreeNode(Integer.valueOf(dataStr[i]));
                    queue.add(cur.left);
                }
            }
        }
        return root;
    }
}
