package 灵神.常用数据结构;

public class RotateImage48 {


    // 4/8/2026还是挺难的，关键是你把第一行挖了，放到最后一列，那么最后一列的元素就要先拷贝一份？同样下一个替换的.看了答案，不是一行一行换，是一个一个换
    /*
        1  2  3  4
        5  6  7  8
        9  10 11 12
        13 14 15 16

        就是先挖坑，不要拿[0,0]举例子，容易搞错，拿[0,1]好一点
        把2 挖出来作为temp，把9填到【0，1】，把15填到【1，0】，把8填到[3,1]，把temp填到【1，3】，神奇的是一行有三个，那么只需要换2个就行
        下标的变化就是 [i,j]作为temp,把[n-j,i]拿过来填上，再把[n-i,n-j]拿过来填上[n-j,i],再把[n-(n-j),n-i]拿过来填上，再把temp填上[n-(n-j),n-i]
        这里目测得出i是level，那么j就是列，从左往右挪，只需换n-1次，还是很难想
     */
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        int level=matrix.length/2;
        for (int i=0;i<level;i++){
            for(int j=i;j<n-i-1;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[n-1-j][i];
                matrix[n-1-j][i]=matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j]=matrix[j][n-1-i];
                matrix[j][n-1-i]=temp;

            }
        }
    }

}
