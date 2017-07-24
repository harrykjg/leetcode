package Advance3;


import DataStruct.LowestCommonAncestor;

import java.util.LinkedList;

/**
 * Created by 502575560 on 7/23/17.
 */
public class MaxTree {
    //不会,只能记,还是挺神奇的
    //http://www.jianshu.com/p/e05e598c8073
    //http://www.cnblogs.com/lishiblog/p/4187936.html
    //https://segmentfault.com/a/1190000007471356
    public static void main(String[]args){
        int[] a={2,5,6,0,3,1};
        maxTree(a);
    }
    public static TreeNode maxTree(int[] a) {
        if(a.length==0){
            return null;
        }
        LinkedList<TreeNode> st=new LinkedList<>();
        for(int i=0;i<a.length;i++){
            TreeNode n=new TreeNode(a[i]);
            if(st.isEmpty()){
                st.push(n);
                continue;
            }
            while (!st.isEmpty()&&st.peek().val<a[i]){//注意这里应该是while,自己写的是if,比如i已经来到0,,此时st中是6,因为0小于6,所以现在就把
                TreeNode temp=st.pop(); //6的right设为0, 如果此时数组就完了那就对的,但是现在数组没完,现在到了3,此时st是6,0(0在上面)
                n.left=temp; //发现3比0小,所以把3的left设为0,3push进去,0被pop掉了,此时数组为6,3,但是此时6的right还是上一步设的0,因此这个while完了之后
                                // 程序继续走倒下面,把6的right设成3
            }
            if(!st.isEmpty()) {
                st.peek().right=n;
            }
            st.push(n);//注意还要把这个小的n push进去
        }
        return st.poll();//返回stack的第一个元素

    }

}
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}