package contest;

import java.util.HashMap;
import java.util.Map;

public class KthAncestorofaTreeNode {
    int[] parent;
    int n;
    Map<Integer, Map<Integer,Integer>> map=new HashMap<>();//加了map去记录还是超时
    public KthAncestorofaTreeNode(int n, int[] parent) {
        this.parent=parent;
        this.n=n;
    }

    public int getKthAncestor(int node, int k) {
        if(map.containsKey(node)){
            if(map.get(node).containsKey(k)){
                return map.get(node).get(k);
            }
        }
        int rs=-1;
        int level=0;
        int copynode=node;
        while(level<k){
            rs=parent[copynode];
            level++;
            copynode=rs;
        }
        if(!map.containsKey(node)){
            map.put(node,new HashMap<Integer, Integer>());
        }
        map.get(node).put(k,rs);
        return rs;
    }
}
