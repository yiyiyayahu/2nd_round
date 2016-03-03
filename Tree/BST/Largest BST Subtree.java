/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Show Hint 
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
*/

/*
如果O(nlogn)就会比较简单。。。但是O(n)的做法我用了好多wrapper，我再想想

其实不一定要用wrapper，我之前用wrapper的时候是想wrapper这么几个variable: boolean isBST; int count; int low; int high
然后low和high要随时更新，这一点我没有做好。。。

下面参照了下其他人的解法，发现其实我只是想把这些变量传递，而int传递不了，那换成数组就好了嘛
这里要注意下更新，尤其是low和high的问题，在长度为3的数组中，low放在了index=1的位置，high放在了index=2的位置
遍历左右子树的过程中，要两套不同的int[],显然不可以share。。。

与validateBST不同的是，这个是从下往上传递low和high的
比如 4  这个tree，validateBST的话，到left也就是1这个节点的时候，high已经赋值为4了，但是呢，这里是从下往上做的，到1的时候left和right都是没有赋值的，1遍历好之后，这个数组的low是1，high也是1
    1 3
*/


public class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        
        int[] count = new int[3];
        isBST(root, count);
        return count[0];
    }
    
    public boolean isBST(TreeNode root, int[] helper) {
        if(root == null) return true;
        int[] left = new int[3];
        int[] right = new int[3];
        boolean leftIsBST = isBST(root.left, left);
        boolean rightIsBST = isBST(root.right, right);
        
        if(leftIsBST && rightIsBST && (root.left==null||root.val > left[2]) && (root.right==null|| root.val < right[1]) ) {
            helper[0] = left[0] + right[0] + 1;
            if(root.left == null) helper[1] = root.val;
            else helper[1] = left[1];
            if(root.right == null) helper[2] = root.val;
            else helper[2] = right[2];
            return true;
        }
        helper[0] = Math.max(left[0], right[0]);
        return false;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        int leftMax = largestBSTSubtree(root.left);
        int rightMax = largestBSTSubtree(root.right);
        
        if(isValidBST(root, null, null)) return leftMax + rightMax + 1;
        return Math.max(leftMax, rightMax);
    }
    
    public boolean isValidBST(TreeNode root, Integer low, Integer high) {
        if(root == null) return true;
        if(low != null && root.val < low) return false;
        if(high != null && root.val >= high) return false;
        
        return isValidBST(root.left, low, root.val) && isValidBST(root.right, root.val, high);
    }
}
