/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/

/*
如果按照我自己的思路呢，我是写不出下面的code的，
我一般会想着看最后一位有没有1，如果有的话，total++然后n向右挪一位，就这种的：
    	if((n^1) < n) {
    		count ++;
    	}
    	n = n >> 1;
但是有个问题就是很容易overflow
比如Integer.MIN_VALUE   2147483648 (10000000000000000000000000000000),和1异或之后就头和尾都是1，很显然就overflow了
然后下面这个写法呢，每次n&(n-1)只会比n小，不会比n大，只要n&(n-1)不是0就还可以继续对吧，只要当前n不是0，total就++
比如101&100=100就相当于把最后的1加到total上，同时还把最后的1去掉了
同样的，100&011=000，也是把最后的1加到total上，然后还把最后的1去掉了
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        if(n == 0) return 0;
        int num = 0;
        while(n != 0) {
            n = n & (n-1);
            num ++;
        }
        return num;
    }
}
