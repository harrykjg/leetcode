package SomeInterviews.karat;

import java.util.ArrayList;
import java.util.List;

public class FindRectangle {
    //9/18/2021
    //https://leetcode.com/discuss/interview-question/437403/Karat-interview-agentor-phone-or-find-rectangle-coordinates
    //第一问是只有一个长方形，找出来。第二问是有多个长方形。应该是假设每个长方形都是长方形，而不会出现这一行有3个0，下一行只有2个零这种情况
    public static void main(String[] args){
        FindRectangle fr=new FindRectangle();
        int[][] m={{1,1,1,1,1},
                {1,0,0,1,1},
                {1,0,0,1,1},
                {1,1,1,1,0}};
//        List<int[]> rs=fr.find2(m);
//        for (int[] r:rs){
//            System.out.println(r[0]+" "+r[1]);
//        }
        int[][] mm={
                {1,0,0,1},
                {0,1,0,1},
                {0,1,1,1},
                {1,0,0,0}
        };
        List<List<int[]>> rs2=fr.find3(mm);
        for (List<int[]> al:rs2){
            for (int[] r:al){
                System.out.println(r[0]+" "+r[1]);
            }
        }
    }
    //第一问，只要找到第一个0记录为左上角，再从右下角出发找第一个0就是答案了。不写了

    //这里写第二问,坐标还是挺恶心的，
    List<int[]> find(int[][] m){
        List<int[]> rs=new ArrayList<>();
//        boolean[][] memo=new boolean[m.length][m[0].length];
        for (int i=0;i<m.length;i++){
            for (int j=0;j<m[0].length;j++){
                if (m[i][j]==0){//遇到0之后，就往右扩看能去到哪，得出的jj-1就是能去到的最右点。同时把去到的点都改成1
                    m[i][j]=1;
                    int[] topLeft={i,j};
                    int jj=j+1;
                    while (jj<m[0].length&&m[i][jj]==0){
                        m[i][jj]=1;
                        jj++;
                    }
                    int ii=i+1;
                    while (ii<m.length&&m[ii][j]==0){//往下的时候，每一行都要遍历宽度，把0改成1
                        for (int k=j;k<jj;k++){
                            m[ii][k]=1;
                        }
                        ii++;
                    }
                    //现在知道宽度和长度了，由i，j可以推出右下角，还是挺恶心的。
                    int[] botRight={ii-1,jj-1};
                    rs.add(topLeft);
                    rs.add(botRight);
                }
            }
        }
        return rs;
    }

    List<int[]> find2(int[][] m){//再写以便第二问，挺恶心的，记者2个for loop中间加2个while，
        List<int[]> rs=new ArrayList<>();
        for (int i=0;i<m.length;i++){
            for (int j=0;j<m[0].length;j++){
                if (m[i][j]==0){
                    int w=j;
                    while (w<m[0].length&&m[i][w]==0){
                        m[i][w]=1;
                        w++;
                    }
                    int d=i+1;
                    while (d<m.length&&m[d][j]==0){
                        for (int k=j;k<w;k++){
                            m[d][k]=1;
                        }
                        d++;
                    }
                    int[] topleft=new int[]{i,j};
                    int[] botright=new int[]{d-1,w-1};
                    rs.add(topleft);
                    rs.add(botright);
                    j=w-1;
                }
            }
        }
        return rs;
    }
    //第三问应该用dfs，就是遇到一个0就dfs所有联通的0，并且把所有的0的坐标都放进结果集。
    List<List<int[]>> find3(int[][] m) {//再写以便第二问，挺恶心的，记者2个for loop中间加2个while，
        List<List<int[]>> rs = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 0) {
                    List<int[]> al=new ArrayList<>();
                    dfs(i,j,m,al);
                    rs.add(al);
                }
            }
        }
        return rs;
    }
    void dfs(int row,int col,int[][] m,List<int[]> al){//这个dfs怎么判断停止？貌似这里不判断，而是上面那里要每次新建个al，在放进结果集

    }
}
