/*
Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
Rectangle Area

https://leetcode.com/problems/rectangle-area/

Assume that the total area is never beyond the maximum possible value of int.
*/

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int common = 0;
        if(C <= E || G <= A || D <= F || H <= B) {
            common = 0;
        } else {
            int len = Math.min(C, G) - Math.max(A, E);
            int height = Math.min(D, H) - Math.max(B, F);
            common = len * height;
        }
        
        int area1 = (C-A) * (D-B);
        int area2 = (G-E) * (H-F);
        return area1 + area2 - common;
    }
}
