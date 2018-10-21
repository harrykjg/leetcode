package UnionFind;

import java.util.HashMap;

/**
 * Created by yufengzhu on 10/9/18.
 */
public class RedundantConnection {
    public static void main(String[]a ){
        RedundantConnection rc=new RedundantConnection();
        int[][] edges=new int[][]{{1,2},{1,3},{2,3}};
        rc.findRedundantConnection(edges);
    }

    //写得不好，要再练
    public int[] findRedundantConnection(int[][] edges) {
        HashMap<Integer,Integer> ids=new HashMap<>();
        int[] rs=new int[2];
        for(int[] edge:edges){
            int a=edge[0];
            int b=edge[1];
            if(!ids.containsKey(a)){
                ids.put(a,a);
            }
            if(!ids.containsKey(b)){
                ids.put(b,b);
            }
            int ida=find(ids,a);
            int idb=find(ids,b);
            if(idb==ida){//有点神奇，只要再遇到2个parent相等的node就说明这个是多余的
                rs[0]=a;
                rs[1]=b;
            }else{
                union(ids,a,b);
            }
        }
        return rs;
    }
    void union(HashMap<Integer,Integer> map,int a,int b){
        int ida=find(map,a);
        int idb=find(map,b);
        if(ida!=idb){
            map.put(idb,ida);
        }
    }
    int find(HashMap<Integer,Integer> map,int a){
        if(map.get(a)==a){
            return a;
        }
        int ap=find(map,map.get(a));
        map.put(a,ap);
        return ap;
    }
}
