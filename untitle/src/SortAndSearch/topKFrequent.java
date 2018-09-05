package SortAndSearch;

import java.util.*;

public class topKFrequent {
    public static void main(String[] args){
        System.out.println("\n"+findKthLargest(new int[]{3,2,1,5,6,4},2));
        System.out.println("\n"+findKthLargest(new int[]{1,1,31,1,23,2,2,2,3,3,3,33,2,1,5,6,4},2));
    }
    public static int findKthLargest(int[] nums, int k) {
        quickSort(nums,0,nums.length-1);
        for (int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        return nums[k];
    }
    public static void quickSort(int[] nums,int left,int right ){
        if(left>=right || left<0 || right>=nums.length)return;
        int mid=sort(nums,left,right);
        quickSort(nums,left,mid-1);
        quickSort(nums,mid+1,right);
    }
    public static int sort(int[] nums,int left,int right){
        int p=nums[left],tmp=0,low=left;
        while(left<right){
            while(nums[left]<=p && left<right)left++;
            while (nums[right]>p && right>left)right--;
            if(left<right){
                tmp=nums[left];
                nums[left++]=nums[right];
                nums[right--]=tmp;
            }
        }
        /**
         * 一共三种情况：1）right==left-1中心点在right;2)right==left,中心点在right;3)right==left,中心点在right左边。
         * 只需处理right,因为出循环后一定是right==left-1或者right==left
         * 如果当前nums[right]比p大，说明中心点在当前位置左边，并且此时right==left
         * right==left-1，说明当前right的位置是中心点
         * */
        if(nums[right]>p) right--;
        nums[low]=nums[right];
        nums[right]=p;
        return right;
    }
    public static int findPeakElement(int[] nums) {
        List<Integer> integers=new ArrayList();
        for (int i:nums)
            integers.add(i);
        Arrays.sort(nums);
        return integers.indexOf(new Integer(nums[nums.length-1]));
    }
    public List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        Map<Integer,List<Integer>> bucket=new TreeMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for (Integer e:map.keySet()){
            int freq=map.get(e);
            if(!bucket.containsKey(freq)){
                bucket.put(freq,new ArrayList());
            }
            bucket.get(freq).add(e);
        }
        List<Integer> res=new LinkedList<>();
        while(res.size()<k){
            Map.Entry<Integer,List<Integer>> entry=((TreeMap<Integer, List<Integer>>) bucket).pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }
}
