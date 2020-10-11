package Advance2;
//05/19/2020 居然一次过
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        if(M.length==0){
            return 0;
        }
        uf uf=new uf(M.length);
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                if(M[i][j]==1){
                    uf.merge(i,j);
                }
            }
        }
        return uf.size;
    }
}
class uf{
    int[] parent;
    int size;
    public uf(int size){
        this.parent=new int[size];
        for(int i=0;i<parent.length;i++){
            parent[i]=i;
        }
        this.size=size;
    }
    int findParent(int a){
        if(parent[a]==a){
            return a;
        }
        return parent[a]=findParent(parent[a]);
    }
    void merge(int a,int b){
        int pa=findParent(a);
        int pb=findParent(b);
        if(pa!=pb){
            parent[pa]=pb;
            size--;
        }
    }
}
