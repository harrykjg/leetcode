
public class ConvertSortedArraytoBinarySearchTree {
//9/5/2018,
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length==0){
            return null;
        }
        int m=(nums.length-1)/2;
        TreeNode root=new TreeNode(nums[m]);
        root.left=build(nums,0,m-1);
        root.right=build(nums,m+1,nums.length-1);
        return root;

    }
    TreeNode build(int[] nums,int b,int e){
        if(b>e){
            return null;
        }
        if(b==e){
            return new TreeNode(nums[b]);
        }
        int m=b+(e-b)/2;
        TreeNode root=new TreeNode(nums[m]);
        root.left=build(nums,b,m-1);
        root.right=build(nums,m+1,e);
        return root;
    }

}
