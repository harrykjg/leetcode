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
        ed.calcEquation2(e,v,q);
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
    //8/31/2018,想的是可以initialize的时候，用map把所有a可以联通到的值都算出来，那么query的时候就直接map。get就完了，但是他们的答案都是要query的时候才去dfs找路径的,dfs写的不好,还不如看上面的
    public double[] calcEquation2(String[][] equations, double[] values, String[][] queries) {
        HashMap<String,HashMap<String,Double>> graph=new HashMap<>();
        HashMap<String,Double> map2=new HashMap<>();
        for(int i=0;i<equations.length;i++){
            String a=equations[i][0];
            String b=equations[i][1];
            double v1=values[i];
            double v2=1/values[i];
            if(!graph.containsKey(a)){
                HashMap<String,Double> map=new HashMap<>();
                map.put(b,v1);
                graph.put(a,map);
            }else{
                graph.get(a).put(b,v1);
            }
            if(!graph.containsKey(b)){
                HashMap<String,Double> map=new HashMap<>();
                map.put(a,v2);
                graph.put(b,map);
            }else{
                graph.get(b).put(a,v2);
            }
        }
        double[] rs=new double[queries.length];
        for(int i=0;i<queries.length;i++){
            String a=queries[i][0];
            String b=queries[i][1];
            if(!graph.containsKey(a)||!graph.containsKey(b)){
                rs[i]=-1.0;
                continue;
            }
            if(a.equals(b)){
                rs[i]=1.0;
                continue;
            }
            if(graph.get(a).containsKey(b)){
                rs[i]=graph.get(a).get(b);
                continue;
            }

            dfs2(a,a,b,1.0,graph,map2,rs,i,new HashSet<String>());
            if(!graph.get(a).containsKey(b)){
                rs[i]=-1.0;
            }

        }
        return rs;
    }
    void dfs2(String ori,String cur,String target,double carry,HashMap<String,HashMap<String,Double>> graph,HashMap<String,Double> map,double[] rs,int index,HashSet<String> memo){
        if(cur.equals(target)){
            rs[index]=carry;
            graph.get(ori).put(target,carry);
            return;
        }
        memo.add(cur);
        for(String s:graph.get(cur).keySet()){
            if(!memo.contains(s)) {
                dfs2(ori, s, target, graph.get(cur).get(s) * carry, graph, map, rs, index, memo);
            }
            if(graph.get(ori).containsKey(target)){
                break;
            }
        }
        memo.remove(cur);
    }
}
