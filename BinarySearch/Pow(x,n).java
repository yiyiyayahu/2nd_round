/*
Implement pow(x, n).
*/

public class Solution {
    public double myPow(double x, int n) {
        double result = 1.0;
        boolean isNeg = false;
        if(n < 0) {
            isNeg = true;
            n = -n;
        }
        if(n == 0) return 1;
        
        double tmp = myPow(x, n/2);
        if(n%2 == 0) result = tmp * tmp;
        if(n%2 == 1) result = tmp * tmp * x;
        
        if(isNeg) result = 1.0/result;
        return result;
    }
}
