package com.sean;

import com.sean.base.ListNode;


/**
 * @ClassName leetcode21
 * @Description: 合并2个有序链表
 * @Author a9705
 * @Date 2022/7/22
 * @Version V1.0
 **/
public class leetcode21 {
    /**
     * 直接链表遍历
     * 时间复杂度 O(n)
     * 空间复杂度 O(n*n)
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //默认头节点
        ListNode head = new ListNode(0);
        //dummy节点，用于指向原始的头节点并且作为答案返回
        ListNode ans = head;

        while (list1 != null && list2 != null) {

            //当前哪个结点小就连接哪个节点
            if (list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            //头节点指向下一个节点
            head = head.next;
        }

        //最后把剩下的数组连接上
        if (list1 != null) {
            head.next = list1;
        }else{
            head.next = list2;
        }

        return ans.next;
    }

    /**
     * 递归
     * 时间复杂度 O(n)
     * 空间复杂度 O(n*n)
      * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val <= list2.val) {
            list1.next = mergeTwoLists1(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists1(list1, list2.next);
            return list2;
        }
    }
}
