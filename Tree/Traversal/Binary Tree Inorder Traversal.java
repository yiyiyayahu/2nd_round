/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].
*/

//left - root - right


/*
我这个解法的空间复杂度是不好的，因为多加了一个set
开始想的是，stack，先push left点，push不进去的时候，pop同时加到list里面，再push当前pop出来的点的right节点
但是问题是容易死循环
比如[1,2]，stack push(1) push(2)，之后pop(2), list.add(2)，但是到1节点的时候还是会push(2)，之后pop(2), list.add(2)。。。
所以就要track一下访问过的节点不要再push了。

更好的方法想不到啊想不到，悲剧

google还要求O(1)的space，天，有一种Morris Traversal
http://blog.csdn.net/linhuanmars/article/details/20187257
http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
*/

/*
这个morris好巧妙，其实是把这个树连起来，就一直track当前curr node的前继
1. 如果curr.left==null，说明左边找好了，这个时候就输出curr.val到lists，同时curr到curr.right
2. 如果curr.left != null，我们就要找curr的前驱，也就是curr左边子树的最右边的节点，分两种情况 prev=curr.left
   一直向右移动prev直到下面这两种情况
   1）如果prev.right == null，很好，直接把right更新为curr，然后curr继续往左边走
   2）如果prev.right == curr，说明这个前驱就是当前的curr，这条线相当于找完了，就输出curr.val到lists，curr=curr.right
   然后恢复tree: prev.right=null
3. 重复以上1、2直到当前节点为空。
Time: O(n) Space: O(1)

直觉上，认为它的复杂度是O(nlgn)，因为找单个节点的前驱节点与树的高度有关。
但事实上，寻找所有节点的前驱节点只需要O(n)时间。n个节点的二叉树中一共有n-1条边，整个过程中每条边最多只走2次，
一次是为了定位到某个节点，另一次是为了寻找上面某个节点的前驱节点
*/
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lists = new ArrayList<Integer>();
        if(root == null) return lists;
        
        TreeNode curr = root;
        TreeNode prev = null;
        
        while(curr != null) {
            if(curr.left == null) {
                lists.add(curr.val);
                curr = curr.right;
            } else {
                prev = curr.left;
                while(prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if(prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    lists.add(curr.val);
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return lists;
    }
}
/*
用一个n来track，有点模拟走过路线的意思，来保证重复路线没有走过！很巧妙诶，还是自己想不出肿么办
time: O(n) space: O(logn)

其实这个解法就是说我是应该接着往左边走，还是左边已经走过了，要开始右边了，当n==null的时候就说明，左边没有了，这时候就add value，然后遍历右边的节点就好了
*/
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lists = new ArrayList<Integer>();
        if(root == null) return lists;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode n = root;
        while(!stack.isEmpty() || n != null) {            
            if(n != null) {
            	stack.push(n);
            	n = n.left;
            } else {
                n = stack.pop();
                lists.add(n.val);
                n = n.right;
            }
        }
        return lists;
    }
}

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lists = new ArrayList<Integer>();
        if(root == null) return lists;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        HashSet<TreeNode> traversed = new HashSet<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode n = stack.peek();
            if(n.left != null && !traversed.contains(n.left)) {
            	stack.push(n.left);
            } else {
                n = stack.pop();
                lists.add(n.val);
                traversed.add(n);
                if(n.right != null) stack.push(n.right);
            }
        }
        return lists;
    }
}
