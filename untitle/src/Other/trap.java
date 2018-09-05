package Other;

public class trap {
    public static void main(String[] args){
        Solution solution=new Solution();
        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
class Solution {
    public int trap(int[] height) {
        int left=0,right=height.length-1,ans=0;
        while(left<right){
            int bound=Math.min(height[left],height[right]);
            if(bound==height[left]){
                while(height[left]<=bound && left<right){
                    ans+=bound-height[left++];
                }
            }
            else {
                while (height[right]<=bound && right>left){
                    ans+=bound-height[right--];
                }
            }
        }
        return ans;
    }
}