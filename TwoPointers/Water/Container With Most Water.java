/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

/*
只要这样两个pointer往中间推进就好了。谁都不希望找比自己小的，然后就移啊移啊直到重合
为什么这道题也可以用two pointer捏~~~
*/
public class Solution {
    public int maxArea(int[] height) {
        if(height == null || height.length == 0) return 0;
        
        int result = Integer.MIN_VALUE;
        int start = 0, end = height.length-1;
        while(start < end) {
            int tmp = Math.min(height[start], height[end]) * (end-start);
            result = Math.max(result, tmp);
            if(height[start] < height[end]) 
                start ++;
            else 
                end --;
        }
        return result;
    }
}
