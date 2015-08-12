/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/*
这次的思路就比之前清晰多了

先想想需要什么：
1. 我首先从上往下BFS，然后equal的话，我就直接backtrack，把整个路径还原，return ret
2. 要考虑这么几个问题：
首先，对于每个node保存之前所有的parents是不现实的，并且用的空间复杂度也比较高
所以最好是一个node只存它上一层的parent，然后再一层层往上找
那这个node的class需要什么呢，当前的string是要的，parents是要的，到第几层的level是要的
那一个node可以有不同的parents指向她对吧，比如lot,lod和aot都可能是lot的parents，所以parents是一个list

那node这个class定义好了，接下来呢和I的思路差不多

这里还需要一个新的数据结构来维护这么多node，防止建重复了。比如lot，很多parent指向它的时候这个lot对应的node应该是唯一的，
所以就用一个hashmap来保存就好了，string对应node

但是又遇到一个问题，就是其实可能对于lot，有多条不同的路径指向它，可能有的长度是2，有的长度是3，
那这种情况下其实3的那个情况就可以舍掉了对吧，因为3这种肯定不是最短路径
所以node存在的时候，要检测下level，是当前最短路径的时候，才加入parents的list，if(node.level == curr.level+1) node.addParent(curr);
*/

class Node {
    String str;
    int level;
    List<Node> parents;
    
    public Node(String s, int l) {
        str = s;
        level = l;
        parents = new ArrayList<Node>();
    }
    
    public void addParent(Node n) {
        parents.add(n);
    }
}

public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> ret = new ArrayList<List<String>>();
        HashMap<String, Node> map = new HashMap<String, Node>();
        
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        map.put(start, new Node(start, 0));
        dict.add(end);
        
        while(!queue.isEmpty()) {
            String s = queue.remove();
            if(s.equals(end)) {
                getPaths(map.get(s), new ArrayList<String>(), ret);
                return ret;
            }
            Set<String> set = getOneDiff(s);
            Node curr = map.get(s);
            for(String word : set) {
                if(!dict.contains(word)) continue;
                if(map.containsKey(word)) {
                    Node node = map.get(word);
                    if(node.level == curr.level+1) {
                        node.addParent(curr);
                    }
                } else {
                    Node newNode = new Node(word, curr.level+1);
                    newNode.addParent(curr);
                    map.put(word, newNode);
                    queue.add(word);
                }
            }
        }
        return ret;
    }
    
    public void getPaths(Node n, List<String> list, List<List<String>> ret) {
        list.add(0, n.str);
        if(n.level == 0) {
            ret.add(list);
            return;
        }
        for(Node p : n.parents) {
            getPaths(p, new ArrayList<String>(list), ret);
        }
    }
    
    public Set<String> getOneDiff(String s) {
        Set<String> set = new HashSet<String>();
        if(s == null || s.length() == 0) return set;
        
        for(int i = 0; i < s.length(); i++) {
            char[] arr = s.toCharArray();
            for(char c = 'a'; c <= 'z'; c++) {
                if(c == arr[i]) continue;
                arr[i] = c;
                set.add(String.valueOf(arr));
            }
        }
        return set;
    }
}
