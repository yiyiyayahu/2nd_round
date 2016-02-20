/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

/*
这个解法优化了一下，思想是这样的：
1. 首先搜索的起点是吧，i只是从0到words[0].length()，然后剩下的就是其他两个指针的事儿了
比如aaa这样，然后每个word的length是1的话，i的值只是0和1，就是从第0个开始还是第一个开始

2. 然后用两个指针 start和j，start就是当前查找的starting indices，然后j就从i开始一直找到结尾
新建一个HashMap tmp来存找过的word，count来track找到的word的总数
然后j往后移，如果发现，诶，map里不存在诶，说明j+n之前的都否定了，重头来，start=j+n;
但是如果发现map一直存在的，但是可能当前的word比map里面多了，那这种情况就是start一直后移直到tmp里面的不比map里面的多
这样一直找一直找，当发现count==m的时候就把start add到list里面去

所以这样下来，时间复杂度是O(m*slen)

但是我原来的那种解法呢，不行了，就start++重新开始
就是外循环是0 ~ slen-m*n，内循环是0 ~ m*n, 时间复杂度会多很多
*/
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<Integer>();
        if(words.length == 0) return list;
        
        int m = words.length, n = words[0].length(), slen = s.length();
        if(slen < m*n) return list;
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String w : words) {
            if(map.containsKey(w)) map.put(w, map.get(w)+1);
            else map.put(w, 1);
        }

        for(int i = 0; i < n; i++) {
            Map<String, Integer> tmp = new HashMap<String, Integer>();
            
            int start = i, count = 0;
            for(int j = i; j <= slen-n; j += n) {
                String str = s.substring(j, j+n);
                if(map.containsKey(str)) {
                    if(!tmp.containsKey(str)) tmp.put(str, 1);
                    else tmp.put(str, tmp.get(str)+1);
                    count ++;
                    while(tmp.get(str) > map.get(str)) {
                        String rmWord = s.substring(start, start+n);
                        if(tmp.get(rmWord) > 1) {
                            tmp.put(rmWord, tmp.get(rmWord)-1);
                        } else {
                            tmp.remove(rmWord);
                        }
                        count --;
                        start += n;
                        if(rmWord.equals(str)) break;
                    }
                    if(count == m) list.add(start);
                } else {
                    tmp.clear();
                    count = 0;
                    start = j + n;
                }
            }
        }
        return list;
    }
}
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
            Map<String, Integer> tmp = new HashMap<String, Integer>(map);
            
            for(int i = start; i < start + m *n; i += m) {
                String str = s.substring(i, i+m);
                if(!tmp.containsKey(str)) break;
                
                if(tmp.get(str) > 1) tmp.put(str, tmp.get(str)-1);
                else tmp.remove(str);
            }
            if(tmp.isEmpty()) list.add(start);
            start ++;
        }
        return list;
    }
}
