/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
*/

/*
这道题开始写的问题是：00的情况
比如4的时候有1001啊之类的情况对吧，但是n=2的时候又没有，怎么加进去呢
结果自己code怎么都写不粗。。。是不是太弱了，咋办
*/
public class Solution {
    public List<String> findStrobogrammatic(int n) {
    	return helper(n, n);
    }	
    public List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));
        if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        List<String> list = new ArrayList<String>();
        List<String> pre = helper(n-2, m);
        for(String s : pre) {
            if (n != m) list.add("0" + s + "0");
            list.add("1" + s + "1");
            list.add("8" + s + "8");
            list.add("6" + s + "9");
            list.add("9" + s + "6");
        }
        return list;
    }
}
