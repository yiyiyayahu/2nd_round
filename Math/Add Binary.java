/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

/*
人家的代码简洁好多。。。

是这样的，如果这道题比较简单呢，就要比较细心，不要犯错。。。
开始我没看清题意直接撸袖子开始写。。。然后发现，不对额，i和j要从后往前，不能从0开始
总之要多检查多跑test case，不要想当然
*/
public class Solution {
    public String addBinary(String a, String b) {
        int c = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0;){
            if(i >= 0) c += a.charAt(i--) - '0';
            if(j >= 0) c += b.charAt(j--) - '0';
            sb.insert(0, (char)((c % 2) + '0'));
            c /= 2;
        }
        if(c == 1) sb.insert(0, "1");
        return sb.toString();
    }
}

public class Solution {
    public String addBinary(String a, String b) {
        if(a == null || b == null) return null;
        int len1 = a.length(), len2 = b.length();
        int len = Math.max(len1, len2);
        char[] arr = new char[len];
        int carry = 0;
        int i = len1-1, j = len2-1, index = len-1;
        while(index >= 0) {
            int sum = (i>=0 ? a.charAt(i)-'0' : 0) + (j >= 0 ? b.charAt(j)-'0' : 0) + carry;
            if(sum >= 2) {
                sum -= 2;
                carry = 1;
            } else {
                carry = 0;
            }
            arr[index--] = (char)(sum+'0');
            i --;
            j --;
        }
        String ret = String.copyValueOf(arr);
        if(carry > 0) ret = "1" + ret;
        return ret;
     }
}
