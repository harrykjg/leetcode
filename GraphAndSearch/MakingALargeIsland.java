package GraphAndSearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MakingALargeIsland {
    public static void main(String[] args){
        MakingALargeIsland ma=new MakingALargeIsland();
        int[][] g={{1,1},{1,1}};
        System.out.println(ma.largestIsland2(g));
    }
    //8/18/2021 明显unionfind 但是实现起来还不是那么容易，尤其是把其中一个0变成1时，怎么检测4个方向上的1都加起来
    //https://leetcode.com/problems/making-a-large-island/discuss/127980/Java-Solution-using-Union-Find
    public int largestIsland(int[][] grid) {
        int[] ids=new int[grid.length*grid[0].length];
        int[] size=new int[grid.length*grid[0].length];//这样就不用非得设一个unionfind class
        Arrays.fill(size,1);
        int col=grid[0].length;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                int id=i*col+j;
                ids[id]=id;
            }
        }
        int[] x={1,0,-1,0};
        int[] y={0,1,0,-1};
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    for (int k=0;k<x.length;k++){
                        int r=i+x[k];
                        int c=j+y[k];
                        if (r>=0&&r<grid.length&&c>=0&&c<col&&grid[r][c]==1){
                            union(i*col+j,r*col+c,ids,size);
                        }
                    }
                }
            }
        }
        int rs=0;
        for (int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==0){
                    int count=1;
                    Set<Integer> set=new HashSet<>();
                    for (int k=0;k<4;k++){//这里是难点，怎么把四个方向上的没链接的东西连起来？要不要把当前的0设成1再取和旁边的连？不要，因为连完之后你还得
                        int r=i+x[k];   //还原，然后去试下一个0的点。不好还原啊。方法就是不把这个0设成1，只把他当作是count=1以便后面加上四个方向上的size。设一个set
                        int c=j+y[k];   //来装四个方向上的东西，遇到第一个方向时，直接把他的root扔进set，遇到第二个时，看set里的东西的root和这第二个是否一样，不一样
                        if (r>=0&&r<grid.length&&c>=0&&c<col&&grid[r][c]==1){//的话才union，一样的话说明这个方向已经和之前的某个方向已经是链接的了
                            int root=find(r*col+c,ids);
                            if (!set.contains(root)){
                                set.add(root);
                                count+=size[root];//注意这里是size【root】，因为union的时候就是把root那的size加上别人的
                            }
                        }
                    }
                    rs=Math.max(rs,count);
                }
            }
        }
        //如果rs没被赋值，说明grid里全是1，因为只要不全是1的话那么肯定有0，有0就至少rs是1。
        return rs==0?size.length:rs;
    }

    int find(int x,int[] ids){
        if (x==ids[x]){
            return x;
        }
        ids[x]=find(ids[x],ids);
        return ids[x];
    }
    void union(int a,int b,int[] ids,int[] size){
        int root1=find(a,ids);
        int root2=find(b,ids);
        if (root1!=root2){
            ids[root2]=root1;
            size[root1]+=size[root2];//谁加谁想清楚，把别人加到root1上
            size[root2]=size[root1];//这行加了也对，不加也对
        }
    }

    public int largestIsland2(int[][] grid) {
        int[] ids=new int[grid.length*grid[0].length];
        int[] count=new int[grid.length*grid[0].length];
        int col=grid[0].length;
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        for (int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1){
                    ids[i*col+j]=i*col+j;//这里不是1的我就没初始化，会导致0的点id为0，也行，因为不影响。
                    count[i*col+j]=1;
                }
            }
        }
        int rs=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    for (int k=0;k<4;k++){
                        int r=i+dx[k];
                        int c=j+dy[k];
                        if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&grid[r][c]==1){
                            union2(i*col+j,r*col+c,ids,count);
                        }
                    }
                    rs=Math.max(rs,count[find2(i*col+j,ids)]);//这里find容易漏
                }
            }
        }
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<col;j++){
                if (grid[i][j]==0){
                    HashSet<Integer> set=new HashSet<>();
                    int num=1;
                    for (int k=0;k<4;k++){
                        int r=i+dx[k];
                        int c=j+dy[k];
                        if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&grid[r][c]==1){
                           int idd=find2(r*col+c,ids);
                           if (!set.contains(idd)){
                               set.add(idd);
                               num+=count[idd];
                           }
                        }
                    }
                    rs=Math.max(rs,num);
                }
            }
        }
        return rs;
    }
    int find2(int a,int[] ids){
        if(ids[a]==a){
            return a;
        }
        ids[a]=find(ids[a],ids);
        return ids[a];
    }
    void union2(int a,int b,int[] ids,int[] counts){
        int id1=find2(a,ids);
        int id2=find2(b,ids);
        if (id1!=id2){
            ids[id2]=id1;
            counts[id1]+=counts[id2];
        }
    }

}
