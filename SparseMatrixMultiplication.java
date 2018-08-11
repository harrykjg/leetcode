/**
 * Created by 502575560 on 7/12/16.
 */
public class SparseMatrixMultiplication {
    //不会
    //https://baike.baidu.com/item/矩阵乘法/5446029?fr=aladdin  介绍举证乘法
    //https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76151/54ms-Detailed-Summary-of-Easiest-JAVA-solutions-Beating-99.9
    //https://blog.csdn.net/xinqrs01/article/details/54311389
    //http://www.cnblogs.com/yrbbest/p/5060667.html  他的方法不太一样
    //先写暴力法
    public int[][] multiply(int[][] a,int[][] b){
        if(a.length==0||b.length==0){
            return new int[0][0];
        }
        int[][] rs=new int[a.length][b[0].length];
        for(int i=0;i<a.length;i++){//这样的话，a[i][k] 和b[k][j]是不是0都不重要，反正肯定是要遍历的
            for(int j=0;j<b[0].length;j++){//按结果矩阵遍历
                for(int k=0;k<a[0].length;k++){//a的列数和b的行数一样
                    rs[i][j]+=a[i][k]*b[k][j];
                }
            }
        }
        return rs;

    }

    //改进方法，看图可知，a[0][0]是被用了b[0]次数的，如果可以改一下计算顺序，每次算的不是rs[i][j]的最终值，而是rs[i][0,1,2,3...]中间的一部分，那么就可以利用a[0][0]如果是0的话，就省掉了b[0]次数了，
    //自己想肯定是很难想到的
    public int[][] multiply2(int[][] a,int[][] b){
        if(a.length==0||b.length==0){
            return new int[0][0];
        }
        int[][] rs=new int[a.length][b[0].length];
        for(int i=0;i<a.length;i++){//这样的话，a[i][k] 和b[k][j]是不是0都不重要，反正肯定是要遍历的
            for(int j=0;j<a[0].length;j++) {
                if(a[i][j]!=0){
                    for(int k=0;k<b[0].length;k++) {//下标很恶心，不好想
                        rs[i][k]+=a[i][j]*b[j][k];
                    }
                }
            }

        }
        return rs;

    }

}
