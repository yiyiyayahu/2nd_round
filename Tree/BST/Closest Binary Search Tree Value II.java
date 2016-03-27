/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.
*/


/*
用一个maxHeap来做倒是ok的，时间复杂度是O(nlogk)
但是提示说用两个stack分别存predecessor和successor，再想想
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
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<>();
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == o2) return 0;
                return Math.abs(o2-target)-Math.abs(o1-target) > 0 ? 1:-1;
            }
            });
        dfs(root, target, k, maxHeap);
        for(int i : maxHeap) list.add(i);
        return list;
    }
    
    public void dfs(TreeNode root, double target, int k, PriorityQueue<Integer> maxHeap) {
        if(root == null) return;
        int val = root.val;
        if(maxHeap.size() < k) maxHeap.offer(val);
        else {
            if(Math.abs(val-target) < Math.abs(maxHeap.peek()-target) ) {
                maxHeap.poll();
                maxHeap.offer(val);
            }
        }
        if(root.left != null) dfs(root.left, target, k, maxHeap);
        if(root.right != null) dfs(root.right, target, k, maxHeap);
    }
}
