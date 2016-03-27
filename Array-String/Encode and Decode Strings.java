/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and 
is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/


/*
这道题难点在于怎么把string分割开。。。没有办法在string后面加，啊神马的

唉，还是没想出。。。虽然开始想过len+string的，但是很快就否定了T.T，因为很可能就是1212这种，分不清len是啥 （喔喔，我想起来了，memset的思想也是把len放前面对不对）
然后我就想不出了额，就想偏了，就可不可以重复多次啊啥的。。。或者后面append index。。。艾玛，这脑洞，都不太行啊

后来看很多人都是len+'#'+string，赶脚自己差了那么一点点，但是否定自己太快了还是就是学艺不精？！
开始也觉得不太行，1##10#这样的sting呢，但是由于开始肯定是len，遇到#之前len确定了，往后读len那么长的就是string了，好像也跑偏不了。。。

我最近不太行啊，怎么总想不出呢。。。哭了
*/
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        
        for(String s : strs) {
            int len = s.length();
            sb.append(len).append('#').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> list = new ArrayList<>();
        int i = 0, index = 0;
        while(i < s.length()) {
            if(s.charAt(i) == '#') {
                int len = Integer.parseInt(s.substring(index, i));
                list.add(s.substring(i+1, i+len+1));
                index = i+len+1;
                i = index;
            } 
            i++;
        }
        return list;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
