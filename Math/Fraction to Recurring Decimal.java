/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/

/*
题倒是不难，但是有几点需要注意
1. denominator为0的情况
2. 有一方为负的情况
3. 注意注意注意，Integer.MIN_VALUE是"-2147483648"，没办法直接转化成int，这样就溢出了！！！太变态了，所以要用long
那如果long/long结果还是long，这点要注意！！！
一般对于integer的操作很有可能就是考溢出啊，正负啊什么的，多想想
*/
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0 || denominator == 0) return "0";
        
        boolean isNeg = (numerator < 0) ^ (denominator < 0);
        
        long numer = (long)numerator, denom = (long)denominator;
        numer = Math.abs(numer);
        denom = Math.abs(denom);
        
        StringBuilder sb = new StringBuilder();
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        
        long result = numer/denom;
        sb.append(result);
        
        long remainder = (numer%denom)*10;
        if(remainder > 0) sb.append(".");
    	
        while(remainder != 0) {
            if(map.containsKey(remainder)) {
                int pos = map.get(remainder);
                sb.insert(pos, "(");
                sb.insert(sb.length(), ")");
                break;
            } 
            long curr = remainder/denom;
            sb.append(curr);
            map.put(remainder, sb.length()-1);
            remainder = (remainder%denom) * 10;
        }
        if(isNeg) sb.insert(0, "-");
        return sb.toString();
    }
}
