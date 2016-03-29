/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
*/

/*
详解参见summary
*/
public class NumArray {
    int[] sums;
    int[] nums;
    
    public NumArray(int[] nums) {
        if(nums.length == 0) return;
        
        this.nums = nums;
        int len = nums.length;
        sums = new int[len+1];
        for(int i = 1; i <= len; i++) {
            updateTree(i-1, nums[i-1]);
        }
    }

    void update(int i, int val) {
        updateTree(i, val-nums[i]);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        if(i == 0) return getSum(j);
        return getSum(j) - getSum(i-1);
    }
    
    private void updateTree(int index, int val) {
        index = index+1;
        while(index < sums.length) {
            sums[index] += val;
            index = getNext(index);
        }
    }
    
    private int getSum(int index) {
        index = index+1;
        int s = 0;
        while(index > 0) {
            s += sums[index];
            index = getParent(index);
        }
        return s;
    }
    
    private int getParent(int index) {
        return index - (index&-index);
    }
    
    private int getNext(int index) {
        return index + (index&-index);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
