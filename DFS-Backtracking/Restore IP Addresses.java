/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

/*
看了discuss之后refactor了一下code，似乎这样写规范一点
*/
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 12) return ret;
        int[] path = new int[4];
        helper(s, 0, path, 0, ret);
        return ret;
    }
    
    public void helper(String s, int curr, int[] path, int count, List<String> ret) {
        if(count == 4 && curr == s.length()) {
            ret.add(path[0] + "." + path[1] + "." + path[2] + "." + path[3]);
            return;
        }
        if(count == 4 || curr == s.length()) return;
        
        for(int len = 1; len <= 3 && curr+len<=s.length(); len ++) {
            String sub = s.substring(curr, curr+len);
            int num = Integer.parseInt(sub);
            if(num > 255 || (s.charAt(curr) == '0'&&len>=2)) break;
            path[count] = num;
            helper(s, curr+len, path, count+1, ret);
            path[count] = -1;
        }
    }
}

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
