package linkedin;

import java.util.HashMap;
import java.util.Map;

public class NumberofSubmatricesThatSumtoTarget1074 {
    static void main() {

    }
    //不会
    //https://leetcode.cn/problems/number-of-submatrices-that-sum-to-target/solutions/801064/gong-shui-san-xie-you-hua-mei-ju-de-ji-b-uttw/
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int[][] dp=new int[matrix.length+1][matrix[0].length+1];//同304题，为啥要长度+1呢，因为左上角算第一个的时候，要减dp[i-1][j-1】，不加会越界
        for (int i=1;i<=matrix.length;i++){
            for (int j=1;j<=matrix[0].length;j++){

                dp[i][j]=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+matrix[i-1][j-1];

            }
        }
        int rs=0;
        //下标不太好理解，这里开始搞错了，应该外面两层是枚举行数，最里层才是枚举列数
        for (int i=1;i<dp.length;i++) {
            for (int j = 1; i-j >=0 ; j++) {//j的意义是从第i行看i行到i-j行单行的submatrix，如j=1时，是[2,0]到[2,0]到[2,1]，到[2,2]。。。这只是1行
                                            //的每个k的枚举，然后j=2，即i行到i-2行中间的submatrix，即2行的submatrix
                Map<Integer,Integer> map=new HashMap<>();//开始放里面的循环里了，就是560题
                map.put(0,1);
                for(int k=1;k<dp[0].length;k++){

                    int curSum=dp[i][k]-dp[i-j][k];//这里开始想错了，以为是需要左上角dp[i][j]-dp[i-k][j]-dp[i][j-1]+dp[i-k][j-1]
                    if(map.containsKey(curSum-target)){
                        rs+=map.get(curSum-target);
                    }
                    map.put(curSum,map.getOrDefault(curSum,0)+1);//开始这里也写错了，是放cursum，不是cursum-k
                }
            }
        }
        return rs;
    }
}
