/*
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.) 
*/

/*
大家都说用贪心就可以了，不用dp的。这。。。贪心我好像一直没有学过诶
*/

/*
我犯了和I一样的问题，这样写就会TimeoutException，因为对于nums中每个元素，我都往前更新数组
其实时间复杂度就变成了O(n^2)了
实际上只要算最远跳就可以了哈，再想想
*/
public class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] jumps = new int[len];
        for(int i = 0; i < len-1; i++) {
            int steps = nums[i];
            int previous = jumps[i];
            for(int j = i; j <= Math.min(len-1, i + steps); j++) {
                jumps[j] = (jumps[j]!=0) ? jumps[j] : previous+1;
            }
            if(jumps[len-1] != 0) break;
        }
        return jumps[len-1];       
    }
}
