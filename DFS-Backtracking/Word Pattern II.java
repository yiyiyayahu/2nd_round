/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:

    pattern = "abab", str = "redblueredblue" should return true.
    pattern = "aaaa", str = "asdasdasdasd" should return true.
    pattern = "aabb", str = "xyzabcxzyabc" should return false.

Notes:
You may assume both pattern and str contains only lowercase letters. 
*/

/*
开始还是没想清楚怎么做。。。知道用DFS，但是没想好怎么写code （可能并木有好好想。。。）
其实就是pattern和str都从index=0开始，一点点match
如果map里面有这个entry的话呢，就从当前的index往后走，看是否一样，不一样这种match就fail掉，return false
如果没有这个entry呢，就str从当前的index开始，算一直到后面的所有substring，有没有match的，recursive来做

这里的剪枝是这样做的：
pattern和str都从后往前看
比如pattern="abaa"，a已经match过了，entry里面有值了，这样str从当前的index不用走到最后，而是走到去掉那两个a map的string的长度的位置
*/

public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        return match(pattern, str, map, 0, 0);
    }
    
    public boolean match(String p, String s, HashMap<Character, String> map, int inp, int ins) {
        int plen = p.length(), slen = s.length();
        if(inp == plen) return ins == slen;
        
        char curr = p.charAt(inp);
        if(map.containsKey(curr)) {
            int len = map.get(curr).length();
            if(ins + len > slen) return false;
            if(!s.substring(ins, ins+len).equals(map.get(curr))) return false;
            return match(p, s, map, inp+1, ins+len);
        }
        
        int end = slen;
        for(int i = plen-1; i > inp; i--) {
            char key = p.charAt(i);
            end -= map.containsKey(key) ? map.get(key).length() : 1;
        }
        for(int i = ins; i < end; i++) {
            String tmp = s.substring(ins, i+1);
            if(map.containsValue(tmp)) continue;
            
            map.put(curr, tmp);
            if(match(p, s, map, inp+1, i+1)) return true;
            map.remove(curr);
        }
        
        return false;
    }
}
