import java.util.*;

/**
 * Created by 502575560 on 6/4/17.
 */
public class EvaluateDivision {
    public static void main(String[] args){
        EvaluateDivision ed=new EvaluateDivision();
        String[][] e={{"a","b"},{"b","c"}};
        double[] v={2d,3d};
        String[][] q={ {"a","c"},{"b","d"}};//{"a","c"},{"b","c"},{"a","e"},
        ed.calcEquation(e,v,q);
    }
    //没什么好的想法,看了别人说可以变成图算法,有向图,有权重(距离),然后dfs,bfs,floyd算法算距离都行貌似
    //用map存邻接表,dfs自己写的改了好几次
    //https://segmentfault.com/a/1190000008330883  dfs
    //http://blog.csdn.net/yeqiuzs/article/details/52506433  dfs
    //http://blog.csdn.net/chanrenyuan/article/details/52552934 dfs
    HashMap<String, HashMap<String,Double>> g=new HashMap<>();
    double[] rs;
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        for(int i=0;i<equations.length;i++){
            String s1=equations[i][0];
            String s2=equations[i][1];
            double v=values[i];
            HashMap<String,Double> m=new HashMap<>();
            if(g.containsKey(s1)){
                m= g.get(s1);
            }
            m.put(s2,v);
            g.put(s1,m);

            HashMap<String,Double> m2=new HashMap<>();
            if(g.containsKey(s2)){
                m2= g.get(s2);
            }
            m2.put(s1,1/v);
            g.put(s2,m2);
        }
        rs=new double[queries.length];
        for(int i=0;i<queries.length;i++){
            boolean b=dfs(i,1d,queries[i][0],queries[i][0],queries[i][1],g,new HashSet<String>());
            if(!b){
                rs[i]=-1d;
            }
        }
        return rs;
    }
    public boolean dfs(int index,double carry,String b,String cur,String e,HashMap<String, HashMap<String,Double>> g,HashSet<String> set){
        if(!g.containsKey(b)||!g.containsKey(e)){
            rs[index]=-1d;
            return true;
        }
        if(b.equals(e)){
            rs[index]=1d;
            return true;
        }

        if(set.contains(cur)){
            return false;
        }
        Map<String,Double>map=g.get(cur);
        if(map.containsKey(e)){
            rs[index]=map.get(e)*carry;
            return true;
        }
        Iterator<String> it=map.keySet().iterator();

        while (it.hasNext()){
            String c=it.next();
            double temp=map.get(c);
            set.add(cur);
            boolean find=dfs(index,temp*carry,b,c,e,g,set);
            set.remove(cur);
            if(find){
                return true;
            }
        }
        return false;
    }
}
