/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/

/*
被call了multiple time其实就是有一些read4读在buffer里，但是没有完全取出来，这种情况要handle
所以要定义几个变量
leetcode那本书里面的解法，要简洁好多
bufferSize=0就表示没有之前的留下来重新读就好了
注意对offset和bufferSize的更新
            offset = (offset+bytes)%4; （因为数组是4位的，所以%4，然后往前移动bytes大小就好了）
            bufferSize = size - bytes; （buffer里剩余没读的size）
*/

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    char[] buffer = new char[4];
    int offset = 0, bufferSize = 0;
    
    public int read(char[] buf, int n) {
        if(n == 0) return 0;
        boolean eof = false;
        int readBytes= 0;

        while(!eof && readBytes < n) {
            int size = bufferSize > 0 ? bufferSize : read4(buffer);
            if(bufferSize == 0 && size < 4) eof = true;
            int bytes = Math.min(n-readBytes, size);
            System.arraycopy(buffer, offset, buf, readBytes, bytes);
            
            offset = (offset+bytes)%4;
            bufferSize = size - bytes;
            readBytes += bytes;
        }
        return readBytes;
    }
}


/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    char[] buffer = new char[4];
    int offset = 0, bufferSize = 0;
    
    public int read(char[] buf, int n) {
        if(n == 0) return 0;
        int readBytes= 0;
        boolean eof = false;
        if(bufferSize > 0) {
            int remainBytes = Math.min(n, bufferSize - offset);
            System.arraycopy(buffer, offset, buf, 0, remainBytes);
            if(n < bufferSize - offset) {
                offset += remainBytes;
            } else {
                offset = 0; 
                bufferSize = 0;
            }
            readBytes += remainBytes;
        }
        while(!eof && readBytes < n) {
            int size = read4(buffer);
            if(size < 4) eof = true;
            int bytes = Math.min(n-readBytes, size);
            System.arraycopy(buffer, 0, buf, readBytes, bytes);
            if(bytes < size) {
                bufferSize = size;
                offset = bytes;
            }
            readBytes += bytes;
        }
        return readBytes;
    }
}
