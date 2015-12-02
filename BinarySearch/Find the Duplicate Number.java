/*
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:

    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n2).
    There is only one duplicate number in the array, but it could be repeated more than once.
*/

/*
很显然，sort，hashmap，brute force，都是不行的
然后我开始思路一直是算sum。。。根本想不通
后来看提示，two pointers，binary search
先算mid，然后统计小于等于mid的个数
如果个数小于等于mid，说明重复在[mid+1,end]之间
反之，就在[start,mid-1]之间
如果duplicate就是mid呢，那么个数肯定是大于mid的
比如1,2,2,3这种，mid=2，小于等于mid的是3个，然后end=mid-1=1，start=1，end=1，mid=1
这时候count<=mid,start=2,跳出，返回start，也就是2
*/
public class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length-1;
        int start = 1, end = n;
        while(start <= end) {
            int mid = (start + end)/2;
            int count = 0;
            for(int i = 0; i <= n; i++) {
                if(nums[i] <= mid) count ++;
            }
            if(count <= mid) start = mid+1;
            else end = mid-1;
        }
        return start;        
    }
}
