package ArrayListAndNumbers;

/**
 * Created by 502575560 on 7/9/17.
 */
public class MedianofTwoSortedArrays {
    //还是很难
    //http://www.cnblogs.com/zuoyuan/p/3759682.html
    //http://blog.csdn.net/zxzxy1988/article/details/8587244
    //http://blog.csdn.net/linhuanmars/article/details/19905515   代码看它的
    //http://blog.csdn.net/yutianzuijin/article/details/11499917/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1=nums1.length;
        int l2=nums2.length;
        int l=l1+l2;
        if(l%2==1){
            return find(nums1,0,nums1.length-1,nums2,0,nums2.length-1,l/2+1);
        }else{
            return (find(nums1,0,nums1.length-1,nums2,0,nums2.length-1,l/2)+find(nums1,0,nums1.length-1,nums2,0,nums2.length-1,l/2+1))*0.5d;
        }
    }
    int find(int[] nums1,int b1,int e1,int[] nums2,int b2,int e2,int k){
        int l1=e1-b1+1;
        int l2=e2-b2+1;
        if(l1>l2){//保证l1比较小,否则nums1和nums2换一下
            return find(nums2,b2,e2,nums1,b1,e1,k);
        }
        if(l1==0){
            return nums2[b2+k-1];
        }
        if(k==1){
            return Math.min(nums1[b1],nums2[b2]);
        }
        int i1=Math.min(k/2,l1);//再把k分成2分,如果k/2还大于nums1的长度的话,那么只能取nums1的最后一个了,并且再nums1取少了那么就要在nums2
        int i2=k-i1;          //中多取些,这就是为啥i2=k-i ,
        if(nums1[b1+i1-1]==nums2[b2+i2-1]){
            return nums1[b1+i1-1];
        }
        else if(nums1[b1+i1-1]<nums2[b2+i2-1]){
            return find(nums1,b1+i1,e1,nums2,b2,e2+i2-1,k-i1);
        }else{
            return find(nums1,b1,b1+i1-1,nums2,b2+i2-1,e2,k-i2);
        }
    }
}
