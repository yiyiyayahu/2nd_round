/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

/*
我觉得还是一道数学题，注意下细节就可以了
然后其实分配个n长度的数组，然后每次选中一个慢慢移的话code比较好写，但是可能效率稍微低一点。但是因为n最大就是9，所以就还好
*/

public class Solution {
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        StringBuilder sb = new StringBuilder();
        int permCount = 1;
        k--;
        for(int i = 0; i < n; i++) {
            nums[i] = i+1;
            permCount *= i+1;
        }
        
        for(int i = 0; i < n; i++) {
            permCount /= n-i;
            int choosed = k / permCount;
            sb.append(nums[choosed]);
            for(int j = choosed; j < n-1; j++) {
                nums[j] = nums[j+1];
            }
            k = k % permCount;
        }
        return sb.toString();
    }
}
