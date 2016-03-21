/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

/*
有重复就是可能nums[start] == nums[mid]，找不到pivot，那就只能start一位一位移
time: O(n) space: O(1)
*/

/*
有重复的时候leetcode解法很好
[2,2,2,3,4,2]
如果发现nums[start] == nums[mid]，可以check一下是否nums[end] == num[mid]，如果不是，只要遍历右边就可以了，不然左右都要遍历
其实最后那种情况，我觉得可以写个辅助函数，check左边，如果是true的话直接返回就可以了，不然的话再遍历右边。不过只是个简单的优化啦
*/
/*
我后来又写了一次，发现犯了好大的错喔
if(nums[start] < nums[mid]) {}
else if(nums[mid] < nums[end]) {}
else {start++;}
就是这三个条件就很乱，没有完整的cover很多case，导致else里面很难handle，当然我觉得也是可以改好的啦
*/
public class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(nums[mid] == target) return true;
            
            if(nums[start] < nums[mid]) {
            	if(nums[start] <= target && nums[mid] > target) {
                    end = mid-1;
                } else {
                    start = mid + 1;
                }
            } else if(nums[start] > nums[mid]) {
                if(nums[end] >= target && nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if(nums[mid] != nums[end]) {
                    start = mid+1;
                } else {
                    start ++;
                }
            }
        }
        return false;
    }
}

public class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(nums[mid] == target) return true;
            
            if(nums[start] < nums[mid]) {
            	if(nums[start] <= target && nums[mid] > target) {
                    end = mid-1;
                } else {
                    start = mid + 1;
                }
            } else if(nums[start] > nums[mid]) {
                if(nums[end] >= target && nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                start ++;
            }
        }
        return false;
    }
}
