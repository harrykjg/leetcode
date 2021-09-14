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

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
