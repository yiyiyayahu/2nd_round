/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
Related problem: Reverse Words in a String II
*/

/*
简单点的做法就是像Reverse Words in a String II这样，把整个reverse了，然后把前k个reverse，后面n-k个再reverse
time: O(n) space: O(1)
*/

public class Solution {
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0) return;
        int len = nums.length;
        k = k%len;
        reverse(nums, 0, len-1);
        
        reverse(nums, 0, k-1);
        reverse(nums, k, len-1);
    }
    public void reverse(int[] nums, int i, int j) {
        while(i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }
}

/*
这个会超时。。。可能时间复杂度太高了，我想想哈
我是一个个换的，不对啊，好像也就是O(n)吧
*/
public class Solution {
    public void rotate(int[] nums, int k) {
    	if(nums == null || nums.length == 0) return;
    	
        int len = nums.length;
        k = k % len;
        if(k == 0) return;
        int i = 0, index = len-1;
        int tmp = nums[0];      
        
        for(int j = 0; j < Math.min(k,len-k); j++) {
            tmp = nums[j];
            i = j;
            while(index != j) {
                index = (i+k)%len;
                int prev = nums[index];
                nums[index] = tmp;
                tmp = prev;
                i = index;
            }
        }
    }
}
