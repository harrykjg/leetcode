import java.util.*;

/**
 * Created by 502575560 on 11/6/16.
 */
public class IntersectionofTwoArrays {
    //就遍历就好了,O(n)复杂度
    //https://discuss.leetcode.com/topic/45685/three-java-solutions
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] rs={};
        if(nums1.length==0|| nums2.length==0){
            return rs;
        }

        HashSet<Integer> set=new HashSet<>();
        HashSet<Integer> set2=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            set.add(nums1[i]);
        }

        for(int i=0;i<nums2.length;i++){
            if(set.contains(nums2[i])){
                set2.add(nums2[i]);
            }
        }
//http://book.2cto.com/201309/31817.html 这里set.toArray方法以前海没遇过,toArray要接受一个对象类型得数组,原声类型如int[]不行,必须是Integer[]才行
        //但是integer数组是没法直接转成int数组得,http://blog.csdn.net/yongh701/article/details/43488507,还是得用for循环才行
        Integer[] rs2=new Integer[set2.size()];
        rs2=(Integer[])set2.toArray(rs2);
        int[] rs3=new int[set2.size()];
        for(int i=0;i<rs3.length;i++){
            rs3[i]=rs2[i];
        }
        return rs3;


    }
}
