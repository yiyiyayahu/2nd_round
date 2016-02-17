/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive. 

Note:
 A naive algorithm of O(n2) is trivial. You MUST do better than that. 

Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2. 
*/

/*
这实在是一道神题，吓死宝宝了。。。
贴一个很好的链接http://huntzhan.org/leetcode-count-of-range-sum/

这题当时是试了一下leetcode的mock新功能，hard, 给了45分钟，尼玛，我想了20分钟愣是不造咋做
第一个想法就是s[i][j]可以由sum[j]-sum[i-1]得出来嘛，然后呢！
然后比如我在index j这里，我就会想找这样的sum[i-1]在range [sum[j]-upper, sum[j]-lower]里面
结果否定了O(n^2)的解法。。。我的脑袋。。。只能陪我走到这里了
之后吧，我就乱七八糟一顿画，但是屁也没想出来。。。苦，好吧，我认怂，看答案

是这么想的：
要想找到这样的sum[i-1]时间复杂度是O(1)基本不可能，所以要是优化呢只能优化到O(logn)
来来来，小伙伴们想一想，O(logn)的算法都有什么丫
答: 1. binary search 2. divide and conquer 类似mergesort那种貌似可以做出来
好吧，我学艺不精，binary search倒是想出来但是做不出。。。因为这种要sort的array啊摔

然后呢，想想binary search，有没有觉得sum[j]-upper<= sum[i-1] <= sum[j]-lower很眼熟啊
好吧，其实我不熟，看答案之后才知道，这妥妥的BST啊！要是构造一个BST，把i<j的sum[i]一个个塞进去，然后算tree的size，不就结了！
所以，这题的第一个解法丧心病狂，构造一个BST，代码巨长！我也是第一次知道面试题还可以这么玩儿的！

恩恩，好了，打算回家去研究一下mergesort那个解法，然后洗洗睡了T.T
*/
public class Solution {
    private class TreeNode {
        long val = 0;
        int leftsize = 0;
        int rightsize = 0;
        int cnt = 0;
        TreeNode left;
        TreeNode right;
        
        private TreeNode(long val) {
            this.val = val;
            this.cnt = 1;
        }
    }
    
    public TreeNode insertToBST(TreeNode root, long val) {
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }
        if(val < root.val) {
            root.left = insertToBST(root.left, val);
            root.leftsize ++;
        } else if(val > root.val) {
            root.right = insertToBST(root.right, val);
            root.rightsize ++;
        } else {
            root.cnt ++;
        }
        return root;
    }
    
    public int search(TreeNode root, long low, long high) {
       int total = root.cnt + root.leftsize + root.rightsize;
       return total - smaller(root, low) - larger(root, high);
    }
    
    public int smaller(TreeNode root, long low) {
        if(root == null) return 0;
        if(root.val == low) return root.leftsize;
        if(root.val > low) return smaller(root.left, low);
        return root.leftsize + root.cnt + smaller(root.right, low);
    }
    
    public int larger(TreeNode root, long high) {
        if(root == null) return 0;
        if(root.val == high) return root.rightsize;
        if(root.val < high) return larger(root.right, high);
        return root.rightsize + root.cnt + larger(root.left, high);
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        if(len == 0) return 0;
        
        int count = 0;
        long[] sum = new long[len+1];
        for(int i = 0; i < len; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        TreeNode root = new TreeNode(sum[0]);
        for(int i = 1; i <= len; i++) {
            count += search(root, sum[i]-upper, sum[i]-lower);
            insertToBST(root, sum[i]);
        }
        return count;
    }
}
