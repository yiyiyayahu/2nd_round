/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. 
For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */


/*
第二次还是不太理解题意
原来read4(char[] buf)是把4个或者剩余的character读到buf里面
然后我用read4读出来的这个buf，再copy到read的buf后面
要注意end of file的问题，就是可能n比file的size大
*/
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        if(n == 0) return 0;
        boolean eof = false;
        int readBytes = 0;
        char[] buffer = new char[4];
        while(!eof && readBytes < n) {
            int size = read4(buffer);
            if(size < 4) eof = true;
            int bytes = Math.min(n-readBytes, size);
            System.arraycopy(buffer, 0, buf, readBytes, size);
            readBytes += bytes;
        }
        return readBytes;
    }
}
