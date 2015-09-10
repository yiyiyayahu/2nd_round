/*
Description:

Count the number of prime numbers less than a non-negative number, n.
*/

/*
这道题的提示写的非常好，是一个特别完整的思维的过程
1.最简单粗暴的想法，countPrimes，遍历n个数字，对于每一个判断isPrime(i)，如果对每一个从0-i遍历看能不能整除的话，是O(n) 总体就是O(n^2)
2.优化，对于isPrime(i)，不用O(n)，O(n^(0.5))就行，因为只要遍历p直到p^2<i就可，复杂度优化到O(n^(1.5))
3.但是好像还是不太好。有没有更好的方法呢，可以用排除法 - 2的倍数都排除掉，3的倍数都排除掉。。。
4.接着优化，4之前已经作为2的倍数排除掉过所以不用管它
5.排除的过程中一定要一直一直乘么，比如2*2，2*3。。。3*2，3*3。。。很明显，3不用从2开始乘，只要从3开始乘就好了，所以只要从i*i开始就好了
6.接着优化，乘到什么时候为止呢，恩恩，看到第二点分析的，遍历p直到p^2<i就可
好啦，分析完毕，可以写代码了
*/
public class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for(int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        
        for(int i = 2; i * i < n; i++) {
            if(!isPrime[i]) continue;
            for(int j = i*i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(isPrime[i]) count++;
        }
        return count;
    }
}
