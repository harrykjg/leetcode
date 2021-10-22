package SomeInterviews.doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TreeNodeChange {
    public static void main(String[] args){
        TreeNode old=new TreeNode("a",1,true);
        TreeNode o1=new TreeNode("b",2,true);
        TreeNode o2=new TreeNode("c",3,true);
        TreeNode o3=new TreeNode("k",1,true);
        old.children.addAll(Arrays.asList(o1,o2,o3));
        TreeNode o4=new TreeNode("d",4,true);
        TreeNode o5=new TreeNode("e",5,true);
        o1.children.addAll(Arrays.asList(o4,o5));
        TreeNode o6=new TreeNode("g",7,true);
        o2.children.add(o6);
        TreeNode o7=new TreeNode("j",2,true);
        o3.children.add(o7);

        TreeNode root=new TreeNode("a",1,true);
        TreeNode r1=new TreeNode("b",2,true);
        TreeNode r2=new TreeNode("c",3,true);
        root.children.addAll(Arrays.asList(r2,r1));
        TreeNode r4=new TreeNode("d",4,true);
        TreeNode r5=new TreeNode("e",5,true);
        TreeNode r6=new TreeNode("f",6,true);
        r1.children.addAll(Arrays.asList(r4,r5,r6));
        TreeNode r7=new TreeNode("g",7,false);
        r2.children.add(r7);
        TreeNode o8=new TreeNode("k",2,true);
        r6.children.add(o8);

        TreeNodeChange tc=new TreeNodeChange();
        System.out.println(tc.change2(old,root));

    }
    /*
    Existing tree
            a(1, T)
      /         \      \
    b(2, T)   c(3, T)    k(1,T)
  /       \           \        \
d(4, T) e(5, T)      g(7, T)    j(2,T)

            New tree
            a(1, T)
          /        \
   b(2, T)         c(3, T)
   /    |    \           \
d(4, T) e(5, T) f(6, T)    g(7, F)
                  \
                  k(2,T)



     */
    //https://www.1point3acres.com/bbs/thread-789286-1-1.html
    //https://leetcode.com/discuss/interview-question/1367130/Doordash-Phone-Interview
    //https://leetcode.com/discuss/interview-question/1265810/Doordash-PhoneScreen
    int rs=0;//就是递归判断，先判断root是否有改变，然后看他们所有的孩子是否各自存在于对方的children里，递归调用，不同就rs++。
    public int change(TreeNode old,TreeNode root){
        if (root==null){//其中一个是空的话，另一个所有的子树都是新的
            allChildren(old);
            return rs;
        }
        if (old==null){
            allChildren(root);
            return rs;
        }
        if (old.key.equals(root.key)){
            if (old.value!=root.value||old.active!=root.active){
                rs++;
            }
        }else {//root的key都不同的话，那么就是所有删除的加所有新的
            allChildren(root);
            allChildren(old);
            return rs;
        }
        HashMap<String, TreeNode> children1=new HashMap<>();
        HashMap<String, TreeNode> children2=new HashMap<>();
        for (int i=0;i<old.children.size();i++){
            children1.put(old.children.get(i).key,old.children.get(i));
        }
        for (int i=0;i<root.children.size();i++){
            children2.put(root.children.get(i).key,root.children.get(i));
        }
        for (String n1:children1.keySet()){
            if (!children2.containsKey(n1)){//新的里没有旧的，则整个旧的子树都算上
                allChildren(children1.get(n1));
            }else {//有相同的key
                change(children1.get(n1),children2.get(n1));
            }
        }

        for (String n2:children2.keySet()){
            if (!children1.containsKey(n2)){//旧的里没有新的，则整个新的子树都算上
                allChildren(children2.get(n2));
            }
        }
        return rs;
    }

    void allChildren(TreeNode node){
        if (node==null){
            return;
        }
        rs+=1;
        for (TreeNode n:node.children){
            allChildren(n);
        }
    }
    //follow up是打印出删改的node


    int rs2;
    public int change2(TreeNode old,TreeNode root){
        if(old==null){
            allChildren2(root);
            return rs2;
        }
        if(root==null){
            allChildren2(old);
            return rs2;
        }
        if (old.key!=root.key){
            allChildren2(root);
            allChildren2(old);
            return rs2;
        }
        if (old.value!=root.value){
            rs2++;
        }else if (old.active!=root.active){
            rs2++;
        }
        HashMap<String, TreeNode> ch1=new HashMap<>();
        HashMap<String, TreeNode> ch2=new HashMap<>();
        for (int i=0;i<old.children.size();i++){
            ch1.put(old.children.get(i).key,old.children.get(i));
        }
        for (int i=0;i<root.children.size();i++){
            ch2.put(root.children.get(i).key,root.children.get(i));
        }
        for (TreeNode n:ch1.values()){
            if (ch2.containsKey(n.key)){
                change2(n,ch2.get(n.key));
            }else{
                allChildren2(n);
            }
        }
        for (TreeNode n:ch2.values()){
            if (!ch1.containsKey(n.key)){
                allChildren2(n);
            }
        }
        return rs2;
    }
    void allChildren2(TreeNode n){
        if (n!=null){
            rs2++;
        }else{
            return;
        }
        for(TreeNode c:n.children){
            allChildren2(c);
        }
    }

    static class TreeNode {
        String key;
        Integer value;
        List<TreeNode> children;
        boolean active;
        public TreeNode(String key, Integer value,boolean active) {
            this.key = key;
            this.value = value;
            this.children = new ArrayList<>();
            this.active=active;
        }
    }
}
