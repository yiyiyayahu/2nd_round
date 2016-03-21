/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

/*
不用helper函数的 O(logn) O(1)
*/
/*
这道题呢，因为是rotate过的
那比如[4,5,6,0,1,2]这种，几乎可以肯定nums[start]不是最小值，如果nums[start] > nums[end]的话，所以指针就要移动，不然的话直接nums[start]
然后呢，是这样想，如果nums[mid] > nums[end] 那结果肯定在mid+1到end之间，不然就是end=mid
关于binary search比较重要的两点一个是返回值，是返回start对应的呢还是肿么，还有就是结束条件，会不会进入死循环之类的，那我们一般只要考虑两个元素这种情况就可以
[1,2] - 直接返回start
但是如果[1,2]只是其中一步，还要进入循环呢，这时候start=0,mid=0,end=1 -> end=mid=0, 循环跳出，这时候start=end=0，都指向元素1
[2,1] - start=0,mid=0,end=1-> start=mid+1=1 -> start=end=0, 都指向元素1
所以发现，当start==end的时候，指向的就是最小值，所以返回nums[start]就可以了
*/
public class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        
        while(start < end && nums[start] > nums[end]) {
            int mid = (start + end)/2;
            if(nums[mid] > nums[end]) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }
}

/*
这里考虑到如果只有两个element可能会死循环，所以加了一个if(start == end - 1) return nums[end];
但是其实当(nums[mid] > nums[end])的时候，mid不可能是最小的，只要start = mid+1;保证指针移动就行了
注意当(nums[mid] < nums[start])，mid还是可能最小的，所以不能end直接等于mid-1
*/
public class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        
        while(start < end && nums[start] > nums[end]) {
            if(start == end - 1) return nums[end];
            int mid = (start + end)/2;
            if(nums[mid] < nums[start]) {
                end = mid;
            } else if(nums[mid] > nums[end]) {
                start = mid;
            }
        }
        return nums[start];
    }
}


public class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        return helper(nums, 0, nums.length-1);
    }
    public int helper(int[] nums, int start, int end) {
        if(start == end) return nums[start];
        if(start == end - 1) return Math.min(nums[start], nums[end]);
        int mid = (start + end)/2;
        if(nums[mid] < nums[start]) {
            return helper(nums, start, mid);
        } else if(nums[mid] > nums[end]) {
            return helper(nums, mid, end);
        } 
        return nums[start];
    }
}
