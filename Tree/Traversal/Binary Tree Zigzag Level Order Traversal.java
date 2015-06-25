/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

/*
这道题嘛，我就加了个int来track一下每一层是leftToRight还是RightToLeft了，感觉也没什么意义啊，和level traversal是一样的
java可以直接加到ArrayList的前面，如果不可以呢，用stack来实现?
http://blog.csdn.net/linhuanmars/article/details/24509105
*/
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();
        queue.add(root);
        int currNum = 0, lastNum = 1;
        int leftToRight = 1;
        
        while(!queue.isEmpty()) {
            TreeNode n = queue.remove();
            lastNum --;
            if(leftToRight == 1) list.add(n.val);
            else list.add(0, n.val);
            
            if(n.left != null) {
                queue.add(n.left);
                currNum ++;
            }
            if(n.right != null) {
                queue.add(n.right);
                currNum ++;
            }
            if(lastNum == 0) {
                result.add(list);
                lastNum = currNum;
                currNum = 0;
                leftToRight = 0-leftToRight;
                list = new ArrayList<Integer>();
            }
        }
        return result;        
    }
}
