/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

click to show corner cases.
Corner Cases:

    Did you consider the case where path = "/../"?
    In this case, you should return "/".
    Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
    In this case, you should ignore redundant slashes and return "/home/foo".
*/
/*
其实有点cheat了。。。就是我用java 的split，把所有的按照/分开。但是其实还好啦，如果一点点parse的话可以用regular expression matching来练
用到一个stack来操作
注意的是，stack先pop出来的是在后面的，我要把它insert到前面去
*/
public class Solution {
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0) return path;
        String[] arr = path.split("/+");
        Stack <String> stack = new Stack<String>();
        for(String str : arr) {
            if(str.equals(".") || str.equals("")) {
                continue;
            } else if(str.equals("..")) {
                if(!stack.isEmpty()) stack.pop();
            } else {
                stack.push(str);
            }
        }
        StringBuilder sb = new StringBuilder();
        if(stack.isEmpty()) return "/";
        while(!stack.isEmpty()) {
            sb.insert(0,"/" + stack.pop());
        }
        return sb.toString();
    }
}
