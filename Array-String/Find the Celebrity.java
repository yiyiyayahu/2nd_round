/*
 Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1. 
*/

/*
不晓得这样做是不是最优的诶，看看别人的解法
*/

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        boolean[] notCelebrity = new boolean[n];
        for(int i = 0; i < n-1; i++) {
            if(notCelebrity[i]) continue;
            for(int j = i+1; j < n; j++) {
                if(notCelebrity[j]) continue;
                if(knows(i,j) == knows(j,i)) {
                    notCelebrity[i] = true;
                    notCelebrity[j] = true;
                }
            }
        }
        for(int i = 0; i < n; i++) {
            if(!notCelebrity[i] && isCelebrity(n,i)) return i; 
        }
        return -1;
    }
    public boolean isCelebrity(int n, int b) {
        for(int i = 0; i < n; i++) {
            if(i == b) continue;
            if(knows(i, b) && !knows(b, i)) continue;
            else return false;
        }
        return true;
    }
}
