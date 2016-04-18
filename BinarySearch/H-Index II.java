/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.
*/

/*
这个比较好理解一些
*/
public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        
        int start = 0, end = citations.length-1, len = citations.length;
        int result = 0;
        while(start <= end) {
            int mid = (start+end)/2;
            if(citations[mid] >= len-mid) {
                result = len-mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return result;
    }
}
/*
如果是O(logn)那么就明显是二分法
二分法就怕指针没挪好就死循环了，比如这里end=mid-1而不是end=mid...
*/
public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int len = citations.length;
        int start = 0, end = len-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(citations[mid] > len-mid) end = mid-1;
            else if(citations[mid] == len-mid) return len-mid;
            else start = mid+1;
        }
        return len-start;
    }
}
