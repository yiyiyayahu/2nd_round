/*
Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.
*/

/*
唉，这道题是看了答案才做出来的，也可能是被greedy吓到了就没有仔细想。。。和那个jump game有点像，就每次track最远的就行
我开始的思路是，nums[1,3]我能组成[1][3][1,3]就有点像permutation，然后我一点点往里面填数字。。。
比如2没有，就把2填进去，然后能组成的就变成了[1],[2],[3]/[1,2],[1,3],[2,3],[1,2,3]
但是其实这道题只问的是minPatches对吧，没有问要加哪个数字

那参考jump game，每次看能跳多远，这个也是类似的，每次看能cover多远
还是看这个[1,3]，i=0的时候最远cover到1，i=1的时候呢，2 cover不到，这个时候就需要patch一个2了，同时更新cover到3
到3的时候，更新cover到6

再看个例子[1,2,3] 11
到3的时候，最远更新到cover:6，这个时候呢，就greedy patch一个7，那么最远就可以到13了，所以就返回1
*/
public class Solution {
    public int minPatches(int[] nums, int n) {
        int count = 0, i = 0;
        for (long covered=0; covered < n; ) {
            if ((i<nums.length && nums[i]>covered+1) || i>=nums.length) {  
                // at this moment, we need (covered+1), patch it.
                covered += covered+1;
                ++count;
            } else { covered += nums[i++]; }
        }
        return count;
    }
}
