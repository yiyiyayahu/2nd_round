/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

/*
开始想多了，但是其实只要两两交换就好了
上面的是简化版，原来code可以这么写，好神奇

我开始一直想不通为什么两两换就可以了，有没有情况是换完之后发现不行的
首先想想
如果[3,5,2,1]...检测到1的时候发现不对，2和1交换，这时候不会有任何问题，因为在1比2小的时候才交换，那么肯定就比2之前的元素5小
同理呢，[3,5,6,7]...检测到6的时候发现不对，5和6交换，也没问题，因为本身5就比3大，那6比3就更大了

我开始觉得有问题呢，可能是我的思维方式是向后比较
[3,5,2,1]，我会3和5比，5和2比，2和1比，但是应该也没有问题喔
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
