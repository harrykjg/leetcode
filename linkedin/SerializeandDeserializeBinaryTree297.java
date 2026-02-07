package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBinaryTree297 {
    static void main() {

    }
    public String serialize(TreeNode root) {
        if (root==null){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            if(cur!=null){
                sb.append(cur.val);
                sb.append(',');

                q.offer(cur.left);
                q.offer(cur.right);
            }else{
                sb.append('#');
                sb.append(',');
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if(data.length()==0){
            return null;
        }
        String[] s=data.split(",");
        TreeNode root=new TreeNode(Integer.valueOf(s[0]));
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int index=1;
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            if(!s[index].equals("#")){
                cur.left=new TreeNode(Integer.valueOf(s[index]));

                q.offer(cur.left);
            }
            index++;//开始这个index放在if里面就错了，那就是只有非#才++了
            if(!s[index].equals("#")){
                cur.right=new TreeNode(Integer.valueOf(s[index]));

                q.offer(cur.right);
            }
            index++;
        }
        return root;
    }
}
