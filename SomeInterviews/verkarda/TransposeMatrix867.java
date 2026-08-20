package SomeInterviews.verkarda;

public class TransposeMatrix867 {
    //写的不好，画图找规律,就是行和列互换，那么需要换那些点呢？开始还以为可以in place交换，结果他不一定是正方形因此必须新建一个matrix，那么
    //就直接遍历得了
    public int[][] transpose(int[][] matrix) {
        int[][] rs=new int[matrix[0].length][matrix.length];
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if(j<rs.length&&i<rs[0].length){
                    rs[j][i]=matrix[i][j];
                }
            }
        }
        return rs;
    }
}
