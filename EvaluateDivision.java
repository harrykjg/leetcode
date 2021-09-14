import java.util.*;

/**
 * Created by 502575560 on 6/4/17.
 */
public class EvaluateDivision {
    public static void main(String[] args){
        EvaluateDivision ed=new EvaluateDivision();
        String[][] e={{"a","b"},{"b","c"}};
        double[] v={2d,3d};
        String[][] q={ {"a","c"},{"b","c"}};//{"a","c"},{"b","c"},{"a","e"},
        ed.calcEquation3(e,v,q);
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
//9/12/2018,写的还不如上一次，写的比较慢还不好，还有concurrent modification exception,还有好几个小问题，还不如看回第一次的
    public double[] calcEquation3(String[][] equations, double[] values, String[][] queries) {
        HashMap<String,HashMap<String,Double>> map=new HashMap<>();
        for(int i=0;i<equations.length;i++){
            String s1=equations[i][0];
            String s2=equations[i][1];
            if(!map.containsKey(s1)){
                HashMap<String,Double> m=new HashMap<>();
                m.put(s2,values[i]);
                map.put(s1,m);
            }else{
                map.get(s1).put(s2,values[i]);
            }
            if(!map.containsKey(s2)){
                HashMap<String,Double> m=new HashMap<>();
                m.put(s1,1/values[i]);
                map.put(s2,m);
            }else{
                map.get(s2).put(s1,1/values[i]);
            }
        }
        double[] rs=new double[queries.length];
        HashSet<String> memo=new HashSet<>();
        for(int i=0;i<queries.length;i++){
            String s1=queries[i][0];
            String s2=queries[i][1];

            if(!map.containsKey(s1)||!map.containsKey(s2)){
                rs[i]=-1.0;
                continue;
            }
            if(s1.equals(s2)){
                rs[i]=1.0;
                continue;
            }
            if(map.get(s1).containsKey(s2)){
                rs[i]=map.get(s1).get(s2);
                continue;
            }
            dfs3(s1,s2,s1,1.0,map,memo,rs,i);
            if(!map.get(s1).containsKey(s2)){
                rs[i]=-1.0;
                continue;
            }
        }
        return rs;
    }
    void dfs3(String s1,String s2,String cur,double carry,HashMap<String,HashMap<String,Double>> map,HashSet<String> memo,double[] rs,int index){

        if(cur.equals(s2)){
            map.get(s1).put(s2,carry);
            rs[index]=carry;
            return;
        }
        memo.add(cur);
        for(String nei:map.get(cur).keySet()){
            double carry2=map.get(cur).get(nei);
            if(!memo.contains(nei)){
                dfs3(s1,s2,nei,carry*carry2,map,memo,rs,index);
            }
            if(map.get(s1).containsKey(s2)){//不加这个就会concurrentmodificationexception，因为这个for循环是看cur的的邻居，而如果dfs下去成功找到s2之后，会改变map，所以不行，具体到底是因为是改变了map的key还是因为改了key所对应的value，没仔细看
                break;
            }
        }
        memo.remove(cur);
    }

    //9/16/2018,写的还算顺，改了2次accept，主要是漏了concurrent exception那里，还有dfs没找到的情况漏了
    public double[] calcEquation4(String[][] equations, double[] values, String[][] queries) {
        double[] rs=new double[queries.length];
        HashMap<String,HashMap<String,Double>> map=new HashMap<>();
        for(int i=0;i<equations.length;i++){
            String a=equations[i][0];
            String b=equations[i][1];
            if(!map.containsKey(a)){
                HashMap<String,Double> m=new HashMap<>();
                map.put(a,m);
            }
            map.get(a).put(b,values[i]);

            if(!map.containsKey(b)){
                HashMap<String,Double> m=new HashMap<>();
                map.put(b,m);
            }
            map.get(b).put(a,1/values[i]);
        }
        HashSet<String> memo=new HashSet<>();
        for(int i=0;i<queries.length;i++){
            if(!map.containsKey(queries[i][0])||!map.containsKey(queries[i][1])){
                rs[i]=-1.0;
                continue;
            }
            if(queries[i][0].equals(queries[i][1])){
                rs[i]=1.0;
                continue;
            }
            if(map.get(queries[i][0]).containsKey(queries[i][1])){
                rs[i]=map.get(queries[i][0]).get(queries[i][1]);
                continue;
            }
            dfs4(queries[i][0],queries[i][0],1.0,queries[i][1],map,rs,i,memo);
            if(!map.get(queries[i][0]).containsKey(queries[i][1])){
                rs[i]=-1.0;
                continue;
            }
        }
        return rs;
    }
    void dfs4(String ori,String cur,Double carry,String target,HashMap<String,HashMap<String,Double>> map,double[] rs,int index,HashSet<String> memo){
        if(cur.equals(target)){
            map.get(ori).put(target,carry);
            rs[index]=carry;
            return;
        }
        memo.add(cur);
        for(String nei:map.get(cur).keySet()){
            if(memo.contains(nei)){
                continue;
            }
            double newcarry=map.get(cur).get(nei)*carry;
            dfs4(ori,nei,newcarry,target,map,rs,index,memo);
            memo.remove(nei);
            if(map.get(ori).containsKey(target)){
                break;
            }
        }
        memo.remove(cur);
    }

    //7/6/2021,漏了dfs去重,以前是用Map<String,Map<String，double>>，我这另外用了个Map<String,Double> val=new HashMap<>()去存value，其实以前那样好点
    public double[] calcEquation5(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,Set<String>> map=new HashMap<>();
        Map<String,Double> val=new HashMap<>();
        for (int i=0;i<equations.size();i++){
            String a=equations.get(i).get(0);
            String b=equations.get(i).get(1);
            double value=values[i];
            if (!map.containsKey(a)){
                Set<String> set=new HashSet<>();
                map.put(a,set);
            }
            map.get(a).add(b);
            if (!map.containsKey(b)){
                Set<String> set=new HashSet<>();
                set.add(a);
                map.put(b,set);
            }
            map.get(b).add(a);
            String ab=a+"#"+b;
            String ba=b+"#"+a;

            val.put(ab,value);//还可以加上a#a这样的直接存上1，否则其实是进行了dfs去找的
            val.put(ba,1.0/value);

        }
        Set<String> memo=new HashSet<>();
        double[] rs=new double[queries.size()];
        Arrays.fill(rs,-1.0);
        for (int i=0;i<queries.size();i++){
            memo.add(queries.get(i).get(0));
            dfs5(queries.get(i).get(0),queries.get(i).get(1),1,map,val,rs,i,memo);
            memo.remove(queries.get(i).get(0));
        }
        return rs;
    }
    void dfs5(String cur,String e,double carry,Map<String,Set<String>> map,Map<String,Double> values,double[] rs,int index,Set<String> memo){
        String key=cur+"#"+e;
        if(values.containsKey(key)){
            rs[index]=carry*values.get(key);
            return ;
        }
        if (!map.containsKey(cur)){
            return;
        }
        for(String nei:map.get(cur)){
            if (memo.contains(nei)){
                continue;
            }
            key=cur+"#"+nei;
            if (values.containsKey(key)){
                memo.add(nei);
                double v=values.get(key);
                double nextCarry=carry*v;
                dfs5(nei,e,nextCarry,map,values,rs,index,memo);
                memo.remove(nei);
            }
        }
    }
}
