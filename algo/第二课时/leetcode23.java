package com.sean;

import com.sean.base.ListNode;

/**
 * @ClassName leetcode23
 * @Description: TODO
 * @Author a9705
 * @Date 2022/7/26
 * @Version V1.0
 **/
public class leetcode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeInternally(lists,0,lists.length-1);
    }

    public ListNode mergeInternally(ListNode[] lists, int start, int end ){

        if(start>end){
            return null;
        }

        if(start==end){
            return lists[start];
        }

        int pivot =(end+start)/2;
        ListNode left=mergeInternally(lists,start,pivot);
        ListNode right=mergeInternally(lists,pivot+1,end);
        return  mergeTwoLists(left,right);
        //return head.next;
    }

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
//    private void merge(ListNode[] lists, int start, int pivot, int end, ListNode head) {
//    }

}
