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

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}