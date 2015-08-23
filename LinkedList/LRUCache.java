/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: 
get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
it should invalidate the least recently used item before inserting a new item.
*/

/*
是一道考数据结构的题，为了根据那个key尽快找到之前的entry，肯定是用个map
开始我想的是用queue，但是这个很明显要插入删除很多次，所以用双链表比较快。。。可是这个也是看了之前写的才做出来，学艺不精啊。。。

唉，而且这道题写了好多好多次才好。。。而且犯的是和上次差不多的错误。。。
首先就是addToHead的时候一定要把n.prev设成null啊设成null啊设成null啊，重要的事儿说三遍！！！
其次就是removeNode的时候要注意:
n.prev.next = n.next
n.next.prev = n.prev
这里n.prev和n.next都可能是null，都要考虑到

还有就是map也要相应地更新呀，不管是set一个新的value还是removeTail都要把值给改了对不对
试试dummyHead和dummyTail吧。估计能简单点
*/
class Node {
    int key;
    int value;
    Node prev;
    Node next;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    HashMap<Integer,Node> map = new HashMap<Integer,Node>();
    Node head;
    Node tail;
    int len = 0;
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node n = map.get(key);
        if(head != n) {
            removeNode(n);
            addToHead(n);
        }
        return n.value;
    }
    
    public void set(int key, int value) {
        Node n = null;
        if(!map.containsKey(key)) {
            n = new Node(key, value);
            map.put(key, n);
            if(len < capacity) {
                len ++;
            } else {
                map.remove(tail.key);
                removeNode(tail);
            }
        } else {
            n = map.get(key);
            removeNode(n);
            n.value = value;
            map.put(key, n);
        }
        addToHead(n);
    }
    
    public void addToHead(Node n) {
        n.prev = null;
        n.next = head;
        if(head != null) head.prev = n;
        head = n;
        if(tail == null) tail = n;
    }

    public void removeNode(Node n) {
        Node prev = n.prev;
        Node next = n.next;
        
        if(prev == null) head = next;
        else prev.next = next;
        
        if(next == null) tail = prev;
        else next.prev = prev;
    }
}
