/*
You are playing the following Nim Game with your friend: There is a heap of stones on the table, 
each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. 
You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. 
Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: 
no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

Hint:

If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the winner?
*/

/*
我觉得就是抢这个4，只要第一个人拿完剩下4个或者4的倍数，那么就肯定会输
比如n=8，如果第一个人拿1个，剩下7个，第二个人只要拿3个，就剩下4了
所以只要n不是4的倍数，4s+1，4s+2，4s+3都是可以的
*/

public class Solution {
    public boolean canWinNim(int n) {
        return n%4 != 0;
    }
}
