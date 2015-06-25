/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

*/

/*
感觉II没什么意义啊，大家都是要么直接add(0,list)要么之后把result reverse一下，其他都和I是一样的。。
*/
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();
        queue.add(root);
        int currNum = 0, lastNum = 1;
        
        while(!queue.isEmpty()) {
            TreeNode n = queue.remove();
            lastNum --;
            list.add(n.val);
            if(n.left != null) {
                queue.add(n.left);
                currNum ++;
            }
            if(n.right != null) {
                queue.add(n.right);
                currNum ++;
            }
            if(lastNum == 0) {
                result.add(0, list);
                lastNum = currNum;
                currNum = 0;
                list = new ArrayList<Integer>();
            }
        }
        return result;       
    }
}
