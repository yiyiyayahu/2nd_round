/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
*/

/*
最简单的brute force，就是遍历，要两两比较
但是两个word之间也都要遍历一遍，效率特别低
所以就想到bit manipulation，一共就是26个小写字母，所以用数组表示words里面每个word的值就可以了
开始我想的是二维数组，但其实呢，一维的就可以，一个int就可以表示一个word
这个处理方式还是要记得的
bitmap[i] |= 1 << (int)(w.charAt(j)-'a');
比如a -> 1 ab -> 11 abc -> 111 ad -> 1001，就是d的话，1<<3变成1000
那两个word没有共同数字就是(bitmap[i]&bitmap[j])==0这里注意一定要有括号，因为&的运算符比较级很低
*/

public class Solution {
    public int maxProduct(String[] words) {
        int len = words.length;
        if(len < 2) return 0;
        
        int[] bitmap = new int[len];
        for(int i = 0; i < len; i++) {
            String w = words[i];
            for(int j = 0; j < w.length(); j++) {
                bitmap[i] |= 1 << (int)(w.charAt(j)-'a');
             }
        }
        
        int max = 0;
        for(int i = 0; i < len-1; i++) {
            for(int j = i+1; j < len; j++) {
                if((bitmap[i] & bitmap[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
