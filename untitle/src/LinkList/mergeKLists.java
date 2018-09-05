package LinkList;

import java.util.Arrays;

class Solution {
//    [
//            1->4->5,
//            1->3->4,
//            2->6
//     ]
//    输出: 1->1->2->3->4->4->5->6
    public static void main(String[] args){
        ListNode[] listNodes=new ListNode[3];
        listNodes[0]=new ListNode(1);
        listNodes[0].next=new ListNode(4);
        listNodes[0].next.next=new ListNode(5);
        listNodes[1]=new ListNode(1);
        listNodes[1].next=new ListNode(3);
        listNodes[1].next.next=new ListNode(4);
        listNodes[2]=new ListNode(2);
        listNodes[2].next=new ListNode(6);
        ListNode head=mergeKLists(listNodes);
        printList(head);
    }
    public static void printList(ListNode head){
        ListNode tmp=head.next;
        while (tmp!=null){
            System.out.print(tmp.val+" ");
            tmp=tmp.next;
        }
        System.out.println();
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head=new ListNode(0),tmp=head;
        for (int i=0;i<lists.length;i++){
            tmp=head;
            for(ListNode j=lists[i];j!=null;) {
                while (tmp.next != null && tmp.next.val < j.val)
                    tmp=tmp.next;
                ListNode t=new ListNode(j.val);
                j=j.next;
                if(tmp.next!=null){
                    t.next=tmp.next;
                    tmp.next=t;
                }
                else tmp.next=t;
                tmp=tmp.next;
            }
        }
        return head.next;
    }
}
