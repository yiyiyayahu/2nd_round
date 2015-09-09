/*
Given an array of citations (each citation is a non-negative integer) of a researcher, 
write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: 
"A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h 
citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 
3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and 
the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
*/

/*
犯了两个错误：
1. 在最后return了0，实际上应该return len
2. 当citations[i] < len-i的时候，其实应该return len-i-1而不是return citations[i]
比如[0,0,4,4]结果是2，但是[0,3,4,4]结果就是3
*/
public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        int len = citations.length;
        for(int i = len - 1; i >= 0; i--) {
            if(citations[i] == len-i) return citations[i];
            else if(citations[i] <= len-i-1) return len-i-1;
        }
        return len;
    }
}


/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.
*/

/*
如果是O(logn)那么就明显是二分法
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
