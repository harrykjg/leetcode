package SomeInterviews.snowflake;

import java.util.*;

public class PreorderTravesalWithInvalidNode {
    /*
    给一堆edges表示tree，可以mark某些nodes失效，求不包括失效node的pre-order遍历。
     */
    //那就按自己的理解就是invalid就略过，但是孩子还是要便利的，那就是正常的travasal只是加入结果集时检查一下是否valid就行了。
    //如果是输入是edges的话也就是建图再同样的用stack遍历也是一样的
    public List<Integer> travasal(Node root, List<Integer> invalidList){
        Set<Integer> set=new HashSet<>(invalidList);
        Stack<Node> st=new Stack<>();
        st.push(root);
        List<Integer> rs=new ArrayList<>();
        while (!st.isEmpty()){
            Node cur=st.pop();
            if(!set.contains(cur.value)){
                rs.add(cur.value);
            }
            if(cur.right!=null){
                st.push(cur.right);
            }
            if(cur.left!=null){
                st.push(cur.left);
            }
        }
        return rs;

    }
}

class Node{
    int value;
    Node left;
    Node right;
}
