package 灵神.二分法.二分答案.最大化最小值;

import java.util.ArrayList;
import java.util.List;

public class FindtheSafestPathinaGrid2812 {
    public static void main(String[] args) {
        List<List<Integer>> al=new ArrayList<>();
        List<Integer> al1=new ArrayList<>();
        al1.add(0);
        al1.add(0);
        al1.add(1);
        al.add(al1);
        List<Integer> al2=new ArrayList<>();
        al2.add(0);
        al2.add(0);
        al2.add(0);
        al.add(al2);
        List<Integer> al3=new ArrayList<>();
        al3.add(0);
        al3.add(0);
        al3.add(0);
        al.add(al3);

        System.out.println(maximumSafenessFactor(al));

    }

    //就是二分法枚举加dfs验证，这样对的但是超时，好的写法比较麻烦不想看了
    //https://leetcode.cn/problems/find-the-safest-path-in-a-grid/solutions/2375565/jie-jin-on2-de-zuo-fa-duo-yuan-bfsdao-xu-r5um/
    public static int maximumSafenessFactor(List<List<Integer>> grid) {
        ArrayList<int[]> al=new ArrayList<>();
        for(int i=0;i<grid.size();i++){
            for(int j=0;j<grid.get(0).size();j++){
                if(grid.get(i).get(j)==1){
                    al.add(new int[]{i,j});
                }
            }
        }
        int b=-1;
        int e=grid.size();
        while (b+1<e){
            int m=e-(e-b)/2;
            boolean canPass=dfs(0,0,m,al,grid,new boolean[grid.size()][grid.size()]);
            if(canPass){
                b=m;
            }else{
                e=m;
            }
        }
        return b==-1?0:b;
    }

    static boolean dfs(int r,int c,int m,List<int[]> theft, List<List<Integer>> grid,boolean[][] memo){

        int dist=grid.size()-1;//必然有小偷,那么最远的小偷就是右上角，而path是沿左边下面走
        for(int j=0;j<theft.size();j++){
            dist=Math.min(dist,Math.abs(r-theft.get(j)[0])+Math.abs(c-theft.get(j)[1]));
        }
        if(m>dist){//m太大了，实际上小偷很近，所以不行
            return false;
        }
        if(r==grid.size()-1&&c==grid.size()-1&&grid.get(grid.size()-1).get(grid.size()-1)!=1){//最后一位还不能是小偷
            return true;
        }

        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for(int i=0;i<dx.length;i++){
            int row=dx[i]+r;
            int col=dy[i]+c;
            if(row>=0&&row<grid.size()&&col>=0&&col<grid.size()&&!memo[row][col]){
                memo[row][col]=true;
                if(dfs(row,col,m,theft,grid,memo)){
                    return true;
                }
            }
        }
        return false;

    }
}
