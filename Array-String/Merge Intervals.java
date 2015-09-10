/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/*
这道题主要就是写个comparator把intervals按照start sort一下
写comparator的时候要注意考虑o1.start==o2.start的情况
其他的就还挺简单的
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
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> lists = new ArrayList<Interval>();
        if(intervals.size() == 0) return lists;
        
        Comparator<Interval> cmp = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start == o2.start) return o1.end - o2.end;
                return o1.start - o2.start;
            }
        };
        Collections.sort(intervals, cmp);
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for(int i = 1; i < intervals.size(); i++) {
            int curr_start = intervals.get(i).start;
            int curr_end = intervals.get(i).end;
            if(curr_start > end) {
                lists.add(new Interval(start, end));
                start = curr_start;
                end = curr_end;
            } else {
                end = Math.max(end, curr_end);
            }
        }
        lists.add(new Interval(start, end));
        return lists;
    }
}
