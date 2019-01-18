package com.qianxiang.leetcode;
/*
Sort a linked list using insertion sort.
这题的思路就是普通的插入排序，先假定首元素为排序好的元素生成一个已排序链表，然后将原链表的元素一个个插入到排序好的链表中
 */

public class InsertSort {
    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return null;
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode currentNode = head.next; // 要插入元素的游标
        head.next = null; // 生成已排序好的链表
        while (currentNode != null)
        {
            ListNode compare = preHead;
            while (compare != null)
            {
                if (compare.next == null || compare.next.val >= currentNode.val)
                    break;
                compare = compare.next;
            }
            ListNode temp = currentNode.next;
            currentNode.next = compare.next;
            compare.next = currentNode;
            currentNode = temp;
        }
        return preHead.next;
    }
}
