package 灵神.二分法;

public class FindFirstandLastPositionofElementinSortedArray34 {
    public static void main(String[] args) {

    }
    //还是写成了这种比较笨的写法
    //https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/1980196/er-fen-cha-zhao-zong-shi-xie-bu-dui-yi-g-t9l9/这个写法好一些
    public int[] searchRange(int[] nums, int target) {
        int[] rs=new int[2];
        rs[0]=-1;
        rs[1]=-1;
        int b=0;
        int e=nums.length-1;
        if (nums.length==0){
            return rs;
        }
        while (b<e-1){
            int m=b+(e-b)/2;
            if(nums[m]==target){
                if((m-1>=0&&nums[m-1]!=target)||m==0){
                    rs[0]=m;
                    break;
                }else{
                    e=m;
                }
            }else if(nums[m]>target){
                e=m;
            }else{
                b=m;
            }
        }
        if(rs[0]==-1){
            if(target==nums[b]){
                rs[0]=b;
            }else if(target==nums[e]){
                rs[0]=e;
            }else{
                rs[0]=-1;
            }
        }
        b=0;e=nums.length-1;
        while (b<e-1){
            int m=b+(e-b)/2;
            if(nums[m]==target){
                if((m+1>nums.length&&nums[m+1]!=target)||m==nums.length-1){
                    rs[1]=m;
                    break;
                }else{
                    b=m;
                }
            }else if(nums[m]>target){
                e=m;
            }else{
                b=m;
            }
        }
        if(rs[1]==-1){
            if(target==nums[e]){
                rs[1]=e;
            }else if(target==nums[b]){
                rs[1]=b;
            }else{
                rs[1]=-1;
            }
        }

        return rs;
    }
}
