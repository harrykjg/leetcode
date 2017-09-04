package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 8/19/17.
 */
public class CountofSmallerNumber {
    //注意和leetcode的Count of Smaller Numbers After Self不一样的

    //http://blog.csdn.net/martin_liang/article/details/74278503  线段树解法
    //http://www.cnblogs.com/EdwardLiu/p/5176693.html 线段树解法
    //用的方法是先sort然后binary search看有多少个小于的
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        Arrays.sort(A);
        List<Integer> rs=new ArrayList<>();
        for(int i:queries){
            rs.add(bin(A,i));
        }
        return rs;
    }
    //用了模版,举例子看看
    int bin(int[] a,int n){
        if(a.length==0){
            return 0;
        }
        int b=0;
        int e=a.length-1;
        int mid=0;
        while (b+1<e){
            mid=b+(e-b)/2;
            if(a[mid-1]<n&&a[mid]>=n){
                return mid;
            }
            if(a[mid]>=n){
                e=mid;
            }else{
                b=mid;
            }
        }
        if(a[b]<n&&a[e]>=n){//这里要举例子看看才行
            return b+1;
        }
        if(a[b]<n){
            return e+1;
        }
        return 0;
    }
}
