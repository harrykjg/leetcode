package DataStruct;

public class ConvertSortedArraytoBinarySearchTree {
    //04/23/2020,一次过
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0){
            return null;
        }
        return helper(nums,0, nums.length-1);
    }
    TreeNode helper(int[] nums,int b,int e){
        if(nums.length==0){
            return null;
        }
        if(b>e){
            return null;
        }
        int m=(b+e)/2;
        TreeNode root=new TreeNode(nums[m]);
        root.left=helper(nums,b,m-1);
        root.right=helper(nums,m+1,e);
        return root;
    }
}
