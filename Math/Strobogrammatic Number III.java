/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

/*
开始想直接count
什么len2-1->len1的所有的加和啊，然后再想怎么count啊，后来发现好麻烦，程序根本没法儿写
还不如就像II一样，先构造出来这些string，然后看看是不是valid，不是valid就不count++好了
这题明天再写一下吧，现在有点生病，实在是脑子不咋转（也可能就是个借口 ==）
*/

public class Solution {
    char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    int count = 0;
    
    public int strobogrammaticInRange(String low, String high) {
        for(int len = low.length(); len <= high.length(); len++) {
            dfs(low, high, new char[len], 0, len - 1);
        }
        return count;
    }
    
    public void dfs(String low, String high, char[] c, int left, int right) {
        if(left > right) {
            String s = new String(c);
            if((s.length() == low.length() && s.compareTo(low) < 0) || 
               (s.length() == high.length() && s.compareTo(high) > 0)) return;
            count++; 
            return;
        }
    
        for(char[] p : pairs) {
            c[left] = p[0]; 
            c[right] = p[1];
            if(c.length != 1 && c[0] == '0') continue;
            if(left < right || left == right && p[0] == p[1]) dfs(low, high, c, left + 1, right - 1);
        }
    }
}
