/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.
*/

/*
这个解法还蛮牛的，就是遍历的过程中把count算出来
https://leetcode.com/discuss/81091/simple-java-solution-o-log-n-2-108-ms-with-explanation
Time complexity:
Because the total number of steps equals to the height of the tree h, 
at each step, calculating the height will cost time O(h - current step) 
so the time complexity is h + (h - 1) + (h - 2) + ... + 1 = O(h^2) = O(log(n)^2).

还有一个解法更牛
https://leetcode.com/discuss/75517/three-solutions-and-explanations
再研究一下！
*/
public class Solution {
    public int countNodes(TreeNode root) {
        return root == null ? 0 : findLastIndex(root, 1);
    }
    private int lHeight(TreeNode node, int count) {
        return node == null ? count - 1 : lHeight(node.left, count + 1);
    }
    private int findLastIndex(TreeNode node, int currIndex) {
        if (node.left == null && node.right == null) return currIndex;
        if (lHeight(node.left, 1) == lHeight(node.right, 1))
            return findLastIndex(node.right, currIndex * 2 + 1);
        else return findLastIndex(node.left, currIndex * 2);
    }
}

/*
下面这个解法第一次是过的，这次就果断妥妥的TLE了。。。
其实是完全可以优化的，很明显的问题就是我从root开始，每一层都在遍历左边，遍历右边，找height

接下来我想到的第一种优化就是把左边的height传进去，但是右边的还是要遍历
可是还是TLE了。。。这次的没过的test case要比上一个长一点。。。。

想一下算法复杂度哈，
最坏的情况就是N个点都遍历了，然后每个点都找了两次height
h*2 + (h - 1)*4 + (h - 2)*8 + ... + 1*2^h-1 ？不晓得算的对不对。。。
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
