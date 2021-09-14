package GraphAndSearch;

import java.util.*;

public class ReconstructItinerary {
    //7/5/2021，写不出
    //居然就是dfs就行,必须从jfk开始，而且ticket会有重复的ticket，比如a飞到b两次.难点在于如何去重，
    //https://leetcode.com/problems/reconstruct-itinerary/discuss/78799/Very-Straightforward-DFS-Solution-with-Detailed-Explanations
    //https://leetcode.com/problems/reconstruct-itinerary/discuss/78766/Share-my-solution 代码简单，但不好想
    //这个基本上是暴力写法跟答案1差不多，但也不太好写
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String,boolean[]> memo=new HashMap<>();
        List<String> rs=new ArrayList<>();
        Map<String,List<String>> map=new TreeMap<>();//开始用map里面装priprityqueue，后面取出来用pq。toArray，这样取出来应该不是排好序的，只是头部排序了！
        for (List<String> ticket:tickets){
            if (!map.containsKey(ticket.get(0))){
                List<String> list=new ArrayList<>();
                list.add(ticket.get(1));
                map.put(ticket.get(0),list);
            }else{
                map.get(ticket.get(0)).add(ticket.get(1));
            }
        }
        for (Map.Entry<String,List<String>> entry:map.entrySet()){
            Collections.sort(entry.getValue());//
            memo.put(entry.getKey(),new boolean[entry.getValue().size()]);//这里就是把每个点的所有neibour裂成一个memo数组，居然有这种操作
        }
        //题目说了必须从jfk开始。
        String start="JFK";
        rs.add(start);

        if (dfs(start,memo,rs,map,tickets.size())){
            return rs;
        }

        return rs;

    }

    boolean dfs(String start,Map<String,boolean[]> memo,List<String> rs,Map<String,List<String>> map,int num){
        if (rs.size()-1==num){//看rs长度-1是否等于tickets的数量，说明走完了全程.因为rs。size个目的地中间夹着size-1个路线
            return true;
        }
        if (!map.containsKey(start)){
            return false;
        }
        List<String> nei=map.get(start);
        for (int i=0;i<nei.size();i++){
            if (memo.get(start)[i]){
                continue;
            }
            memo.get(start)[i]=true;
            rs.add(nei.get(i));
            if (rs.size()-1==num){
                return true;
            }
            if (map.containsKey(nei.get(i))) {
                if (dfs(nei.get(i), memo, rs, map, num)) {
                    return true;
                }
            }
            rs.remove(rs.size() - 1);
            memo.get(start)[i]=false;

        }
        return false;
    }
}
