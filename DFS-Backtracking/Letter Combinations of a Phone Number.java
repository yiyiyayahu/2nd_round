/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/


/*
time: O(3^n) space: O(n)
用一个String[]来存dict还是很巧妙的
*/
public class Solution {
    private String[] dict = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return result;
        helper(digits, "", result, 0);
        return result;
    }
    
    public void helper(String digits, String str, List<String> result, int step) {
        if(step == digits.length()) {
            result.add(str);
            return;
        }
        
        int num = digits.charAt(step) - '0';
        String word = dict[num];
        for(int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
            helper(digits, str+c, result, step+1);
        }
    }
}
