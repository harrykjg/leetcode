package linkedin;

import java.util.*;

public class GraphValidTree261 {
    //其实不用单独写union和isconnect，直接再代码里写union和判断是否已经connect就行,为啥用union find不需要最后检查遇到了几个node而dfs需要？
    //因为union find检查了边数要等于n-1并且没有重复的edges，然后遍历检查有没环就行了
    public boolean validTree(int n, int[][] edges) {
        int[] ids=new int[n];
        if(n!=edges.length+1){
            return false;
        }
        for (int i=0;i<n;i++){
            ids[i]=i;
        }
        for (int i=0;i<edges.length;i++){
            int a=edges[i][0];
            int b=edges[i][1];
            if(!isConnect(ids,a,b)){
                union(ids,a,b);
            }else {
                return false;
            }
        }
        return true;
    }
    int find(int[] ids,int id){
        if(ids[id]==id){
            return id;
        }
        ids[id]=find(ids,ids[id]);
        return ids[id];
    }
    void union(int[] ids,int a,int b){
        int ra=find(ids,a);
        int rb=find(ids,b);
        if(ra!=rb){
           ids[rb]=ra;
        }
    }
    boolean isConnect(int[] ids,int a,int b){
        int ra=find(ids,a);
        int rb=find(ids,b);
        return ra==rb;
    }
    public boolean validTree2(int n, int[][] edges) {
        if(edges.length!=n-1){
            return false;
        }
        Map<Integer, List<Integer>> map=new HashMap<>();
        for (int i=0;i<edges.length;i++){
            if(!map.containsKey(edges[i][0])){
                map.put(edges[i][0],new ArrayList<>());
            }
            map.get(edges[i][0]).add(edges[i][1]);
            if(!map.containsKey(edges[i][1])){
                map.put(edges[i][1],new ArrayList<>());
            }
            map.get(edges[i][1]).add(edges[i][0]);
        }
        Set<Integer> set=new HashSet<>() ;
        return dfs(0,-1,map,set)&&set.size()==n;//还真的必须检测是否所有点都见到，否则比如如n=4，edge[[2,3],[1,2],[1,3]]
        //他是检测不出有环的，因为他只看0，发现没邻居就返回true了，如果从1或2或3开始就能检测出环，
    }
    boolean dfs(int i,int parent,Map<Integer,List<Integer>> map,Set<Integer> memo){
        memo.add(i);
        List<Integer> neighbour=map.get(i);
        if(neighbour!=null){//这个是需要的，如n=4，edge[[2,3],[1,2],[1,3]]，则一进来0没有neighbour，因此要检测
            for (int nei:neighbour){
                if(nei==parent){
                    continue;
                }
                if(!memo.contains(nei)){
                    if(!dfs(nei,i,map,memo)){
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }//不能else返回false，如n=1，edge为空，则是valid的tree

        return true;
    }
}
