/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. 
Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
*/


/*
开始没想清，以为只有小于等于两个#,#连在一起才是valid
但是 "#" 和 "10,8,#,#,#" 都是valid的
之后想的是，比如1,#,#那就说明1这个点走完了，就全部pop出来，然后放一个#占位
这样比如10,8,###这种情况，stack里面是[10,8,#]看到第二个#的时候把8,# pop出来，放入一个#表示10的左子树已经遍历完毕变成[10,#]
之后又遇到一个#就10,# pop出来，再push进一个#就表示全部完结了
所以最后应该stack.size()==1并且peek是#

但是这样做有一个问题，就是1,#,#,#,#应该是invalid的，但是我这种做法会return true
改了一下，就是发现stack.size()==1并且peek是#的时候，这个tree本来不应该有其他东西了，如果这时候string还没完，就return false

时间O(n)，空间O(n) 20ms感觉很一般诶
*/
public class Solution {
    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] arr = preorder.split(",");
        
        for(String s : arr) {
            if(stack.size()==1 && stack.peek().equals("#")) return false;
            if(!s.equals("#")) {
                stack.push(s);
            } else {
                while(stack.size()>=2 && stack.peek().equals("#")) {
                    stack.pop();
                    stack.pop();
                }
                stack.push("#");
            }
        }
        return stack.size()==1 && stack.peek().equals("#");
    }
}

/*
一个巧妙的用indgree和outdegree的做法
一个node，indegree是1，outdegree是2，就是指2个child，1个parent
新加入一个node的话呢，肯定用掉一个indegree的名额，但是如果不是null的话呢就又加入两个outdegree
用一个int diff来track indgree和outdegree的差值，其实也就是可以填的坑
如果发现坑没了，自然就是return false
然后最后遍历结束应该是diff==0
*/
public class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] arr = preorder.split(",");
        int diff = 1;
        for(String s : arr) {
            if(--diff < 0) return false;
            if(!s.equals("#")) diff += 2;
        }
        return diff == 0;
    }
}

