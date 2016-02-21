/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/

/*
唉，我也不晓得为什么，greedy的题感觉就是想不出？或者就是我好多题都想不出，只是因为做过了所以现在有印象？好悲惨啊
greedy我脚着就是简单粗暴型选手
比如这道题，我看到a，那就简单粗暴的把之前所有大于它的并且之后还有可能出现的统统除掉（用stack）
开始我想着一步到位，其实如果遍历两遍这个问题就比较好解决，第一遍是统计所有字符出现的次数，第二部开始构建string
写code要注意几点，也是我开始没有考虑到的，导致很多test case没过
1. 其实我看到一个char c，我就应该把counts[c]--，因为已经遇到过了
2. 如果这个stack里面已经有c就不用把所有大于它的字符pop出来了

写出来了之后觉得这道题也不难啊，为啥开始想不出。。。我开始想到了counts，但是stack没想出，然后看到了tags里面有stack想到了stack但是还是没想出。。。
我觉得就是我没有想到简单粗暴的把之前所有的character都去掉这件事儿。。。再做两道greedy感受一下
*/
public class Solution {
    public String removeDuplicateLetters(String s) {
        StringBuilder sb = new StringBuilder();
        if(s.length() == 0) return "";
        
        int[] counts = new int[256];
        for(int i = 0; i < s.length(); i++) {
            counts[s.charAt(i)] ++;
        }
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c]--;
            while(!stack.isEmpty() && !stack.contains(c) && c < stack.peek() && counts[stack.peek()] > 0) {
                stack.pop();
            }
            if(stack.isEmpty() || !stack.contains(c)) stack.push(c);
        }
        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
