/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/

/*
contain duplicates就可能出现nums[mid] == nums[start] == nums[end]这种情况([1,1,1,0,1])，那就左边右边还要遍历，所以worst case就是O(n)
*/

public class Solution {
    public int findMin(int[] nums) {
         if(nums == null || nums.length == 0) return 0;
         
         int start = 0, end = nums.length-1;
         while(start < end && nums[start] >= nums[end]) {
             int mid = (start + end)/2;
             if(nums[mid] > nums[end]) {
                 start = mid+1;
             } else if(nums[mid] < nums[end]) {
                 end = mid;
             } else {
                 end--;
             }
         }
         return nums[start];
    }
}
public class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        
        while(start < end && nums[start] >= nums[end]) {
            int mid = (start + end)/2;
            if(nums[mid] < nums[start]) {
                end = mid;
            } else if(nums[mid] > nums[end]) {
                start = mid+1;
            } else {
                start = start + 1;
            }
        }
        return nums[start];       
    }
}
