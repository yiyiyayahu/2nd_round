/*
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
*/

/*
对于这两个排序的要求，其实我都是用的java自带的函数，这样真的可以么？
一个是Arrays.sort()
还有一个是Collections.sort(list);
*/

/*
这样写code就简洁一些
    if(!map.containsKey(key)) map.put(key, new ArrayList<String>());
    map.get(key).add(s);
*/
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        if(strs.length == 0) return ret;
       
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if(!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
        for(String key : map.keySet()) {
            List<String> l = map.get(key);
            Collections.sort(l);
            ret.add(l);
        }
        return ret;
    }
}

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if(strs.length == 0) return ret;
        
        Arrays.sort(strs);
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            List<String> list;
            if(map.isEmpty() || !map.containsKey(key) ) {
                list = new ArrayList<String>();
            } else {
                list = map.get(key);
            }
            list.add(s);
            map.put(key, list);
        }
        for(String k : map.keySet()) {
            List<String> l = map.get(k);
            ret.add(l);
        }
        return ret;
    }
}

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if(strs.length == 0) return ret;
        
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            List<String> list;
            if(map.isEmpty() || !map.containsKey(key) ) {
                list = new ArrayList<String>();
                list.add(s);
                map.put(key, list);
            } else {
                list = map.get(key);
                list.add(s);
                map.put(key, list);
            }
        }
        for(String k : map.keySet()) {
            List<String> l = map.get(k);
            Collections.sort(l);
            ret.add(l);
        }
        return ret;
    }
}
