package binarysearch;

public class SearchinRotatedSortedArrayII {
    //7/19/2021。还是挺恶心的。靠记吧。只要m不等于b就可以分成线性移动或者继续二分。继续二分那再想清楚就好了
    //https://www.cnblogs.com/grandyang/p/4325840.html
    //https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28202/Neat-JAVA-solution-using-binary-search
    public boolean search(int[] nums, int target) {
        if (nums.length==0){
            return false;
        }
        int b=0;
        int e=nums.length-1;
        int m=b+(e-b)/2;
        if(nums[m]==target){
            return true;
        }
        while (b<e){
            m=b+(e-b)/2;
            if (nums[m]==target){
                return true;
            }
            if (nums[m]==nums[b]){
                if (nums[e]==nums[m]){
                    b++;//注意不能把e也左移，只能移动一个
                }else {//举例如33334，左边肯定是全相等的
                    b=m+1;//不是b=m，因为比如b=3 e=4那么m还是3。所以要加1
                }
            }else{
                if (nums[m]<=nums[e]){//右边排序，注意没有这个等号也会错。可能是右边全相等
                    if (target<=nums[e]&&target>=nums[b]){
                        b=m+1;
                        continue;
                    }else {
                        e=m;
                    }
                    continue;
                }else {
                    if (nums[m]>nums[b]){
                        if (target<=nums[m]&&target>=nums[b]){
                            e=m;
                        }else {
                            b=m+1;
                        }
                    }
                }
            }
        }
        return nums[b]==target;//while循环是b<e就不能漏这个
    }
    //8/14/2021 记这个，用b和e判断，用在第一题也是可以用b和e判断，只是这里多了一个else if(nums[m]>nums[e])
    public boolean search2(int[] nums, int target) {
        int b=0;
        int e=nums.length-1;
        while(b<e){
            int m=(b+e)/2;
            if(nums[m]==target){
                return true;
            }
            if(nums[m]<nums[e]){
                if(target>nums[m]&&target<=nums[e]){
                    b=m+1;
                }else{
                    e=m;
                }
            }else if(nums[m]>nums[e]){
                if(target>=nums[b]&&target<nums[m]){
                    e=m;
                }else{
                    b=m+1;
                }
            }else{
                e--;//注意这里必须是e--，b++是不行的.反例是[1,0,1,1,1]找0
            }
        }
        return nums[b]==target;
    }
}
