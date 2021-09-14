package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 502575560 on 8/19/17.
 */
public class CountofSmallerNumber {
    public static void main(String[] a){
        CountofSmallerNumber cs=new CountofSmallerNumber();
        int[] ar={1,8,7,5,3,5,0,10};
        int[] q={6,12,7};
        System.out.println(cs.countOfSmallerNumber3(ar,q));
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
        int count;//用来记录val相同的点
        int leftcount;
        public segmentTree(int val){
            this.val=val;
            count=1;
        }
    }
//6/10/2021 这题应该是线段树的变种应用吧，他没用到线段树区间的特点，所以不需要建左右区间,我觉得就是构造了个bst外加一些property
    public List<Integer> countOfSmallerNumber3(int[] A, int[] queries) {
        List<Integer> rs=new ArrayList<>();
        if(A.length==0){
            for (int i=0;i<queries.length;i++){
                rs.add(0);
            }
            return rs;
        }
        segmentTree root=new segmentTree(A[0]);

        for(int i=1;i<A.length;i++){
            insert(A[i],root);
        }
        for(int q:queries){
            rs.add(query2(root,q));
        }
        return rs;
    }
    //建树不是很好想，开始想着一个插入的node，如果路过了比自己小的node，应该会把路过的node记在leftcount吧，比如先插入1，再插入8，那么8应该知道有一个数比他小，
    //结果这里不需要这样。而8后面插入的小于8得数才会被计入leftcount。这样也行的原因是在query时，总是从头开始搜索，所以说比如query8，那么肯定会经过1，经过的时候
    //把1带上就行了。都要画图理解
    segmentTree insert(int a, segmentTree root){//自顶而下插入
        if(root==null){
            root=new segmentTree(a);
        }else if(root.val==a){
            root.count++;
            return root;
        }
        else if (root.val>a){
            segmentTree left=insert(a,root.left);
            root.left=left;
            root.leftcount++;
        }
        else if(root.val<a){
            segmentTree right=insert(a,root.right);
            root.right=right;
        }
        return root;
    }
    int query2(segmentTree node,int target){
        if(node==null){
            return 0;
        }
        if(node.val==target){
            return node.leftcount;
        }
        if(node.val>target){
            return query2(node.left,target);
        }else {
            return node.leftcount+node.count+query2(node.right,target);
        }

    }
}
