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
}
