package 灵神.sidingWindow.双指针.双数组双指针;

public class MaximumDistanceBetweenaPairofValues1855 {
    public static void main(String[] args) {

    }

    //一次过但是while条件要想一下
    public int maxDistance(int[] nums1, int[] nums2) {
        int rs=0;
        int b=0;
        int e=0;
        while (b<nums1.length){//这里要注意，不用管e，因为如果b到了最后了，说明nums1[b]已经是最小了，假如e还没到到最后，那么e再往右也没有意义，因为e也是越往又越小。
            //如果有答案的话，在b的最后一个的时候，内层的while循环已经便利nums2了。但是如果e到了最后，b再往右是有可能有答案的

            while (b<nums1.length&&e+1<nums2.length&&nums1[b]<=nums2[e+1]){
                e++;
            }
            if (b<=e&&nums1[b]<=nums2[e]){
                rs=Math.max(rs,e-b);
            }
            b++;

        }
        return rs;
    }


}
