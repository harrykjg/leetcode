package SomeInterviews.snap;

import java.util.*;

public class EvaluateDivision399 {
    //3/2/2026,还是挺难写的，equation如果是"bc","cd"， value=5.0，那么bc和cd是一个整体，不是说c要约掉,写的还是有点恶心，看以前的用
    //Map<String,Map<String，double>>可能快些？这里用的是返回值的dfs，用void可能好些？
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,Double> memo=new HashMap<>();
        Map<String,List<String>> map=new HashMap<>();
        for (int i=0;i<values.length;i++){
            String key1=equations.get(i).get(0)+"#"+equations.get(i).get(1);
            String key2=equations.get(i).get(1)+"#"+equations.get(i).get(0);
            memo.put(key1,values[i]);
            memo.put(key2,1d/values[i]);
            memo.put(equations.get(i).get(0),1d);//用来排除没出现过的字符
            memo.put(equations.get(i).get(1),1d);
            map.putIfAbsent(equations.get(i).get(0),new ArrayList<>());
            map.get(equations.get(i).get(0)).add(equations.get(i).get(1));

            map.putIfAbsent(equations.get(i).get(1),new ArrayList<>());
            map.get(equations.get(i).get(1)).add(equations.get(i).get(0));
        }
        double[] rs=new double[queries.size()];
        Arrays.fill(rs,-1);
        for (int i=0;i<queries.size();i++){
            Set<String> set=new HashSet<>();
            set.add(queries.get(i).get(0));
            String key=queries.get(i).get(0)+"#"+queries.get(i).get(1);
            if(!memo.containsKey(queries.get(i).get(0))||!memo.containsKey(queries.get(i).get(1))){
                rs[i]=-1d;
                continue;
            }
            if (memo.containsKey(key)){
                rs[i]=memo.get(key);
            }else if(queries.get(i).get(0).equals(queries.get(i).get(1))){
                rs[i]=1d;
            } else{
               double value= dfs(1d,queries.get(i).get(0),queries.get(i).get(1),map,memo,set,rs);
               rs[i]=value;
               memo.put(key,value);
            }
        }
        return rs;
    }
    double dfs(double cur,String b, String e,Map<String,List<String>> map,Map<String,Double> memo,Set<String> set,double[] rs){
        if(b.equals(e)){
            return cur;
        }
        set.add(b);
        List<String> neighbour=map.get(b);
        if(neighbour!=null){
            for(String nei:neighbour){
                String key=b+"#"+nei;
                if(!set.contains(nei)){
                    double value=dfs(cur*memo.get(key),nei,e,map,memo,set,rs);
                    if(value!=-1){
                        return value;
                    }
                }
            }
        }
        set.remove(b);
        return -1d;
    }
}
