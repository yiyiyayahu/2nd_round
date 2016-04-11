/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/


/*
写的这是什么和什么，虽然过了，但是我觉得这方法有点太简单粗暴？
我就是num2的每个字符遍历，和num1乘起来，然后再加和，分配了很多extra space。。。额，看看之前做的好啦
*/
public class Solution {
    public String multiply(String num1, String num2) {
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        String s = "";
        for(int i = arr2.length-1; i >= 0; i--) {
            s = addTwoString(s, helper(arr1, arr2[i], arr2.length-1-i)); 
        }
        if(s.startsWith("0")) return "0";
        return s;
    }

    public String helper(char[] arr1, char c2, int level) {
        if(c2 == '0') return "0";
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = arr1.length-1; i >= 0; i--) {
            int curr = (arr1[i]-'0') * (c2-'0') + carry;
            carry = curr/10;
            sb.append(curr%10);
        }
        if(carry > 0) sb.append(carry);
        sb.reverse();
        for(int i = level; i > 0; i--) {
            sb.append("0");
        }
        return sb.toString();
    }

    public String addTwoString(String s1, String s2) {
        if(s1.length() == 0) return s2;
        StringBuilder sb = new StringBuilder();
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int len1 = arr1.length, len2 = arr2.length;
        int i = arr1.length-1, j = arr2.length-1;
        int carry = 0;
        while(i >= 0 || j >= 0 || carry > 0) {
            int sum = (i>=0?arr1[i]-'0':0) + (j>=0?arr2[j]-'0':0)+carry;
            carry = sum/10;
            sb.append(sum%10);
            i--;
            j--;
        }
        return sb.reverse().toString();
    }
}
