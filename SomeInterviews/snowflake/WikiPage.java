package SomeInterviews.snowflake;

import java.util.*;

public class WikiPage {
/*
一个wiki page会链接到其他的wiki page。求从start wiki page跳转到target wiki page的最少点击次数。
额外要求实现一个获得某一wiki page所有链接的simulator
解题思路：bfs；搜索时需要记录当前结点距离start page的距离
 */
    WikiSimulator ws;
    public WikiPage(WikiSimulator ws){
        this.ws=ws;
    }
    public int minDist(String start,String end){
        Queue<String> q=new LinkedList<>();
        q.offer(start);
        int rs=0;
        Set<String> set=new HashSet<>();
        set.add(start);
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                String cur=q.poll();
                if(cur.equals(end)){
                    return rs+1;
                }
                List<String> neighbour=ws.getLinks(cur);
                if(neighbour!=null){
                    for(String nei:neighbour){
                        if(!set.contains(nei)){
                            q.offer(nei);
                            set.add(nei);
                        }
                    }
                }
            }
            rs++;
        }

        return -1;
    }
}

/* 找路径的话，就设个parent map，每次存下一个点的parent，最后按target顺着找parent，再reverse就行了
public List<String> shortestPath(String start, String target) {
    List<String> result = new ArrayList<>();

    if (start == null || target == null) {
        return result;
    }

    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    Map<String, String> parent = new HashMap<>();

    queue.offer(start);
    visited.add(start);
    parent.put(start, null);

    while (!queue.isEmpty()) {
        String cur = queue.poll();

        if (cur.equals(target)) {
            break;
        }

        for (String next : wiki.getLinks(cur)) {
            if (!visited.contains(next)) {
                visited.add(next);
                parent.put(next, cur);
                queue.offer(next);
            }
        }
    }

    if (!parent.containsKey(target)) {
        return result;
    }

    String cur = target;
    while (cur != null) {
        result.add(cur);
        cur = parent.get(cur);
    }

    Collections.reverse(result);
    return result;
}
 */

class WikiSimulator {
    private Map<String, List<String>> graph;

    public WikiSimulator() {
        graph = new HashMap<>();
    }

    public void addPage(String page, List<String> links) {
        graph.put(page, links);
    }

    public List<String> getLinks(String page) {
        return graph.getOrDefault(page, new ArrayList<>());
    }
}
