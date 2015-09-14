/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.
*/

/*
https://leetcode.com/discuss/46578/java-o-n-solution-using-deque-with-explanation

这道题要用Deque来解，还真是不知道这么一个数据结构
就是一个queue有两头，可以从前面出，也可以从后面出
那利用这个数据结构如何做呢
首先这个queue里应该只放要考虑的元素，就是index在i-k+1到i之间，不符合的从前面推出来
然后捏，就是i要压进来对吧，那从尾部把所有比nums[i]小的都踢出来，因为那些元素在后续的操作中都没啥用。。再把nums[i]放进去
最后，其实留在queue的头部的就是当前的max
好巧妙的做法呀
*/

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return nums;
        int n = nums.length;
        int[] ret = new int[n-k+1];
        int index = 0;
        
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            //remove element not in range
            while(!q.isEmpty() && q.peek() < i-k+1) q.poll();
            //remove smaller element
            while(!q.isEmpty() && nums[q.peekLast()] < nums[i]) q.pollLast();
            q.offer(i);
            if(i >= k-1) {
                ret[index++] = nums[q.peek()];
            }
        }
        return ret;
    }
}
