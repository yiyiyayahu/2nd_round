/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/

/*
我的思路是这样的，这次不能因为start<previous end, 就total++ 因为可能[0,30] [5,40] [35,40]只需要两间
所以我希望有这样一个data structure可以快速找到最小的结束时间，就想到了maxHeap
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval in1, Interval in2) {
                if(in1.start == in2.start) return in1.end-in2.end;
                return in1.start-in2.start;
            }
        });  
        int total = 1;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        maxHeap.offer(intervals[0].end);
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i].start < maxHeap.peek()) {
                total++;
            } else {
                maxHeap.poll();
            }
            maxHeap.offer(intervals[i].end);
        }
        return total;
    }
}
