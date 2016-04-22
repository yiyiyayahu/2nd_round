/*
leetcode上面的一道题，关于suffix tree的一种简单写法
Given a string s and an array of smaller strings T, design a method to search s for each small string in T

search return的ArrayList<Integer>是这个substring的starting index
比如"abcabc"
stree.search("c") -> [2,5]
stree.search("ab") -> [0,3]
*/

public class SuffixTree {
  SuffixTreeNode root = new SuffixTreeNode();
  
  public SuffixTree(String s) {
    for(int i = 0; i < s.length(); i++) {
      String suffix = s.substring(i);
      root.insertString(suffix, i);
    }
  }
  
  public ArrayList<Integer> search(String s) {
    return root.search(s);
  }
}

public class SuffixTreeNode {
  HashMap<Character, SuffixTreeNode> children = new HashMap<>();
  char value;
  ArrayList<Integer> indexes = new ArrayList<>();
  
  public SuffixTreeNode() {
    
  }
  
  public void insertString(String s, int index) {
    indexes.add(index);
    if(s == null || s.length() == 0) return;
    SuffixTreeNode child = null;
    value = s.charAt(0);
    if(children.containsKey(value)) {
      child = children.get(value);
    } else {
      child = new SuffixTreeNode();
      children.put(value, child);
    }
    String remainder = s.substring(1);
    child.insertString(remainder, index);
  }
  
  public ArrayList<Integer> search(String s) {
    if(s == null || s.length() == 0) return indexes;
    char first = s.charAt(0);
    if(children.containsKey(first)) {
      String remainder = s.substring(1);
      return children.get(first).search(remainder);
    }
    return null;
  }
}


/*
但是如果我会更喜欢写成这个样子
*/
class SuffixTree {
	  SuffixTreeNode root = new SuffixTreeNode();
	  
	  public SuffixTree(String s) {
	    for(int i = 0; i < s.length(); i++) {
	      String suffix = s.substring(i);
	      insertString(suffix, i);
	    }
	  }
	  
	  public ArrayList<Integer> search(String s) {
		SuffixTreeNode tmp = root;
	    for(int i = 0; i < s.length(); i++) {
	    	char curr = s.charAt(i);
	    	tmp = tmp.children.get(curr);
	    }
	    return tmp.indexes;
	  }
	  
	  public void insertString(String suffix, int index) {
		  SuffixTreeNode tmp = root;
		  for(int i = 0; i < suffix.length(); i++) {
			  char curr = suffix.charAt(i);
			  if(!tmp.children.containsKey(curr)) {
				  tmp.children.put(curr, new SuffixTreeNode());
			  }
			  SuffixTreeNode next = tmp.children.get(curr);
			  next.indexes.add(index);
			  tmp = next;
		  }
	  }
}


class SuffixTreeNode {
  HashMap<Character, SuffixTreeNode> children = new HashMap<>();
  char value;
  ArrayList<Integer> indexes = new ArrayList<>();
  
  public SuffixTreeNode() {
    
  }
}
