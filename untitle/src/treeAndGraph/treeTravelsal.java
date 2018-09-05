package treeAndGraph;

import java.util.*;

/**
 * Definition for a binary tree node.
 * */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int leftNums;
    TreeNode(int x) { val = x; }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

class Point{
    int x;
    int y;
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
}
public class treeTravelsal {
    public static void main(String[] args){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
//        root.left.left=new TreeNode(6);
//        root.left.right=new TreeNode(2);
//        root.left.left.left=new TreeNode(7);
//        root.left.left.right=new TreeNode(4);
        root.right=new TreeNode(3);
//        root.right.left=new TreeNode(0);
//        root.right.right=new TreeNode(8);
        treeTravelsal treeTravelsal=new treeTravelsal();
        TreeNode ans=treeTravelsal.findCommonParent(root,root.left,root.right);
        System.out.println(ans.val);
//        int ans=treeTravelsal.maxPathSum(root);
//        System.out.println(ans);
//
//        treeTravelsal treeTravelsal1=new treeTravelsal();
//        TreeNode root2=new TreeNode(-3);
//        System.out.println(treeTravelsal1.maxPathSum(root2));
//
//        treeTravelsal treeTravelsal2=new treeTravelsal();
//        TreeNode root3=new TreeNode(1);
//        root3.left=new TreeNode(-2);
//        root3.right=new TreeNode(-3);
//        root3.right.left=new TreeNode(-2);
//        root3.left.left=new TreeNode(1);
//        root3.left.right=new TreeNode(3);
//        root3.left.left.left=new TreeNode(-1);
//        System.out.println(treeTravelsal2.maxPathSum(root3));
//        List<Integer> ans=treeTravelsal.countSmaller(new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41});
//        List<Integer> ans=treeTravelsal.countSmaller(new int[]{78,66,66,78,66,51,78,78,51,66,65,36,100,41});
//        for (Integer i:ans)
//            System.out.print(i);
    }
    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans=findCommonParent(root,p,q);
        return ans;
    }
    public TreeNode findCommonParent(TreeNode root,TreeNode p,TreeNode q){
        if(root!=null){
            if(root==p) return p;
            if(root==q) return q;
            TreeNode left=findCommonParent(root.left,p,q);
            TreeNode right=findCommonParent(root.right,p,q);
            if((left==p && right==q) || (left==q && right==p)){
                return root;
            }
            if(left!=null) return left;
            if(right!=null) return right;
        }
        return null;
    }
    /**
     * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     * */
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length==0) return new ArrayList<>();
        List<Integer> res=new ArrayList<>();
        res.add(0);
        TreeNode root=new TreeNode(nums[nums.length-1]);
        for (int i=nums.length-2;i>=0;i--){
            int right=BtCnt(root,nums[i],0);
            res.add(0,right);
        }
        return res;
    }
    public int BtCnt(TreeNode p,int num,int len){//不讨论相等的情况
        if(num>p.val) {
            if(p.right!=null)
                return BtCnt(p.right,num,len+p.leftNums+1);
            else {
                p.right=new TreeNode(num);
                p.right.leftNums=0;
                return len+p.leftNums+1;
            }
        }
        else{
            p.leftNums++;
            if(p.left!=null)
                return BtCnt(p.left,num,len);
            else {
                p.left=new TreeNode(num);
                p.left.leftNums=0;
                return len;
            }
        }
    }
    /**
     * 给定一个非空二叉树，返回其最大路径和。
     *
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * tip:因为必须记录左右子树中较大子树路径的值，所以需要全局变量记录中间最大值
     * */
    private int ans=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        countNode(root);
        return ans;
    }
    public int countNode(TreeNode node){
        if(node==null) return 0;
        int left=countNode(node.left);
        int right=countNode(node.right);
        int max=Math.max(left,right)+node.val;
        max=Math.max(max,node.val);
        ans=Math.max(ans,node.val);
        ans=Math.max(ans,left+right+node.val);
        return max;
    }

    /**
     * 二叉搜索树中第k小元素
     * */
    public static int calLeftTreeNum(TreeNode root){//include itself
        if(root==null) return 0;
        return calLeftTreeNum(root.left)+calLeftTreeNum(root.right)+1;
    }
    public int kthSmallest(TreeNode root, int k) {
        int leftNum=calLeftTreeNum(root.left);
        if(leftNum+1==k) return root.val;
        if(leftNum+1>k) return kthSmallest(root.left,k);
        else if(leftNum+1<k) return kthSmallest(root.right,k-leftNum-1);
        return -1;
    }
    /**
     * 计算二维数组中有多少岛
     * */
    public int numIslands(char[][] grid) {
        if(grid.length==0)return 0;
        int row=grid.length,col=grid[0].length;
        int visited[][]=new int[row][col],ans=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(visited[i][j]==0 && grid[i][j]=='1'){//还没有访问过
                    ans++;
                    List<Point> bfsList=new ArrayList<>();
                    Point cur=new Point(i,j);
                    bfsList.add(cur);
                    while(!bfsList.isEmpty()){
                        cur=bfsList.get(0);
                        bfsList.remove(0);
                        int nexti=cur.x-1,nextj=cur.y;
                        if(nexti>=0 && nexti<row && nextj>=0 && nextj<col && visited[nexti][nextj]==0 && grid[nexti][nextj]=='1'){
                            visited[nexti][nextj]=1;
                            bfsList.add(new Point(nexti,nextj));
                        }
                        nexti=cur.x+1;nextj=cur.y;
                        if(nexti>=0 && nexti<row && nextj>=0 && nextj<col && visited[nexti][nextj]==0 && grid[nexti][nextj]=='1'){
                            visited[nexti][nextj]=1;
                            bfsList.add(new Point(nexti,nextj));
                        }
                        nexti=cur.x;nextj=cur.y-1;
                        if(nexti>=0 && nexti<row && nextj>=0 && nextj<col && visited[nexti][nextj]==0 && grid[nexti][nextj]=='1'){
                            visited[nexti][nextj]=1;
                            bfsList.add(new Point(nexti,nextj));
                        }
                        nexti=cur.x;nextj=cur.y+1;
                        if(nexti>=0 && nexti<row && nextj>=0 && nextj<col && visited[nexti][nextj]==0 && grid[nexti][nextj]=='1'){
                            visited[nexti][nextj]=1;
                            bfsList.add(new Point(nexti,nextj));
                        }
                    }
                }
            }
        }
        return ans;
    }
    /**
     * 给已有完全二叉树添加兄弟连接
     * */
    public void connect(TreeLinkNode root) {
        if(root==null) return;
        List<TreeLinkNode> queue=new ArrayList<>();
        queue.add(root);
        int baseSize=0,curSize=1;
        while(baseSize<curSize){
            int nextSize=0;
            for(int i=baseSize;i<curSize;i++){
                TreeLinkNode tmp=queue.get(i);
                if(i+1<curSize)
                    tmp.next=queue.get(i+1);
                if(tmp.left!=null){
                    queue.add(tmp.left);
                    nextSize++;
                }
                if(tmp.right!=null){
                    queue.add(tmp.right);
                    nextSize++;
                }
            }
            baseSize=curSize;
            curSize+=nextSize;
        }
    }
    /**
     * 中序、前序建树
     * */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length!=inorder.length || preorder.length==0)return null;
        TreeNode cur=new TreeNode(preorder[0]);
        if(preorder.length==1)return cur;
        int curIndex=0;
        while(curIndex<inorder.length && inorder[curIndex]!=preorder[0]){curIndex++;}
        cur.left=buildTree(Arrays.copyOfRange(preorder,1,curIndex+1),Arrays.copyOfRange(inorder,0,curIndex));
        cur.right=buildTree(Arrays.copyOfRange(preorder,curIndex+1,preorder.length),Arrays.copyOfRange(inorder,curIndex+1,inorder.length));
        return cur;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> Result=new ArrayList<>();
        if(root==null)return Result;
        List<TreeNode> treeNodes=new ArrayList<>();
        Map<TreeNode,Boolean> treeNodeMap=new HashMap<TreeNode,Boolean>();
        treeNodeMap.put(root,false);
        treeNodes.add(root);
        while(!treeNodes.isEmpty()){
            TreeNode top=treeNodes.get(treeNodes.size()-1);
            if(treeNodeMap.get(top)){//true说明第二次访问
                Result.add(top.val);
                treeNodes.remove(treeNodes.size()-1);
                if(top.right!=null) {
                    treeNodes.add(top.right);
                    treeNodeMap.put(top.right,false);
                }
            }
            else{
                if(top.left!=null){
                    treeNodes.add(top.left);
                    treeNodeMap.put(top.left,false);
                }
                treeNodeMap.put(top,true);
            }
        }
        return Result;
    }
    /**
     * z字形层次遍历
     * */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null) return new ArrayList<>();
        List<TreeNode> lev1=new ArrayList<>();
        List<TreeNode> lev2=new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
        lev1.add(root);
        while(!lev1.isEmpty()||!lev2.isEmpty()){
            List<Integer> tmpList=null;
            while(!lev1.isEmpty()){
                tmpList=new ArrayList<>();
                TreeNode tmpTreeNode=lev1.get(0);
                tmpList.add(tmpTreeNode.val);
                lev1.remove(0);
                if(tmpTreeNode.right!=null)
                    lev2.add(tmpTreeNode.right);
                if(tmpTreeNode.left!=null)
                    lev2.add(tmpTreeNode.left);
            }
            while(!lev2.isEmpty()){
                tmpList=new ArrayList<>();
                TreeNode tmpTreeNode=lev2.get(0);
                tmpList.add(tmpTreeNode.val);
                lev2.remove(0);
                if(tmpTreeNode.left!=null)
                    lev1.add(tmpTreeNode.left);
                if(tmpTreeNode.right!=null)
                    lev1.add(tmpTreeNode.right);
            }
            ans.add(tmpList);
        }
        return ans;
    }
    public void printTree(TreeNode root){
        Queue<TreeNode> queue=new LinkedList();
        queue.add(root);
        int num=1,start=0;
        while (!queue.isEmpty()){
            int cnt=0;
            for (int i=start;i<num;i++){
                TreeNode cur=queue.poll();
                System.out.print(cur.val+"\t\t");
                if(cur.left!=null){
                    System.out.print(" l:("+cur.left.val+"\t");
                    queue.add(cur.left);
                    cnt++;
                }
                if(cur.right!=null){
                    System.out.print(" r:"+cur.right.val+")\t");
                    queue.add(cur.right);
                    cnt++;
                }
            }
            num=cnt;
            System.out.println();
        }
    }
}
