/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/*
看两个点斜率，如果斜率相同就在一条直线上
用一个map来存和当前点的斜率对应的count
注意两点：
1. 和当前点重合的点，用一个duplicate的变量来track
2. vertical，斜率标记为Integer.MAX_VALUE*1.0
3. horizontal，这里很神奇的是，居然可以有0.0和-0.0，所以后来统一检测认为是0.0
4. 有可能全部是重复的点，map为空
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        int len = points.length;
        int max = 0;
        for(int i = 0; i < len; i++) {
            int duplicate = 1;
            map.clear();
            for(int j = i+1; j < len; j++) {
                Double k = 0.0;
                if(points[i].x == points[j].x) {
                    if(points[i].y == points[j].y) {
                        duplicate++;
                        continue;
                    }
                    else k = Integer.MAX_VALUE * 1.0;
                }
                else {
                	if(points[j].y == points[i].y) k = 0.0;
                	else k = (double)(points[j].y - points[i].y) / (points[j].x - points[i].x);
                }
                if(!map.containsKey(k)) map.put(k, 1);
                else map.put(k, map.get(k)+1);
            }
            
            for(Integer count : map.values()) {
                if(count + duplicate > max) {
                    max = count + duplicate;
                }
            }
            if(map.isEmpty()) max = Math.max(max, duplicate);
        }
        return max;
    }
}
