

import java.util.*;

/**
 * Created by 502575560 on 6/26/16.
 */
public class cloneGraph {
    public static void main(String[] args) {
        cloneGraph cg = new cloneGraph();
        UndirectedGraphNode node = new UndirectedGraphNode(0);
        UndirectedGraphNode node2 = new UndirectedGraphNode(1);
        UndirectedGraphNode node3 = new UndirectedGraphNode(2);
        node.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node3);

        // {0,1#1,2#2,2}
        System.out.println(cg.cloneGraph3(node).label);

    }
    //2016年6月25,bfs,写的还不好,还要多用了一个set,后来去掉了.
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null){
            return null;
        }
        HashMap<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
        UndirectedGraphNode copy=new UndirectedGraphNode(node.label);
        map.put(node,copy);
        Queue<UndirectedGraphNode> q=new LinkedList<>();//注意这个q是用来装原来的node的,想成装新的node好像不好想
        q.add(node);

        while(!q.isEmpty()){
            UndirectedGraphNode ori=q.poll();
            UndirectedGraphNode cp=null;
            if(!map.containsKey(ori)){
                map.put(ori,new UndirectedGraphNode(ori.label));
            }
            cp=map.get(ori);
            for(int i=0;i<ori.neighbors.size();i++){
                UndirectedGraphNode nei=ori.neighbors.get(i);
                UndirectedGraphNode cpnei=null;
                if(!map.containsKey(nei)){
                    map.put(nei,new UndirectedGraphNode(nei.label));
                    q.add(nei);
                }
                cpnei=map.get(nei);
                cp.neighbors.add(cpnei);

            }

        }
        return copy;
    }
//这个是dfs,这个dfs自己写的都没信心居然过了
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        HashMap<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
        UndirectedGraphNode copy=new UndirectedGraphNode(node.label);
        map.put(node,copy);
        dfs(copy,node,map);

        return copy;

    }
    public void dfs(UndirectedGraphNode cp,UndirectedGraphNode ori,HashMap<UndirectedGraphNode,UndirectedGraphNode> map){

        for(int i=0;i<ori.neighbors.size();i++){
            UndirectedGraphNode temp=ori.neighbors.get(i);
            if(!map.containsKey(temp)){
                UndirectedGraphNode cpnei=new UndirectedGraphNode(temp.label);
                cp.neighbors.add(cpnei);

                map.put(temp,cpnei);
                dfs(cpnei,temp,map);
            }else{
                cp.neighbors.add(map.get(temp));
            }
        }

    }
//九章第二轮 2／10／2018,好久没写dfs还不太会写,磕磕绊绊的，但是这个dfs就比以前的那个好
    public UndirectedGraphNode cloneGraph3(UndirectedGraphNode node) {
        HashMap<Integer,UndirectedGraphNode> map=new HashMap<>();
        if(node==null){
            return null;
        }
        dfs2(node,map);
        return map.get(node.label);
    }
    void dfs2(UndirectedGraphNode node,HashMap<Integer, UndirectedGraphNode> map){
        if(map.containsKey(node.label)){
            return;
        }
        if(!map.containsKey(node.label)){
            UndirectedGraphNode copy=new UndirectedGraphNode(node.label);
            map.put(node.label,copy);
            for(int i=0;i<node.neighbors.size();i++){
                if(map.containsKey(node.neighbors.get(i).label)){
                    copy.neighbors.add(map.get(node.neighbors.get(i).label));
                }else{
                    dfs2(node.neighbors.get(i),map);
                    copy.neighbors.add(map.get(node.neighbors.get(i).label));

                }
            }
        }
    }
    //九章第二轮2／10／2018,bfs,也没写对,操
    public UndirectedGraphNode cloneGraph4(UndirectedGraphNode node) {
        HashMap<Integer,UndirectedGraphNode> map=new HashMap<>();
        if(node==null){
            return null;
        }
        Queue<UndirectedGraphNode> q=new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()){
            UndirectedGraphNode ori=q.poll();
            UndirectedGraphNode copy=null;
            if(!map.containsKey(ori.label)){
                copy=new UndirectedGraphNode(ori.label);
                map.put(ori.label,copy);

            }else{
                copy=map.get(ori.label);
            }
            for(UndirectedGraphNode nei:ori.neighbors){
                if(map.containsKey(nei.label)){
                    copy.neighbors.add(map.get(nei.label));
                }else{
                    UndirectedGraphNode cpnei=new UndirectedGraphNode(nei.label);
                    map.put(nei.label,cpnei);
                    copy.neighbors.add(cpnei);
                    q.offer(nei);
                }

            }

        }
        return map.get(node.label);
    }
}

class UndirectedGraphNode {
         int label;
         List<UndirectedGraphNode> neighbors;
         UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };