package GraphAndSearch;

import java.util.*;

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
    //九章第二轮，2／14／2018，不记得拓扑排序到底是要怎样了，了解之后还是不能完全写出来,关键是怎么记录和找到入度为0的点
    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> rs=new ArrayList<>();
        if(graph==null||graph.size()==0){
            return rs;
        }
        HashMap<DirectedGraphNode,Integer> map=new HashMap<>();
        HashSet<DirectedGraphNode> set=new HashSet<>();
        for(int i=0;i<graph.size();i++){
            ArrayList<DirectedGraphNode> nei=graph.get(i).neighbors;
            set.add(graph.get(i));
            for (int j=0;j<nei.size();j++){
                if(!map.containsKey(nei.get(j))){
                    map.put(nei.get(j),1);
                }else{
                    map.put(nei.get(j),map.get(nei.get(j))+1);
                }
            }
        }
        //原来写的便利set的时候又从set里删除，就会遇到ConcurrentModificationException

        LinkedList<DirectedGraphNode> q=new LinkedList<>();//用了q其实就是bfs了
        for(DirectedGraphNode key:graph){//在array里但是没在map里的，说明是入度为0，加入queue同时也加入结果集
            if(!map.containsKey(key)){
                q.offer(key);
                rs.add(key);
            }
        }
        //再遍历q即把入度为0的点的neighbor的入度都减1
        while(!q.isEmpty()){
            DirectedGraphNode temp=q.poll();
            for(DirectedGraphNode n:temp.neighbors){
                map.put(n,map.get(n)-1);
                if(map.get(n)==0){
                    q.offer(n);
                    rs.add(n);
                }
            }
        }

        return rs;
    }
//2／23/2018用dfs，自己写不出，还是参考,开始还用了一个set来记录某个node是否已经加入结果集，结果那样写是错的（为啥？），要检测这个点在不result里，不在的话才去dfs
    public ArrayList<DirectedGraphNode> topSort3(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> rs=new ArrayList<>();
        if(graph.size()==0){
            return rs;
        }
//        HashSet<DirectedGraphNode> set=new HashSet<>();
        for(DirectedGraphNode node:graph){
            if(!inresult(rs,node)){
                dfs(node,rs);
            }
        }
        Collections.reverse(rs);
        return rs;

    }
    public void dfs(DirectedGraphNode node,ArrayList<DirectedGraphNode> rs){
        for(DirectedGraphNode nei:node.neighbors){
            if(!inresult(rs,nei)){
                dfs(nei,rs);
            }
        }
        rs.add(node);
    }
    boolean inresult(ArrayList<DirectedGraphNode> rs,DirectedGraphNode n){
        for(DirectedGraphNode nn:rs){
            if(n==nn){
                return true;
            }
        }
        return false;
    }
}
class DirectedGraphNode {

     int label;
     ArrayList<DirectedGraphNode> neighbors;
     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 };
