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
