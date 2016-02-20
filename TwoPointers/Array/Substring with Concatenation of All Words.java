/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

/*
这个解法的优化是，与其再重新start++那么从头找，不如看看能跳过几个word那种长度的单词，明儿再想想，好难写啊。。。。
*/
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        for(String word:words){
            if(map.containsKey(word)){
                map.put(word,map.get(word)+1);
            }else{
                map.put(word,1);
            }
        }
        int length = words[0].length();
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<length;i++){
            //just start from different point...
            Map<String,Integer> tmp = new HashMap<String,Integer>();
            int start = i;
            int count = 0;
            for(int j=i;j+length<=s.length();j+=length){
                String word = s.substring(j,j+length);
                if(map.containsKey(word)){
                    tmp.put(word,tmp.getOrDefault(word,0)+1);
                    count+=1;
                    while(tmp.get(word)>map.get(word)){ //At most greater than zero..
                        String removeWord = s.substring(start,start+length);
                        if(tmp.get(removeWord)>1){
                            //greater than 0..
                            tmp.put(removeWord,tmp.get(removeWord)-1);
                        }else{
                            tmp.remove(removeWord);
                        }
                        start+=length;
                        count-=1;
                        if(removeWord.equals(word))
                            break;
                    }
                    if(count==words.length)
                        result.add(start);
                }else{
                    tmp.clear();
                    count = 0;
                    start = j+length;
                }
            }
        }
        return result;
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
