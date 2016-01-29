/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

/*
我觉得这道题更多的是找规律，就是[0,1,3,2]然后反过来加2^2 append到list后面[0,1,3,2,6,7,5,4]依次类推，所以用backtracing
我code写的还是不熟练，改了好多次。。。首先是0怎么处理，然后再调用的时候不能用curr++，而要先+1，可以++curr或者直接curr+1
*/
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<Integer>();
        if(n < 0) return list;

        helper(list, n, 0);
        return list;
    }
    
    public void helper(List<Integer> list, int n, int curr) {
        
        if(curr == 0) {
            list.add(0);
        } else {
            int num = (int)Math.pow(2, curr-1);
            for(int i = num-1; i >= 0; i--) {
                list.add(list.get(i)+num);
            }
        }
        if(curr == n) return;
        helper(list, n, ++curr);
    }
}
