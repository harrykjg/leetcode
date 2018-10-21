package google;

import java.util.*;

/**
 * Created by yufengzhu on 10/13/18.
 */
public class shortestPath {
    public static void main(String[] a){
        shortestPath sp=new shortestPath();
        HashMap<String,List<String>> map=new HashMap<>();
        ArrayList<String> al=new ArrayList<>();
        al.add("b");
//        al.add("c");
//        al.add("d");
        map.put("a",al);
        ArrayList<String> al2=new ArrayList<>();
        al2.add("a");
        al2.add("c");
//        al.add("d");
        map.put("b",al2);
        ArrayList<String> al3=new ArrayList<>();
//        al.add("a");
        al3.add("b");
        al3.add("d");
        map.put("c",al3);
        ArrayList<String> al4=new ArrayList<>();
//        al.add("a");
//        al.add("b");
        al4.add("c");
        map.put("d",al4);
        ArrayList<String> target=new ArrayList<>();
        target.add("a");
//        target.add("c");
        sp.findPath(map,target);

    }
    /*
    白人小哥没多废话，上来给了个用dictionary存undirected graph， 给了一个存target_nodes的list，让返回一个存其他所有点到target_nodes最短距离的dictionary。
例子： 有ABCD四个点的complete graph，target_nodes是['A', 'C'],返回应该是{'A':0, 'B':1, 'C':0, 'D':1}，顺序无所谓
complete path即每个点都连到所有其他的点
     */
    public Map<String,Integer> findPath(Map<String,List<String>> graph,List<String> target){
        Queue<String> q=new LinkedList<>();
        Set<String> memo=new HashSet<>();
        HashMap<String,Integer> rs=new HashMap<>();
        for(String s:target){
            memo.add(s);
            q.add(s);
            rs.put(s,0);
        }
        int step=0;
        int count1=q.size();
        int count2=0;
        while (!q.isEmpty()){
            String cur=q.poll();
            count1--;
            for(String nei:graph.get(cur)){
                if(memo.add(nei)){
                    q.offer(nei);
                    rs.put(nei,step+1);
                    count2++;
                }
            }
            if(count1==0){
                step++;
                count1=count2;
                count2=0;
            }
        }
        return rs;
    }
}
