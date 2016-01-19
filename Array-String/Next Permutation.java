/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/*
第一次没想出来，这次还是没想出来
其实是找规律的一道题，和之前的什么permutations没啥关系

最简单的一个：
如果从后往前完全是升序的，那么就把所有元素reverse就ok了
然后呢，再想想
比如从后往前，发现，诶，有个元素打破了这种升序的关系，那么怎么办呢
比如1，2，4，3，3到4是升序的，到了2反而降了下来，那说明到2这里这个permutation就截止了，也就是2这个位置应该由比它大一点点的数字取代了对吧
所以，2这个位置先记录下来，然后再从后往前找，找第一个比2大的数，然后和2互换，进入下面一轮permutation。那么这回找到了3，和2互换后是1,3,4,2
但是这样还是不对的对吧，这样相当于找到这个循环的最后一种情况了。。。所以再把互换位置，也就是目前3之后的所有元素reverse，变成1,3,2,4就是最后的答案
*/
public class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int len = nums.length;
        int p = -1;
        for(int i = len-1; i > 0; i--) {
            if(nums[i] > nums[i-1]) {p = i-1; break;}
        }
        for(int i = len-1; i >= 0; i--) {
            if(p != -1 && nums[i] > nums[p]) {
                swap(nums, i, p);
                break;
            }
        }
        for(int i = 0; i < (len-1-p)/2; i++) {
            swap(nums,p+1+i,len-1-i);
        }
        
    }
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
