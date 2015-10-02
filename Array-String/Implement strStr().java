/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

/*
这个方法无疑是不太好的，brute force的做法
犯了两个低级错误，在while loop里面没有break出来。。。
没有考虑到找不到的情况，就是index都已经到len1了就是没有找到

唉，还是手生了啊
*/

public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null) return -1;
        int len1 = haystack.length(), len2 = needle.length();
        if(len1 == 0 && len2 == 0) return 0;
        if(len1 < len2) return -1;
        
        int index = 0, j = 0;
        while(index < len1) {
            int i = index;
            while(i < len1 && j < len2) {
                if(haystack.charAt(i) == needle.charAt(j)) {
                    i ++;
                    j ++;
                } else {
                	break;
                }
            }
            if(j == len2) return index;
            index ++;
            j = 0;
        }
        if(index == len1) return -1;
        return index;
    }
}
