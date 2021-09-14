package GraphAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TheMostSimilarPathinaGraph {
    public static void main(String[] args){
        TheMostSimilarPathinaGraph tm=new TheMostSimilarPathinaGraph();
        int[][] roads={{0,2},{0,3},{1,2},{1,3},{1,4},{2,4}};
        String[] names={"ATL","PEK","LAX","DXB","HND"};
        String[] target={"ATL","DXB","HND","LAX"};
        tm.mostSimilar(5,roads,names,target);
    }
    //9/5/2021.很难
    //https://www.youtube.com/watch?v=DTn8A9D-NCM 基本是抄他的代码
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        int[][] memo=new int[n][targetPath.length];
        for (int[] m:memo){
            Arrays.fill(m,-1);
        }
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        for (int[] r:roads){
            if (!map.containsKey(r[0])){
                map.put(r[0],new ArrayList<>());
            }
            if (!map.containsKey(r[1])){
                map.put(r[1],new ArrayList<>());
            }
            map.get(r[0]).add(r[1]);
            map.get(r[1]).add(r[0]);
        }
        int[][] minChild=new int[n][targetPath.length];//意义是某个city作为原点，从某个step开始到targetpath最后，他的所有邻居中值最小的那个邻居。很难理解
        int min=Integer.MAX_VALUE;
        int start=0;
        for (int i=0;i<n;i++){
            int cost=dfs(i,0,targetPath,map,memo,names,minChild);
            if (cost<min){
                min=cost;
                start=i;
            }
        }
        List<Integer> rs=new ArrayList<>();
        while (rs.size()<targetPath.length){//上面的循环得出从0步开始哪个city到targetpath最后是最小的值，然后就按这个
            rs.add(start);
            start=minChild[start][rs.size()-1];//比如例子的targetPath长度是4，这里rs.size()-1的值只会取到0，1，2。理解不了
        }
        return rs;
    }
    int dfs(int city, int step,String[] targetPath,HashMap<Integer,List<Integer>> map,int[][] memo,String[] names,int[][] minChild){
        if (memo[city][step]!=-1){
            return memo[city][step];
        }
        int cos=0;
        if (!targetPath[step].equals(names[city])){
            cos=1;
        }
        if (step==targetPath.length-1){
            return cos;
        }
        int min=Integer.MAX_VALUE;
        for (int nei:map.get(city)){//他这里dfs居然不去重，那么就会两个点来回来回走，步数会变，服了。
            int neiCost=dfs(nei,step+1,targetPath,map,memo,names,minChild);//意义应该是遍历这个city的所有的邻居，得到每个邻居作为第step+1步到最后
            if (neiCost<min){//的所需距离，然后找出最小的cost的那个city，存到minChild里，那最后dfs完之后在上面的那个方法会得到最终的start点，然后对这个stat点
                min=neiCost;  //再取他的所有邻居里到最后步数最小的那个邻居。
                minChild[city][step]=nei;
            }
        }
        cos+=min;
        memo[city][step]=cos;
        return cos;
    }
}
