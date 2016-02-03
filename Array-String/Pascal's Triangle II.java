/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

/*
to convert int[] to arraylist, you cannot do:
1. Arrays.asList(arr), since arr should be initialized as Integer[] arr
2. new ArrayList<Integer>(arr), also wrong, no such constructor
*/
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        int[] arr = new int[rowIndex+1];
        for(int i = 0; i <= rowIndex; i++) {
            arr[i] = 1;
            for(int j = i-1; j > 0; j--) {
                arr[j] = arr[j] + arr[j-1];
            }
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i <= rowIndex; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
