package tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBST {

    //6/26/2021 先根和level order都可以，写一个先根的,反正就是SerializeandDeserializeBT的解法这里也可以，这里由于是bst，答案就可以甚至不用"N"或井号代表null，没理解
    //https://leetcode.com/problems/serialize-and-deserialize-bst/discuss/93177/what's-the-difference-between-this-and-297
    // https://my.oschina.net/liyurong/blog/1602613
    // lc的答案看不懂
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        if (root==null){
            return "N"+",";//这里逗号容易漏
        }
        sb.append(root.val).append(",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    int index=0;
    public TreeNode deserialize(String data) {
        String[] s=data.split(",");
        TreeNode node=buildTree(s);
        return node;
    }
    TreeNode buildTree(String[] s){
        if (index>=s.length){
            return null;
        }
        if (s[index].equals("N")){
            index++;
            return null;
        }
        TreeNode node=new TreeNode(Integer.valueOf(s[index]));
        index++;
        node.left=buildTree(s);
        node.right=buildTree(s);
        return node;
    }

    //再写个level order
    public String serialize2(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode n=q.poll();
            if (n==null){
                sb.append("N").append(",");
                continue;
            }
            sb.append(n.val).append(",");
            q.offer(n.left);
            q.offer(n.right);
        }
        return sb.toString();
    }
    public TreeNode deserialize2(String data) {
        String[] s=data.split(",");
        if (s[0].equals("N")){
            return null;
        }
        TreeNode root=new TreeNode(Integer.valueOf(s[0]));
        int i=1;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode n=q.poll();
            if (s[i].equals("N")){
                n.left=null;
                i++;
            }else {
                n.left=new TreeNode(Integer.valueOf(s[i++]));
                q.offer(n.left);
            }
            if (s[i].equals("N")){
                n.right=null;
                i++;
            }else {
                n.right=new TreeNode(Integer.valueOf(s[i++]));
                q.offer(n.right);
            }
        }
        return root;

    }
}
