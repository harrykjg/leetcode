package DataStruct;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 8/14/18.
 */
public class SerializeandDeserializeBinaryTree {
    public static void main(String[] args){
        SerializeandDeserializeBinaryTree sd=new SerializeandDeserializeBinaryTree();
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.right.left=new TreeNode(4);
        root.right.right=new TreeNode(5);
        System.out.println(sd.serialize(root));
    }
//自己想的就是层次遍历，比较不好的解法
    //https://blog.csdn.net/yeruby/article/details/49885267  他比较了先序和层次遍历的serilize的结果，代码不要看他的
    //https://www.jianshu.com/p/e099d10bc0db 先序的deserilize不是太好想
    public String serialize(TreeNode root) {
        if(root==null){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int count=1;
        int count2=0;
        int count3=0;
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            count--;
            if(cur!=null){
                count3++;
                sb.append(cur.val);
                sb.append(",");
                q.offer(cur.left);
                q.offer(cur.right);
            }else{
                sb.append("#");//无论这个点是否为空，都把左右写进去，这样是为了最后出来的树是个满二叉树，可以用i*2+1和i*2+2得到左右节点，但是带来了一个问题，怎么直到啥时候才不offer节点呢，就是到了这个
                sb.append(",");  //二叉树的最后一层。那怎么直到是到了最后一层呢？应该可以再写个方法先判断层数，但是我这里自饿的是用3个count，count1和count2记录每一层有几个节点，count3去记录这一层中非空节点
                q.offer(null);   //的个数，如果某一层一层遍历完了之后发现count3是0的，即这一层所有都是空节点了，所以可以停止了
                q.offer(null);
            }
            count2+=2;
            if(count==0){
                count=count2;
                count2=0;
                if(count3==0){
                    break;
                }else{
                    count3=0;
                }
            }
        }
        return sb.toString().substring(0,sb.length()-1);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==null||data.length()==0){
            return null;
        }
        String[] d=data.split(",");
        return helper(d,0);
    }
    TreeNode helper(String[] data,int index){
        if(index>=data.length){
            return null;
        }
        if(data[index].equals("#")){
            return null;
        }
        TreeNode left=helper(data, 2*index+1);
        TreeNode right=helper(data,2*index+2);

        TreeNode root=new TreeNode(Integer.valueOf(data[index]));
        root.left=left;
        root.right=right;
        return root;

    }

    //以前的代码，这样写还不错,记这个，是层次遍历
    public String serialize2(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        q.offer(root);
        StringBuilder sb=new StringBuilder();
        while(!q.isEmpty()){
            TreeNode temp=q.poll();
            if(temp==null){
                sb.append("null"+",");
                continue;
            }
            sb.append(temp.val+",");
            q.add(temp.left);
            q.add(temp.right);
        }
        String rs=sb.toString();
        rs=rs.substring(0,rs.length()-1);
        return rs;

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] s=data.split(",");
        int i=1;
        Queue<TreeNode> q=new LinkedList<>();
        if(s[0].equals("null")){
            return null;
        }
        TreeNode root=new TreeNode(Integer.valueOf(s[0]));
        q.offer(root);
        while(i<s.length&&!q.isEmpty()){
            TreeNode cur=q.poll();
            if(!s[i].equals("null")){
                cur.left=new TreeNode((Integer.valueOf(s[i])));
                q.add(cur.left);
            }
            i++;
            if(!s[i].equals("null")){
                cur.right=new TreeNode((Integer.valueOf(s[i])));
                q.add(cur.right);
            }
            i++;

        }
        return root;

    }

    //9/16/2018,半背
    public String serialize3(TreeNode root) {
        String rs="";
        if(root==null){
            return rs;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            if(cur==null){
                rs+="#"+",";
                continue;
            }
            rs+=cur.val+",";
            q.offer(cur.left);
            q.offer(cur.right);
        }
        return rs.substring(0,rs.length()-1);
    }
    //没记清楚，是要用queue
    public TreeNode deserialize3(String data) {
        if(data.length()==0){
            return null;
        }

        String[] s=data.split(",");
        if(s[0].equals("#")){
            return null;
        }
        TreeNode root=new TreeNode(Integer.parseInt(s[0]));

        int index=1;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            if(index<s.length&&!s[index].equals("#")){
                cur.left=new TreeNode(Integer.parseInt(s[index]));
                q.offer(cur.left);
            }
            index++;
            if(index<s.length&&!s[index].equals("#")){
                cur.right=new TreeNode(Integer.parseInt(s[index]));
                q.offer(cur.right);
            }
            index++;
        }
        return root;
    }
}
