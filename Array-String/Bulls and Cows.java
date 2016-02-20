/*
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/

/*
这道应该考的是hashtable，因为都是number，那估计就是0-9，我只要记录出现次数就可以了，所以用一个len=10的int array来存
但是我fail了一个test case：
"1122" "1222"应该返回3A0B，但是我返回的是3A1B，后来改了下code就过了
*/
public class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        StringBuilder sb = new StringBuilder();
    
        int[] arr = new int[10];
        for(int i = 0; i < secret.length(); i++) {
            int num = secret.charAt(i) - '0';
            if(guess.charAt(i)-'0' == num) {
                bulls ++;
            } else {
                arr[num] ++;
            }
        }
        for(int i = 0; i < guess.length(); i++) {
            int curr = guess.charAt(i) - '0';
            
            if(curr != secret.charAt(i)-'0' && arr[curr] > 0) {
                cows ++;
                arr[curr] --;
            }
        }
        sb.append(bulls).append("A").append(cows).append("B");
        return sb.toString();
    }
}

/*
看到的好一点的解法，就是不用像我上面那样遍历两遍string，但是最后还要遍历一次数组，其实差不多啦，看是string长还是数组长了
而且这个还要多分配一个array
*/

public class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        StringBuilder sb = new StringBuilder();
    
        int[] secretCounts = new int[10];
        int[] guessCounts = new int[10];
        for(int i = 0; i < secret.length(); i++) {
            int valS = secret.charAt(i) - '0';
            int valG = guess.charAt(i) - '0';
            if(valS == valG) {
                bulls ++;
            } else {
                secretCounts[valS] ++;
                guessCounts[valG]++;
            }
        }
        for(int i = 0; i < 10; i++) {
            cows += Math.min(secretCounts[i], guessCounts[i]);
        }

        sb.append(bulls).append("A").append(cows).append("B");
        return sb.toString();
    }
}
