package DataStruct;

public class DesignBrowserHistory {
    //9//13/2021 直接doubley linkedlist就行了，只维护一个pointer
    Node head;
    public void BrowserHistory(String homepage) {
        head=new Node(homepage);
    }

    public void visit(String url) {
        Node n=new Node(url);
        n.pre=head;
        head.next=n;
        head=n;
    }

    public String back(int steps) {
        int i=0;
        while (head.pre!=null&&i<steps){
            head=head.pre;
            i++;
        }
        return head.val;
    }

    public String forward(int steps) {
        int i=0;
        while (head.next!=null&&i<steps){
            head=head.next;
            i++;
        }
        return head.val;
    }
    class Node{
        String val;
        Node pre;
        Node next;
        public Node(String val){
            this.val=val;
        }
    }
}
