package GraphAndSearch;

import java.util.*;

public class cloneGraphNew {

    //04/12/2020，写的不太好
    public Node cloneGraph(Node node) {
        if(node==null){
            return node;
        }
        HashMap<Integer, Node> map=new HashMap<>();

        dfs(node,map);
        return map.get(node.val);

    }
    Node dfs(Node node, Map<Integer,Node> map){

        if(map.containsKey(node.val)){
             return map.get(node.val);
        }
        Node copy=new Node(node.val);
        map.put(node.val,copy);
        for(Node nei:node.neighbors){
            Node cpNei=dfs(nei,map);
            copy.neighbors.add(cpNei);
        }
//        map.put(node.val,copy);//开始想的把这个copy里的neighbor全都copy完之后才把copy放到map里，结果就回memory leak，放到前面就对了！
        //举个例子自己看看
        return copy;
    }
    //bfs
    public Node cloneGraph2(Node node) {
        if(node==null){
            return node;
        }
        HashMap<Integer, Node> map=new HashMap<>();
        Queue<Node> q=new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()){
            Node cur=q.poll();
            Node copy=null;
            if (!map.containsKey(cur.val)){
                copy=new Node(cur.val);
                map.put(cur.val,copy);
            }
            copy=map.get(cur.val);
            for(Node nei:cur.neighbors){
                if(!map.containsKey(nei.val)){
                    Node cpnei=new Node(nei.val);
                    map.put(nei.val,cpnei);
                    q.offer(nei);
                }
                copy.neighbors.add(map.get(nei.val));

            }
        }
        return map.get(node.val);
    }
//05/29/2020
    public Node cloneGraph3(Node node) {
        if(node==null){
            return node;
        }
        HashMap<Node,Node> map=new HashMap<>();
        Queue<Node> q=new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()){
            Node cur=q.poll();
            if(!map.containsKey(cur)){
                Node copy=new Node(cur.val);
                map.put(cur,copy);
            }
            List<Node> neiList=cur.neighbors;
            for(Node nei:neiList){
                if(map.containsKey(nei)){
                    map.get(cur).neighbors.add(map.get(nei));
                }else{
                    Node copyNei=new Node(nei.val);
                    map.put(nei,copyNei);
                    map.get(cur).neighbors.add(map.get(nei));
                    q.offer(nei);//这里开始还想要另一个set去重，结果放到这就行了
                }
            }
        }
        return map.get(node);
    }

    //6/11/2021,写的不太好
    public Node cloneGraph4(Node node) {
        if(node==null){
            return node;
        }
        Map<Node,Node> map=new HashMap<>();
        Queue<Node> queue=new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            Node copy=null;
            if(map.containsKey(cur)){
                copy=map.get(cur);
            }else{
                copy=new Node(cur.val);
                map.put(cur,copy);
            }
            for(Node nei:cur.neighbors){
                Node neiCopy=null;
                if(map.containsKey(nei)){
                    neiCopy=map.get(nei);
                }else{
                    neiCopy=new Node(nei.val);
                    map.put(nei,neiCopy);
                    queue.offer(nei);//基本的bfs去重忘了，只要每个node走一次那么就不用担心node的neibour会加到重复的。map正好作为去重用
                }
                copy.neighbors.add(neiCopy);
            }
        }
        return map.get(node);
    }
//8/22/2021 多写了个set，其实用看以前的用map判断就好了
    public Node cloneGraph5(Node node) {
        if(node==null){
            return null;
        }
        Map<Node ,Node> map=new HashMap<>();
        Queue<Node> q=new LinkedList<>();
        q.offer(node);
        Set<Node> set=new HashSet<>();
        set.add(node);
        while(!q.isEmpty()){
            Node cur=q.poll();
            Node cp=null;
            if(!map.containsKey(cur)){
                cp=new Node(cur.val);
                map.put(cur,cp);
            }
            cp=map.get(cur);
            for(Node nei:cur.neighbors){
                if(!map.containsKey(nei)){
                    Node cpNei=new Node(nei.val);
                    map.put(nei,cpNei);
                    cp.neighbors.add(cpNei);
                }else{
                    cp.neighbors.add(map.get(nei));
                }
                if(!set.contains(nei)){
                    q.offer(nei);
                    set.add(nei);
                }
            }
        }
        return map.get(node);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}