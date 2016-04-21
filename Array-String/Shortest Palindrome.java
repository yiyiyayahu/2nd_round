/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
*/

/*
String的题实在不好写啊，真的要多练一下 T.T
*/
public class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        if(n <= 1) return s;
        
        int idx = 0;
        for(int center = n/2; center>=0; center--){
            if(valid(s, center, 1)){   //is the longest valid palindrome? (assume length is even)
                idx = 2*center+1;
                break;
            }
            if(valid(s, center, 0)){  //assume length is odd
                idx = 2*center;
                break;
            }
    
        }
        String suffix = s.substring(idx+1);
        StringBuilder b = new StringBuilder(suffix);
        return b.reverse().append(s).toString();
    }
    public boolean valid(String s, int center, int shift){
        int i = center, j = center+shift;
        while(i>=0 && j<s.length()){
            if(s.charAt(i)!=s.charAt(j)){
                break;
            }
            i--;
            j++;
        }
        return i<0;
    }
}

/*
KMP的解法
https://leetcode.com/discuss/64309/clean-kmp-solution-with-super-detailed-explanation

KMP是string match的一种高效解法，brute force要O(mn)，但是KMP只用O(m+n)，by maintaining an array, locate prefix and postfix, thus, can skip some characters when checking
a b a b c
0 0 1 2 0
compute这个table的原理是，开始j和i都指向0，然后i往后面走，如果遇到s[i]==s[j]match的时候就把i的值设为j+1，同时j也往后面走
如果发现s[i]!=s[j],j就退回到s[i-1]再match和s[i]match，如果还不match就再次退回

和另外一个abababc match的时候就会是这样的
开始abab match，但是走到a的时候就不对了，这个时候呢，与其从第二个字符开始和s1重新match检测，我们可以看到，
a之前的ab和要match的string的prefix ab是一样的，也就是说走到a不对之前的两个字符ab其实是不用检测的，
这时候我们需要检查从ababc的第三个字符a开始。。。

We can convert this problem to an alternative problem"find the longest palindrome substring starts from index 0
但是KMP怎么应用到这道题里面呢
We add "#" here to force the match in reverse(s) starts from its first index What we do in KMP here 
is trying to find a match between prefix in s and a postfix in reverse(s). 
The match part will be palindrome substring
catacb 转化成
c a t a c b # b c a t a c
0 0 0 0 1 0 0 0 1 2 3 4 5

a a b a a a # a a a b a a
0 1 0 1 2 2 0 1 2 2 3 4 5

Then we run KMP on it, the value in last cell will be our solution. 
In this problem, we don't need to use KMP to match strings but instead we use the lookup table in KMP to find the palindrome.
就是prefix和postfix match的一种方法对吧，这时候发现其实前五个字符是match的，只要把后面的string reverse过add到string的前面就ok了
*/

/*
精简了一下code
*/
public class Solution {
    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);

        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s){
        int[] table = new int[s.length()];
    
        int index = 0;
        for(int i = 1; i < s.length(); ){
            if(s.charAt(index) == s.charAt(i)){
                table[i] = ++index;
                i++;
            } else {
                if(index > 0){
                    index = table[index-1];
                } else {
                    index = 0;
                    i ++;
                }
            }
        }
        return table;
    }
}

public class Solution {
    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);

        //get the maximum palindrome part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s){
        //get lookup table
        int[] table = new int[s.length()];
    
        //pointer that points to matched char in prefix part
        int index = 0;
        //skip index 0, we will not match a string with itself
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(index) == s.charAt(i)){
                //we can extend match in prefix and postfix
                table[i] = ++index;
            } else {
                //match failed, we try to match a shorter substring
    
                //by assigning index to table[i-1], we will shorten the match string length, and jump to the 
                //prefix part that we used to match postfix ended at i - 1
                index = table[i-1];
    
                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }
    
                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if(s.charAt(index) == s.charAt(i)){
                    //if match, then extend one char 
                    index ++ ;
                }
    
                table[i] = index;
            }
    
        }
    
        return table;
    }
}
