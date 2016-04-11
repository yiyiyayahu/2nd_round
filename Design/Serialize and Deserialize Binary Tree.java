/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory
buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization 
algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized 
to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

/*
其实倒是挺好像的，就和之前做的verify preorder traversal一样，记录下preorder traversal，如果是null，就用#占位，因为是string，用，分隔
所以[1,2,3,null,null,4,5] serialize的结果是1,2,#,#,3,4,#,#,5,#,#
但是后来怎么deserialize想了好一会儿。。。其实recursive是最简单的啦
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode n = stack.pop();
            if(n == null) sb.append("#").append(",");
            else {
                sb.append(n.val).append(",");
                stack.push(n.right);
                stack.push(n.left);
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int[] index = new int[1];
        return helper(arr, index);
    }
    
    public TreeNode helper(String[] arr, int[] index) {
        if(arr[index[0]].equals("#")) {
            index[0] ++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[index[0]++]));
        root.left = helper(arr, index);
        root.right = helper(arr, index);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
