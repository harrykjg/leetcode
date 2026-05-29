package SomeInterviews.snap;

import java.util.*;

public class CountUnreachablePairsofNodesinanUndirectedGraph2316 {
    static void main() {
        CountUnreachablePairsofNodesinanUndirectedGraph2316 cu=new CountUnreachablePairsofNodesinanUndirectedGraph2316();
        int[][] p={{0,2},{0,5},{2,4},{1,6},{5,4}};
        System.out.println(cu.countPairs(7,p));
    }

    //3/4/2026
    public long countPairs(int n, int[][] edges) {
        UF uf=new UF(n);
        List<Integer> al=new ArrayList<>();
        for (int i=0;i<edges.length;i++){
            uf.union(edges[i][0],edges[i][1]);
        }
        long rs=0;
        Set<Integer> set=new HashSet<>();
        for (int i=0;i<n;i++){
            int f=uf.find(i);
            if(!set.contains(f)){
                set.add(f);
                al.add(f);
            }
        }
        //开始这里想错了，以为是二重循坏看每一个节点，其实是要看每一个group,然后想着二重循环，但是超时， 其实不用二重循环，
        // 应该是从第二个遇到的count乘以前面所有已经遇到的count的和，还比较难想
        long cur=0;
        for (int i=0;i<al.size();i++){
            rs+=cur*uf.count[al.get(i)];
            cur+=uf.count[al.get(i)];
        }
        return rs;
    }
    class UF{
        int[] count;
        int[] ids;
        public UF(int n){
            ids=new int[n];
            count=new int[n];
            for (int i=0;i<n;i++){
                ids[i]=i;
            }
            Arrays.fill(count,1);
        }
        int find(int x){
            if(ids[x]==x){
                return x;
            }
            ids[x]=find(ids[x]);
            return ids[x];
        }
        void union(int a,int b){
            int fa=find(a);
            int fb=find(b);
            if(fa!=fb){
                ids[fb]=fa;
                count[fa]+=count[fb];
                count[fb]=count[fa];
            }
        }
    }
}
