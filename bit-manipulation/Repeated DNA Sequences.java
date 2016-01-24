/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
*/

/*
和之前做的一道bit manipulation的挺像，就是maximum length的那个
都是把word转换为integer，如果integer一样，说明word相同
ACGT因为四个字母，所以就0,1,2,3表示，每个字母占两个bit
*/

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<String>();
        
        int len = s.length();
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for(int i = 0; i <= len-10; i++) {
            String sub = s.substring(i, i+10);
            int tmp = 0;
            for(int j = i; j < i+10; j++) {
                tmp = (tmp << 2) | convertToInt(s.charAt(j)); 
            }
            if(hashSet.contains(tmp) && !list.contains(sub)) list.add(sub);
            else hashSet.add(tmp);
        }
        return list;
        
    }
    public int convertToInt(char c) {
        switch(c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: return 0;
        }
    }
}
