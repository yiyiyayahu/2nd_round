/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/

/*
这个应该能扩展到n个list
*/
public class ZigzagIterator {
    int index = 0, iterating = 1;
    List<Integer> v1;
    List<Integer> v2;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        if(v1.size() == 0) iterating = 2;
    }

    public int next() {
        int ret = 0;
        if(iterating == 1) {
            ret = v1.get(index);
            if(index < v2.size()) {
            	iterating = 2;
            } else {
            	index ++;
            }
        } else {
            ret = v2.get(index);
            index ++;
            if(index < v1.size()) {
            	iterating = 1;
            }            
        }
        return ret;
    }

    public boolean hasNext() {
        return index < v1.size() || index < v2.size();
    }
}

/*
这个我只是简单地写了一下，其实扩展到n个这个我还是没有做好，java应该怎么弄呢，还是摆弄一下指针应该，是不是可以只用一个index来回走呢
*/
public class ZigzagIterator {
    int i1, i2, i, len;
    List<Integer> v1, v2;
    boolean iterateV1;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i1 = 0; i2 = 0; i = 0; iterateV1 = true;
        this.v1 = v1; this.v2 = v2;
        len = v1.size() + v2.size();
    }

    public int next() {
        int ret = 0;
        if(!iterateV1 || i1 >= v1.size()) {
            ret = v2.get(i2++);
            iterateV1 = true;
        } else {
            ret = v1.get(i1++);
            if(i2 < v2.size()) iterateV1 = false;
        }
        i++;
        return ret;
    }

    public boolean hasNext() {
        return i < len;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
