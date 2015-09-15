/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
*/

/*
https://leetcode.com/discuss/44281/8-lines-o-log-n-c-java-python
http://www.cnblogs.com/grandyang/p/4629032.html

这道题我觉得不好想啊啊啊啊，而且感觉没什么意思呢
比如56，十位上有(5+1)个，个位上也就是10-19有10个，6+10=16
可以用(x+8)/10来判断一个数是否大于等于2。

举个例子54215，比如现在求百位上的1（即len是100），54215的百位上是2。看到xx100到xx199的百位上都是1， 
这里xx从0到54，即100->199, 1100->1199...54100->54199, 这些数的百位都是1，百位上的1总数是55*10

如果n是54125,这时由于它的百位是1，先看xx100到xx199，其中xx是0到53，即54*10, 然后看54100到54125，这是26个。
所以百位上的1的总数是54*10 + 26

如果n是54025，那么只需要看xx100到xx199中百位上的1，这里xx从0到53，总数为54*10
*/

public class Solution {
    public int countDigitOne(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10)
            ones += (n/m + 8) / 10 * m + (n/m % 10 == 1 ? n%m + 1 : 0);
        return ones;        
    }
}
