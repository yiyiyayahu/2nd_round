/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> lists = new ArrayList<Interval>();
        
        boolean isAdded = false;
        int start = newInterval.start, end = newInterval.end;
        int i = 0;
        while(i < intervals.size()) {
            int curr_start = intervals.get(i).start;
            int curr_end = intervals.get(i).end;
            if(start > curr_end) {
                lists.add(intervals.get(i));
                i++;
            } else if(end < curr_start) {
                lists.add(new Interval(start, end));
                isAdded = true;
                break;
            } else {
                start = Math.min(start, curr_start);
                end = Math.max(end, curr_end);
                i++;
            } 
        }
        
        while(i < intervals.size()) {
            lists.add(intervals.get(i));
            i++;
        }
        if(!isAdded) lists.add(new Interval(start, end));
        return lists;
    }
}
