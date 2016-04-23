/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".
*/

/*
开始没太懂，后来擦知道vowels是元音，就是aeiou
*/
public class Solution {
    public String reverseVowels(String s) {
        int i = 0, j = s.length()-1;
        char[] arr = s.toCharArray();
        String vowels = "aeiouAEIOU";
        while(i < j) {
            if(vowels.indexOf(arr[i]) == -1) { i++; continue; }
            if(vowels.indexOf(arr[j]) == -1) { j--; continue; }
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++; j--;
        }
        return String.valueOf(arr);
    }
}
