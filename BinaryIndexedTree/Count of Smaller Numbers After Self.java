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

mergesort的时间复杂度是O(nlogn)
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

我大概懂这个思路是什么意思了
就是原来binary indexed tree不是存sum嘛，然后这道题sum根本没有意义，那就是希望能存比它小的值的count，怎么做呢
比如扫到一个nums[i]=2，然后在binary indexed tree里面，把所有大于2的value的位置，count++
那么就需要这样一个array来当这个binary indexed tree
index -> nums[i]的值  value -> count(<nums[i])
update和get的思想和binary indexed tree是一样的
update: given nums[i] - index=nums[i] - 把getNext(index)的所有tree[index] ++
get: given nums[i] - index=nums[i] - 把getParent(index)的所有tree[index]加和，返回

所以呢，这个array的length应该是max(nums[i])
但是因为tree的index都是>0，所以先算一遍找一个min，把所有的nums[i]-min+1，这样就保证了每个值都对应了一个valid index

举个例子 [2,-1,0] -> min=-1 -> [4,1,2] -> max=4 tree[5] 
从右开始往左更新 
getCount(2-1,tree)=0 同时update tree tree[2]++->1  2->4 tree[4]++ ->1 [0,0,1,0,1]
getCount(1-1,tree)=0 同时update tree tree[1]++->1 1->2 tree[2]++ -> 2 -> 4 tree[4]++ -> 2 [0,1,2,0,2]
getCount(4-1,tree)=0->getCount(2,tree)=2 ->2 同时update tree tree[4]++

可是为什么是getCount(tmp[i]-1, tree)呢,因为update的时候自己也++了，所以不能算自己？因为tmp[i]-1是第一个小于tmp[i]的数？
*/
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        if(len == 0) return list;       

        int min = nums[0];
        for(int i = 0; i < len; i++) {
            if(nums[i] < min) min = nums[i];
        }
        int max = Integer.MIN_VALUE;
        int[] tmp = new int[len];
        for(int i = 0; i < len; i++) {
            tmp[i] = nums[i] - min + 1;
            max = Math.max(max, tmp[i]);
        }      
        
        int[] tree = new int[max+1];
        for(int i = len-1; i >= 0; i--) {
            list.add(0, getCount(tmp[i]-1, tree));
            update(tmp[i], tree);
        }
        return list;
    }

    public int getCount(int i, int[] tree) {
        int count = 0;
        while(i > 0) {
            count += tree[i];
            i -= i&(-i);
        }
        return count;
    }

    public void update(int i, int[] tree) {
        while(i < tree.length) {
            tree[i] ++;
            i += i&(-i);
        }
    }
}
