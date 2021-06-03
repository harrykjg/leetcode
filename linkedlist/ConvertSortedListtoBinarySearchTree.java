package linkedlist;

/**
 * Created by 502575560 on 10/7/17.
 */
public class ConvertSortedListtoBinarySearchTree {

    public static void main(String[] args){
        ListNode n=new ListNode(0);
        n.next=new ListNode(2);
        n.next.next=new ListNode(3);
        n.next.next.next=new ListNode(4);
        n.next.next.next.next=new ListNode(5);
        ConvertSortedListtoBinarySearchTree cs=new ConvertSortedListtoBinarySearchTree();
        cs.sortedListToBST(n);
    }
    //这题还是不好想,以前想的是用快慢指针找中点,然后中点new成新的treenode,然后中点的左右孩子分别递归调用前半段和后半段这个想法会导致中点前一个点不好写(比如
    //当前list只有1个或者2个节点时)
    //看了别人用inorder的想法不错,递归还不是很好想,手机app上的解法,典型的自低向上的构造树
    //http://blog.csdn.net/linhuanmars/article/details/23904937 计数的解法

    //手机app inorder解法
    ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        cur=head;
        if(head==null){
            return null;
        }
        int length=0;
        ListNode h=head;
        while (h!=null){
            length++;
            h=h.next;
        }
        return helper(0,length-1);//改成1,length也行,所以说意义实际上是处理length个node
    }

    TreeNode helper(int b,int e){//
        if(e<b){//开始写的e<=b就错了,这两个参数的作用实际上就是用来某个节点左边或者右边还应不应该有节点
            return null;
        }
        int m=b+(e-b)/2;
        TreeNode left=helper(b,m-1);
        TreeNode mid=new TreeNode(cur.val);
        cur=cur.next;
        TreeNode right=helper(m+1,e);
        mid.left=left;
        mid.right=right;

        return mid;
    }
    //后来有个想法就是把wal设成null,想着可以把原list的那个节点设成null,其实是不行的,wal=null只是把wal这个pointer变成null了,list里的那个节点还是存在的

    //9／16／2018,靠背的
    ListNode cur2;
    public TreeNode sortedListToBST2(ListNode head) {
        if(head==null){
            return null;
        }
        cur2=head;
        int len=0;
        ListNode temp=head;
        while (temp!=null){
            len++;
            temp=temp.next;
        }
        return helper2(0,len-1);
    }
    TreeNode helper2(int b,int e){
        if(b>e){
            return null;
        }
        if(b==e){
            TreeNode n=new TreeNode(cur2.val);
            cur2=cur2.next;
            return n;
        }
        int m=b+(e-b)/2;
        TreeNode left=helper2(b,m-1);
        TreeNode n=new TreeNode(cur2.val);
        cur2=cur2.next;//这里容易忘
        TreeNode right=helper2(m+1,e);
        n.left=left;
        n.right=right;
        return n;
    }
    //9/28/2018,不到2个星期又写不出了
    ListNode node3;
    public TreeNode sortedListToBST3(ListNode head) {
        int i=0;
        node3=head;
        ListNode cur=head;
        while (cur!=null){
            cur=cur.next;
            i++;
        }
        return helper3(0,i-1);
    }
    TreeNode helper3(int b,int e){
        if(b>e){
            return null;
        }
        if(b==e){
            TreeNode n= new TreeNode(node3.val);
            node3=node3.next;
            return n;
        }
        int m=b+(e-b)/2;
        TreeNode left=helper3(b,m-1);
        TreeNode root=new TreeNode(node3.val);
        node3=node3.next;
        TreeNode right=helper3(m+1,e);
        root.left=left;
        root.right=right;
        return root;
    }
    //1/3/2021,自己还是想的快慢指针分段的想法，参考别的写法也是可以写出来的，和以前的写法不一样
    //https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/discuss/35476/Share-my-JAVA-solution-1ms-very-short-and-concise.下面评论的写法
    public TreeNode sortedListToBST4(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next==null){//这个容易漏
            return new TreeNode(head.val);
        }
        ListNode slow=head;
        ListNode fast=head;
        ListNode pre=null;
        while (fast!=null&&fast.next!=null){
            pre=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        pre.next=null;//比如1，2，3，4，5，现在把3作为中点了，需要一个pre去来把砍断，注意，从3开始砍断是不够的，因为这样的话1，2，3还存在，而我们只要1，2
        TreeNode newnode=new TreeNode(slow.val);
        newnode.left=sortedListToBST4(head);
        newnode.right=sortedListToBST4(slow.next);
        return newnode;
    }

    //6/2/2021,不是很好想到找walker前面一个节点.第一个手机app写法还是不错
    public TreeNode sortedListToBST5(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next==null){
            return new TreeNode(head.val);
        }

        ListNode wal=head;
        ListNode run=head;
        ListNode pre=null;
        while (run!=null&&run.next!=null){
            pre=wal;
            wal=wal.next;
            run=run.next.next;
        }
        pre.next=null;

        TreeNode node=new TreeNode(wal.val);
        node.left=sortedListToBST5(head);
        node.right=sortedListToBST5(wal.next);
        return node;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}