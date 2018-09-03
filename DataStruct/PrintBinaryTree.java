package DataStruct;

import java.util.*;

/**
 * Created by yufengzhu on 8/28/18.
 */
public class PrintBinaryTree {
    //自己想的，改了几次，方法不是那么elegant，就是先得出高度，然后得出每一行应该要2的height次方-1个string，于是先设好height行string，每行有2的height次方-1个空string，然后层次遍历，画个图理解，
    //每个node（除了根结点之外）自己的位置是由他父节点所在的那一半区间的左半或者右半部分决定的，所以说pull出来的这个节点要决定下一行他的左右子树的位置，而这个pull出来的节点的卫视是由他父节点决定的
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        int height=getHeight(root);
        int len=(int)Math.pow(2,height)-1;
        for(int i=0;i<height;i++){
            String[] a=new String[len];
            Arrays.fill(a,"");
            rs.add(Arrays.asList(a));
        }
        HashMap<TreeNode,int[]> map=new HashMap<>();//这个map存的是这个节点所在的左端点和右端点的位置，
        map.put(root,new int[]{0,len-1});//根结点他所在的位置就是左和右的中间
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        int step=0;
        int count1=1;
        int count2=0;

        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            int b=map.get(cur)[0];
            int e=map.get(cur)[1];
            count1--;
            int mid=b+(e-b)/2;
            if(step==height-1){
                break;
            }
            List<String> al=rs.get(step);
            al.set(mid,String.valueOf(cur.val));

            if(cur.left!=null){//cur节点决定得失下一行的位置，所以其实一上来就是处理第二行的，第一行的再最后再设回中间就行了
                helper(b,mid-1,al,cur.left);
                q.offer(cur.left);
                map.put(cur.left,new int[]{b,mid-1});
                count2++;
            }
            if(cur.right!=null){
                helper(mid+1,e,al,cur.right);
                q.offer(cur.right);
                map.put(cur.right,new int[]{mid+1,e});
                count2++;
            }

            if(count1==0){
                count1=count2;
                count2=0;
                step++;
            }


        }

        rs.get(0).set(len/2,String.valueOf(root.val));
        return rs;
    }
    int getHeight(TreeNode root){
        if(root==null){
            return 0;
        }
        int rs=1;
        int lh=getHeight(root.left);
        int rh=getHeight(root.right);
        return Math.max(rs+lh,rs+rh);
    }
    void helper(int begin,int end,List<String> al,TreeNode node){
        if(begin==end){
            al.set(begin,String.valueOf(node.val));
        }else{
            int mid=begin+(end-begin)/2;
            al.set(mid,String.valueOf(node.val));
        }
    }
}
