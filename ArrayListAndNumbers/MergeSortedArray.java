package ArrayListAndNumbers;

public class MergeSortedArray {
    //8/15/2021 题目有点奇怪，m的值肯定是nums1有值的长度，不会说是nums1有3个非0的数但是给你m=2
    //就从后往前两个pointer
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index=nums1.length-1;
        int i1=m-1;
        int i2=n-1;
        while(i1>=0&&i2>=0){
            if (nums1[i1]>nums2[i2]){
                nums1[index]=nums1[i1--];
            }else {
                nums1[index]=nums2[i2--];
            }
            index--;
        }
        while (i1>=0){
            nums1[index]=nums1[i1--];
        }
        while (i2>=0){
            nums1[index]=nums2[i2--];
        }
    }
}
