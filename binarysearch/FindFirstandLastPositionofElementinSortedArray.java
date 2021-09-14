package binarysearch;

public class FindFirstandLastPositionofElementinSortedArray {
    //7/19/2021。二分找到最左target然后二分找最右，其中找到target之后也是一步一步挪的，否则再二分太麻烦了
    public int[] searchRange(int[] nums, int target) {
        int[] rs=new int[]{-1,-1};
        if (nums.length==0){
            return rs;
        }
        int b=0;
        int e=nums.length-1;
        while (b<=e){//有=号的就要b=m+1和e=m-1，即b和e都要变，否则可能会死循环
            int m=b+(e-b)/2;
            if (nums[m]==target){
                if ((m-1>=0&&nums[m-1]!=nums[m])||m==0){
                    rs[0]=m;
                    break;
                }else {
                    e=m-1;
                }
            }else if (nums[m]>target){
                e=m-1;
            }else {
                b=m+1;
            }
        }
        if (rs[0]==-1){
            return rs;
        }

        e=nums.length-1;
        while (b<=e){
            int m=b+(e-b)/2;
            if (nums[m]==target){
                if ((m+1<nums.length&&nums[m+1]!=nums[m])||m==nums.length-1){
                    rs[1]=m;
                    break;
                }else {
                    b=m+1;
                }
            }else if (nums[m]>target){
                e=m-1;
            }else {
                b=m+1;
            }
        }

        return rs;
    }
    //8/13/2021用while（b<e)写二分法，不太好写，用上面又等号的好写些
    public int[] searchRange2(int[] nums, int target) {
        int[] rs=new int[2];
        rs[0]=-1;
        rs[1]=-1;
        if(nums.length==0){
            return rs;
        }
        if(nums.length==1){
            if(nums[0]==target){
                return new int[]{0,0};
            }
            return rs;
        }
        int b=0;
        int e=nums.length-1;
        while(b<e){
            int m=(b+e)/2;
            if(nums[m]==target){
                if(m==0||(m>0&&nums[m-1]!=target)){
                    rs[0]=m;
                    break;
                }else{
                    e=m;
                    continue;
                }
            }
            if(nums[m]>target){
                e=m;
            }else{
                b=m+1;
            }
        }
        if(rs[0]==-1){
            if(nums[b]==target){
                rs[0]=b;
            }else{
                return new int[]{-1,-1};
            }

        }
        b=rs[0];
        e=nums.length-1;
        while(b<e){
            int m=(b+e)/2;
            if(nums[m]==target){
                if(m==nums.length-1||nums[m+1]!=target){
                    rs[1]=m;
                }else{
                    b=m+1;
                    continue;
                }
            }
            if(nums[m]<target){
                b=m+1;
            }else{
                e=m;
            }
        }
        if(rs[1]==-1){
            rs[1]=b;
        }
        return rs;
    }
}
