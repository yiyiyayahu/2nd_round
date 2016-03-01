/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
*/

public class Solution {
    public String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[4];
        if(num == 0) return "Zero";
        int index = 3;
        while(num > 0) {
            arr[index--] = num%1000;
            num = num/1000;
        }
        for(int i = 0; i < 4; i++) {
            if(arr[i] == 0) continue;
            sb.append(helper(arr[i]));
            if(i == 0) sb.append(" Billion ");
            if(i == 1) sb.append(" Million ");
            if(i == 2) sb.append(" Thousand ");
        }
        if(sb.charAt(sb.length()-1) == ' ') sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    

    public String helper(int n) {
        String[] strOnes = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] strTens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] strTys = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        
        StringBuilder sb = new StringBuilder();

        if(n/100 > 0) {
            sb.append(strOnes[n/100-1]).append(" Hundred");
            n = n%100;
        }
        if(n >= 10 && n <= 19) {
            if(sb.length() != 0) sb.append(" ");
            sb.append(strTens[n-10]);
            return sb.toString();
        } 
        if(n/10 > 0) {
            if(sb.length() != 0) sb.append(" "); 
            sb.append(strTys[n/10-2]);
            n = n%10;
        }
        if(n != 0) {
            if(sb.length() != 0) sb.append(" "); 
            sb.append(strOnes[n-1]);
        }
        return sb.toString();
    }
}
