/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

/*
这道题是按照视频里面的算法来解的

DFS(G) :
  mark all nodes unexplored
  for every node v in G:
    if(v not explored) DFS(G, v)

DFS(G, v) :
  mark v explored
  for every edge starting from v- (v,u) in G
    DFS(G, u)
  f(v) = current_label
  current_label --
  
比如   /  v  \
     s \  u  / w
假设箭头都是向右的
如果从s开始，currrent_label = 4, 调用 DFS(G, v) 再调用 DFS(G, w)这个时候w是sink node，没有其他可以指向的了，所以f(w)=4, current_label--
之后就退回到了v, v也是没什么可以指向的了，所以f(v)=3，之后退回到了原来的s调用这里，这时候s还是在for循环里面，所以再调用DFS(G, u)
然后发现呢，u指向了w，可是w已经traverse过了，就是degree set过了，所以就不用再调用了，f(u)=2, 最后就是f(s)=1

那么这个course schedule的问题是呢，有可能有cycle
有cycle的处理就是，如果我在上面这个过程中，发现，诶，怎么已经visited过，那么就是cycle return false
*/
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses];
        int[] degree = new int[numCourses];
        if(prerequisites.length == 0) return true;
        for(int i = 0; i < numCourses; i++) {
            if(!helper(i, prerequisites, degree, visited, numCourses)) return false;
        }

        return true;
    }
    
    public boolean helper(int course, int[][] prerequisites, int[] degree, boolean[] visited, int currDegree) {
        if(visited[course]) return false;
        if(degree[course] != 0) return true;
        
        visited[course] = true;
        for(int i = 0; i < prerequisites.length; i++) {
            if(prerequisites[i][1] == course) {
                int next = prerequisites[i][0];
                if(!helper(next, prerequisites, degree, visited, currDegree)) return false;
            }
        }
        visited[course] = false;
        degree[course] = currDegree--;
        return true;
    }
}
