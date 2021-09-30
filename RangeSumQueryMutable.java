/**
 * Created by yufengzhu on 7/8/18.
 */
public class RangeSumQueryMutable {
// 要用线段树，不会,看他们的思路再自己写
    // http://www.cnblogs.com/Liok3187/p/4978027.html 概念
    //http://blog.csdn.net/sbitswc/article/details/50175009
    //http://www.2cto.com/kf/201406/306699.html
    // http://www.raychase.net/3534#range-sum-query-mutable

    public static void main(String[] args){
        RangeSumQueryMutable rs=new RangeSumQueryMutable();
        rs.NumArray2(new int[]{9,-8});
        rs.update2(0,3);
        System.out.println(rs.sumRange2(1,1));

        System.out.println(rs.sumRange2(0,1));
    }
    SegmentTree root=new SegmentTree();

    public void RangeSumQueryMutable(int[] nums) {
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
    public int updateTree(int i,int val,SegmentTree root){//自底向上更新,居然是返回int的
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

    SegmentTree root2;
    public void NumArray2(int[] nums) {
        root2=buildTree2(0,nums.length-1,nums);
    }
    SegmentTree buildTree2(int b,int e,int[] nums){
        if (b==e){
            return new SegmentTree(b,e,nums[b]);
        }
        int m=(b+e)/2;
        SegmentTree left=buildTree2(b,m,nums);
        SegmentTree right=buildTree2(m+1,e,nums);
        SegmentTree root=new SegmentTree(b,e,left.sum+right.sum);
        root.left=left;
        root.right=right;
        return root;
    }
    public void update2(int index, int val) {
        if (index<root2.begin||index>root2.end){
            return;
        }
        updateHelper2(root2,index,val);
    }
    void updateHelper2(SegmentTree node, int index,int val){
        if(node==null){
            return;
        }
        if (node.begin==index&&node.end==index){
            node.sum=val;
            return;
        }
        int m=node.begin+(node.end-node.begin)/2;
        if (index<=m){
            updateHelper2(node.left,index,val);
        }else {
            updateHelper2(node.right,index,val);
        }
        node.sum=node.left.sum+node.right.sum;
    }

    public int sumRange2(int left, int right) {
        return helper2(root2,left,right);
    }

    int helper2(SegmentTree root,int begin,int end){
        if (root==null){
            return 0;
        }
        if (root.begin>end||root.end<begin){
            return 0;
        }
        if (root.begin==begin&&root.end==end){
            return root.sum;
        }
        int m=(root.begin+root.end)/2;//他这个query不是基于输入的begin和end，而是基于root的begin和end判断,不是很好想
        if (end<=m){
            return helper2(root.left,begin,end);
        }else if (begin>m){
            return helper2(root.right,begin,end);
        }
        return helper2(root.left,begin,m)+helper2(root.right,m+1,end);
    }


}
class SegmentTree {
    SegmentTree left;
    SegmentTree right;
    int begin;
    int end;
    int sum;
    public SegmentTree(){};
    public SegmentTree(int begin,int end,int sum){
        this.begin=begin;
        this.end=end;
        this.sum=sum;
    }
}
