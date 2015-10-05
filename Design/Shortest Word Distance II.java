/*
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list. 
*/

public class WordDistance {
    HashMap<String, ArrayList<Integer>> map;

    public WordDistance(String[] words) {
        map = new HashMap<String, ArrayList<Integer>>();
        for(int i = 0; i < words.length; i++) {
            String w = words[i];
            ArrayList<Integer> l = new ArrayList<Integer>();
            if(map.containsKey(w)) {
                l = map.get(w);
            } 
            l.add(i);
            map.put(w, l);
        }
    }

    public int shortest(String word1, String word2) {
        int distance = Integer.MAX_VALUE;
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        for(int i : list1) {
            for(int j : list2) {
                distance = Math.min(distance, Math.abs(i-j));
            }
        }
        return distance;
    }
}
