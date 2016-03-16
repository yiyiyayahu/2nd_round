/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/


/*
这道题实在是尝试了好多次。。。
开始想的是[0,1,2,3,4,5]这种hashset，然后每次看到一个connection就remove node，如果没有可以remove的node就+1，但是总有各种各样的corner case没有考虑到
首先这个array不是sort过得对吧，其次没有可以remove的了，怎么办呢。。。

后来觉得自己算法实在薄弱就跑去看算法书。然后greedy有一种union find的算法
https://www.youtube.com/watch?v=8mYfZeHtdNc&list=PLe-ggMe31CTexoNYnMhbHaWhQ0dvcy43t

union find主要解决的就是这种connected components in a graph的问题，一般有两步
1. find(v) - 找到v所属的这个connected components的root
2. union(v,w) - if(find(v) != find(w)) merge these two connected components into one

开始的解法是
[0,1,2,3,4,5] 遇到一个[0,1]，然后就把0的root设为1 - [1,1,2,3,4,5]
这时候再遇到一个[0,2]呢，要把0的root更新为2， 同时把所有和0在一个set里面的都更新为2，数组变为[2,2,2,3,4,5]

这个解法的缺点在于union需要O(n)，虽然find只要O(1)，但是总体需要O(n^2) 因为要做N个循环的union

然后union find的改进做法就是instead of updating all，just update the parent
[0,1,2,3,4,5] 遇到一个[0,1] - [1,1,2,3,4,5]
union[0,2] 找到0的root是1，然后把1的parent改为2 - [1,2,2,3,4,5]
但是find的话要一直find到root，比如调用find(0)要沿着这个去找 - find(1) - find(2) 因为id[2]==2 说明是root，返回2
所以这个解法呢union是O(N) find也是O(N)
缺点是很有可能这个tree too tall - find too expensive

再进一步的优化是prevent the tree from growing too tall
就是merge的时候比较一下两边的大小，smaller tree to be the child of the root of the larger tree

接着优化，就是这个tree还可以再flatten一点，用path compression - 知道root之后，可以把这个path上面的parent都设为root，这样这个tree就很矮了
*/
public class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] id = new int[n];
        
        for(int i = 0; i < n; i++) {
            id[i] = i;
        }
        
        for(int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0], node2 = edges[i][1];
            int pid = id[node1], qid = id[node2];
            for(int j = 0; j < n; j++) {
                if(id[j] == pid) id[j] = qid;
            }
        }
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(id[i] == i) count++;
        }
        return count;
    }
}
/*
下面的这个是优化版的，就是weighted+path compression，总是把small的merge到大的里面，并且进一步flatten这个tree
path compression - make every other node in the path points to its grandparent
find O(logN) depth of p/q
union O(1) given root
*/
public class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] root = new int[n];
        int[] size = new int[n];
        Set<Integer> set = new HashSet<Integer>();
        
        for(int i = 0; i < n; i++) {
            root[i] = i;
            size[i] = i;
        }
        
        for(int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0], node2 = edges[i][1];
            
            if(size[node1] <= size[node2]) {
                union(root, node1, node2);
                size[node2] += size[node1];
            } else {
                union(root, node2, node1);
                size[node1] += size[node2];
            }
        }
        for (int i = 0; i < n; i++) {
            set.add(findRoot(root, i));
        }
        return set.size();
    }
    //path compression
    private int findRoot(int[] root, int p) {
        while(p != root[p]) {
            root[p] = root[root[p]];
            p = root[p];
        }
        return p;
    }
    
    private void union(int[] root, int p, int q) {
        int pid = findRoot(root, p); 
        int qid = findRoot(root, q);
        root[pid] = qid;
    }
}
