import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    public static void main(String[] args){
        Solution solution=new Solution();
        solution.longestCommonPrefix(new String[]{"c","c"});
    }
//    编写一个函数来查找字符串数组中的最长公共前缀。
//
//    如果不存在公共前缀，返回空字符串 ""。
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0) return "";
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        int maxLen=strs[0].length();
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<maxLen;i++){
            boolean hasChar=true;
            for (int j=1;j<strs.length;j++){
                if(strs[j].length()<maxLen || strs[j].charAt(i)!=strs[0].charAt(i)){
                    hasChar=false;
                    break;
                }
            }
            if(!hasChar)
                break;
            else
                sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }
//    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
//    有效字符串需满足：
//
//    左括号必须用相同类型的右括号闭合。
//    左括号必须以正确的顺序闭合。
//    注意空字符串可被认为是有效字符串。
    public boolean isValid(String s) {
        int f1=0,f2=0,f3=0;
        Stack<Character> OPStack=new Stack<>();
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            switch (c){
                case '(':f1++;OPStack.add(c);break;
                case ')':f1--;break;
                case '{':f2++;OPStack.add(c);break;
                case '}':f2--;break;
                case '[':f3++;OPStack.add(c);break;
                case ']':f3--;break;
            }
            if(f1<0 || f2<0 || f3<0) return false;
            if(c==')'||c=='}'||c==']'){
                char cr=OPStack.pop();
                if(c==')' && cr!='(') return false;
                if(c=='}' && cr!='{') return false;
                if(c==']' && cr!='[') return false;
            }
        }
        if(f1==0 && f2==0 && f3==0 && OPStack.isEmpty()) return true;
        return false;
    }
    /**
     * 计算表达式
     * */
    public int calculate(String s) {
        this.eval(s.toCharArray(), 0, s.length());
        return this.result;
    }

    private int result;


    private static int value(int sign, int val) {
        //return sign*val;
        return sign < 0 ? -val : val;
    }


    private int eval(char[] cs, int pos, int limit) {
        int value1 = 0;
        int value2 = 0;
        int sign = 1;
        EVAL_LOOP: while (pos < limit) {
            char c = cs[pos++];
            switch (c) {
                case ' ':
                    break;
                case '(':
                    pos = this.eval(cs, pos, limit);
                    value1 += value(sign, this.result);
                    value2 = 0;
                    sign = 1;
                    break;
                case ')':
                    break EVAL_LOOP;
                case '+':
                    value1 += value(sign, value2);
                    value2 = 0;
                    sign = 1;
                    break;
                case '-':
                    value1 += value(sign, value2);
                    value2 = 0;
                    sign = -1;
                    break;
                default:
                    value2 = value2 * 10 + c - '0';
                    break;
            }
        }
        this.result = value1 + value(sign, value2);
        return pos;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans= new ArrayList<>();
        if(root==null) return ans;
        List<TreeNode> zigListLev=new ArrayList<>();
        zigListLev.add(root);
        int sizeZigLev=1,cnt=0,sizeBase=0;
        while(sizeZigLev>sizeBase){
            int sizeNext=0;
            List<Integer> tmpList=new ArrayList<>();
            for (int i=sizeBase;i<sizeZigLev;i++){
                TreeNode tmp=zigListLev.get(i);
                tmpList.add(tmp.val);
                if(tmp.left!=null){
                    zigListLev.add(tmp.left);
                    sizeNext++;
                }
                if(tmp.right!=null){
                    zigListLev.add(tmp.right);
                    sizeNext++;
                }
            }

            if(cnt%2==0)ans.add(tmpList);
            else ans.add(reverseList(tmpList,0,tmpList.size()-1));
            cnt++;
            sizeBase=sizeZigLev;
            sizeZigLev+=sizeNext;
        }
        return ans;
    }
    public static List<Integer> reverseList(List<Integer> list,int low,int hi){
        while(low<hi){
            int tmp=list.get(low);
            list.set(low,list.get(hi));
            list.set(hi,tmp);
            low++;hi--;
        }
        return list;
    }
    static int sticker(int[] score) {
        int[] ans=new int[score.length];
        int sum=0;
        ans[0]=1;
        for (int i=1;i<score.length;i++){
            if(score[i]>score[i-1])
                score[i]=score[i-1]+1;
            else if(score[i]<score[i-1]){
                score[i]=score[i-1]-1;
                if(score[i]<0) {
                    score[i - 1]++;
                    score[i] = 0;
                }
            }
            else score[i]=score[i-1];
        }
        for (int i=0;i<score.length;i++){
            sum+=ans[i];
        }
        return sum;
    }
}

public class Main {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<List<Integer>> ret = new Solution().zigzagLevelOrder(root);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }
}