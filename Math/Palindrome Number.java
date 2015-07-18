/*
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/

/*
这里我要注意一下，之前我写的
        while(x / div > 0) {
            div *= 10;
        }
        div = div/10;
这样貌似会导致div overflow，比如x=1000000001，再乘个10绝对就overflow了，div的数值就不对了
何必这么麻烦，直接while(x / div >= 10)就好了
*/

public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        
        int div = 1;
        while(x / div >= 10) {
            div *= 10;
        }
        
        int start = div, end = 1;
        while(start >= end) {
            if((x/start)%10 != (x/end)%10) return false;
            start /= 10;
            end *= 10;
        }
        return true;
    }
}
