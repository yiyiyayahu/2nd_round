/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/

/*
不晓得，觉得这道题很无聊。。。不过我还是没太审好题了
首先吧，这种"a", "it"不能算作是abbreviation，所以["a","a"] "a" return true
其次吧，比如["hello"] "hello"也是return true。。。因为和dictionary的是一个，人家说的是no other word from the dictionary has the same abbreviation.
所以，[] "hello"也是true，因为dictionary是空的。。。
*/
public class ValidWordAbbr {
    HashMap<String, String> map = new HashMap<String, String>();
    
    public ValidWordAbbr(String[] dictionary) {
        for(String w : dictionary) {
            String key = getAbbr(w);
            if(key == null) continue;
            
            if(map.isEmpty() || !map.containsKey(key)) {
                map.put(key, w);
            } else if(!map.get(key).equals(w)) {
                map.put(key, "#");
            }
        }
    }

    public boolean isUnique(String word) {
        String key = getAbbr(word);
        if(map.isEmpty() || !map.containsKey(key)) return true;
        if(map.get(key).equals(word)) return true;
        return false;
    }
    
    private String getAbbr(String word) {
        int len = word.length();
        if(len <= 2) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0)).append(len-2).append(word.charAt(len-1));
        return sb.toString();
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
