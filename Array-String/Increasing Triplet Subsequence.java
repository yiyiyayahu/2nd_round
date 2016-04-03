/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
*/

/*
mock interview了一下，发现代码比上次简洁诶。。。果然思路清晰怎么都好办，不晓得我onsite会肿么样。。。哭
就是track min和max，如果min和max都更新过了，找到一个值比max大就ok了
*/
public class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3) return false;
        int min = nums[0], max = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > max && max != Integer.MIN_VALUE) return true;
            if(nums[i] <= min) min = nums[i];
            else if(nums[i] < max || max==Integer.MIN_VALUE) max = nums[i];
        }
        return false;
    }
}

/*
这道题我fail了好多次。。。囧
我的思路很简单，就是一个smallest和一个secondSmall还有一个total
如果遍历，发现诶，后面的比较小诶，而且这个时候secondSmall还没找到，那这个元素妥妥的是最小的啊，更新
接着遍历，发现，有个元素大于smallest，那就total++，secondSmall更新
继续遍历，如果发现这个元素比secondSmall还要大诶，好样的，return true
接下来就比较麻烦了
如果这个比smallest大，比secondSmall小，那可以把它设为secondSmall，这个还好
如果和smallest相等呢？这个时候最好什么都不干 （我fail的test case就是这样的，[1,0,10,0,100], [1,2,1,2]）
如果比smallest还小呢，这个就麻烦了（我最后fail的test case是这样的）
  开始我想的是，直接total置0，smallest更新，一切重新开始。但是如果是这样的[1,2,-1,-1,5]，如果从1开始数，这个increasing triplet是存在的，可是我碰到-1更新，反而不存在了
  我的解决方法是，直接从-1开始先遍历一下，如果找到大的了，皆大欢喜，不然的话就重置
  但是其实这样时间复杂度呢？
*/
public class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3) return false;
        int smallest = nums[0], secondSmall = Integer.MAX_VALUE;
        int total = 0;
        for(int i = 1; i < nums.length; i++) {
            if(total == 0) {
                if(nums[i] <= smallest) {
                    smallest = nums[i];
                } else {
                    total = 1;
                    secondSmall = nums[i];
                }
            } else {
                if(nums[i] > secondSmall) return true;
                
                if(nums[i] < smallest) {
                    for(int j = i+1; j < nums.length; j++) {
                        if(nums[j] > secondSmall) return true;
                    }
                    smallest = nums[i];
                    total = 0;
                } else if(nums[i] > smallest) {
                    secondSmall = nums[i];
                }
            }
        }
        return false;
    }
}
