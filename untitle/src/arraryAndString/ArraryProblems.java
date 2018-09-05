package arraryAndString;

import java.util.*;

public class ArraryProblems {
    public static  void main(String[] args){
        System.out.println(kthSmallest1(new int[][]{{1,3,5},{6,7,12},{11,14,14}},4));
    }
    /**
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
     * 请注意，它是排序后的第k小元素，而不是第k个元素。
     * */
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m-1][n-1];
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            int cnt = countLessEqual(matrix, mid, m, n);
            if (cnt < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
    private int countLessEqual(int[][] matrix, int target, int m, int n) {
        int i = m - 1, j = 0, res = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= target) {
                res += (i + 1);
                j++;
            } else
                i--;
        }
        return res;
    }
    public static int kthSmallest1(int[][] matrix, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                queue.offer(matrix[i][j]);
                if(queue.size()>k)
                    queue.poll();
            }
        }
        return queue.peek();
    }
    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口最大值。
     * */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0) return new int[0];
        int[] res=new int[nums.length-k+1];
        Deque<Integer> deque=new LinkedList<>();
        for (int i=0;i<nums.length;i++){
            while (!deque.isEmpty() && deque.peek()<i-k+1){
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.getLast()]<nums[i]){
                deque.pollLast();
            }
            deque.offer(i);
            if(i>=k-1)
                res[i-k+1]=nums[deque.peek()];
        }
        return res;
    }
    /**
     *给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
     * */
    public static String minWindow(String s, String t) {
        int begin=0,end=0,min=s.length(),count=0,ansbegin=0;
        int[] needtofind=new int[256];
        for (int i=0;i<t.length();i++){
            needtofind[t.charAt(i)]++;
        }
        while (end<s.length()){
            Character c=s.charAt(end);
            if(needtofind[c]>0)
                count++;
            needtofind[c]--;
            end++;
            while (count==t.length()){
                if (end-begin<min) {
                    ansbegin=begin;
                    min = end - begin;
                }
                if(needtofind[s.charAt(begin)]<0) {
                    needtofind[s.charAt(begin)]++;
                    begin++;
                }
                else count--;
            }
        }
        return s.substring(ansbegin,ansbegin+min);
    }
    //3ms
    public String minWindow2(String s, String t) {
        if(s.length() < t.length() || s.length() == 0 || s == null || t.length() == 0 || t == null){
            return "";
        }
        int length = Integer.MAX_VALUE;
        int[] map = new int[256];
        int start = 0,end = 0;
        int found = t.length();
        int head = 0;
        for(int i = 0;i<t.length();i++){
            map[t.charAt(i)]++;
        }
        while(end < s.length()){
            if(map[s.charAt(end++)]-- > 0){
                found--;
            }
            while(found == 0){
                if(end - start < length){
                    length = end - (head = start);
                }
                if(map[s.charAt(start++)]++ == 0){
                    found++;
                }
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(head, head + length);
    }
    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口最大值。
     * */
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int max=Integer.MIN_VALUE;
//        List<Integer> list=new LinkedList<>();
//        for (int i=0;i<nums.length;i++){
//            while ()
//        }
//    }
    /**
     *给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length==0) return new ArrayList<>();
        int i=0,j=0,down=matrix.length,row=matrix.length,col=matrix[0].length,right=matrix[0].length,top=1,left=0;
        List<Integer> res=new LinkedList();
        while (true){
            while (j<right && res.size()<col*row)//向右
                res.add(matrix[i][j++]);
            j--;i++;right--;
            while(i<down && res.size()<col*row)
                res.add(matrix[i++][j]);
            j--;i--;down--;
            while (j>=left && res.size()<col*row)
                res.add(matrix[i][j--]);
            j++;i--;left++;
            while (i>=top && res.size()<col*row)
                res.add(matrix[i--][j]);
            i++;j++;top++;
            if(res.size()==col*row) break;
        }
        return res;
    }
    /**
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * */
    public static int[] productExceptSelf(int[] nums) {
        int l=nums.length;
        int res[]=new int[l];
        res[0]=1;
        for (int i=1;i<l;i++){
            res[i]=res[i-1]*nums[i-1];
        }
        int right=1;
        for (int i=l-2;i>=0;i--){
            right*=nums[i+1];
            res[i]=res[i]*right;
        }
        return res;
    }
    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     * */
    public static Map<Character,Integer> OpPriorityMap=new HashMap(){};
    static {
        OpPriorityMap.put('+',1);
        OpPriorityMap.put('-',1);
        OpPriorityMap.put('*',2);
        OpPriorityMap.put('/',2);
        OpPriorityMap.put('#',0);
    }
    public static int calculate(String s) {
        s+='#';
        Stack<Integer> Nums=new Stack<>();
        Stack<Character> Ops=new Stack<>();
        int num=0;
        for (int i=0;i<s.length();i++){
            Character c=s.charAt(i);
            if(c==' ') continue;
            else if(c>='0' && c<='9'){
                while (c>='0' && c<='9') {
                    num = num * 10 + c - '0';
                    c=s.charAt(++i);
                }
                Nums.add(num);
                num=0;
            }

            switch (c){
                case '(':Ops.add(c);break;
                case ')':
                    while (Ops.peek()!='(') {
                        Nums.add(countExp(Nums.pop(),Nums.pop(),Ops.pop()));
                    }
                    Ops.pop();
                    break;
                case '+':
                    case '-':
                        case '*':
                            case '/':
                                if (!Ops.isEmpty() && Ops.peek()=='(') {Ops.add(c);break;}
                                else {
                                    if(!Ops.isEmpty()) {
                                        int cmp = OpPriorityMap.get(c) - OpPriorityMap.get(Ops.peek());
                                        if (cmp <= 0) Nums.add(countExp(Nums.pop(), Nums.pop(), Ops.pop()));
                                    }
                                }Ops.add(c);break;
                                case '#':while (!Ops.isEmpty()){
                                    Nums.add(countExp(Nums.pop(),Nums.pop(),Ops.pop()));
                                }
            }
        }
        return Nums.pop();
    }
    public static int countExp(int a,int b,Character op){
        switch (op){
            case '+':return b+a;
            case '-':return b-a;
            case '*':return b*a;
            case '/':return b/a;
        }
        return 0;
    }
    /**
     *      给定一个未排序的整数数组，找出最长连续序列的长度。
     *
     *      要求算法的时间复杂度为 O(n)。
     *
     *      示例:
     *
     *      输入: [100, 4, 200, 1, 3, 2]
     *      输出: 4
     *      解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numsSet=new HashSet<>();
        for (int i:nums){
            numsSet.add(i);
        }
        int max=0,left,right;
        for (int i:nums){
            left=right=i;
            Set<Integer> tmp=new HashSet<>();
            while(numsSet.contains(left-1)){
                left--;
                numsSet.remove(left);
            }
            while(numsSet.contains(right+1)){
                right++;
                numsSet.remove(right);
            }
            numsSet.remove(i);
            if(right-left+1>max)
                max=right-left+1;
        }
        return max;
    }
//    根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
//
//    给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
//
//    如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
//    如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
//    如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
//    如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
//    根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
    public void gameOfLife(int[][] board) {
        //0:死细胞变死细胞、1：活细胞变活细胞、2：死细胞变活细胞、3：活细胞变死细胞
        int row=board.length,col=board[0].length,live=0,dead=0;
        int dir[][]=new int[][]{{-1,-1},{0,-1,},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                live=0;dead=0;
                for (int[] p:dir){
                    int x=i+p[0],y=j+p[1];
                    if(x>=0 && x<row && y>=0 &&y<col){
                        if(board[x][y]%2==0)dead++;
                        else live++;
                    }
                }
                if(board[i][j]%2==0 && live==3) board[i][j]=2;
                else if(board[i][j]%2==1){
                    if(live<2 || live>3)board[i][j]=3;
                    if(live==2 || live==3)board[i][j]=1;
                }
            }
        }
        for (int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 0 || board[i][j] == 3) board[i][j] = 0;
                else board[i][j] = 1;
            }
        }
        return;
    }

//    给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
    public static int firstMissingPositive(int[] nums) {
        for (int i=0;i<nums.length;i++){
            while(nums[i]<=nums.length && nums[i]>0 && nums[nums[i]-1]!=nums[i]){
                int tmp=nums[i];
                nums[i]=nums[tmp-1];
                nums[tmp-1]=tmp;
            }
        }
        for (int i=0;i<nums.length;i++){
            if(nums[i]!=i+1)
                return i+1;
        }
        return nums.length+1;
    }
    public static void printArr(int[][] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

//    给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
//    注意：你不能倾斜容器，n 至少是2。
    public static int maxArea(int[] height) {
        int ans=0,low=0,hi=height.length-1;
        while(low<hi){
            int area=Math.min(height[low],height[hi])*(hi-low);
            if(area>ans)
                ans=area;
            int tmplow=height[low],tmphi=height[hi];
            if(tmplow<tmphi){
                while (low<height.length && height[low]<=tmplow) low++;
            }
            else if(tmplow>tmphi){
                while (hi>=0 && height[hi]<=tmphi) hi--;
            }
            else {
                while (low<height.length && height[low]<=tmplow) low++;
                while (hi>=0 && height[hi]<=tmphi) hi--;
            }
        }
        return ans;
    }
//    给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
    public static int findDuplicate(int[] nums) {
        int len=nums.length,mid=len/2,lcnt=0,hcnt=0,low=0,hi=len;
        while(low<=hi){
            mid=(hi+low)/2;
            lcnt=0;hcnt=0;
            for (int i=0;i<len;i++){
                if(nums[i]<mid) lcnt++;
                if(nums[i]>mid) hcnt++;
            }
            if(len-(hcnt+lcnt)>=2) return mid;
            if(lcnt>=mid) hi=mid;
            if(lcnt<mid) low=mid;
        }
        return -1;
    }
    /**
     * 三数和
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            if((i>0 && nums[i]!=nums[i-1])|| i==0) {
                int res = -nums[i], lo = i + 1, hi = nums.length - 1;
                while (lo < hi) {
                    if (nums[lo] + nums[hi] > res) hi--;
                    else if (nums[lo] + nums[hi] < res) lo++;
                    else {
                        ans.add(new ArrayList(Arrays.asList(nums[i], nums[lo++], nums[hi--])));
                        while(lo<hi && nums[lo]==nums[lo-1]) lo++;
                        while(hi>lo && nums[hi]==nums[hi+1])hi--;
                    }
                }
            }
        }
        return ans;
    }
    /**
     *
     给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法
     * */
    public static void setZeroes(int[][] matrix) {
        if(matrix.length==0)return;
        int col0=1;
        printArr(matrix);
        System.out.println();
        for (int i=1;i<matrix.length;i++){
            if(matrix[i][0]==0) col0=0;
            for (int j=1;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        printArr(matrix);
        System.out.println();
        for (int i=matrix.length-1;i>=0;i--){
            for (int j=matrix[0].length-1;j>0;j--){
                if(matrix[i][0]==0 || matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
            if(col0==0)matrix[i][0]=0;
        }
    }
    /**
     * 给定一个未排序的数组，请判断这个数组中是否存在长度为3的递增的子序列。
     * */
    public static boolean increasingTriplet(int[] nums) {
        if(nums.length<3) return false;
        int m1=Integer.MAX_VALUE,m2=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=m1)m1=nums[i];
            else if(nums[i]<=m2)m2=nums[i];
            else return true;
        }
        return false;
    }
}
