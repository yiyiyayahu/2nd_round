/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

*/

/*
time: O(nlogn) space: O(1)
肯定有O(n)的解法
*/
public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return nums[len/2];
    }
}


/*
O(n)的解法
限制条件是必须有一个majority element，不然就不能用moore voting

Runtime: O(n) — Moore voting algorithm: We maintain a current candidate and a counter initialized to 0. 
As we iterate the array, we look at the current element x:
If the counter is 0, we set the current candidate to x and the counter to 1.
If the counter is not 0, we increment or decrement the counter based on whether x is the current candidate.
After one pass, the current candidate is the majority element. Runtime complexity = O(n).

这种做法就是，pick一个element，我们加上它出现的次数，减去不是它的元素出现的次数，如果减到0就重新pick当前element
这样一个loop下来剩下的肯定是majority element
但是怎么证明呢。。。

一般分两步：
1. uses Moore’s Voting Algorithm to get a candidate for majority element.
2. Check if the element obtained in step 1 is majority
这道题因为已经告诉你一定有那么一个majority了，所以第二步就省略了
http://www.geeksforgeeks.org/majority-element/
*/
public class Solution {
    public int majorityElement(int[] nums) {
        int counter = 0;
        int curr = 0;
        for(int i = 0; i < nums.length; i++) {
            if(counter == 0) {
                curr = nums[i];
                counter ++;
            } else {
                if(nums[i] == curr) counter ++;
                else counter --;
            }
        }
        return curr;
    }
}
