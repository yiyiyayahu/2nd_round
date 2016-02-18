/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

*/


/*
下面的这个解法是错误的，原因是：
prerequisites可能不是完全的，比如一共有4个course但是可能prerequisite没有，或者只有一个[0,1]这样的
那我直接返回degree就不对了，因为degree是从4开始往下面减的。。。
同理，如果len==0的时候也不能直接返回new int[0]，因为有很多种可能性的
*/
public class Solution {
    static int currDegree;
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int len = prerequisites.length;
        int[] degree = new int[numCourses];
        
        currDegree = numCourses-1;
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for(int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < len; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        int[] visited = new int[numCourses];
       
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(graph, i, visited, degree)) return new int[0];
        }      
        return degree;
    }
    public static boolean dfs(List<List<Integer>> graph, int course, int[] visited, int[] degree) {
        if(visited[course] == 2) return true;
        if(visited[course] == 1) return false;
        
        visited[course] = 1;
        for(int i = 0; i < graph.get(course).size(); i++) {
            int next = graph.get(course).get(i);
            if(!dfs(graph, next, visited, degree)) return false;
        }
        visited[course] = 2;
        degree[course] = currDegree --;
        return true;
    }
}
