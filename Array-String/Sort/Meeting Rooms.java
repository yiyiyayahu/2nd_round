/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
*/

/*
这道题还挺简单的，按照starting time sort一下，然后看下一个的start time是不是小于上一个的end time就可以了
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
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals.length <= 1) return true;
        
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval in1, Interval in2) {
                if(in1.start == in2.start) return in1.end-in2.end;
                return in1.start-in2.start;
            }
        });

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i].start < intervals[i-1].end) return false;
        }
        return true;
    }
}
