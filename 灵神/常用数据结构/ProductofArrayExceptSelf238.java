package 灵神.常用数据结构;

public class ProductofArrayExceptSelf238 {

    //4/9/2026设了所有两个array，其实右边那个必须要，边走边算就行了
    public int[] productExceptSelf(int[] nums) {
        int[] left=new int[nums.length];
//        int[] right=new int[nums.length];
        int pre=1;
        for (int i=1;i<nums.length;i++){
            left[i]=nums[i-1]*pre;
            pre=left[i];
        }
        left[0]=1;
        int[] rs=new int[nums.length];
        rs[rs.length-1]=left[rs.length-1];//这个下标有点恶心
        pre=1;
        for (int i=nums.length-2;i>=0;i--){
            int right=pre*nums[i+1];
            pre=right;
            rs[i]=left[i]*right;
        }
        return rs;
    }
}
