/*
 Given a string s, return all the palindromic permutations (without duplicates) of it. 
 Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

    If a palindromic permutation exists, we just need to generate the first half of the string.
    To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.

*/

/*
艾玛，这道题做了好久。。。还是自己学艺不精。。。赶脚差好多啊
时间复杂度还是可以的，2ms

其实就是先找出half，就是可以构成palindrome的那部分
比如aba -> a aabb -> ab aaaa -> aa
然后找half的各种组合
最后再填满就可以了

写code的过程中出了好多错。开始直接在arr[c]上面做改动，导致后来检测到同样的字符的时候以为是not valid直接返回list，在46行加了个判断就好了
接下来出的问题是我用了个start，每次都从half的start往后面开始往里面加。。。但是有可能先加的index=1 后加index=0的值啊
再然后呢，就是重复的加了好多遍，比如half是aaa。。。这样要用到之前写过好多次的while循环啊

总之自己差好多要多练啊！！！
*/

public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> list = new ArrayList<String>();
        int len = s.length();
        if(len == 0) return list;
        
        int[] arr = new int[256];
        for(int i = 0; i < len; i++) {
            arr[s.charAt(i)] ++;
        }
        boolean hasOneOdd = false;
        char oddChar = ' ';
        StringBuilder half = new StringBuilder();
        
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            
            if(arr[c] %2 == 1) {
                if(hasOneOdd && oddChar != c) return list;
                hasOneOdd = true;
                oddChar = c;
            } 

            while(arr[c] >= 2) {
                half.append(c);
                arr[c] -= 2;
            }
        }
        
        dfs(s, 0, new boolean[half.length()], oddChar, half.toString(), new char[len], list);
        return list;
    }
    
    private void dfs(String s, int index, boolean[] visited, char oddChar, String half, char[] strArr, List<String> list) {
        int len = s.length();

        if(index == len/2) {
            if(len%2 == 1) strArr[index] = oddChar;
            for(int i=index-1; i>= 0; i--) {
                strArr[len-1-i] = strArr[i];
            }
            list.add(String.valueOf(strArr));
            return;
        } 
        for(int i = 0; i < half.length(); i++) {
            if(visited[i]) continue;
            strArr[index] = half.charAt(i);
            visited[i] = true;
            dfs(s, index+1, visited, oddChar, half, strArr, list);
            visited[i] = false;
            while(i < half.length()-1 && half.charAt(i) == half.charAt(i+1)) i++; 
        }
    }
}
