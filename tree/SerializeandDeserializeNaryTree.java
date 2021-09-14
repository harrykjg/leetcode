package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//7/7/2021，自己写的level order，思路就是第一个root value直接写出来，后面用括号阔住他的children，没有子树的话括号就是中间没有东西，level order用q就可以对应出每个
//node的的children
public class SerializeandDeserializeNaryTree {
    public String serialize(Node root) {
        String rs="";
        if (root==null){
            return rs;
        }
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        rs+=root.val;
        while (!q.isEmpty()){
            Node cur=q.poll();
            List<Node> children=cur.children;
            rs+="(";
            for (Node nei:children){
                rs+=nei.val+",";
                q.offer(nei);
            }
            rs+=")";
        }
        return rs;
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length()==0){
            return null;
        }
        int i=0;
        int temp=0;
        while (i<data.length()&&Character.isDigit(data.charAt(i))){
            temp=temp*10+data.charAt(i)-'0';
            i++;
        }
        data=data.substring(i);
        Node root=new Node(temp);
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            Node n=q.poll();
            int index=data.indexOf(')');
            String childrenString=data.substring(1,index);
            n.children=new ArrayList<>();//就算没有children也要建，不然lc不行
            if (childrenString!=null&&childrenString.length()!=0){
                String[] array=childrenString.split(",");
                if (array.length>0){
                    for (String v:array){
                        Node child=new Node(Integer.valueOf(v));
                        n.children.add(child);
                        q.offer(child);
                    }
                }
            }
            data=data.substring(index+1);
        }
        return root;
    }
}
class Node {
    public int val;
    public List<Node> children;
    public Node() {}
    public Node(int _val) {
        val = _val;
    }
    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
