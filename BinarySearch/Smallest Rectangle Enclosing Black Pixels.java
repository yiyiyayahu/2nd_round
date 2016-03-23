/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. 
Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle 
that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
*/

/*
这道题也写了好久。。。
思路不难，无非就是用四个binary search算四个boundary
但是这个code搞来搞去不好写啊啊啊啊
开始还进入死循环了。。。后来发现还是要按自己的思路
然后判断return哪个值的时候呢，就看while的end条件，然后看最后的start和end的值分别是什么样的，这样就能判断出该return什么了
*/

public class Solution {
    public int minArea(char[][] image, int x, int y) {
        if(image.length == 0) return 0;
        
        int m = image.length, n = image[0].length;
        
        int up = findBound(image, 0, x, true, false, true);
        int down = findBound(image, x, m-1, true, false, false);        
        int left = findBound(image, 0, y, false, true, true);
        int right = findBound(image, y, n-1, false, true, false);

        return (right - left + 1) * (down - up + 1);
    }
    
    public int findBound(char[][] image, int start, int end, boolean checkRow, boolean checkCol, boolean zeroToMid) {
        int m = image.length, n = image[0].length;
        
        while(start <= end) {
            int mid = (start + end)/2;
            boolean hasBlack = false;
            if(checkRow) {
                for(int j = 0; j < n; j++) {
                    if(image[mid][j] == '1') {
                        hasBlack = true;
                        break;
                    }
                }
            } 
            if(checkCol) {
                for(int i = 0; i < m; i++) {
                    if(image[i][mid] == '1') {
                        hasBlack = true;
                        break;
                    }
                }
            }
            if(zeroToMid) {
                if(hasBlack) end = mid-1;
                else start = mid + 1;
            } else {
                if(hasBlack) start = mid+1;
                else end = mid-1;
            }
        }
        if(zeroToMid) return end+1;
        else return start-1;
    }
}
