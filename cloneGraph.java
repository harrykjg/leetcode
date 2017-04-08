

import java.util.*;

/**
 * Created by 502575560 on 6/26/16.
 */
public class cloneGraph {
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

}

class UndirectedGraphNode {
         int label;
         List<UndirectedGraphNode> neighbors;
         UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    };