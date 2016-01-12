/*
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.
*/

/*
其实我觉得这题挺无聊的。。。就是找规律，然后发现，喔，必须是perfect square才能最后是亮的
要考虑越界的问题
*/
public class Solution {
    public int bulbSwitch(int n) {
        if(n == 0) return 0;
        int num = 1;
        for(int i = 2; i <= n/2; i++) {
            if(i >= Math.sqrt(Integer.MAX_VALUE)) break;
            if(i * i <= n) num ++;
        }
        return num;
    }
}
