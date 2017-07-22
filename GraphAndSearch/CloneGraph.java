package GraphAndSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 502575560 on 7/13/17.
 */
public class CloneGraph {
//这次写想的是和最近写的copylinkedlistwithrandomnode那样的,用的是bfs,就是在q.add那里要记得在map中不存在的才add进去
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null){
            return null;
        }
        HashMap<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
        LinkedList<UndirectedGraphNode> q=new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()){
            UndirectedGraphNode n=q.poll();
            UndirectedGraphNode copy;
            if(!map.containsKey(n)){
                copy=new UndirectedGraphNode(n.label);
                map.put(n,copy);
            }else{
                copy=map.get(n);
            }
            for(int i=0;i<n.neighbors.size();i++){

                UndirectedGraphNode nb=n.neighbors.get(i);

                if(!map.containsKey(nb)){
                    UndirectedGraphNode copynb=new UndirectedGraphNode(nb.label);
                    copy.neighbors.add(copynb);
                    map.put(nb,copynb);
                    q.add(nb);//这里开始写在if外面了那就死循环了
                }else{
                    copy.neighbors.add(map.get(nb));
                }
            }

        }
        return map.get(node);
    }
}
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};
