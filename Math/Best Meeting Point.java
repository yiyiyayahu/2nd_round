/*
A group of two or more people wants to meet and minimize the total travel distance. 
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?
*/

/*
distance的这个算法，使得我们可以拆成两个问题，row和col的分别算，最后加和就ok了
一维的话呢，
如果点的数目是even，1，2，3，4那实际上就是dist(1,4)+dist(2,3)best meeting point应该是2，3中点
如果点的数目是odd，1，2，3应该就是dist(1,3)，也就是meeting point就是2
再想想呢，要算的是整个的距离，其实不管是even还是odd，只要是将size/2的点设为中点，每个元素都算到中点的距离加和就是最后的结果

下面的解法注意col整个arraylist要先sort一下
*/
public class Solution {
    public int minTotalDistance(int[][] grid) {
        if(grid.length == 0) return 0;
        
        int sum = 0;
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        int rowmid = row.get(row.size()/2);
        for(int i : row) {
            sum += Math.abs(i - rowmid);
        }
        Collections.sort(col);
        int colmid = col.get(col.size()/2);
        for(int j : col) {
            sum += Math.abs(j - colmid);
        }
        return sum;
    }
}
