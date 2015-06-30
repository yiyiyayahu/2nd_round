/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

/*
利用postorder最后一位为root的规律，然后从inorder中切开两半，recursive
*/
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0 || inorder.length != postorder.length) return null;
        
        int len = inorder.length;
        return helper(inorder, 0, len-1, postorder, 0, len-1);
    }
    
    public TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if(inStart > inEnd) return null;
        if(inStart == inEnd) return new TreeNode(inorder[inStart]);
        TreeNode root = new TreeNode(postorder[postEnd]);
        int mid = 0;
        for(int i = inStart; i <= inEnd; i++) {
            if(inorder[i] == postorder[postEnd]) {
                mid = i;
                break;
            }
        }
        int leftLen = mid - inStart;
        root.left = helper(inorder, inStart, mid-1, postorder, postStart, postStart+leftLen-1);
        int rightLen = inEnd - mid;
        root.right = helper(inorder, mid+1, inEnd, postorder, postEnd-rightLen, postEnd-1);
        return root;
    }
}
