/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/

/*
我开始的时候不知道怎么想的，想用两个hashmap分别存<String, List<Integer>>这种，就是list里面放index，然后再比较index。。。我是不是疯了，额
其实character对应string就可以了。。。
char c to character就是new Character(c)就OK了
后来我又忘了这种情况。。。
"abba", str = "dog dog dog dog"
肿么这么简单的题还犯了这么多错。。。
*/
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if(pattern.length() != strs.length) return false;
        
        HashMap<Character, String> map = new HashMap<Character, String>();
        int len = strs.length;
        
        for(int i = 0; i < len; i++) {
            Character c = new Character(pattern.charAt(i));
            String s  = strs[i];
            if(!map.containsKey(c)) {
                if(map.containsValue(s)) return false;
                map.put(c, s);
            } else {
                if(!map.get(c).equals(s)) return false;
            }
        }
        return true;
    }
}
