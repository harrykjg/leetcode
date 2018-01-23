package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 8/19/17.
 */
public class CountofSmallerNumber {
    public static void main(String[] a){
        CountofSmallerNumber cs=new CountofSmallerNumber();
        int[] ar={1,2,3,4};
        int[] q={0,12,3};
        cs.countOfSmallerNumber2(ar,q);
    }
    //注意和leetcode的Count of Smaller Numbers After Self不一样的

    //http://blog.csdn.net/martin_liang/article/details/74278503  貌似不是标准的线段树，是类似的变形
    //http://www.cnblogs.com/EdwardLiu/p/5176693.html 他说的构造树的复杂度是n，应该是nlogn吧
    //用的方法是先sort然后binary search看有多少个小于的
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        Arrays.sort(A);
        List<Integer> rs=new ArrayList<>();
        for(int i:queries){
            rs.add(bin(A,i));
        }
        return rs;
    }
    //用了模版,举例子看看
    int bin(int[] a,int n){
        if(a.length==0){
            return 0;
        }
        int b=0;
        int e=a.length-1;
        int mid=0;
        while (b+1<e){
            mid=b+(e-b)/2;
            if(a[mid-1]<n&&a[mid]>=n){
                return mid;
            }
            if(a[mid]>=n){
                e=mid;
            }else{
                b=mid;
            }
        }
        if(a[b]<n&&a[e]>=n){//这里要举例子看看才行
            return b+1;
        }
        if(a[b]<n){
            return e+1;
        }
        return 0;
    }

    //1／21／2018，九章第二轮，sort之后再二分法就没意思了,线段树法还是不会，要练，
    public List<Integer> countOfSmallerNumber2(int[] A, int[] queries) {
        List<Integer> rs=new ArrayList<>();
        if(A==null||queries.length==0){
            return rs;
        }
        if (A.length == 0) {
            for (int i=0; i<queries.length; i++){
                rs.add(0);
            }
            return rs;
        }
        segmentTree root=new segmentTree(A[0]);
        for(int i=1;i<A.length;i++){
            build(root,A[i]);
        }
        for(int i:queries){
            rs.add(query(i,root));
        }
        return rs;
    }
    //这个build方法开始我用的是void方法以为可以去建，比如root。left是空，然后build(root.left,a)之后root。left我以为就建好了，其实还是空，
    //我以为是引用传递所以应该可以的，可能这里是因为root。left本来就是空，那么空的copy是另一个空了？然后这个新的null指向了something并不印象圆的的null
    segmentTree build(segmentTree root,int a){//build的时候开始想着新插入的大的数的leftcount如何maintain，结果是不用maintain，在query的时候从小的node累加过来的
        if(root==null){
            root=new segmentTree(a);
        }
        else if(root.val==a){
            root.count++;

        }
        else if(root.val>a){
            root.leftcount++;
            root.left=build(root.left,a);
        }else{
            root.right=build(root.right,a);
        }
        return root;
    }
    int query(int a,segmentTree node){
        if(node==null){
            return 0;
        }
        if(a==node.val){
            return node.leftcount;
        }
        if(a>node.val){
            return node.count+node.leftcount+query(a,node.right);
        }
        return query(a,node.left);
    }
    class segmentTree{
        int val;
        segmentTree left;
        segmentTree right;
        int count;
        int leftcount;
        public segmentTree(int val){
            this.val=val;
            count=1;
        }
    }
}
