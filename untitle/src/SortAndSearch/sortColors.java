package SortAndSearch;

public class sortColors {
    //[2,0,2,1,1,0]
    public static void main(String[] args){
        sortColors sortColors=new sortColors();
        int[] ans=new int[]{2,0,2,1,1,0};
        sortColors.sortColors1(ans);
        for (int i=0;i<ans.length;i++)
            System.out.print(ans[i]+" ");
    }
    public void sortColors1(int[] nums) {
        int red=0,blue=nums.length-1;
        for (int i=0;i<blue;i++){
            if(nums[i]==0){
                nums[i]=nums[red];
                nums[red++]=0;
            }
            else if(nums[i]==2){
                nums[i]=nums[blue];
                nums[blue--]=2;
            }

            System.out.print("red:"+red+",blue:"+blue+",");
            for (int j=0;j<nums.length;j++)
                System.out.print(nums[j]+" ");
            System.out.println();
        }
    }
}
