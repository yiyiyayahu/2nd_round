/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.
*/

/*
下面这个解法第一次是过的，这次就果断妥妥的TLE了。。。
其实是完全可以优化的，很明显的问题就是我从root开始，每一层都在遍历左边，遍历右边，找height

接下来我想到的第一种优化就是把左边的height传进去，但是右边的还是要遍历
可是还是TLE了。。。这次的没过的test case要比上一个长一点。。。。
*/
public class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        
        int leftlevel = 0, rightlevel = 0;
        TreeNode left = root;
        TreeNode right = root;
        while(left != null) {
            leftlevel ++;
            left = left.left;
        }
        
        while(right != null) {
            rightlevel ++;
            right = right.right;
        }
        if(leftlevel == rightlevel) return (int)(Math.pow(2, leftlevel)-1);
        
        return countNodes(root.left) + 1 + countNodes(root.right);
    }
}

/*
简单优化了一下
*/
public class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        
        int height = -1;
        TreeNode left = root;
        while(left != null) {
            height ++;
            left = left.left;
        }
        return helper(root, height);
    }

    public int helper(TreeNode root, int height) {
        if(root == null) return 0;
        
        TreeNode tmp = root.right;
        int rightHeight = 0;
        while(tmp != null) {
            rightHeight ++;
            tmp = tmp.left;
        }

        if(rightHeight == height) {
            return (int)(Math.pow(2, height)) + helper(root.right, rightHeight-1);
        } else {
            return (int)(Math.pow(2, height-1)) + helper(root.left, height-1);
        }
    }
}
