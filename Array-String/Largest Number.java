/*
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
*/

/*
如果直接转成string[] sort会快一点，但是差不多啦
*/
public class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i]+"";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String i, String j) {
                String s1 = i+j;
                String s2 = j+i;
                return s2.compareTo(s1);
            }
        });
        
        if(strs[0].charAt(0) == '0') return "0";
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }
}

public class Solution {
    public String largestNumber(int[] nums) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for(int i = 0; i < nums.length; i++) list.add(nums[i]);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = String.valueOf(o1) + String.valueOf(o2);
                String s2 = String.valueOf(o2) + String.valueOf(o1);
                return s2.compareTo(s1);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++) {
            sb.append(list.get(i));
        }

        while(sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}

/*
这个还是有一个test case没过 [121,12] 因为如果按照我的写法，其实121和12的compare值是0，所以出来的结果就是12112，但是最大的应该是12121
我觉得有点难改啊啊啊
*/

public class Solution {
    public String largestNumber(int[] nums) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for(int i = 0; i < nums.length; i++) list.add(nums[i]);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = String.valueOf(o1), s2 = String.valueOf(o2);
                int len1 = s1.length(), len2 = s2.length();
                if(s1.charAt(0) != s2.charAt(0)) return s2.charAt(0)-s1.charAt(0);
                int i = 0;
                char prev = s1.charAt(0);
                while(i < Math.max(len1, len2)) {
                    char c1 = (i<len1) ? s1.charAt(i) : prev;
                    char c2 = (i<len2) ? s2.charAt(i) : prev;
                    if(c1 != c2) return c2-c1;
                    i++;
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++) {
            sb.append(list.get(i));
        }

        while(sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
