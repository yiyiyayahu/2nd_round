/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

/*
Time: O(n) Space: O(1)的做法，不用stack，感觉快了好多诶。。。只要4ms
不用stack的trick就是reuse原来的arr，然后通过指针移动达到和stack类似的效果
比如[9,8,10,5,12] i=-1 -> i=0 -> i=1 -> i--,i--=-1 preorder[0]=10 min=10
我觉得不是很好想，下面那个stack的还比较好想一点
*/
public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if(preorder.length < 2) return true;
        
        int min = Integer.MIN_VALUE, i = -1;
        for(int val : preorder) {
            if(val < min) return false;
            while(i >= 0 && val > preorder[i]) {
                min = preorder[i--];
            }
            preorder[++i] = val;
        }
        return true;
    }
}
/*
有了下面那个TLE的解法之后现在思路就清晰多了
一开始拿到这道题的时候有些懵，总想着构造这样一个tree，实际上没有必要
考虑一下不太行的情况，无非就是[9,8,10,5,12]这种，从10开始已经应该是比9大的了，但是遇到5就应该是invalid preorder了
所以呢，用一个stack，遇到9push，遇到小于peek的push，遇到大的就把stack里面小于它的pop出来，并且记录最后pop的那个值，也就是root的值，这里是9
然后接着往后走，如果发现有一个比root小的，就return false
这样时间就是O(n) 但是空间还是不太好，因为用了一个stack，所以space是O(n)
再想想怎么优化
*/
public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if(preorder.length < 2) return true;
        
        Stack<Integer> stack = new Stack<Integer>();
        int min = Integer.MIN_VALUE;
        for(int i = 0; i < preorder.length; i++) {
            int val = preorder[i];
            if(val < min) return false;
            
            while(!stack.isEmpty() && val > stack.peek()) {
                min = stack.pop();
            }
            stack.push(val);
        }
        return true;
    }
}
/*
我这个做法不好，已经TLE了，好多重复计算，时间复杂度O(n^2)
思路是这样的preorder[i]肯定是当前的root value了，然后从i+1开始往后找，所有开始小于这个root value的都是left subtree，
看到一个大于它的就说明right subtree开始了，但是如果在right这边发现有一个比root value小，那就return false了，不然就recursive的去找
*/
public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length-1);
    }

    public boolean verify(int[] preorder, int i, int j) {
        if(i > j) return true;

        int root = preorder[i];
        int midpoint = -1;
        for(int k = i+1; k <= j; k++) {
            if(preorder[k] > root) {
                if(midpoint == -1) midpoint = k; 
            } else {
                if(midpoint != -1) return false;
            }
        }
        if(midpoint == -1) return verify(preorder, i+1, j);
        return verify(preorder, i+1, midpoint-1) && verify(preorder, midpoint, j);
    }
}
