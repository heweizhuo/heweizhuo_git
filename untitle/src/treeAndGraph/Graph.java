package treeAndGraph;

import java.util.*;

class point{
    int x;
    int y;
    int value;
    public point(int x,int y,int value){
        this.x=x;
        this.y=y;
        this.value=value;
    }
}
public class Graph {
    public static void main(String[] args){
        System.out.println(longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
    }
    /**
     *给定一个整数矩阵，找出最长递增路径的长度。
     *
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
     * */
    public static int longestIncreasingPath(int[][] matrix) {
        if(matrix.length==0) return 0;
        int row=matrix.length,col=matrix[0].length,max=0;
        int[][] dp=new int[row][col];
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                dp[i][j]=dpWalk(i,j,dp,matrix,row,col);
                if(dp[i][j]>max) max=dp[i][j];
            }
        }
        return max;
    }
    public static int dpWalk(int i,int j,int [][]dp,int[][]matrix,int row,int col){
        for(int ii=0;ii<row;ii++){
            for (int jj=0;jj<col;jj++){
                System.out.print(dp[ii][jj]);
            }
            System.out.println();
        }
        Scanner scanner=new Scanner(System.in);
        scanner.nextLine();

        if(dp[i][j]!=0) {
            return dp[i][j];
        }
        int [][] dir=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int max=1;
        for (int[] auto:dir){
            int x=i+auto[0],y=j+auto[1];
            if(x>=row || x<0 || y>=col || y<0 || matrix[x][y]<=matrix[i][j])continue;
            int tmp= dpWalk(x,y,dp,matrix,row,col);
            max=max>tmp+dp[i][j]+1?max:tmp+dp[i][j]+1;
        }
        dp[i][j]=max;
        return dp[i][j];
    }
    /**
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     * */
    public static int[] canFinish(int numCourses, int[][] prerequisites) {
        int status[]=new int[numCourses];//入度+1、出度-1
        int ans[]=new int[numCourses];
        int cnt=0;
        Queue<Integer> queue=new LinkedList<>();
        Map<Integer,List<Integer>> map=new HashMap<>();
        for (int i=0;i< prerequisites.length;i++){
            status[prerequisites[i][0]]++;
            List<Integer> tmp=map.getOrDefault(prerequisites[i][1],new LinkedList<>());
            tmp.add(prerequisites[i][0]);
            map.put(prerequisites[i][1],tmp);
        }
        for (int i=0;i<numCourses;i++){
            if(status[i]==0){
                queue.add(i);
                ans[cnt]=i;
                cnt++;
            }
        }
        while(!queue.isEmpty()){
            int top=queue.poll();
            List<Integer> list=map.getOrDefault(top,new ArrayList<>());
            for (Integer i:list){
                status[i]--;
                if(status[i]==0){
                    queue.add(i);
                    ans[cnt]=i;
                    cnt++;
                }
            }
        }
        if(cnt!=numCourses)return new int[0];
        return ans;
    }
    /**
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
     *
     * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     * */
    public static int findCircleNum(int[][] M) {
        if(M.length==0) return 0;
        int nums=M.length,cir=0;
        int[] visited=new int[nums];
        for (int i=0;i<nums;i++){
            Queue<Integer> queue = new LinkedList<>();
            if(visited[i]==0){
                queue.add(i);
                visited[i]=1;
                cir++;
                while(!queue.isEmpty()){
                    int top=queue.poll();
                    for (int j=0;j<nums;j++){
                        if(visited[j]==0 && M[top][j]==1) {
                            queue.add(j);
                            visited[j]=1;
                        }
                    }
                }
            }
        }
        return cir;
    }

    public int findCircleNum2(int[][] M) {
        int[] visited = new int[M.length];
        int ans = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                ans++;
                dfs(visited, M, i);
            }
        }
        return ans;
    }
    public static void dfs(int[] visited,int[][] M,int i){
        for(int j=0;j<M.length;j++){
            if(visited[j]==0 && M[i][j]==1) {
                visited[j] = 1;
                dfs(visited,M,j);
            }
        }
    }
    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * */
    public static void solve(char[][] board) {
        if(board.length==0) return;
        int row=board.length,col=board[0].length;
        for (int i=0;i<col;i++){
            dfs(board,0,i);
            dfs(board,row-1,i);
        }
        for (int j=0;j<row;j++){
            dfs(board,j,0);
            dfs(board,j,col-1);
        }
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(board[i][j]=='O')
                    board[i][j]='X';
                if(board[i][j]=='?')
                    board[i][j]='O';
            }
        }
    }
    public static void dfs(char[][] board,int i,int j){
        if(board[i][j]!='O') return;
        board[i][j]='?';
        if(i+1<board.length) dfs(board,i+1,j);
        if(i-1>=0) dfs(board,i-1,j);
        if(j+1<board[0].length) dfs(board,i,j+1);
        if(j-1>=0) dfs(board,i,j-1);
    }
}
