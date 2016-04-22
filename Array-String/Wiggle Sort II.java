/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

/*
改进的O(n)的做法，但是code还是好难写T_T
1. find the median element - 可以用heap，参照findKthLargestNumber
2. smaller than median -> fill in the last even slot
3. larger than median -> fill in the first odd slot
(that is, using two pointers, left starts from the first odd, right starts from the last even)
4. median -> fill the rest slot

During the swap, to avoid messing up with the original array, use an indexing mapping to store the latter index
Accessing A(0) actually accesses nums[1].
Accessing A(1) actually accesses nums[3].
Accessing A(2) actually accesses nums[5].
Accessing A(3) actually accesses nums[0].
Accessing A(4) actually accesses nums[2].
Accessing A(5) actually accesses nums[4].

https://leetcode.com/discuss/95156/step-by-step-explanation-of-index-mapping-in-java
https://leetcode.com/discuss/77133/o-n-o-1-after-median-virtual-indexing
*/

public class Solution {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        if(len <= 1) return;  
        
        //find the median
        int median = findKthLargest(nums, (len+1)/2);
        
        //3-way partitioning using mappedIndex
        int left = 0, right = len-1, i = 0;
        while(i <= right) {
            if(nums[getIndex(i,len)] > median) {
                swap(nums, getIndex(left++,len), getIndex(i++,len));
            } else if(nums[getIndex(i,len)] < median) {
                swap(nums, getIndex(right--,len), getIndex(i,len));
            } else {
                i++;
            }
        }
    }

    public int getIndex(int idx, int n) {
        return (2 * idx + 1) % (n | 1);
    }
    
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k);
        for(int i = 0; i < nums.length; i++) {
            if(queue.size() < k) {
                queue.add(nums[i]);
            } else {
                if(nums[i] > queue.peek()) {
                    queue.remove();
                    queue.add(nums[i]);
                }
            }
        }
        return queue.peek();
    }
}

/*
这道题好难写啊。。。哭
这个其实和题目要求是不符合的，time: O(nlogn) space: O(n)
Small half:    4 . 3 . 2 . 1 . 0 .
Large half:    . 9 . 8 . 7 . 6 . 5
----------------------------------
Together:      4 9 3 8 2 7 1 6 0 5

其实Arrays降序排列比较好，但是java里面没有builtIn的function而且也不能加Comparator，所以就用index来体现就好了sorted[len-1-i]
用(1+2*i)%(len|1)来统一处理odd和even的pointer很巧妙诶！
len|1就是len是odd的时候还是len，len是even的时候变成len+1

假设sorted是降序排列的（这样比较好想） 9 8 7 6 5 4 3 2 1 0
大的应该放到odd里面，小的放到even里面，放的顺序就和上图一样
*/
public class Solution {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        if(len <= 1) return;
        
        int[] sorted = Arrays.copyOf(nums, len);
        Arrays.sort(sorted);
       
        int m = len | 1, index = -1; 
        for(int i = 0; i < len; i++) {
            nums[(1+2*i)%m] = sorted[len-1-i];
        }
    }
}
