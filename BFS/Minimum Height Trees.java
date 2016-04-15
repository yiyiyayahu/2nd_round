/*
For a undirected graph with tree characteristics, we can choose any node as the root. 
The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges 
(each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and 
thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:

How many MHTs can a graph have at most?
Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/


/*
这道题构建一个graph想到了，然后开始想的是dfs，类似topological sort，每个node有个level值，然后遍历看每个node当root结果会肿么样
但是一：时间复杂度不好，二，code写不粗。。。后来就看了下答案
自己graph的题还是不熟练啊。。。
https://leetcode.com/discuss/71763/share-some-thoughts

方法很巧妙，用two pointer两头找，即使从leaf节点找起，
如果后来两个指针重合，说明就一个root使得最小height，不然如果两个指针挨着，就是两个root最小height，把这两个root都加到list里面

如果写code呢，就是从leaf找起
比如[[1, 0], [1, 2], [1, 3]]
leaf是0，2，3, n-leaf size=1 然后把[1,[0,2,3]]里面的0，2，3 remove掉，remove完2的时候发现，诶，1变成了新的leave，add到newLeaves的list里面
因为while循环跳出了，最后返回[1]

[[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
leaf node是0,1,2,5    [3,[0,1,2,4]] [4,[3,5]]
6-4=2  [3,[0,1,2,4]] - [3,[4]] - add to newLeaves  [4,[3,5]] - [4,[3]] - add to newLeaves
return [3,4]

好牛的做法啊，自己想不粗。。
*/
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ret = new ArrayList<>();
        if(n == 1) { ret.add(0); return ret; }
        
        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        List<Integer> leaves = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(adj.get(i).size() == 1) leaves.add(i);
        }
        
        while(n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for(int i : leaves) {
                for(int j : adj.get(i)) {
                    adj.get(j).remove(i);
                    if(adj.get(j).size() == 1) newLeaves.add(j);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}
