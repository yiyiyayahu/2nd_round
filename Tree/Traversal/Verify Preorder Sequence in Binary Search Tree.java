/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

/*
我这个做法不好，已经TLE了，好多重复计算，时间复杂度O(n^2)
思路是这样的preorder[i]肯定是当前的root value了，然后从i+1开始往后找，所有开始小于这个root value的都是left subtree，
看到一个大于它的就说明right subtree开始了，但是如果在right这边发现有一个比root value小，那就return false了，不然就recursive的去找
*/
public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length-1);
    }

    public boolean verify(int[] preorder, int i, int j) {
        if(i > j) return true;

        int root = preorder[i];
        int midpoint = -1;
        for(int k = i+1; k <= j; k++) {
            if(preorder[k] > root) {
                if(midpoint == -1) midpoint = k; 
            } else {
                if(midpoint != -1) return false;
            }
        }
        if(midpoint == -1) return verify(preorder, i+1, j);
        return verify(preorder, i+1, midpoint-1) && verify(preorder, midpoint, j);
    }
}
