package com.qianxiang.leetcode;
/*
Given a singly linked list L: L 0→L 1→…→L n-1→L n,
reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…

You must do this in-place without altering the nodes' values.

For example,
Given{1,2,3,4}, reorder it to{1,4,2,3}.

思路：先使用快慢指针，将链表从中间一分为2，然后再将后一部分的链表用头插法倒序。最后将两部分交替连在一起


 */

public class ReserveLink {

    // false
    public void reorderList1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        ListNode preHead = new ListNode(0);
        ListNode cur = head.next.next;
        preHead.next = head.next;
        preHead.next.next = null;
        ListNode temp = null;
        while (cur != null)
        {
            temp = cur.next;
            cur.next = preHead.next;
            preHead.next = cur;
            cur = temp;
        }
        head.next = preHead.next;
    }

    // true
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode after = slow.next;
        slow.next = null;
        ListNode pre = null;
        ListNode temp = null;
        while (after != null)
        {
            temp = after.next;
            after.next = pre;
            pre = after;
            after = temp;
        }
        ListNode first = head;
        after = pre;
        while (first != null && after != null)
        {
            ListNode ftemp = first.next;
            ListNode aftemp = after.next;
            first.next = after;
            first = ftemp;
            after.next = first;
            after = aftemp;
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(0);
        ListNode cur = root;
        for (int i = 1; i < 5; ++i) {
            cur.next = new ListNode(i);
            cur = cur.next;

        }
        ReserveLink r1 = new ReserveLink();
        r1.reorderList(root);
        cur = root;
        while (cur != null)
        {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
