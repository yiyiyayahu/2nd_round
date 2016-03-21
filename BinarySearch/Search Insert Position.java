/*
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

/*
time: O(logn) space:O(1)
*/
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(nums[mid] < target) {
                start = mid + 1;
            } else if(nums[mid] == target) {
                return mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}

/*
我觉得下面这个start++，end--是不对的，就不是binary search变成two pointer了。。。所以time complexity就是O(n)
*/
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target) start ++;
            else end --;
        }
        return start;
    }
}
