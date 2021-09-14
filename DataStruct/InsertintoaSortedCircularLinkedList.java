package DataStruct;

public class InsertintoaSortedCircularLinkedList {
    //8/17/2021还可能是3-3-3插入2这种的，还有漏了考虑插入的值是大于list的最大值或者小于list的最小值的。
    //https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/discuss/136918/(Accepted)-C%2B%2B-Solution-in-O(n)-with-Explanation
    public Node insert(Node head, int insertVal) {
        Node cur=head;
        if (head==null){
            Node n= new Node(insertVal);
            n.next=n;
            return n;
        }
        if (head.next==head){
            Node n=new Node(insertVal);
            head.next=n;
            n.next=head;
            return head;
        }
        boolean inserted=false;
        while (true){//这里还挺恶心的，怎么判断是否已经过了一圈,不能用cur！=head判断因为一开始cur就是等于head的，最后看别人写的是在while里面判断
            if (cur.next.val>=insertVal&&cur.val<=insertVal){
                Node n=new Node(insertVal);
                n.next=cur.next;
                cur.next=n;
                inserted=true;
                break;
            }
            if (cur.next.val<cur.val&&(insertVal>=cur.val||insertVal<=cur.next.val)){//说明到了头了,并且inset的值大于最大或小于最小
                Node n=new Node(insertVal);
                n.next=cur.next;
                cur.next=n;
                inserted=true;
                break;
            }
            cur=cur.next;
            if (cur==head){
                break;
            }
        }
        if (!inserted){
            Node n=new Node(insertVal);
            n.next=cur.next;
            cur.next=n;
        }
        return head;
    }
    //8/23/2021 改了2次才对，主要是边界条件
    public Node insert2(Node head, int insertVal) {
        if(head==null){
            Node newn=new Node(insertVal);
            newn.next=newn;
            return newn;
        }
        Node cur=head;
        while(true){
            if(cur.next!=null&&cur.next.val>insertVal&&cur.val<=insertVal){
                Node newn=new Node(insertVal);
                newn.next=cur.next;
                cur.next=newn;
                return head;
            }
            if(cur.next.val<cur.val&&(insertVal>=cur.val||insertVal<=cur.next.val)){//(insertVal>=cur.val||insertVal<=cur.next.val)这个开始漏了就错了，
                Node newn=new Node(insertVal);      //因为可能这个数有合适的2个node之间插入，只是先到了末尾。
                newn.next=cur.next;
                cur.next=newn;
                return head;
            }

            cur=cur.next;
            if(cur==head){
                break;
            }
        }
        Node newn=new Node(insertVal);
        newn.next=head.next;
        head.next=newn;
        return head;
    }
    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
}
