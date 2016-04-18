/*
Follow up for N-Queens problem.
(no two queens share the same row, column, or diagonal.)

Now, instead outputting board configurations, return the total number of distinct solutions.
*/

/*
可以压缩为一维array，每次都是填一行的值
比如helper(arr, 0, n)就是填arr[0]，填的值代表第0行把Q放在第几列 [0,n-1]中取一个valid的值
开始可能arr[0]=0，然后index->1 填arr[1]的时候发现不能填0，1只能填2或者3，如果填2的话呢是走不下去的，这时候就会返回上一级，然后填3
[-1,-1,-1,-1] -> [0,-1,-1,-1] -> [0,2,-1,-1] -> failed
                              -> [0,3,-1,-1] -> [0,3,1,-1] -> [0,3,1,2]
这个前面是来检测不在对角线上，后面是检测不在同一列上，在同一行的情况不会出现，因为每一行就填一个值
if(Math.abs(index-j) == Math.abs(i-arr[j]) || arr[j] == i)
*/
public class Solution {
    static int count;
    public int totalNQueens(int n) {
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        count = 0;
        helper(arr, 0, n);
        return count;
    }

    public void helper(int[] arr, int index, int n) {
        if(index == n) {
            count ++;
            return;
        }
        
        for(int i = 0; i < n; i++) {
            boolean isValid = true;
            for(int j = 0; j < index; j++) {
                if(arr[j] == -1) continue;
                if(Math.abs(index-j) == Math.abs(i-arr[j]) || arr[j] == i) {
                    isValid = false;
                    break;
                }
            }
            if(isValid) {
                arr[index] = i;
                helper(arr, index+1, n);
                arr[index] = -1;
            }
        }
    }
}

/*
这道题现在做起来好多了，没有开始那么无措
开始没过是因为以为只考虑前后1位就行了，但是不能同一个对角线的话要把其他的非0点都考虑进去
*/
public class Solution {
    int count = 0;
    public int totalNQueens(int n) {
        int[] nums = new int[n];
        helper(1, nums, n);
        return count;
    }
    public void helper(int curr, int[] nums, int n) {
        if(curr == n+1) {
            count ++;            
            return;
        }
        for(int i = 0; i < nums.length; i++) {
          boolean isValid = true;
            if(nums[i] != 0) continue;
            
          for(int j = 0; j < nums.length; j++) {
            if(nums[j] == 0) continue;
            if(Math.abs(i-j) == Math.abs(curr - nums[j])) isValid = false;
          }
            if(isValid) {
                nums[i] = curr;
                helper(curr+1, nums, n);
                nums[i] = 0;
            }
        }
    }
}
