package backTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class permute {
    public static void main(String[] args){
        permute permute=new permute();
        List<List<Integer>> lists=permute.permute1(new int[]{1,2,3,4});
        System.out.println(lists.size());
        for (List<Integer> list:lists){
            for (Integer i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
    public List<List<Integer>> permute1(int[] nums) {
        Set<List<Integer>> set=new HashSet<>();
        permuteBackTracking(set,new ArrayList<>(),0,nums);
        List<List<Integer>> res=new ArrayList<>();
        res.addAll(set);
        return res;
    }
    public static void permuteBackTracking(Set<List<Integer>> lists,List<Integer> curlist,int i,int[] nums){
        if(i>nums.length) return;
        if(i==nums.length){
            List<Integer> tmp=new ArrayList<>();
            tmp.addAll(curlist);
            lists.add(tmp);
        }
        for (int j=0;j<nums.length;j++) {
            if(!curlist.contains(nums[j])) {
                curlist.add(nums[j]);
                permuteBackTracking(lists, curlist, i + 1, nums);
                curlist.remove(new Integer(nums[j]));
            }
        }
    }
}
