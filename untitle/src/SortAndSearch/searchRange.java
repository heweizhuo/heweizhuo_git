package SortAndSearch;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class searchRange {
    public static void main(String[] args){
        System.out.println(searchMatrix(new int[][]{{-1,3}},3));
    }
    public int[] searchRange1(int[] nums, int target) {
        int[] range=new int[]{-1,-1};
        for (int i=0;i<nums.length;i++){
            if(nums[i]==target){
                range[0]=(range[0]==-1?i:Math.min(i,range[0]));
                range[1]=Math.max(range[1],i);
            }
        }
        return range;
    }
    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     * */
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start-o2.start!=0)
                    return o1.start-o2.start;
                else return o1.end-o2.end;
            }
        });
        for (int i=1;i<intervals.size();i++){
            if(intervals.get(i).start==intervals.get(i-1).start){
                intervals.remove(i-1);
                i--;
            }
            else if(intervals.get(i).start<=intervals.get(i-1).end) {
                intervals.get(i-1).end=Math.max(intervals.get(i).end,intervals.get(i-1).end);
                intervals.remove(i--);
            }
        }
        return intervals;
    }
    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * */
    public static int search(int[] nums, int target) {
        int left=0,right=nums.length-1,mid=0;
        while(left<=right){
            mid=(left+right)/2;
            if(nums[mid]==target)return mid;
            if(nums[mid]>nums[left]){//说明左边升序
                if(nums[mid]>=target && nums[left]<=target) right=mid-1;
                else left=mid+1;
            }
            else if(nums[mid]<nums[left]){//右边升序
                if(nums[mid]<=target && nums[right]>=target) left=mid+1;
                else right=mid-1;
            }
            else left=mid+1;
        }
        return -1;
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0) return false;
        int row=matrix.length,col=matrix[0].length,mark=-1,min=Math.min(row,col);
        for (int i=0;i<min;i++){
            if(matrix[i][i]<target)mark=Math.max(mark,i);
            else if(matrix[i][i]==target) return true;
            else break;
        }
        if(mark==-1)return false;
        for (int i=0;i<=mark;i++){
            for (int j=mark+1;j<col;j++){
                if(matrix[i][j]==target)
                    return true;
            }
        }
        for (int i=mark+1;i<row;i++){
            for(int j=0;j<=mark;j++){
                if(matrix[i][j]==target)
                    return true;
            }
        }
        return false;
    }
}
