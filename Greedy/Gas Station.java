/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

/*
这道题好难想诶。。我开始只能想出O(n^2)的解法。。。
这道题要这么想，类似于最大连续数组和
首先，如果所有的gas[i]-cost[i]加和<0的话，ok，没有解，返回-1，如果>0，那么肯定是有解的
然后呢，接着往下想，比如我在[i,j]这个区间内，发现，诶，怎么加和<0了，那说明这个解肯定不在i到j之中对不对
所以，就是遍历一遍数组，如果发现，第一个位置k让区间[k,n]>0了，并且一共的和大于0，那么k就是那个start
*/

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        if(len == 0 || len != cost.length) return -1;
        int currSum = 0, leftGas = 0, start = 0;

        for(int i = 0; i < len; i++) {
            int diff = gas[i] - cost[i];
            currSum += diff;
            leftGas += diff;
            if(currSum < 0) {
                currSum = 0;
                start = i+1;
            }
        }
        if(leftGas < 0) return -1;
        return start;
    }
}
