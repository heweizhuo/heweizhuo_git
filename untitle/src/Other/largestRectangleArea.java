package Other;


public class largestRectangleArea {
    public static void main(String[] args){
        System.out.println(largestRectangleArea(new int[]{5,5,1,7,1,1,5,2,7,6}));
    }
    public static int largestRectangleArea(int[] heights) {
        if(heights.length==1) return heights[0];
        int max=0;
        for (int i=1;i<=heights.length;i++){
            while (i<heights.length && heights[i]>heights[i-1])
                i++;
            int min=heights[i-1],area=0;
            for (int j=i-1;j>=0;j--){
                min=Math.min(min,heights[j]);
                area=min*(i-j);
                max=Math.max(area,max);
            }
        }
        return max;
    }
}
