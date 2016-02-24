/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
             
        int len1 = arr1.length, len2 = arr2.length; 
        for(int i = 0; i < Math.min(len1, len2); i++) {
            int curr = compareNum(arr1[i], arr2[i]);
            if(curr == 1) return 1;
            if(curr == -1) return -1;
        }
        if(len1 < len2) {
            for(int i = len1; i < len2; i++) {
                for(char c : arr2[i].toCharArray()) {
                    if(c != '0') return -1;
                }
            }
        }
        if(len1 > len2) {
            for(int i = len2; i < len1; i++) {
                for(char c : arr1[i].toCharArray()) {
                    if(c != '0') return 1;
                }
            }
        }
        return 0;
     }       
    public int compareNum(String s1, String s2) {
        int v1 = 0, v2 = 0;
        for(int i = 0; i < s1.length(); i++) {
            v1 *= 10;
            v1 += s1.charAt(i) - '0';
        }
        for(int i = 0; i < s2.length(); i++) {
            v2 *= 10;
            v2 += s2.charAt(i) - '0';
        }
        if(v1 > v2) return 1;
        else if(v1 < v2) return -1;
        else return 0;        
     }
}
