/**
 * Created by yufengzhu on 7/8/18.
 */
public class RangeSumQueryMutable {
// 要用线段树，不会,看他们的思路再自己写
    // http://www.cnblogs.com/Liok3187/p/4978027.html 概念
    //http://blog.csdn.net/sbitswc/article/details/50175009
    //http://www.2cto.com/kf/201406/306699.html
    // http://www.raychase.net/3534#range-sum-query-mutable

    SegmentTree root=new SegmentTree();

    public RangeSumQueryMutable(int[] nums) {
        if(nums!=null&&nums.length!=0){
            buildTree(0,nums.length-1,nums,root);
        }
    }
    public int buildTree(int b,int e,int[] nums,SegmentTree root){
        root.begin=b;
        root.end=e;
        if(b==e){
            root.sum=nums[b];
            return root.sum;
        }
        int mid=(b+e)/2;
        SegmentTree left=new SegmentTree();
        root.left=left;
        int l=buildTree(b,mid,nums,left);
        SegmentTree right=new SegmentTree();
        root.right=right;
        int r=buildTree(mid+1,e,nums,right);
        root.sum=l+r;
        return root.sum;
    }
    void update(int i, int val) {
        if(i<root.begin||i>root.end){
            return;
        }
        updateTree(i,val,root);
    }
    public int updateTree(int i,int val,SegmentTree root){//自底向上更新
        if(root.begin==root.end&&root.begin==i){
            int diff=val-root.sum;
            root.sum=root.sum+diff;
            return diff;
        }
        int m=(root.begin+root.end)/2;
        int diff=0;
        if(i<=m){
            diff=updateTree(i,val,root.left);
        }else{
            diff=updateTree(i,val,root.right);
        }
        root.sum+=diff;
        return diff;
    }
    public int sumRange(int i, int j) {
        return helper(root,i,j);
    }
    public int helper(SegmentTree root,int i,int j){
        if(root.begin==root.end||(root.begin==i&&root.end==j)){
            return root.sum;
        }
        int m=(root.begin+root.end)/2;
        if(j<=m){
            return helper(root.left,i,j);
        }else if(i>m){
            return helper(root.right,i,j);
        }else{
            return helper(root.left,i,m)+helper(root.right,m+1,j);
        }
    }

}
class SegmentTree {
    SegmentTree left;
    SegmentTree right;
    int begin;
    int end;
    int sum;
}
