package LinkList;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LinkListOP {
    public static void main(String[] args){
        ListNode head=new ListNode(-1);
        head.next=new ListNode(5);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(0);
        LinkListOP listNodeOP=new LinkListOP();
        ListNode res=listNodeOP.sortList(head);
        printLinkList(res);
    }
    public static void printLinkList(ListNode head){
        ListNode tmp=head;
        while(tmp!=null){
            System.out.print(tmp.val+" ");
            tmp=tmp.next;
        }
        System.out.println();
    }
    public ListNode sortList(ListNode head) {
        ListNode newHead=new ListNode(0),tmp=head,walkNode=newHead;
        while(tmp!=null){
            while (walkNode.next!=null && walkNode.next.val<tmp.val)
                walkNode=walkNode.next;
            ListNode t=new ListNode(tmp.val);
            tmp=tmp.next;
            if(walkNode!=null){
                t.next=walkNode.next;
                walkNode.next=t;
            }
            else walkNode.next=t;
            walkNode=newHead;
        }
        return newHead.next;
    }
    public static int lenOfLinkList(ListNode head){
        ListNode tmp=head;
        int len=0;
        while(tmp!=null){
            len++;
            tmp=tmp.next;
        }
        return len;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int Alen=lenOfLinkList(headA),Blen=lenOfLinkList(headB),dlen=Math.abs(Alen-Blen);
        ListNode walkLink1,walkLink2;
        if(Alen>Blen){walkLink1=headA;walkLink2=headB;}
        else {walkLink1=headB;walkLink2=headA;}
        for (int i=0;i<dlen;i++){
            walkLink1=walkLink1.next;
        }
        while(walkLink1!=null && walkLink2!=null){
            if(walkLink1==walkLink2)
                return walkLink1;
            walkLink1=walkLink1.next;
            walkLink2=walkLink2.next;
        }
        return null;
    }
    /**
     给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     * */
    public ListNode oddEvenList(ListNode head) {
        ListNode odd=new ListNode(0),even=new ListNode(0),tmp=head,oddtmp=odd,eventmp=even;
        int cnt=1;
        while(tmp!=null){
            if(cnt%2==0){
                eventmp.next=tmp;
                eventmp=eventmp.next;
            }
            else {
                oddtmp.next=tmp;
                oddtmp=oddtmp.next;
            }
            tmp=tmp.next;
            cnt++;
        }
        eventmp.next=null;
        oddtmp.next=even.next;
        return odd.next;
    }
    /**
     * 将两个链表相加
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tl1=l1;
        ListNode tl2=l2;
        int flag=0;
        ListNode res=new ListNode(0),head=res;
        while(tl1!=null && tl2!=null){
            int num=tl1.val+tl2.val+flag;
            if(num>=10){
                num%=10;
                flag=1;
            }
            else flag=0;
            res.next=new ListNode(num);
            tl1=tl1.next;
            tl2=tl2.next;
            res=res.next;
        }
        ListNode tmp;
        if(tl1!=null)tmp=tl1;else tmp=tl2;
        while (tmp!=null){
            int num=flag+tl1.val;
            if(num>=10){
                num%=10;
                flag=1;
            }
            else flag=0;
            res.next=new ListNode(num);
            res=res.next;
            tmp=tmp.next;
        }
        if(flag!=0)
            res.next=new ListNode(flag);
        return head.next;
    }
}
