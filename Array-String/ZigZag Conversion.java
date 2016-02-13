/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

/*
这个题吧，不难，但是好多edge case要考虑
转化为index来想比较简单
n=3的话
0   4   8    12
1 3 5 7 9 11
2   6   10

n=4
0     6      12
1   5 7    11
2 4   8 10
3     9
可以看出来，除了第一行和最后一行，中间的都是有填充字母的，然后第一行最后一行两个元素间隔最大numRows + numRows-2
然后看中间的那几行的间隔，
开始我想错了，以为是从bigInterval开始一直减2就是每行的间隔
但是其实中间几行元素和元素的间隔不是固定的，比如n=4的第二行间隔是4,2,4, 第三行间隔是2,4,2
所以写成了下面的code
还有一个没考虑到的是numRows-2 <= 0
*/
public class Solution {
    public String convert(String s, int numRows) {
        if(s.length() == 0 || numRows > s.length()) return s;
        
        StringBuilder sb = new StringBuilder();
        int numMid = numRows-2 <= 0? 0 : numRows-2;
        int bigInterval = numRows + numMid;
        int interval = bigInterval;
        for(int i = 0; i < numRows; i++) {
            if(i == numRows-1) interval = bigInterval;
            boolean flag = true;
            for(int j = i; j < s.length(); ) {
                sb.append(s.charAt(j));
                if(i==0 || i == numRows-1 || flag) {
                    j += interval; 
                    flag = false;
                } else {
                    j += bigInterval-interval; 
                    flag = true;
                }
            }
            interval -= 2;
        }
        return sb.toString(); 
    }
}
