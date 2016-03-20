/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". 
We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
*/

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Arrays.sort(strings);
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> ret = new ArrayList<List<String>>();
        
        for(String s : strings) {
            String key = getKey(s);
            if(map.containsKey(key)) {
                map.get(key).add(s);
            } else {
                List<String> l = new ArrayList<>();
                l.add(s);
                map.put(key, l);
            }
        }
        for(List<String> list : map.values()) ret.add(list);
        return ret;
    }
    
    public String getKey(String s) {
        if (s.charAt(0) == 'a') return s;
        char[] arr = new char[s.length()];
        int diff = s.charAt(0) - 'a';
        for(int i=0; i < arr.length; i++) {
            int letterNum = s.charAt(i)-'a';
            if(letterNum >= diff) arr[i] = (char)(s.charAt(i) - diff);
            else arr[i] = (char)(s.charAt(i) + 26 - diff);
        }
        return String.valueOf(arr);
    }
}
