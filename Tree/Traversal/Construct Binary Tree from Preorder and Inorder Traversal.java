/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

/*
同理，利用preorder的第一个是root的规律
*/
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || preorder.length != inorder.length) return null;
        int len = preorder.length;
        return helper(preorder, 0, len-1, inorder, 0, len-1);
    }
    
    public TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart > preEnd) return null;
        if(preStart == preEnd) return new TreeNode(preorder[preStart]);
        TreeNode root = new TreeNode(preorder[preStart]);
        
        int mid = inStart;
        for(; mid <= inEnd; mid++) {
            if(inorder[mid] == preorder[preStart]) {
                break;
            }
        }
        int leftLen = mid - inStart;
        int rightLen = inEnd - mid;
        root.left = helper(preorder, preStart+1, preStart+leftLen, inorder, inStart, mid-1);
        root.right = helper(preorder, preEnd-rightLen+1, preEnd, inorder, mid+1, inEnd);
        return root;
    }
}
