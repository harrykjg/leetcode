

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 502575560 on 6/21/16.
 */
public class Codec {

    //http://blog.csdn.net/ljiabin/article/details/49474445   serilize和他一样
    //http://blog.csdn.net/pointbreak1/article/details/49504133   deserilize和他一样
    //https://leetcode.com/discuss/66117/easy-to-understand-java-solution  preorder的遍历
    public static void main(String[] args){
        DataStruct.tree.TreeNode r=new DataStruct.tree.TreeNode(1);
        r.left=new DataStruct.tree.TreeNode(2);
        r.right=new DataStruct.tree.TreeNode(3);
        r.right.left=new DataStruct.tree.TreeNode(4);
        r.right.right=new DataStruct.tree.TreeNode(5);
        System.out.print(serialize(r));
        deserialize(serialize(r));
    }

    // Encodes a tree to a single string.
    public static String serialize(DataStruct.tree.TreeNode root) {
        Queue<DataStruct.tree.TreeNode> q=new LinkedList<DataStruct.tree.TreeNode>();
        q.offer(root);
        StringBuilder sb=new StringBuilder();
        while(!q.isEmpty()){
            DataStruct.tree.TreeNode temp=q.poll();
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
    public static DataStruct.tree.TreeNode deserialize(String data) {
        String[] s=data.split(",");
        if(s.length==0){
            return null;
        }
        int i=1;
        Queue<DataStruct.tree.TreeNode> q=new LinkedList<>();
        if(s[0].equals("null")){
            return null;
        }
        DataStruct.tree.TreeNode root=new DataStruct.tree.TreeNode(Integer.valueOf(s[0]));
        q.offer(root);
        while(i<s.length&&!q.isEmpty()){
            DataStruct.tree.TreeNode cur=q.poll();
            if(!s[i].equals("null")){
                cur.left=new DataStruct.tree.TreeNode((Integer.valueOf(s[i])));
                q.add(cur.left);
            }
            i++;
            if(!s[i].equals("null")){
                cur.right=new DataStruct.tree.TreeNode((Integer.valueOf(s[i])));
                q.add(cur.right);
            }
            i++;

        }
        return root;

    }
}


  class TreeNode {
      int val;
      DataStruct.tree.TreeNode left;
      DataStruct.tree.TreeNode right;
      TreeNode(int x) { val = x; }
  }
