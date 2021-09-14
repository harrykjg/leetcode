package UnionFind;

import java.util.Arrays;
import java.util.Comparator;

public class RemoveMaxNumberofEdgestoKeepGraphFullyTraversable {
    //7/28/2021 不太会
    //https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/discuss/831475/Java-Union-find-solution
    //看了思路就是unionfind。分别用2个unionfind表示alice和bob，edges要把双向的先排在前面，这样便利的时候先分别把alice和bob的uf的点连上，然后在遇到单向的edge
    //看这个edge的两端是否已经链接，是的话就可以remove，最后看这两个uf的component总是是否为1，不是的话说明有人没连上
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]-o1[0];
            }
        });
        UF uf1=new UF(n);
        UF uf2=new UF(n);
        for (int i=0;i<n;i++){
            uf1.ids[i]=i;
            uf2.ids[i]=i;
        }
        int rs=0;
        for (int[] edge:edges){
            int id1=uf1.find(edge[1]-1);
            int id2=uf1.find(edge[2]-1);
            int id21=uf2.find(edge[1]-1);
            int id22=uf2.find(edge[2]-1);
            if (edge[0]==3){
                if (id1==id2||id22==id21){//由于这edge是双向的，减少的话只能减一次
                    rs++;
                }
                uf1.union(edge[1]-1,edge[2]-1);
                uf2.union(edge[1]-1,edge[2]-1);

            }else if (edge[0]==1){
                if (id1==id2){
                    rs++;
                }else {
                    uf1.union(edge[1]-1,edge[2]-1);
                }
            }else {
                if (id21==id22){
                    rs++;
                }else {
                    uf2.union(edge[1]-1,edge[2]-1);
                }
            }
        }
        if (uf1.count!=1||uf2.count!=1){
            return -1;
        }
        return rs;

    }
    class UF{
        int count;
        int[] ids;
        public UF(int count){
            this.count=count;
            ids=new int[count];
            Arrays.fill(ids,-1);
        }
        int find(int id){
            if (ids[id]==id){
                return id;
            }
            ids[id]=find(ids[id]);
            return ids[id];
        }
        void union(int a,int b){
            int id1=find(a);
            int id2=find(b);
            if (id1!=id2){
                ids[id1]=id2;
                count--;
            }
        }

    }
}
