package 灵神.链表二叉树回溯.树.LCA;

import java.util.*;

public class StepByStepDirectionsFromaBinaryTreeNodetoAnother2096 {
    static void main() {

    }
    //12/24/2025，看提示才会的。自己写的内存超了，看别人说是路径需要用用一个来维护，不能每次新建string
    public String getDirections2(TreeNode root, int startValue, int destValue) {
        if(root==null){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        String one=find(sb,root,startValue);
        String two=find(sb,root,destValue);
        //删除common prefix（处理 start和end在同一条路径上的情况）
        int i=0;
        for(;i<one.length()&&i<two.length();i++){
            if (one.charAt(i)!=two.charAt(i)){
                break;
            }
        }
        one=one.substring(i);
        two=two.substring(i);
        //把start这条path换成U
        StringBuilder sb2=new StringBuilder();
        for (i=0;i<one.length();i++){
            sb2.append('U');
        }
        return sb2.toString()+two;
    }

    String find(StringBuilder cur,TreeNode root, int val){
        if(root==null){
            return "";
        }
        if(root.val==val){
            return cur.toString();
        }
        cur.append('L');
        String one=find(cur,root.left,val);
        cur.setCharAt(cur.length()-1,'R');
        String two=find(cur,root.right,val);
        cur.deleteCharAt(cur.length()-1);//恢复这里容易漏，而且不太好理解。 上面dfs返回了string了，就和这个cur没关系了，
                                             //下面返回的one two都是dfs返回的新的string了，但这个cur是整个call chain中唯一的，
                                            //你不恢复的话，这个find方法返回到上一层的时候这个cur就多带了一个‘R'
        if(one.length()==0&&two.length()==0){
            return "";
        }else if(one.length()>0){
            return one;
        }else{
            return two;
        }

    }

    //3/17/2026,自己想的，超时了，但是比上面的方法好理解,无非就是把树建图，从上到下遍历树的时候就知道root和叶子的关系是什么，因此就是用一个pair
    //对象就可以表示邻居和path，再bfs遍历，而bfs的时候还要记录路径，那就是bfs的queue里也要存一个自己建的对象，包含邻居和现有的path
    public String getDirections(TreeNode root, int startValue, int destValue) {
        Map<Integer, List<Pair>> map=new HashMap<>();//这里开始用的key是treenode，但是由于starvalue是int，所以换成int，如果start是node就用node可以
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            map.putIfAbsent(cur.val,new ArrayList<>());
            TreeNode left=cur.left;
            TreeNode right=cur.right;
            if(left!=null){
                map.get(cur.val).add(new Pair(left.val,"L"));
                map.putIfAbsent(left.val,new ArrayList<>());
                map.get(left.val).add(new Pair(cur.val,"U"));
                q.offer(left);
            }
            if(right!=null){
                map.get(cur.val).add(new Pair(right.val,"R"));
                map.putIfAbsent(right.val,new ArrayList<>());
                map.get(right.val).add(new Pair(cur.val,"U"));
                q.offer(right);
            }
        }
        Set<Integer> set=new HashSet<>();//开始忘了加visited，还是得去重
        set.add(startValue);
        Queue<Path> q2=new LinkedList<>();
        q2.offer(new Path("",startValue));
        while (!q2.isEmpty()){
            Path cur=q2.poll();
            List<Pair> neighbour=map.get(cur.value);
            if(neighbour!=null){
                for(Pair p:neighbour){
                    String path=cur.path+p.path;
                    if(p.value ==destValue){
                        return path;
                    }
                    if(!set.contains(p.value)){
                        q2.offer(new Path(path,p.value));
                        set.add(p.value);
                    }

                }
            }
        }
        return "";
    }
    class Pair{
        Integer value;
        String path;
        public Pair(Integer value,String path){
             this.value=value;
             this.path=path;
        }
    }
    class Path{
        String path;
        int value;
        public Path(String path,int value){
            this.path=path;
            this.value=value;
        }
    }
}
