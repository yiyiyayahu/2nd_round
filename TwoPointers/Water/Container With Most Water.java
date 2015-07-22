/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

/*
只要这样两个pointer往中间推进就好了。谁都不希望找比自己小的，然后就移啊移啊直到重合
为什么这道题也可以用two pointer捏~~~

算法3：时间复杂度O（n），两个指针i, j分别从前后向中间移动，两个指针分别表示容器的左右边界
。每次迭代用当前的容量更新最大容量，然后把高度小的边界对应的指针往中间移动一位。                                        

正确性证明：由于水的容量是有较小的那个边界决定的，因此某次迭代中，假设height[i] < height[j]，那么j 减小肯定不会使水的容量增大，
只有i 增加才有可能使水的容量增大。
但是会不会有这种可能：当前的i 和 某个k (k > j)是最大容量, 这也是不可能的，因为按照我们的移动规则，
既然右指针从k 移动到了j，说明i 的左边一定存在一个边界 m，使m > k，那么[m, k]的容量肯定大于[i, k]，所以[i,k]不可能是最大容量。
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
