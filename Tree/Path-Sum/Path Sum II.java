/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

/*
犯了两个错误：
1. result.add(new ArrayList<Integer>(list));这里用的是result.add(list)；导致后面删list点的时候result里面的也删掉了，结果就错了
2. 加到result里面之后还要执行一步list.remove(list.size()-1);
因为我这个程序调用完left就直接调用right，如果left找到了但是list没有把最后一个节点删掉的话，right还会往里面加的

时间复杂度O(n) 空间的话O(n)吧，因为还要存list<List<Integer>>
*/
public class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        helper(root, sum, new ArrayList<Integer>());
        return result;
    }
    
    public void helper(TreeNode root, int sum, List<Integer> list) {
        if(root == null) return;
        
        list.add(root.val);
        if(sum == root.val && root.left == null && root.right == null) {
            result.add(new ArrayList<Integer>(list));
            list.remove(list.size()-1);
            return;
        }
        helper(root.left, sum-root.val,list);
        helper(root.right, sum-root.val, list);
        list.remove(list.size()-1);
    }
}
