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
flip from true to false, false to true
flag ^= true;

true ^ true -> false
false ^ true -> true
*/
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> list = new ArrayList<Integer>();
        
        boolean leftToRight = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode n = queue.remove();
                if(leftToRight) list.add(n.val);
                else list.add(0, n.val);
                
                if(n.left != null) queue.add(n.left);
                if(n.right != null) queue.add(n.right);
            }
            leftToRight ^= true;
            ret.add(list);
            list = new ArrayList<Integer>();
        }
        return ret;           
    }
}
/*
这道题嘛，我就加了个int来track一下每一层是leftToRight还是RightToLeft了，感觉也没什么意义啊，和level traversal是一样的
java可以直接加到ArrayList的前面，如果不可以呢，
一种方法是改变加入queue的顺序
if(leftToRight == 1) {
    if(n.left != null) queue.add(n.left);
    if(n.right != null) queue.add(n.right);
} else {
    if(n.right != null) queue.add(n.right);
    if(n.left != null) queue.add(n.left);
}

这个blog比较复杂，用stack来实现的，有时间看看？
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
