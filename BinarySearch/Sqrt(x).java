/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/

/*
但这道题的trick是溢出！整数乘法很容易溢出的
但是这题有意思的是，二分过程中终止条件的确认。因为整数的乘法有可能导致溢出，而这种溢出的检测跟整数加法直接判断是否小于0是不同的，
因为整数的乘法有可能引起多次溢出，当奇数次溢出时是负数，但是偶数次溢出时就又变回正数了，比如
2147395599
如果用是否小于0来终止二分的话，它的平方根会返回617921965，而不是46339。

所以，二分的终点确定比较重要，在运算中想通过是否小于0来判断溢出，是不可靠的，
最好的办法就是在二分之前，要求end小于sqrt(INT_MAX)。sqrt(INT_MAX)是个常量，所以不算cheat。

其实这里return的话只要return end就可以。。。不return start是因为tmp < x的时候start后移可能就变大了 但是end前移是ok的
为什么返回end呢： 考虑return哪个的时候可以考虑循环终止时候的情况，这里就是start==end：
start==end，如果这个时候tmp<x，start后移了一位，跳出循环，那说明前一个小，后一个大（不然end为什么会向前挪）返回end ok
*/
public class Solution {
    public int mySqrt(int x) {
        if(x < 0) return -1;
        
        int start = 0;
        int limit = (int) Math.sqrt(Integer.MAX_VALUE);
        int end = limit;
        if(x/2 < limit) end = x/2+1;
        while(start <= end) {
            int mid = (start + end)/2;
            int tmp = mid * mid;
            if(tmp == x) return mid;
            else if(tmp < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }
}
