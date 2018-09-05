package LinkList;

import java.util.HashMap;

public class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }

    public static void main(String[] args){
        RandomListNode head=new RandomListNode(-1);
        head.next=new RandomListNode(-1);
        head.random=head.next;
        head.next.random=head;
        RandomListNode test=copyRandomList(head);
    }
    public static RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode newHead=new RandomListNode(0),tmp=head,newNode=newHead;
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        while(tmp!=null){
            newNode.next=new RandomListNode(tmp.label);
            map.put(tmp,newNode.next);
            tmp=tmp.next;
            newNode=newNode.next;
        }
        tmp=head;
        while (tmp!=null){
            RandomListNode cur=map.get(tmp),rand=map.get(tmp.random);
            cur.random=rand;
            tmp=tmp.next;
        }
        return newHead.next;
    }
}
