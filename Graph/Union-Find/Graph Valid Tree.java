/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices 
are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.

*/

/*
其实就是要满足两点：
1. no cycles
2. one connected component

优化的解法真的好快啊，只要1ms
开始我是想着要检查所有node的root是一个才可以return true
但是实际上只要edges.length == n-1并且没有cycle就可以说明只有一个connected component了
*/
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(n <= 0) return false;
        if(edges.length != n-1) return false;
        
        int[] root = new int[n];
        int[] size = new int[n];
        
        for(int i = 0; i < n; i++) {
            root[i] = i;
            size[i] = i;
        }
        
        for(int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0], node2 = edges[i][1];
            int p = findRoot(root, node1), q = findRoot(root, node2);
            if(p == q) return false;
            
            if(size[p] <= size[q]) {
                root[p] = q;
                size[q] += size[p];
            } else {
                root[q] = p;
                size[p] += size[q];
            }
        }
        return true;
    }
    
    public int findRoot(int[] root, int p) {
        while(root[p] != p) {
            root[p] = root[root[p]];
            p = root[p];
        }
        return p;
    }
}


public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(n <= 0) return false;
        
        int[] id = new int[n];
        for(int i = 0; i < n; i++) {
            id[i] = i;
        }
        
        for(int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0], node2 = edges[i][1];
            int pid = id[node1], qid = id[node2];
            if(pid == qid) return false;
            for(int j = 0; j < n; j++) {
                if(id[j] == pid) id[j] = qid;
            }
        }
        int tmp = id[0];
        for(int i = 1; i < n; i++) {
            if(id[i] != tmp) return false;
        }
        return true;
    }
}
