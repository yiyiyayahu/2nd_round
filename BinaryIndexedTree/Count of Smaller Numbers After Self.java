/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

Google
*/

/*
这道题真心好难写喔。。。先写的是mergesort版本的，好多trick
我想到mergesort是因为我记得毛毛有考过我这道题，不太确定是不是一样的了。。。
总之就是merge的时候，每次right挪到left，left的元素就要+1，说明right<left

但是trick好多啊，我不能在这个过程中就sort，不然index就不准了。。。所以后来看discuss里面有人是用一个index的array来maintain的。。。不晓得更好的方法

然后这道题有用BST的，有用binary indexed tree的，有用mergesort的。。。好难，哭了
感觉mergesort比binary indexed tree快诶，mergesort才11ms
想想时间复杂度
*/
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums.length == 0) return list;
        
        int len = nums.length;
        int[] count = new int[len];
        int[] indexes = new int[len];
        for(int i = 0; i < len; i++) {
            indexes[i] = i;
        }
        mergeSort(nums, 0, len-1, count, indexes);
        
        for(int i = 0; i < len; i++) {
            list.add(count[i]);
        }
        return list;
    }
    
    public void mergeSort(int[] nums, int start, int end, int[] count, int[] indexes) {
        if(start >= end) return;
        
        int mid = (start + end)/2;
        mergeSort(nums, start, mid, count, indexes);
        mergeSort(nums, mid+1, end, count, indexes);
        
        int left = start, right = mid+1, rightcount = 0;
        int[] new_indexes = new int[end-start+1];
        int i = 0;
        
        while(left <= mid && right <= end) {
            if(nums[indexes[right]] < nums[indexes[left]]) {
                new_indexes[i] = indexes[right];
                rightcount ++;
                right ++;
            } else {
                new_indexes[i] = indexes[left];
                count[indexes[left]] += rightcount;
                left ++;
            }
            i++;
        }
        
        while(left <= mid) {
            new_indexes[i] = indexes[left];
            count[indexes[left]] += rightcount;
            left ++;
            i ++;
        }
        
        while(right <= end) {
            new_indexes[i++] = indexes[right++];
        }
        
        //update nums to sorted
        for(int k = start; k <= end; k++) {
            indexes[k] = new_indexes[k-start];
        }
    }
}

/*
想不粗啊。。。。
1. 先找最小的值min
2. 扫一遍nums，更新一下nums，都变成nums[i]-min+1
3. 建一个binary indexed tree，但是不存sum了，这次存count

其实不是很懂，再想想
*/
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums.length == 0) return list;
        
        int len = nums.length;
        int[] count = new int[len];
        int[] indexes = new int[len];
        for(int i = 0; i < len; i++) {
            indexes[i] = i;
        }
        mergeSort(nums, 0, len-1, count, indexes);
        
        for(int i = 0; i < len; i++) {
            list.add(count[i]);
        }
        return list;
    }
    
    public void mergeSort(int[] nums, int start, int end, int[] count, int[] indexes) {
        if(start >= end) return;
        
        int mid = (start + end)/2;
        mergeSort(nums, start, mid, count, indexes);
        mergeSort(nums, mid+1, end, count, indexes);
        
        int left = start, right = mid+1, rightcount = 0;
        int[] new_indexes = new int[end-start+1];
        int i = 0;
        
        while(left <= mid && right <= end) {
            if(nums[indexes[right]] < nums[indexes[left]]) {
                new_indexes[i] = indexes[right];
                rightcount ++;
                right ++;
            } else {
                new_indexes[i] = indexes[left];
                count[indexes[left]] += rightcount;
                left ++;
            }
            i++;
        }
        
        while(left <= mid) {
            new_indexes[i] = indexes[left];
            count[indexes[left]] += rightcount;
            left ++;
            i ++;
        }
        
        while(right <= end) {
            new_indexes[i++] = indexes[right++];
        }
        
        //update nums to sorted
        for(int k = start; k <= end; k++) {
            indexes[k] = new_indexes[k-start];
        }
    }
}
