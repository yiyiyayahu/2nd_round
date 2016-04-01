/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), 
write a program to output the skyline formed by these buildings collectively (Figure B).
 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
For instance, the dimensions of all buildings in Figure A are recorded as: 
[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
The output is a list of "key points" (red dots in Figure B) in the format of 
[ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height.
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
Notes:
The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, 
[...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: 
[...[2 3], [4 5], [12 7], ...]
*/

/*
这道题是真的不好想也不好写。。。好多tricky part，思路是这样的
maxHeap - > height
1. divide the buildings into [x,y,isLeft] [x,y,isRight]
2. sort it according to x (some trick in sorting)
3. when hit [x,y,isLeft] and compare the curr height to the maximum height, if it is larger than the maximum height, it is a turning point
   when hit [x,y,isRight], the current height can be poped up, and if the current maximum height is different from previous one, it is a turning point as well
   if the maxHeap isEmpty, then it means that it is hitting an end
    
-> so we can just add current height to maxHeap when isLeft, and remove the current height from maxHeap when isRight
if the current maxHeap.peek() is different than previous max height, then it is a turning point

关于sort：
[2,4,7],[2,4,6],[2,4,5]
顺序其实应该是[2,7],[2,6],[2,5],[4,5],[4,6],[4,7]，这样才能保证输出是[2,7],[4,0]
因为如果[2,5]在前面的话就会直接输出[2,5]了，显然是不对的。同理，如果[4,7]在前面的话，curr!=prev，也会输出

还有就是[0,2,3],[2,5,3]
[2,3](right)和[2,3](left)是连着的，如果right那个在前面，直接pop出来了，就会多一个[2,0]的点，所以要让left的在前面
*/
public class Solution {
    private class Coordinate {
        int x;
        int y;
        boolean isLeft;
        private Coordinate(int x, int y, boolean isLeft) {
            this.x = x;
            this.y = y;
            this.isLeft = isLeft;
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<>();
        if(buildings.length == 0) return ret;
        
        //separate buildings into a list of Coordinates
        List<Coordinate> list = new ArrayList<>();
        for(int[] b : buildings) {
            list.add(new Coordinate(b[0],b[2],true));
            list.add(new Coordinate(b[1],b[2],false));
        }
        //sort list according to the front
        //[2,7,true] should come before [2,6,true]
        //[2,7,false] should come after [2,6,false]
        //[2,3,true] should come before [2,3,false]
        Collections.sort(list, new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                if(o1.x == o2.x) {
                    if(o1.isLeft && o2.isLeft) return o2.y-o1.y;
                    if(!o1.isLeft && !o2.isLeft) return o1.y-o2.y;
                    return o1.isLeft ? -1 : 1;
                }
                return o1.x - o2.x;
            }
        });
        
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){  
            @Override  
            public int compare(Integer a, Integer b) {  
                return b - a;  
            }  
        }); 
        int curr = 0, prev = 0;
        for(Coordinate co : list) {
            if(co.isLeft) {
                maxHeap.add(co.y);
            } else {
                maxHeap.remove(co.y);
            }
            curr = maxHeap.isEmpty() ? 0 : maxHeap.peek();
            if(curr != prev) {
                ret.add(new int[] {co.x, curr});
                prev = curr;
            }
        }
        return ret;
    }
}
