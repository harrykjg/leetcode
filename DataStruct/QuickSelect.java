package DataStruct;

/**
 * Created by yufengzhu on 9/4/18.
 */
public class QuickSelect {//用于快速选择第k大的东西
    public static void main(String[] args){
        QuickSelect qs=new QuickSelect();
        int[] nums={5,4,8,9,2,4};
        System.out.print(qs.slect(nums,5));
    }
    //自己写的联系quickselect
    public int slect(int[] nums,int k){
        return quick(nums,0,nums.length-1,k-1);
    }
    int quick(int[] nums,int b,int e,int k){
        int key=nums[b];
        int l=b;
        int r=e;
        while (l<r){
            while (l<r&&nums[r]>key){
                r--;
            }
            if(l<r){
                nums[l]=nums[r];
                l++;
            }
            while (l<r&&nums[l]<=key){
                l++;
            }
            if(l<r){
                nums[r]=nums[l];
                r--;
            }
        }
        nums[l]=key;
        if(l==k){
            return nums[l];
        }
        if(l>k){
            return quick(nums,b,l-1,k);//开始写的l没有-1和+1就不对了
        }else{
            return quick(nums,l+1,e,k);
        }
    }
}
