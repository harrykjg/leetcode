package DataStruct;

/**
 * Created by yufengzhu on 1/7/18.
 */
public class KthSmallestElementinaSortedMatrix {

    public static void main(String[] args){
        int[][] m={{1,5,9},{10,11,13},{12,13,15}};
//        int[][] m={{1,2},{1,3}};
        System.out.println(kthSmallest(m,8));
        KthSmallestElementinaSortedMatrix ks=new KthSmallestElementinaSortedMatrix();
        ks.kthSmallest2(m,8);

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
                while (j<matrix[0].length&&matrix[i][j]<=m){//这里=号意思是小于等于这个数的都算上
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
    //9／7／2018，还是不好想，不记得是二分on值,而且也不好写，不用二分模版的话b=m+1，e=m才对，还是很麻烦.
    public  int kthSmallest2(int[][] matrix, int k) {
        if(matrix.length==0){
            return -1;
        }
        int b=matrix[0][0];
        int e=matrix[matrix.length-1][matrix[0].length-1];
        int m=0;
        while (b+1<e){
            m=b+(e-b)/2;
            if(tooSmall(matrix,k,m)){
                b=m;//用模版的话b=m+1和e=m-1的话会错
            }else{
                e=m;
            }
        }
        if(tooSmall(matrix,k,b)){
            return e;
        }
        return b;
    }
    boolean tooSmall(int[][] matrix,int k,int m){//toosmall意思是m太小了，导致小于等于m的count不够多
        int count=0;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][matrix.length-1]<=m){
                count+=matrix[0].length;
            }else{
                for(int j=0;j<matrix[0].length;j++){
                    if(matrix[i][j]<=m){
                        count++;
                    }else{
                        break;
                    }
                }
            }
            if(count>=k){//这里容易少写=
                return false;
            }
        }
        return true;
    }
}
