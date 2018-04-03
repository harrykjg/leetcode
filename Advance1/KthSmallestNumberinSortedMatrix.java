package Advance1;

import java.util.HashSet;

/**
 * Created by 502575560 on 7/16/17.
 */
public class KthSmallestNumberinSortedMatrix {
    //http://www.jiuzhang.com/solutions/kth-smallest-number-in-sorted-matrix/
    //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
    public static void main(String[] a){
        KthSmallestNumberinSortedMatrix ks=new KthSmallestNumberinSortedMatrix();
        int[][] m={{1,5},{2,5}};
        ks.kthSmallest(m,2);
    }
    //3／24／2018，九章第二轮，还是不太会,后来看提示用二分on值，自己写了一下改了好几次accept了，用的是二分模版，比非模版的多了个set，
    //九章答案没有用set但是用了boolean去找exist。非模版的不用set就行，真是吊
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        int count=0;
        int b=matrix[0][0];
        int e=matrix[matrix.length-1][matrix[0].length-1];
        int m=0;
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                set.add(matrix[i][j]);
            }
        }
        while (b<e-1){
            m=b+(e-b)/2;
            int c=findCount(matrix,m);
            if(c==k&&set.contains(m)){
                return m;
            }
            if(c<k){
                b=m;
            }else {
                e=m;
            }
        }
        if(set.contains(b)&&findCount(matrix,b)>=k){//这里容易喽findcount这个条件，而且要>=k才对，==不对的，因为如果matrix里有很多个相同的数字，比如前四个最小的数都是4，那么我找
            //第三个小数即k=3时是符合条件的，很容易错
            return b;
        }
        return e;
    }
    int findCount(int[][] m,int target){
        int count=0;
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){
                if(m[i][j]>target){
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
