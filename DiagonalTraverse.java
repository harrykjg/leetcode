public class DiagonalTraverse {
    //9/14/2021 很恶心
    //https://leetcode.com/problems/diagonal-traverse/discuss/97712/Concise-Java-Solution  看他写的规律
    public int[] findDiagonalOrder(int[][] mat) {
        int count=mat.length*mat[0].length;
        int c=0;
        int i=0;
        int j=0;
        int[] rs=new int[count];
        while (c<count){
            while (i>=0&&i<mat.length&&j>=0&&j<mat[0].length){
                rs[c]=mat[i--][j++];
                c++;
                if(i>=mat.length){
                    i=mat.length-1;
                    j+=2;
                    break;
                }
                if(j>=mat[0].length){
                    j=mat[0].length-1;
                    i+=2;
                    break;
                }
                if(i<0){
                    i=0;
                    break;
                }
                if(j<0){
                    j=0;
                    break;
                }
            }
            while (c<count&&i>=0&&i<mat.length&&j>=0&&j<mat[0].length){
                rs[c]=mat[i++][j--];
                c++;
                if(i>=mat.length){
                    i=mat.length-1;
                    j+=2;
                    break;
                }
                if(j>=mat[0].length){
                    j=mat[0].length-1;
                    i+=2;
                    break;
                }
                if(i<0){
                    i=0;
                    break;
                }
                if(j<0){
                    j=0;
                    break;
                }
            }
        }
        return rs;
    }

    //10/4/2021  这个比上面的好一些
    /*
       x x x  2*3 4道
       x x x

        3*3的matrxi 顺序是00 10 01 20 11 02 12 21 22  5道

        x x x x  3*4的是 00 10 01 20 11 02 03 12 21 22 13 23  6道 看规律可知n*m 举证要n+m-1道斜线。并且开始的第一道的行列i+j=0；最后的i+j=n+m-2
        x x x x     看规律可知，第一道i+j=0；第二道i+j=1。。第三道2。。假设道上行+列为k，则每个k有k则每一道的长度是1，2，3，3，2，1然而这几条规律用处不大
        x x x x     关键还是看第一个人那几个规律
     */
    public int[] findDiagonalOrder2(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        int count=m*n;
        int[] rs=new int[m*n];
        int i=0;
        int j=0;
        int index=0;
        while (index<count){
            while (index<count&&i>=0&&i<m&&j>=0&&j<n){
                rs[index++]=mat[i][j];
                i--;
                j++;
            }
            if(j>=n){//穿右边了。这里必须是检测j优先，如果检测i就错了。而下面那里必须是检查i优先
                j=n-1;
                i+=2;
            }
            if (i<0){
                i=0;//向右上方向穿到上沿线了。
            }
            while (index<count&&i>=0&&i<m&&j>=0&&j<n){
                rs[index++]=mat[i][j];
                i++;
                j--;
            }
            if (i>=m){//穿下面
                i=m-1;
                j+=2;
            }
            if(j<0){
                j=0;
            }
        }
        return rs;
    }
}
