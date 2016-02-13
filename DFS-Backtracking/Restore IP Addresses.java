/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

/*
唉，这道题思路还可以，但是写了好多次都是错的
1. 开始没考虑清楚，就是num在0到255之间，add到list之后，其实还要再调用helper(。。start,curr+1。。)，不然出来就只有一种结果了
2. 还有一种情况关于0的情况没考虑到，比如00,01这样，我如果用num那种处理的话，就直接变成0和1了。。这样显然是不行的，所以当检测到"0"的时候只能直接把0放到list里面
所以加了一个判断 - if(s.charAt(start) == '0' && curr > start) return;
*/

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 12) return ret;
        helper(s, 0, 0, 0, new StringBuilder(), ret);
        return ret;
    }
    
    public void helper(String s, int start, int curr, int count, StringBuilder sb, List<String> ret) {
        if(count == 4 && curr == s.length()) ret.add(sb.toString());
        if(count == 4 || curr == s.length() || curr-start > 2) return;
        if(s.charAt(start) == '0' && curr > start) return;
        
        StringBuilder tmp = new StringBuilder(sb);
        int num = 0;
        for(int i = start; i <= curr; i++) {
            num *= 10;
            num += s.charAt(i)-'0';
        }
        
        if(num >= 0 && num <= 255) {
            if(tmp.length()==0) tmp.append(num);
            else tmp.append(".").append(num);
            
            helper(s, curr+1, curr+1, count+1, tmp, ret);
        } 
        helper(s, start, curr+1, count, sb, ret);
    }
}
