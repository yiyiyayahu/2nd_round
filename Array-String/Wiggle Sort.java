/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

/*
开始想多了，但是其实只要两两交换就好了
上面的是简化版，原来code可以这么写，好神奇
*/

public class Solution {
    public void wiggleSort(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            int tmp = nums[i-1];
            if( (i%2==1) == (nums[i] < tmp) ) {
                nums[i-1] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}

public class Solution {
    public void wiggleSort(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            int tmp = nums[i-1];
            if( ((i%2==1) && (nums[i]<tmp)) || ((i%2==0) && (nums[i]>tmp)) ) {
                nums[i-1] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
