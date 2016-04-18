/*
There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. 
The overall run time complexity should be O(log (m+n)).
*/

/*
if (aMid < bMid) Keep [aRight + bLeft]

else Keep [bRight + aLeft]

*/
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if( (m+n)%2 != 0) {
            return (double)getKth(nums1, 0, nums2, 0, (m+n)/2+1);
        } else {
            return (getKth(nums1, 0, nums2, 0, (m+n)/2) + getKth(nums1, 0, nums2, 0, (m+n)/2+1))/2.0;
        }        
    }
    
    public double getKth(int[] nums1, int astart, int[] nums2, int bstart, int k) {
        int m = nums1.length, n = nums2.length;
        if(astart > m-1) return nums2[bstart+k-1];
        if(bstart > n-1) return nums1[astart+k-1];
        if(k == 1) return Math.min(nums1[astart], nums2[bstart]);
        
        int amid = Integer.MAX_VALUE, bmid = Integer.MAX_VALUE;
        if(astart+k/2-1 < m) amid = nums1[astart+k/2-1];
        if(bstart+k/2-1 < n) bmid = nums2[bstart+k/2-1];
        if(amid < bmid) {
            return getKth(nums1, astart + k/2, nums2, bstart, k-k/2);
        } else {
            return getKth(nums1, astart, nums2, bstart+k/2, k-k/2);
        }
    }
}

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if( (m+n)%2 != 0) {
            return (double)helper(nums1, 0, m-1, nums2, 0, n-1, (m+n)/2+1);
        } else {
            return (helper(nums1, 0, m-1, nums2, 0, n-1, (m+n)/2) + helper(nums1, 0, m-1, nums2, 0, n-1, (m+n)/2+1))/2.0;
        }
    }
    
    public double helper(int[] A, int astart, int aend, int[] B, int bstart, int bend, int k) {
        int m = aend - astart + 1, n = bend - bstart + 1;
        if(m > n) return helper(B, bstart, bend, A, astart, aend, k);
        if(m == 0) return B[k-1];
        if(k == 1) return Math.min(A[astart], B[bstart]);
        
        int partA = Math.min(k/2, m);
        int partB = k - partA;
        
        int tmpA = A[astart + partA - 1], tmpB = B[bstart + partB - 1];
        if(tmpA == tmpB) return tmpA;
        if(tmpA < tmpB) {
            return helper(A, astart+partA, aend, B, bstart, bend, k-partA);
        } else {
            return helper(A, astart, aend, B, bstart+partB, bend, k-partB);
        }
    }
}
