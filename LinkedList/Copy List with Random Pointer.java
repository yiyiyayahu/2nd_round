/*
 A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list. 
*/

/*
这道题因为之前做过，而且解法很巧妙，就还记得
第一次的时候不太明白题意，就是什么叫each node contains an additional random pointer which could point to any node in the list or null
其实就是每个node除了自己的指针之外又多出一个random的pointer，指向哪个点不确定，但是因为是指针嘛，只能指向一个点
copy就是clone出来一个一模一样的list

难点就是random 因为random的指针，可能你copy的过程中，这个node还没new出来，连不上
那我想的就是，有没有可能，我一遍遍历一边clone，但是random指针先指向上一个list中一些存在的点啊神马的
后来发现，喔喔，我想起来之前怎么做的了，就是每个node都在clone出来一个node放在next的位置
比如1-2-3变成1-1'-2-2'-3-3',这样就可以把random指针弄出来了
但是要注意的一点是：
1. 还是null pointer，这个random也可能是null的要注意判断
2. 弄完了要把以前的list还原回去，别不管了

时间O(n) 空间O(1)
*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return head;
        RandomListNode tmp = head;
        while(tmp != null) {
          RandomListNode clone = new RandomListNode(tmp.label);
          RandomListNode next = tmp.next;
          tmp.next = clone;
          clone.next = next;
          tmp = next;
        }
        tmp = head;
        while(tmp != null && tmp.next != null) {
          if(tmp.random!=null) tmp.next.random = tmp.random.next;
          tmp = tmp.next.next;
        }
        tmp = head;
        RandomListNode ret = tmp.next;
        RandomListNode curr = ret;
        while(tmp != null && curr != null) {
            tmp.next = curr.next;
            if(curr.next != null) curr.next = curr.next.next;
            tmp = tmp.next;
            curr = curr.next;
        }
        return ret;       
    }
}

/*
下面这个方法算是比较常规，就是用一个hashmap来做映射，key-旧节点 value-新节点
第一次遍历，建立map的同时，把next连接好
第二次遍历，把random连接好
这样就完成了
时间O(n) 空间O(n)
*/
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return head;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode n = head;
        RandomListNode newHead = new RandomListNode(head.label);
        map.put(n, newHead);
        RandomListNode clone = newHead;
        n = n.next;
        
        while(n != null) {
          RandomListNode curr = new RandomListNode(n.label);
          clone.next = curr;
          map.put(n, curr);
          clone = clone.next;
          n = n.next;
        }
        clone = newHead;
        n = head;
        while(clone != null) {
          clone.random = map.get(n.random);
          clone = clone.next;
          n = n.next;
        }
        return newHead;
    }
}
