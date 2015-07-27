/*
Given an array of integers that is already sorted in ascending order, 
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

/*
add: O(1) find: O(n) space: O(n)
*/
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] indices = new int[2];
        int start = 0, end = numbers.length-1;
        while(start <= end) {
            int sum = numbers[start] + numbers[end];
            if(sum == target) {
                indices[0] = start+1;
                indices[1] = end+1;
                return indices;
            } else if(sum < target) start ++;
            else end --;
        }
        return indices;
    }
}

/*
add: O(logn) store into one sorted array (binary search)
find: O(n) two pointers
space: O(n)
*/