package com.live.zhf.leetcode;
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义满十进一的数
        int num = 0;
        //定义一个ListNode，作为链表头
        ListNode proNode = new ListNode(0);
        //定义一个ListNode，接受两数的和
        ListNode currentNode = new ListNode(0);
        //先连接两个Node
        proNode.next=currentNode;

        do {
            //两数相加
            int sum = (l1!=null?l1.val:0) + (l2!=null?l2.val:0) + num;
            //是否满十
            num = sum/10;
            //得出个位数
            int result = sum%10;
            //填入结果
            currentNode.val = result;
            l1 = l1!=null?l1.next:l1;
            l2 = l2!=null?l2.next:l2;
            if(l1!=null || l2!=null || num!=0) {
                currentNode.next = new ListNode(0);
                currentNode = currentNode.next;
            }
        }while(l1!=null || l2!=null || num!=0);
        return proNode.next;
    }
}
