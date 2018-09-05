package SortAndSearch;

public class findMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        if(n>m) return findMedianSortedArrays(nums2,nums1);
        if(n==0) return (nums2[(m/2)]+nums2[(m-1)/2])/2.0;
        int low=0,hi=2*n,L1=0,L2=0,R1=0,R2=0;
        while(low<=hi){
            int c1=(low+hi)/2;
            int c2=(n+m)-c1;
            L1=(c1==0)?Integer.MIN_VALUE:nums1[(c1-1)/2];
            L2=(c2==0)?Integer.MIN_VALUE:nums2[(c2-1)/2];
            R1=(c1==2*n)?Integer.MAX_VALUE:nums1[c1/2];
            R2=(c2==2*m)?Integer.MAX_VALUE:nums2[c2/2];
            if(L1>R2) hi=c1-1;
            else if(L2>R1) low=c1+1;
            else break;
        }
        return (Math.max(L1,L2)+Math.min(R1,R2))/2.0;
    }
}
