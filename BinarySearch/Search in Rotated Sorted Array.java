/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

/*
其实思路清晰了这些问题也就避免了，当然可以简化一下code，不过我开始是这样想的
*/
public class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(target == nums[mid]) return mid;
            else if(target < nums[mid]) {
                if(nums[mid] > nums[end]) {
                    //leftside is sorted
                    if(target >= nums[start]) end = mid-1; //in left
                    else start = mid+1;
                } else {
                    //rightside is sorted but not in right
                    end = mid-1;
                }
            } else {
                if(nums[mid] > nums[end]) {
                    //leftside is sorted but not in left
                    start = mid+1;
                } else {
                    //rightside is sorted
                    if(target <= nums[end]) start = mid+1; //in right
                    else end = mid-1;
                }
            }
        }
        return -1;
    }
}
/*
time: O(logn) space: O(1)
唉，这道题submit了好多次，开始写的是
  if(nums[end] >= target && nums[mid] < target) {
      start = mid + 1;
  } else {
      end = mid - 1;
  }
但是[3,1],1这个test case就直接跪了。。。
然后加了nums[mid+1] <= target这种还要判断当前的mid+1, mid-1的index是不是valid
暂时没想到一个很好的办法来简化code
但是submit这么多次也是醉了
*/
/*
nums[start] < nums[mid]改成nums[start] <= nums[mid]就好了，就避免了[3,1],1这种情况
*/
public class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(nums[mid] == target) return mid;
            
            if(nums[start] <= nums[mid]) {
            	if(nums[start] <= target && nums[mid] > target) {
                    end = mid-1;
                } else {
                    start = mid + 1;
                }
            } else {
                if(nums[end] >= target && nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                
            }
        }
        return -1;
    }
}

public class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(nums[mid] == target) return mid;
            
            if(nums[start] < nums[mid]) {
            	if(nums[start] <= target && mid-1 >= 0 && nums[mid-1] >= target) {
                    end = mid-1;
                } else {
                    start = mid + 1;
                }
            } else {
                if(nums[end] >= target && mid+1 < nums.length && nums[mid+1] <= target) {
                    start = mid + 1;
                } else {
                    end = mid-1;
                }
            }
        }
        return -1;
    }
}
