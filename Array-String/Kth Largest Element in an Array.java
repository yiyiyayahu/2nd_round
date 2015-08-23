/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, 
not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/*
O(nlogk)的做法就是用大小为k的最小堆，这样堆顶就是最终的结果
*/
public class Solution {
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
