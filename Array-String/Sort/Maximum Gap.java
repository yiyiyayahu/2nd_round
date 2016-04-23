/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

/*
很神奇的一道题，我没想出来。。。其实算是桶排序
是这样的额，maximum gap不可能大于ceiling((max-min)/(len-1))
所以就以这个作为bucket的size，把nums分为一个一个小bucket，每个值map到对应的bucket里面去 bucket index=(nums[i] - min)/bSize;
但是我只要keep这个bucket里面最小的和最大的值就可以了
然后maximum gap应该出现在两个挨着的bucket p和q pmax-qmin
但是由于有些bucket可能是空的，就是没有值map到里面去，这点要注意
      pmax = bucketList[i].high != -1 ? bucketList[i].high : pmax;
      qmin = bucketList[i+1].low != -1 ? bucketList[i+1].low : qmin;
就是bucket空的时候pmax和qmin保持不变
*/
class Bucket{
    int low;
    int high;
    public Bucket(){
        low = -1;
        high = -1; 
    }
}
public class Solution {
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int len = nums.length;
        int min = nums[0], max = nums[0];
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < min) min = nums[i];
            else if(nums[i] > max) max = nums[i];
        }
        if(max == min) return 0;
        
        int tmp = (max-min)/(len-1);
        int bSize = (max-min)%(len-1)==0? tmp: tmp+1;
        int blistSize = (max-min)/bSize + 1;
        
        Bucket[] bucketList = new Bucket[blistSize];
        for(int i = 0; i < blistSize; i++) {
        	bucketList[i] = new Bucket();
        }
        for(int i = 0; i < nums.length; i++) {
            int index = (nums[i] - min)/bSize;
            Bucket b = bucketList[index];
            if(b.low == -1 || nums[i] < b.low) b.low = nums[i];
            if(b.high == -1 || nums[i] > b.high) b.high = nums[i];
        }
        
        int pmax = 0, qmin = 0, result = 0;
        for(int i = 0; i < blistSize-1; i++) {       	
            pmax = bucketList[i].high != -1 ? bucketList[i].high : pmax;
            qmin = bucketList[i+1].low != -1 ? bucketList[i+1].low : qmin;
            result = Math.max(result, qmin-pmax);
        }
        return result;
    }
}
