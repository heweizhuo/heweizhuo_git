package SortAndSearch;

import java.util.Arrays;
import java.util.PriorityQueue;

public class wiggleSort {
    public static void main(String[] args){
        wiggleSort wiggleSort=new wiggleSort();
        int[] arr=new int[]{1,3,2,2,3,1};
        wiggleSort.wiggleSort2(arr);
        for (int i=0;i<arr.length;i++)
            System.out.print(arr[i]+" ");
    }
    public void wiggleSort1(int[] nums) {
        if(nums.length==1) return;
        int mid=findKthElement(nums,0,nums.length-1,nums.length/2),n=nums.length;
        int left=0,right=nums.length-1,i=0;
        while(i<right){}
    }
    public void wiggleSort2(int[] nums){
        Arrays.sort(nums);
        int i,n=nums.length;
        int[] newnums=new int[nums.length];
        for (i=0;i<n;i++)
            newnums[newPos(i,n)]=nums[n-i-1];
        for (i=0;i<n;i++)
            nums[i]=newnums[i];
    }
    public int newPos(int oldPos,int n){
        return (oldPos*2+1)%(n|1);
    }
    public int findKthElement(int[] nums,int i,int j,int k){
        int left=pation(nums,i,j);
        while (left!=k-1 && i<j){
            if(left<k-1)
                i=left+1;
            else if(left>k-1) j=left-1;
            left = pation(nums, i, j);
        }
        return nums[k-1];
    }
    public int pation(int[] nums,int lo,int hi){
        int l=lo,h=hi;
        while(l<h){
            while (nums[l]<=nums[lo] && l<h) l++;
            while (nums[h]>nums[lo]) h--;
            if(l<h)swap(nums,l,h);
        }
        swap(nums,h,lo);
        return h;
    }
    public void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
