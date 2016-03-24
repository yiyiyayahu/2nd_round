/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

/*
palindrome可以找到这样一个center从中间往外面扩展。但是因为可能是两个字母中间为center，比如bb，所以一共有2n-1个center
O(n^2)
下面这个解法只用了13ms诶
很简洁的code
这里之所以这么写
start = i-(len-1)/2;
end = i + len/2;

考虑两种情况
1. len为奇数，也就是i是center，比如aba这样，i=1 len=3 len/2和(len-1)/2结果都是1，没差，得到的start=0 end=2
2. len为偶数，也就是i,i+1之间的是center，比如abba这样，i=1, len=4 (len-1)/2=1, len/2=2因为i离start近一些嘛，所以start就是i-(len-1)/2=0, end=3
*/
public class Solution {
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        
        for(int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > end-start) {
                start = i-(len-1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end+1);
    }
    private int expand(String s, int left, int right) {
        int L = left, R = right;
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L --;
            R ++;
        }
        return R-L-1;
    }
}
/*
就是从中间开始扩展
开始expand只有一个变量center，但是这样是不行的：
aba - ok
abba的话center是在bb中间的，并不代表一个字母，所以用个left，right更方便
*/
public class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int len = s.length();
        int maxLen = 0;
        String result = "";

        for(int i = 0; i < 2*len - 1; i++) {
            int left = i/2; int right = i/2;
            if(i % 2 == 1) right ++;
            
            String str = expandFromCenter(s, left, right);
            if(str.length() > maxLen) {
                result = str;
                maxLen = str.length();
            }
        }

        return result;
    }
    
    public String expandFromCenter(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        return s.substring(left+1, right);
    }
}
