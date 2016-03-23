/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

/*
下面的code似乎比之前的思路清晰点
*/
public class Solution {
    public String minWindow(String s, String t) {
        int slen = s.length(), tlen = t.length();
        if(slen < tlen) return "";
        
        int start = 0, end = start;
        int[] T = new int[256];
        int[] foundT = new int[256];
        int charsFound = 0;
        String minWindow = "";

        for(int i = 0; i < t.length(); i++) {
            T[t.charAt(i)] ++;
        }
        
        while(start < slen) {
            while(end < slen && charsFound < tlen) {
                char c = s.charAt(end);
                if(foundT[c] < T[c]) charsFound ++;
                if(T[c] > 0) foundT[c] ++;
                end ++;
            }
            if(charsFound == tlen) {
                if(minWindow.equals("") || end-start < minWindow.length()) {
                    minWindow = s.substring(start, end);
                }
            }
            char curr = s.charAt(start);
            if(T[curr] > 0) {
                foundT[curr] --;
                if(foundT[curr] < T[curr]) charsFound --;
            }
            start ++;
        }
        return minWindow;
    }
}
/*
唉，这道题写了好久，总是有错误
思想就是：
1. 对于T弄个hashmap来存T有哪些字符，每个字符出现了多少次
（但是又由于呢，T是string嘛，只要一个256位的array来记录就好了，hashmap太烦了）
2. 用两个指针start和end，start和end中间是包含T中所有字符的substring
先固定start，然后找end，end一点点向右移，移动的过程中可以再用一个array来track都走过了哪些char，再用一个num来记录走过了多少T种包含的，都是辅助啦
3. 找到这样的一对start和end之后再缩小这个窗口，start若是可以后移的话就后移一位
4. start++重复步骤2和3

错误点：
1. 开始TLE的原因是呢，我直接for循环了，所以我不是从上次找到可能窗口的start的下一个开始，而是从0，1，2等等找下去，这样当然就TLE了
2. 这个循环里面，我开始是把if(foundChars == tlen) break;放到前面来check，那么问题就是，end++之后end变成了slen，超出了范围，就有exception了
    while(end < slen) {
        char curr = s.charAt(end);
        if(foundT[curr] < strT[curr]) {
            foundChars ++;
        }
        foundT[curr] ++;
        if(foundChars == tlen) break;
        end++;
    }
*/
public class Solution {
    public String minWindow(String s, String t) {
        int slen = s.length(), tlen = t.length();
        int[] strT = new int[256];
        for(int i = 0; i < tlen; i++) {
            strT[t.charAt(i)] ++;
        }
        
        String ret = "";
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        while(start < slen) {
            char c = s.charAt(start);
            if(strT[c] == 0) {
                start++;
                continue;
            }
            
            int[] foundT = new int[256];
            int foundChars = 0;
            int end = start;
            while(end < slen) {
                char curr = s.charAt(end);
                if(foundT[curr] < strT[curr]) {
                    foundChars ++;
                }
                foundT[curr] ++;
                if(foundChars == tlen) break;
                end++;
            }
            if(foundChars < tlen) break;
            
            while(start <= end) {
                c = s.charAt(start);
                if(foundT[c] > strT[c]) {
                    start++;
                    foundT[c]--;
                } else {
                    break;
                }
            }
            
            if(end-start+1 < minLen) {
                minLen = end-start+1;
                ret = s.substring(start, end+1);
            }
            start++;
        }
        return ret;
    }
}
