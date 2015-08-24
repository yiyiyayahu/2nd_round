/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

/*
这里我总是重复分配空间会不会不太好？可以直接利用原来的lists？比如0和最后一个放到0里面，一次类推？
时间复杂度应该是：如果lists数组的长度是k，每个ListNode的长度是n
一共重复logk次，每次遍历merge两个list的话大概是O(n)所以是O(nlogk)
空间复杂度我貌似用了O(n)? n + n/2 + n/4 + n/8 + ... + 1
*/


/*
还有一种方法是用最小堆做 - peek就是最小的，这样每次往里放，就自动找到最小的了，都帮你sort好了
时间复杂度还是O(nlogk)
只是这里一定要自己写个Comparator来比较ListNode
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        
        ListNode dummyHead = new ListNode(-1);
        ListNode tmp = dummyHead;
        
        for(ListNode l : lists) {
            if(l != null) queue.add(l);
        }
        
        while(!queue.isEmpty()) {
            ListNode n = queue.poll();
            tmp.next = n;
            tmp = tmp.next;
            if(n.next != null) queue.offer(n.next);
        }
        return dummyHead.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        List<ListNode> ret = helper(new ArrayList<ListNode>(Arrays.asList(lists)));
        return ret.get(0);
    }
    
    public List<ListNode> helper(List<ListNode> lists) {
        int start = 0, end = lists.size()-1;
        List<ListNode> ret = new ArrayList<ListNode>();
        while(start <= end) {
            ListNode l = null;
            if(start == end) l = lists.get(start);
            else l = mergeTwoLists(lists.get(start), lists.get(end));
            ret.add(l);
            start ++;
            end --;
        }
        while(ret.size() > 1) {
            ret = helper(ret);
        }
        return ret;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummyHead = new ListNode(-1);
        ListNode tmp = dummyHead;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                tmp.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                tmp.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        if(l1 == null) tmp.next = l2;
        if(l2 == null) tmp.next = l1;
        return dummyHead.next;
    }
}
