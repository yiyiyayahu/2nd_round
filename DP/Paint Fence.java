/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
*/

/*
我觉得这道题很难诶。。。
很明显的DP，关键是如何做
开始想简单了：前两个一样count[i-2]*(k-1) + 前两个不一样 count[i-3]*(k-1)*k
但是后来发现，诶，好像不太对吧，好像总是有重复计算的
如果对于每个i，都有这么两个值来track就好了 - # of ways 和前面一样s，和前面不一样d2
对于不一样的更新其实是(s+d)*(k-1)，tmp作为一个缓存来存之前的same
但是要注意同时更新s和d

比如k=3
n=1, 3
n=2, same:3 diff:3*2=6 total 9
n=3, same:3*2 diff: 9*2=18 total 24
n=4, same:18 diff:24*2= 48 total 66
...
*/

public class Solution {
    public int numWays(int n, int k) {
        if(n<=1 || k==0) return n*k;
        
        int s = k, tmp = k, d = k*(k-1);
        for(int i = 2; i < n; i++) {
            s = d;
            d = (k-1) * (tmp+d);
            tmp = s;
        }
        return s + d;
    }
}
