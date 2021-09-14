package Advance1;

import java.util.HashSet;

/**
 * Created by 502575560 on 7/16/17.
 */
public class KthSmallestNumberinSortedMatrix {//和Search a 2D Matrix II的matrix一样，就是求的东西不一样
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
//6/14/2021，改了一次对了，其实是写的不好的，首先不需要set取检测是否二分出来的元素存在。还有找count的写法实际上是暴力法，应该利用sort的性质找count
    public int kthSmallest2(int[][] matrix, int k) {
        int b=matrix[0][0];
        int e=matrix[matrix.length-1][matrix[0].length-1];
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                set.add(matrix[i][j]);
            }
        }
        while (b+1<e){
            int m=b+(e-b)/2;
            int count=findCount2(matrix,m);
            if (count==k&&set.contains(m)){
                return m;
            }
            if (count<k){
                b=m;
            }else {
                e=m;
            }
        }
        if (findCount2(matrix,b)>=k&&set.contains(b)){//这里没写=就错了，matrix里会有重复的数字
            return b;
        }
        return e;

    }
    int findCount2(int[][] m,int z){
        int count=0;
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){ //其实这种写法是暴力的，见以前的第二个链接。就是先比较第一行找最后一列，如果比z小则说明这一行前面的都小，
                if(m[i][j]>z){      //否则列左移，然后看下一行，列延续上一行的列值。或者看最后一行第一列，然后移动到下一列，行延续上一行的值。
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
