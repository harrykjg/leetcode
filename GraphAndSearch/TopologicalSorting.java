package GraphAndSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by 502575560 on 7/13/17.
 */
public class TopologicalSorting {
    //https://segmentfault.com/a/1190000004275641   只写了广度优先
    //http://www.cnblogs.com/lishiblog/p/4187867.html 这个写了dfs没怎么看懂
    //http://blog.csdn.net/zyf19930610/article/details/55804741 也写了dfs
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> res = new ArrayList<DirectedGraphNode>();
        if (graph == null || graph.size() == 0) {
            return res;
        }
        HashMap<DirectedGraphNode,Integer> rudu=new HashMap<>();
        for(int i=0;i<graph.size();i++){//统计入度
            ArrayList<DirectedGraphNode> nei=graph.get(i).neighbors;
            for(int j=0;j<nei.size();j++){
                DirectedGraphNode n=nei.get(j);
                if(!rudu.containsKey(nei.get(j))){
                    rudu.put(n,1);
                }else{
                    rudu.put(n,rudu.get(n)+1);
                }
            }
        }
        LinkedList<DirectedGraphNode> q=new LinkedList<>();
        for(DirectedGraphNode key:graph){//先挑入度为0的放入queue中,同时也加入结果集
            if(!rudu.containsKey(key)){
                q.offer(key);
                res.add(key);
            }
        }
        while (!q.isEmpty()){
            DirectedGraphNode n=q.poll();
            for(DirectedGraphNode nei:n.neighbors){
                rudu.put(nei,rudu.get(nei)-1);
                if (rudu.get(nei)==0){
                    q.add(nei);
                    res.add(nei);
                    rudu.remove(nei);
                }
            }
        }
        return res;
    }
}
class DirectedGraphNode {

     int label;
     ArrayList<DirectedGraphNode> neighbors;
     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 };
