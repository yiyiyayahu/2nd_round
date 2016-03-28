/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

/*
我觉得这道题很难。。。开始想不出，虽然能想到是要先构建一个graph然后再traverse进行topological sort，但是还是没有想很清楚

下面这个解法呢，是这样的
hashmap存character对应的它顺序之后的一个set of characters
然后呢，order[26]这个array主要是track BFS的level的，在level0的先入queue，然后level1 。。。遍历的过程中再一点点append到string里面
这个level的计算呢，就是如果一个char是其他char的后续的话，order[c]++
然后我遍历一圈没有环的话，应该所有的都遍历过了，如果还有order[c] > 0的char的话就说明the order invalid了
*/
public class Solution {
    public String alienOrder(String[] words) {
        HashMap<Character, HashSet<Character>> map = new HashMap<>();
        int[] order = new int[26];
        
        for(int i = 0; i < words.length; i++) {
            char[] arr = words[i].toCharArray();
            for(char c : arr) {
                if(!map.containsKey(c)) map.put(c, new HashSet<Character>());
            }
            if(i == 0) continue;
            
            String s1 = words[i-1], s2 = words[i];
            int len = Math.min(s1.length(), s2.length());
            int j = 0;
            while(j < len && s1.charAt(j)==s2.charAt(j)) j++;
            if(j == len) continue;
            if(map.get(s1.charAt(j)).add(s2.charAt(j))) {
                order[s2.charAt(j) - 'a'] ++;
            }
        }
        
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i < 26; i ++) {
            char c = (char)(i+'a');
            if(order[i] == 0 && map.containsKey(c)) queue.add(c);
        }
        
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            char c = queue.remove();
            sb.append(c);
            HashSet<Character> set = map.get(c);
            for(char ch : set) {
                order[ch-'a'] --;
                if(order[ch-'a']==0) queue.add(ch);
            }
        }
        
        for(int i = 0; i < 26; i++) {
            if(order[i] > 0) return "";
        }
        return sb.toString();
    }
}
