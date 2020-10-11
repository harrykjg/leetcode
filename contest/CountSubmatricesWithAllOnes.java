package contest;

public class CountSubmatricesWithAllOnes {
    //不会，参考他的，但是他视频里没有解释的很清楚，看ipad的上的解释
    //https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720227/Pre-computation-VIDEO-solution-O(m*n*n)
    //用stack的貌似看不懂
    //https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720265/Java-Detailed-Explanation-From-O(MNM)-to-O(MN)-by-using-Stack
//https://www.geeksforgeeks.org/number-of-submatrices-with-all-1s/
    public int numSubmat(int[][] mat) {
        int[][] help=new int [mat.length][mat[0].length];
        for(int i=0;i<help.length;i++){
            int c=0;
            for(int j=mat[0].length-1;j>=0;j--){
                if(mat[i][j]==1){
                    c++;
                }else{ c=0;
                }
                help[i][j]=c;
            }
        }
        int rs=0;
        for(int i=0;i<help.length;i++){
            int c=0;
            for(int j=0;j<mat[0].length;j++){
                int min =Integer.MAX_VALUE;
                for(int k=i;k<mat.length;k++){
                    min=Math.min(min,help[k][j]);
                    rs+=min;

                }


            }
        }
        return rs;
    }
}
