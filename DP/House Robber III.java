/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. 
After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
/*
改进了一下，因为下面那个解法总是会遍历每个node好多次
root -chosen的时候，遍历一遍left，right没chosen的情况
然后not chosen的时候，又要遍历一下left，right没选中的情况

后来想想着缓存一下，就是用一个int[2]的array，total[0]存当前node被chosen时候的最大值，total[1]存当前node not chosen的最大值
这样每个node只要遍历一遍就可以了

唉，我不知道是昨天晚上状态不好还是什么，现在想题啊还是做题啊都有点慢，抓紧起来啊啊啊，多多练习！
*/
public class Solution {
    public int rob(TreeNode root) {
        int[] total = new int[2];
        dfs(root, total);
        return Math.max(total[0], total[1]);
    }

    //total[0] - chosen; total[1] - not chosen
    public void dfs(TreeNode node, int[] total) {
        if(node == null) return;
        int[] left = new int[2];
        int[] right = new int[2];
        dfs(node.left, left);
        dfs(node.right, right);
        
        total[0] = node.val + left[1] + right[1];
        total[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    }
}
/*
这个解法会TLE，只是个思路。。。有很多重复计算，再想想如何把结果存起来。。。
*/
public class Solution {
    public int rob(TreeNode root) {
        return Math.max(dfs(root, true), dfs(root,false));
    }
    
    public int dfs(TreeNode node, boolean isChosen) {
        if(node == null) return 0;
        
        int left = dfs(node.left, false);
        int right = dfs(node.right, false);
        
        if(isChosen) {
            return left+right+node.val;
        } else {
            int leftIn = dfs(node.left, true);
            int rightIn = dfs(node.right, true);
            return Math.max(left, leftIn) + Math.max(right, rightIn);
        }
    }
}
