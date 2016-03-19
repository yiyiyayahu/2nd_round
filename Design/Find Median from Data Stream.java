/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2
*/

/*
cc150上面hard那一章的题
自己开始脑残了。。。java自带的PriorityQueue是minHeap
minHeap是上面的是堆里面最小的，用来存比较大的值
maxHeap是上面的是堆里面最大的，用来存比较小的值

这道题呢，就是随着data进来，更新maxHeap和minHeap，更新的过程中保证maxHeap.size() >= minHeap.size()
maxHeap: <= median的值
minHeap: > median的值
偶数情况，就是maxHeap.size()==minHeap.size()返回两者peek的average
奇数情况，返回maxHeap的peek()

那如何保证maxHeap.size() >= minHeap.size()
就是比如两者size相等的时候，如果num想加入到maxHeap里面去，就随便；但是要是想去minHeap元素就要挪个位置出来了，就把minHeap的peek扔到maxHeap里面去再把num加进来
*/

class MedianFinder {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2-i1;
            }
    });
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        if(maxHeap.size() == minHeap.size()) {
            if(!minHeap.isEmpty() && num > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }
        } else {
            if(num < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(maxHeap.isEmpty()) return 0;
        if(maxHeap.size() == minHeap.size()) {
            return (double)((maxHeap.peek()+minHeap.peek())/2.0);
        }
        return (double)(maxHeap.peek());
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
