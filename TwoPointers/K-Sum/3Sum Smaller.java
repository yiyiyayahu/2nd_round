/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that 
satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
*/

/*
我开始的问题是start，end固定，中间找第三个，但是问题是，start和end怎么移，我没法儿只移一个
后来发现比较巧妙的做法是，不是每次都count++，而是像下面这样count += (k-j)，这些都是解就好了嘛
*/

public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if(nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);

        int count = 0;
        for(int i = 0; i < nums.length-2; i++) {
        	int newtarget = target - nums[i];
        	int j = i + 1, k = nums.length-1;
        	while(j < k) {
        		if(nums[j] + nums[k] >= newtarget) k--;
        		else {
        			count += (k-j);
        			j++;
        		}
        	}
        }
        return count;
    }
}


/*
开始的code长这样。。。
public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int start = 0, end = nums.length - 1;
        int count = 0;
        while(start <= end) {
            int sum = nums[start] + nums[end];
            if(sum > target) {
                end --;
            } else {
                for(int i = start+1; i < end; i++) {
                    if(sum + nums[i] < target) count ++;
                    else break;
                }
                start ++;
            }
        }
        return count;
    }
}
*/
