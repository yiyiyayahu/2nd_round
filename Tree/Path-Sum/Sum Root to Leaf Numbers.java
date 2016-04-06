/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/

public class Solution {
    int result = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        helper(root, 0);
        return result;
    }
    public void helper(TreeNode n, int tmp) {
        tmp = tmp * 10 + n.val;
        if(n.left == null && n.right == null) {
            result += tmp;
            return;
        } 
        if(n.left != null) helper(n.left, tmp);
        if(n.right != null) helper(n.right, tmp);
    }
}

/*
开始尝试用Integer来传递，发现失败。。。还是用int[1]好了
但是为什么呢，Integer不是一个object？
Integer是一个object，但是是immutable的，所以我加完了之后并不会改变原来的值
*/
public class Solution {
    public int sumNumbers(TreeNode root) {
        int[] sum = new int[1];
        dfs(root, 0, sum);
        return sum[0];
    }

    public void dfs(TreeNode root, int curr, int[] sum) {
        if(root == null) return;

        curr = curr * 10 + root.val;
        if(root.left == null && root.right == null) {
            sum[0] += curr;
            return;
        }
        dfs(root.left, curr, sum);
        dfs(root.right, curr, sum);
    }
}
