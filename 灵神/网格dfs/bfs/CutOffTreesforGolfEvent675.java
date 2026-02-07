package 灵神.网格dfs.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CutOffTreesforGolfEvent675 {
    public static void main(String[] args) {

    }
    //题目的例子给的太简单了，看回以前的解释，其实每一步都是bfs，把所有数放进pq里，然后从0,0点开始bfs找最小的那个数，再递归bfs找pq里的下一个。懒得写了
    public int cutOffTree(List<List<Integer>> forest) {

    }

    //1/24/2026 改了半天结果超时，就是注意大于1才能放进pq里，还有bfs还是需要memo吧，现在是可以ac的
    /*
    1,2,5
    0,0,6
    7,8,9  这个答案是8，因为6之后要通过9->8到达7再把7砍了。。因此不能bfs只看四周。因此需要把全部tree先放进pq里，再一个个找路径过去砍
     */
    int rs=0;
    public int cutOffTree2(List<List<Integer>> forest) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]-b[2]);
        int m=forest.size();
        int n=forest.get(0).size();
        if(forest.get(0).get(0)==0){
            return -1;
        }
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(forest.get(i).get(j)>1){
                    pq.offer(new int[]{i,j,forest.get(i).get(j)});
                }
            }
        }
        helper(0,0,pq,forest);
        if(pq.size()>0){
            return -1;
        }
        return rs;

    }
    void helper(int row,int col,PriorityQueue<int[]> pq,List<List<Integer>> forest){
        int m=forest.size();
        int n=forest.get(0).size();
        if (pq.isEmpty()){
            return;
        }
        Queue<int[]> q=new LinkedList<>();
        int[] target=pq.peek();//开始这里就poll了就不对了，后面得看pq的size来判断是否全都找到
        q.offer(new int[]{row,col});
        int step=0;
        int[] cur=new int[2];
        boolean found=false;
        boolean[][] set=new boolean[m][n];
        set[row][col]=true;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                cur=q.poll();
                if(cur[0]==target[0]&&cur[1]==target[1]){
                    rs+=step;
                    forest.get(cur[0]).set(cur[1],1);
                    found=true;
                    pq.poll();
                    break;
                }
                int[] dx={0,1,0,-1};
                int[] dy={1,0,-1,0};
                for (int j=0;j<4;j++){
                    int nx=dx[j]+cur[0];
                    int ny=dy[j]+cur[1];
                    if(nx>=0&&nx<m&&ny>=0&&ny<n&&forest.get(nx).get(ny)!=0&&!set[nx][ny]){
                        set[nx][ny]=true;
                        q.offer(new int[]{nx,ny});
                    }
                }

            }
            if(found){
                break;
            }
            step++;
        }
        if(found){//这里容易漏
            helper(cur[0],cur[1],pq,forest);

        }
    }
}
