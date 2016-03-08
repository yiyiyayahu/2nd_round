/*
Given two words (beginWord and endWord), and a dictionary, 
find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

/*
我觉得这样写和下面的没啥区别啊。。。咋就以前的code现在TLE了，苦
不过看runtime，确实差好多，最新的这个是103ms，之前的写法吧是804ms
莫非就是一个是把getOneDiff单拿出来，然后我这种recursive调用低效？不晓得诶。。
*/
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        wordList.add(endWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                char[] w = s.toCharArray();
                for (int j = 0; j < w.length; j++) {
                    char c = w[j];
                    for (int k = 0; k < 26; k++) {
                        w[j] = (char)(k + 'a');
                        String temp = new String(w);
                        if (endWord.equals(temp))
                            return level;
                        if (wordList.contains(temp)) {
                            queue.add(temp);
                            wordList.remove(temp);
                        }
                    }
                    w[j] = c;
                }
            }
        }
        return 0;
    }
}

/*
这种写法已经TLE了。。。囧
*/
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        Queue<String> queue = new LinkedList<String>();
        Queue<String> tmp = new LinkedList<String>();
        queue.add(beginWord);
        int len = 1;
        
        while(!queue.isEmpty()) {
            String word = queue.remove();
            for(String s : getOneDiff(word)) {
                if(s.equals(endWord)) return len+1;
                if(wordDict.contains(s)) {
                    wordDict.remove(s);
                    tmp.add(s);
                }
            }
            if(queue.isEmpty()) {
                queue = tmp;
                len ++;
                tmp = new LinkedList<String>();
            }
        }
        return 0;
    }
    
    public Set<String> getOneDiff(String s) {
        Set<String> stringSet = new HashSet<String>();
        if(s == null || s.length() == 0) return stringSet;
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char[] arr = s.toCharArray();
            for(char ch = 'a'; ch <= 'z'; ch++) {
            	if(ch == c) continue;
            	arr[i] = ch;
            	stringSet.add(String.valueOf(arr));
            }
        }
        return stringSet;
    }
}


/*
开始用这个会超时。。。
*/
    public Set<String> getOneDiff(String word) {
        Set<String> set = new HashSet<String>();
        for(int i = 0; i < word.length(); i++) {
            for(char c = 'a'; c <= 'z'; c++) {
                String newWord = word.substring(0,i) + c + word.substring(i+1);
                set.add(newWord);
            }
        }
        return set;
    }
*/
