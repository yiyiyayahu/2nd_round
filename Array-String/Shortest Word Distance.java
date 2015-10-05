/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list. 
*/

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || words.length == 0) return -1;
        int index1 = -1, index2 = -1;
        int distance = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                index1 = i;
                if(index2 != -1) distance = Math.min(distance, Math.abs(index1-index2));
            } else if(words[i].equals(word2)) {
                index2 = i;
                if(index1 != -1) distance = Math.min(distance, Math.abs(index1-index2));
            }
        }
        return distance;       
    }
}


/*
Shortest Word Distance III

This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

Note:
You may assume word1 and word2 are both in the list. 
*/
public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if(words == null || words.length == 0) return -1;
        int index1 = -1, index2 = -1;
        int distance = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                index1 = i;
                if(index2 != -1 && index1 != index2) 
                	distance = Math.min(distance, Math.abs(index1-index2));
            } 
            if(words[i].equals(word2)) {
                index2 = i;
                if(index1 != -1 && index1 != index2) 
                	distance = Math.min(distance, Math.abs(index1-index2));
            }
        }
        return distance;      
    }
}
