/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
*/

/*
终于有个一次过的题了，撒花撒花撒花^_^

参考了一下Trie的implementation，然后这道题的不同之处就在于'.'的处理
之前先想好了几种情况
aa  a.  .a ..
然后用backtracking来写个辅助函数来search

所以做题要注意的几点：
1. 先想好可能的corner case，记得要handle
2. 思想集中，这样思路会比较清晰
3. 写完检查

我觉得至少我看到了自己的进步，虽然还远远不够，但是还蛮开心的
诶，我是不是对自己要求太低了
*/

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isLeaf;
    
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isLeaf = false;
    }
    
    public Map<Character, TrieNode> getChildren() {
        return children;
    }
    
    public boolean isLeaf() {
        return isLeaf;
    }
}

public class WordDictionary {
    
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode tmp = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Map<Character, TrieNode> children = tmp.getChildren();
            if(!children.containsKey(c)) children.put(c, new TrieNode());
            tmp = children.get(c);
        }
        tmp.isLeaf = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return helper(word, 0, root);
    }
    
    public boolean helper(String word, int i, TrieNode start) {
        if(i == word.length()) {
            if(start != null && start.isLeaf()) return true;
            return false;
        }
        if(start == null) return false;
        
        TrieNode tmp = start;
        char c = word.charAt(i);
        Map<Character, TrieNode> children = tmp.getChildren();
        if(c == '.') {
            for(TrieNode node : children.values()) {
                if(helper(word, i+1, node)) return true;
            }
            return false;
        }
        if(!children.containsKey(c)) return false;
        tmp = children.get(c);
        return helper(word, i+1, tmp);
    }

}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
