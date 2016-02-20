/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

/*
哭了，下面这个是原来可以过的解法，结果现在TLE了。。。
*/
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<Integer>();
        if(words.length == 0) return list;
        
        int len = s.length(), n = words.length, m = words[0].length();
        if(len < m*n) return list;
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String w : words) {
            int num = 0;
            if(map.containsKey(w)) num = map.get(w);
            map.put(w, num+1);
        }
        
        int start = 0;
        while(start <= len-m*n) {
            String curr = s.substring(start, start+m);
            if(!map.containsKey(curr)) {
                start ++;
                continue;
            }
            int i = start;
            Map<String, Integer> tmp = new HashMap<String, Integer>(map);
            while(i < start+m*n) {
                String str = s.substring(i, i+m);
                if(tmp.isEmpty() || !tmp.containsKey(str)) break;
                
                if(tmp.get(str) > 1) tmp.put(str, tmp.get(str)-1);
                else tmp.remove(str);
                i += m;
            }
            if(tmp.isEmpty()) list.add(start);
            start ++;
        }
        return list;
    }
}
