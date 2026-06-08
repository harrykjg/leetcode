package SomeInterviews.snowflake;

import java.util.*;

public class Topology {
    /*
    you're given a list where the ith index is the privileges that role i has,
    and a list of grants, where the second role inherits the privileges of the first (basically edges).
     You have to calculate the privileges each role has after inheriting from their ancestors.
     I asked a clarifying question for coding, confirmed it would be a DAG, no cycles.
     the input was like [['A'],['B'],['C']] for privileges, then grants was like [[0, 1], [1, 2], [2,3]].
     So output would be [['A'], ['A', 'B'], ['A','B','C']]
     */
    //意思是0有权限a，1有权限b，2有权限c，然后1继承了0，2继承了1，2继承了1得到结果[['A'], ['A', 'B'], ['A','B','C']]
    //一开始还看不出是拓扑排序，感觉就是建图然后硬算每一条路径？其实想想，你必须从parent开始向孩子走，这样child才能获得parent的权限，
    //那么如果1继承多个人的权限这样行吗，应该也行，拓扑排序的话就是从入度为0的开始，比如遇到0的入度为0，遇到[0,1]的时候把0的权限给了1,
    //假如又遇到x,[x,1]则继续把x的权限给1，没毛病
    public List<List<String>> resolvePrivileges(int n, List<List<Integer>> grants, List<List<String>> allowedList, List<List<String>> disallowedList){
        List<List<String>> rs=new ArrayList<>();
        Map<Integer, Set<String>> privs=new HashMap<>();
        Map<Integer,Integer> degree=new HashMap<>();
        Queue<Integer> q=new LinkedList<>();
        Map<Integer,Set<Integer>> neighbour=new HashMap<>();
        for (int i=0;i<grants.size();i++){
            int parent=grants.get(i).get(0);
            int child=grants.get(i).get(1);
            degree.put(child,degree.getOrDefault(child,0)+1);
            neighbour.putIfAbsent(parent,new HashSet<>());
            neighbour.get(parent).add(child);
        }
        for (int i=0;i<allowedList.size();i++){//开始是遍历grant的，那不对，因为有些int可能不出现grant里
            if(!degree.containsKey(i)){
                q.add(i);
            }
        }
        //设本来自己的previleges
        for (int i=0;i<allowedList.size();i++){
            privs.putIfAbsent(i,new HashSet<>());
            privs.get(i).addAll(allowedList.get(i));
        }
        while (!q.isEmpty()){
            int cur=q.poll();//获得当前入度为0的点。
            // 注意：当前 role 先移除自己的 disallowed，而不是到最后的时候再移除，否则你disallow就传给邻居了
            for (String dis : disallowedList.get(cur)) {
                privs.get(cur).remove(dis);
            }
            Set<Integer> nei=neighbour.get(cur);
            if(nei!=null){
                for (int i:nei){//对cur的每个邻居，把邻居的入度-1
                    degree.put(i,degree.get(i)-1);
                    if(degree.get(i)==0){
                        q.offer(i);
                    }
                    //把cur的权限给邻居
                    privs.putIfAbsent(i,new HashSet<>());
                    privs.get(i).addAll(privs.get(cur));
                }
            }
        }
        for (int i=0;i<n ;i++){
            Set<String> set=privs.getOrDefault(i,new HashSet<>());
            for (String dis:disallowedList.get(i)){
                set.remove(dis);
            }
            rs.add(new ArrayList<>(set));
        }
        return rs;
    }
}
