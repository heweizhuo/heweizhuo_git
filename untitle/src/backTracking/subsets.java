package backTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class subsets {
    public static void main(String[] args){
        subsets subsets=new subsets();
        List<List<Integer>> tmp=subsets.subsets1(new int[]{1,2,3,4});
        for (List<Integer> list:tmp){
            for (Integer i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> subsets1(int[] nums) {
        if(nums.length==0) return new ArrayList<>();
        Set<List<Integer>> set=new HashSet<>();
        subsetBackTracking(set,new ArrayList<>(),nums,0,0);
        List<List<Integer>> ans=new ArrayList<>();
        ans.addAll(set);
        return ans;
    }
    public void subsetBackTracking(Set<List<Integer>> set,List<Integer> subnums, int[] nums, int len,int curindex){
        if(len>nums.length) return;
        if(len<=nums.length) {
            List<Integer> tmp=new ArrayList<>();
            tmp.addAll(subnums);
            set.add(tmp);
        }
        for (int i=curindex;i<nums.length;i++){
            if(!subnums.contains(nums[i])) {
                subnums.add(nums[i]);
                subsetBackTracking(set, subnums, nums, len + 1,i+1);
                subnums.remove(new Integer(nums[i]));
            }
        }
    }
}
