/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

/*
这道题其实就是用BFS或者DFS traverse整个graph
我开始没太想清楚，主要这个node和node之间我要连接起来对吧，不然我怎么构成一个图
和那个copy list有点像，就是node和cloned是一一对应的对吧
然后有些点可能以前traverse过了，就要提取出来，不要再新建了，所以用一个hashmap来存原来的node和对应的clonedNode
*/
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        
        queue.add(node);
        UndirectedGraphNode ret = new UndirectedGraphNode(node.label);
        map.put(node, ret);
        
        while(!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.remove();
            UndirectedGraphNode cloned = map.get(curr);
            
            for(UndirectedGraphNode n : curr.neighbors) {
                if(map.containsKey(n)) {
                    cloned.neighbors.add(map.get(n));
                } else {
                    UndirectedGraphNode c = new UndirectedGraphNode(n.label);
                    map.put(n, c);
                    cloned.neighbors.add(c);
                    queue.add(n);
                }
            }
        }
        return ret;
    }
}
