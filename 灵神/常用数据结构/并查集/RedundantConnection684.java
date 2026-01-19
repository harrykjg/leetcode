package 灵神.常用数据结构.并查集;

public class RedundantConnection684 {
    static void main() {

    }
    //就是直接union find，遇到edge，发现已经链接的就是结果
    public int[] findRedundantConnection(int[][] edges) {
        UF uf=new UF();
        uf.ids=new int[edges.length+1];
        for(int i=1;i<uf.ids.length;i++){
            uf.ids[i]=i;
        }
        for(int i=0;i<edges.length;i++){
            if(!uf.isConnect(edges[i][0],edges[i][1])){
                uf.union(edges[i][0],edges[i][1]);
            }else{
                return edges[i];
            }
        }
        return new int[]{};
    }
    class UF{
        int[] ids;
        public UF(){
        }
        int find(int x){
            if(ids[x]==x){
                return x;
            }
            ids[x]=find(ids[x]);
            return ids[x];
        }
        void union(int x,int y){
            int px=find(x);
            int py=find(y);
            if(px==py){
                return;
            }
            if(px<=py){
                ids[py]=px;
            }else{
                ids[px]=py;
            }
        }
        boolean isConnect(int x,int y){
            int px=find(x);
            int py=find(y);
            if(px==py){
                return true;
            }
            return false;
        }

    }

}
