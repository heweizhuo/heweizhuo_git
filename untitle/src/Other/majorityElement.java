package Other;

import java.util.HashMap;
import java.util.Map;

public class majorityElement {
    public int majorityElement(int[] nums) {
        if(nums.length==0)return -1;
        int ans=nums[0],count=1;
        for (int i=1;i<nums.length;i++){
            if(count==0){
                ans=nums[i];
                count=1;
                continue;
            }
            if(nums[i]==ans){
                count++;
            }
            else count--;
        }
        return ans;
    }
}
