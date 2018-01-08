package DataStruct;

/**
 * Created by yufengzhu on 1/7/18.
 */
public class KthSmallestElementinaSortedMatrix {

    public static void main(String[] args){
        int[][] m={{1,5,9},{10,11,13},{12,13,15}};
//        int[][] m={{1,2},{1,3}};
        System.out.println(kthSmallest(m,8));

    }
    //九章第二轮，还是不会
    //用的是二分on值,吊
    public static int kthSmallest(int[][] matrix, int k) {
        if(matrix.length==0){
            return -1;
        }
        int b=matrix[0][0];
        int e=matrix[matrix.length-1][matrix[0].length-1];
        while (b<e){
            int m=b+(e-b)/2;
            int count=0;

            for(int i=0;i<matrix.length;i++){
                int j=0;//这里j是正着数还是倒着数应该是一样的把，但是count那里就合之前写的code不一样的，多了=号
                while (j<matrix[0].length&&matrix[i][j]<=m){//这里=号我觉得就是靠感觉，很难解释
                    j++;
                    count++;
                }
            }
            if(count>=k){//注意这里和之前的不一样，这里有等号才对，因为那里是count<k，就是m值要从左右都逼近，才能得出对的值，想想
                e=m;
            }else{
                b=m+1;
            }
        }
        return b;
    }
}
