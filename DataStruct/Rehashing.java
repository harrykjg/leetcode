package DataStruct;

/**
 * Created by 502575560 on 7/11/17.
 */
public class Rehashing {
    //http://www.jianshu.com/p/fafc17cb76f1
    //https://segmentfault.com/a/1190000004903435
    public ListNode[] rehashing(ListNode[] hashTable) {
        ListNode[] rs=new ListNode[hashTable.length*2];
        int len=rs.length;
        for(int i=0;i<hashTable.length;i++){
            ListNode cur=hashTable[i];
            while (cur!=null){
                ListNode n=new ListNode(cur.val);
                int index=(cur.val%len+len)%len;
                if(rs[index]==null){
                    rs[index]=n;
                }else{
                    ListNode temp=rs[index];
                    while (temp.next!=null){
                        temp=temp.next;
                    }
                    temp.next=n;
                }

                cur=cur.next;
            }
        }
        return rs;
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}