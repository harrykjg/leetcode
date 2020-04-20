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