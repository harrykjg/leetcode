package SomeInterviews;

public class facebookMonotonicCount {
    public static void main(String[] args){
        facebookMonotonicCount fm=new facebookMonotonicCount();
        System.out.println(fm.count(new int[]{1,3,3,4,4,2,2,1,1}));//5,3,2,3,4
    }

    //https://www.1point3acres.com/bbs/thread-777442-1-1.html
    int count(int[] nums){
        if (nums.length<=1){
            return 0;
        }
        int rs=0;
        int i=1;
        int pre=nums[0];
        while (i<nums.length){
            if (nums[i]==pre){
                i++;
                continue;
            }
            if (nums[i]>pre){
                while (i<nums.length&&nums[i]>=pre){
                    pre=nums[i];
                    i++;
                }
                rs++;
            }else{
                while (i<nums.length&&nums[i]<=pre){
                    pre=nums[i];
                    i++;
                }
                rs++;
            }
        }
        return rs;
    }
}
