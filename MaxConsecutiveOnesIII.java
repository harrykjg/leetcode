public class MaxConsecutiveOnesIII {
    public static void main(String[] args){
        MaxConsecutiveOnesIII mc=new MaxConsecutiveOnesIII();
        //                                          0 1 2 3 4 5 6 7 8 9 10
        System.out.println(mc.longestOnes(new int[]{1,0,0,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,0,0,1,0,1,0,0,1,0,0,1,1},9));
    }
    //8/19/2021 不会。看到提示prefix sum还是不会，看到sliding window好像会了但还是没写对。改了挺久才过
    //https://leetcode.com/problems/max-consecutive-ones-iii/discuss/248287/java-sliding-windows-with-comments-in-line 看别人写的很简单
    public int longestOnes(int[] nums, int k) {
        int rs=0;
        int b=0;
        int e=0;
        int kk=k;
        while (e<nums.length){
            while (e<nums.length&&nums[e]==1){
                e++;
            }
            while (e<nums.length&&nums[e]==0&&kk>0){
                e++;
                kk--;
            }
            while (e<nums.length&&nums[e]==1){
                e++;
            }
            rs=Math.max(rs,e-b);
            if (kk>0){//如果kk还可以填说明还可以右扩，若kk大于0就开始缩的话是错的
                continue;
            }
            while (b<e&&nums[b]==1){//一定要挪到下一个0之后，即把这个0填的kk收回来，
                b++;
            }
            if (b<e&&nums[b]==0){
                kk++;
                b++;
            }
            if(k==0){//不加这个会死循环动不了
                e++;
                b++;
            }
        }
        return rs;
    }

    //别人的写法是先一直扩，遇到0也一直扩，直到用了k+1个filp之后再缩回一个filp，再看大小。这个和以前做过的lengthOfLongestSubstringKDistinct还有minimumwindowsubstring
    //那些的扩缩都不一样，那边没有说扩到超过一个的。
    public int longestOnes2(int[] nums, int k) {//还是不太好想
        int b=0;
        int used=0;
        int rs=0;
        for (int e=0;e<nums.length;e++){
            if (nums[e]==0){
                used++;
            }
            while (used>k){
                if (nums[b]==0){
                    used--;
                }
                b++;
            }//他没超用k时得到的长度也是记录的了
            rs=Math.max(e-b+1,rs);
        }
        return rs;
    }
}
