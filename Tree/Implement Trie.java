/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

class TrieNode {
    // Initialize your data structure here.
    Map<Character, TrieNode> children;
    boolean isLeaf;
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isLeaf = false;
    }
}

public class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode tmp = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!tmp.children.containsKey(c)) {
                tmp.children.put(c, new TrieNode());
            }
            tmp = tmp.children.get(c);
        }
        tmp.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode n = searchWord(word);
        return (n!=null) && n.isLeaf; 
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchWord(prefix) != null;
    }
    
    public TrieNode searchWord(String word) {
        TrieNode tmp = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!tmp.children.containsKey(c)) {
                return null;
            } else {
                tmp = tmp.children.get(c);
            }
        }
        return tmp;      
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
