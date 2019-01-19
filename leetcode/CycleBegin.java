package com.qianxiang.leetcode;
/*
Given a linked list, return the node where the cycle begins. If there is no cycle, returnnull.
Follow up:
Can you solve it without using extra space?

Given a linked list, determine if it has a cycle in it.
Follow up:
Can you solve it without using extra space?



解题思路：
使用快慢两个指针，如果存在环则两个指针一定会相遇。这就解决了相遇问题.
证明如下：
如下图所示，X,Y,Z分别为链表起始位置，环开始位置和两指针相遇位置，X,Y间距a,Y,Z间距b,Z,Y间距c,
则根据快指针速度为慢指针速度的两倍，可以得出：
2*(a + b) = a + b + n * (b + c)；即
a=(n - 1) * b + n * c = (n - 1)(b + c) +c;
注意到b+c恰好为环的长度，故可以推出，如将此时两指针分别放在起始位置和相遇位置，并以相同速度前进，当一个指针走完距离a时，
另一个指针恰好走出 绕环n-1圈加上c的距离。故两指针会在环开始位置相遇。

 */
public class CycleBegin {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast!=null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast)
            {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
            {
                ListNode slow2 = head;
                while (slow != slow2)
                {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}
