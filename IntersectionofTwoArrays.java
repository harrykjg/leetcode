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
    //8/13/2021
    //看了答案评论如下
    /*
    This is a Facebook interview question.
They ask for the intersection, which has a trivial solution using a hash or a set.

Then they ask you to solve it under these constraints:
O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
You are told the lists are sorted.

Cases to take into consideration include:
duplicates, negative values, single value lists, 0's, and empty list arguments.
Other considerations might include
sparse arrays.

function intersections(l1, l2) {
    l1.sort((a, b) => a - b) // assume sorted
    l2.sort((a, b) => a - b) // assume sorted
    const intersections = []
    let l = 0, r = 0;
    while ((l2[l] && l1[r]) !== undefined) {
       const left = l1[r], right = l2[l];
        if (right === left) {
            intersections.push(right)
            while (left === l1[r]) r++;
            while (right === l2[l]) l++;
            continue;
        }
        if (right > left) while (left === l1[r]) r++;
         else while (right === l2[l]) l++;

    }
    return intersections;
}
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        List<Integer> rs=new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i1=0;
        int i2=0;
        while (i1<nums1.length&&i2<nums2.length){
            int a=nums1[i1];
            int b=nums2[i2];
            if(i1>0&&nums1[i1-1]==nums1[i1]){
                i1++;
                continue;
            }
            if(i2>0&&nums2[i2-1]==nums2[i2]){
                i2++;
                continue;
            }
            if (a==b){
                rs.add(a);
                i1++;
                i2++;
                continue;
            }
            if(a<b){
                i1++;
            }else{
                i2++;
            }
        }

        int[] rss=new int[rs.size()];
        for(int i=0;i<rs.size();i++){
            rss[i]=rs.get(i);
        }
        return rss;
    }
}
