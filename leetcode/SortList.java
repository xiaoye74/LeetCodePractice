package com.qianxiang.leetcode;
/*
Sort a linked list in O(nlogn) time using constant space complexity.
看到O(nlogn)的时间复杂度就想到归并排序。
归并排序的思想就是：通过一次次二分，最后分到只有两个元素，然后使两个元素有序，然后反推回来使整体有序。
一般步骤为：
1）将待排序数组（链表）取中点并一分为二；
2）递归地对左半部分进行归并排序；
3）递归地对右半部分进行归并排序；
4）将两个半部分进行合并（merge）,得到结果。

所以对应此题目，可以划分为三个小问题：
1）找到链表中点 （快慢指针思路，快指针一次走两步，慢指针一次走一步，快指针在链表末尾时，慢指针恰好在链表中点）；
2）写出merge函数，即如何合并链表。 （见merge-two-sorted-lists 一题解析）
3）写出mergesort函数，实现上述步骤。
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = this.getMid(head);
        ListNode midNext = mid.next;
        mid.next = null;
        return mergeSort(sortList(head),sortList(midNext));
    }

    public ListNode mergeSort(ListNode n1, ListNode n2)
    {
        ListNode preHead = new ListNode(0);
        ListNode cur1 = n1;
        ListNode cur2 = n2;
        ListNode cur = preHead;
        while (cur1 != null && cur2 != null)
        {
            if (cur1.val < cur2.val)
            {
                cur.next = cur1;
                cur1 = cur1.next;
            }
            else
            {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        if (cur1 != null)
            cur.next = cur1;
        if (cur2 != null)
            cur.next = cur2;
        return preHead.next;
    }

    public ListNode getMid(ListNode head)
    {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
