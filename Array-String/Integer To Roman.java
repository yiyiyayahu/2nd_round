/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

/*
I-1 V-5 X-10 L-50 C-100 D-500 M-1000
roman的规则是：
1）在左边被减掉的只能是I(1) X(10) C(100) 
2）左边被减的只能出现一个，没有IIV这种情况
3）左减不可跨位 99不可以IC(100-1) 只能90+9->XCIX

这个做法好简洁啊！！！
*/
public class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        
        String [] symbol = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};    
        int [] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}; 
        for(int i = 0; num != 0; i++) {
            while(num >= value[i]) {
                sb.append(symbol[i]);
                num -= value[i];
            }
        }
        return sb.toString();
    }
}
