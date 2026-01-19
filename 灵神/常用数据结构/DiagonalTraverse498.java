package 灵神.常用数据结构;

public class DiagonalTraverse498 {
    static void main() {

    }
    //自己想的就是设一个初始方向，右上就是i-1，j+1，左下就是反过来，只要有一个坐标越界，就翻转，想的是用dfs。
    public int[] findDiagonalOrder(int[][] mat) {
        int[] rs=new int[mat.length*mat[0].length];
        dfs(0,0,0,rs,mat,1);
        return rs;
    }
    void dfs(int index,int row,int col,int[] rs,int[][] mat,int dir){
        if(index>=rs.length||row<0||row>mat.length||col<0||col>=mat[0].length){
            return;
        }
        rs[index++]=mat[row][col];
        if(dir==1){//右上
            while (row-1>=0&&col+1<mat[0].length){
                rs[index++]=mat[row-1][col+1];
                row-=1;
                col+=1;
            }
            if(row-1<0){
                if(col+1<mat[0].length){
                    dfs(index,row,col+1,rs,mat,0);
                }else{//右上角的情况
                    dfs(index,row+1,col,rs,mat,0);
                }
            }else if(col+1>=mat[0].length){
                dfs(index,row+1,col,rs,mat,0);
            }
        }else{//往左下,这里和右上的情况不对称哈，上面有3个dfs，就是多了往右上到右上角的情况，而到左下角的情况不特殊
            while (row+1<mat.length&&col-1>=0){
                rs[index++]=mat[row+1][col-1];
                row+=1;
                col-=1;
            }
            if(row+1>=mat.length){
                dfs(index,row,col+1,rs,mat,1);
            }else{
                dfs(index,row+1,col,rs,mat,1);
            }
        }
    }
}
