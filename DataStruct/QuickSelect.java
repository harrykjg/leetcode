package DataStruct;

/**
 * Created by yufengzhu on 9/4/18.
 */
public class QuickSelect {//用于快速选择第k小的东西
    //可以用leetcode kth largest element来验证
    public static void main(String[] args){
        QuickSelect qs=new QuickSelect();
        int[] nums={1,3,4,2};//{5,4,8,9,2,4
        System.out.print(qs.select2(nums,1));
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
    //6/8/2021
    public int select2(int[] nums,int k){

        return partition(nums,0,nums.length-1,k);
    }
    int partition(int[] nums,int b,int e,int k){

        int x=nums[b];
        int bb=b;
        int ee=e;
        while (bb<ee){
            while (bb<ee&&nums[ee]>x){
                ee--;
            }
            if(bb<ee){
                nums[bb]=nums[ee];
                bb++;
            }
            while (bb<ee&&nums[bb]<=x){
                bb++;
            }
            if(bb<ee){
                nums[ee]=nums[bb];
                ee--;
            }
        }
        nums[bb]=x;
        if(bb==k-1){
            return x;
        }
        if(bb<k-1){
            return partition(nums,bb+1,e, k);
        }
        return partition(nums,b,bb-1,k);
    }

}
