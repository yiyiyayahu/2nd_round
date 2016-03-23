/*
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
*/

/*
这道题不是很好想啊。。。囧，我一直没想出来
其实是这样的，一个character我可以选择abbreviate或者不abbreviate，如果不abbr的话呢，count就加1, 如果abbr，加进去的同时count置0
比如第二个char-o 1ord/2rd
这里要注意sb的处理。。。这里倒是很巧妙的通过控制长度的方法控制了最后的值。。。但是一般来说按照我的风格我会新分配一些区间新建一些StringBuilder
*/

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<String>();
        dfs(0, word.toCharArray(), new StringBuilder(), 0, res);
        return res;
    }
    
    public void dfs(int pos, char[] word, StringBuilder sb, int count, List<String> list) {
        int sbOriginSize = sb.length();
        if(pos == word.length) {
            if(count > 0) sb.append(count);
            list.add(sb.toString());
        } else {
            //abbreviate word[pos]
            dfs(pos+1, word, sb, count+1, list);
            //not abbreviate word[pos]
            if(count > 0) sb.append(count);
            sb.append(word[pos]);
            dfs(pos+1, word, sb, 0, list);
        }
        sb.setLength(sbOriginSize);
    }
}
