package GraphAndSearch;

import java.util.HashMap;
import java.util.List;

public class EmployeeImportance {
    //9/5/2021  一次过，其实不需要2个map，直接一个map的key是id，value是employee就行了
    int rs=0;
    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        HashMap<Integer,Integer> score=new HashMap<>();
        for (Employee e:employees){
            map.put(e.id,e.subordinates);//题目说了是unique的
            score.put(e.id,e.importance);
        }
        dfs(id,map,score);
        return rs;
    }
    void dfs(int id,HashMap<Integer,List<Integer>> map, HashMap<Integer,Integer> score){
        rs+=score.get(id);
        for (int nei:map.get(id)){
            dfs(nei,map,score);
        }

    }

    //10/21/2021 一次过
    int rs2=0;
    public int getImportance2(List<Employee> employees, int id) {
        HashMap<Integer,Employee> map=new HashMap<>();
        for (Employee em:employees){
            map.put(em.id,em);
        }
        dfs2(id,map);
        return rs2;
    }
    void dfs2(int id,HashMap<Integer,Employee> map){
        Employee cur=map.get(id);
        rs2+=cur.importance;
        for(int nei:cur.subordinates){
            dfs2(nei,map);
        }
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
