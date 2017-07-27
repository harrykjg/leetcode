package binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 7/24/17.
 */
public class FindPeakElementII {

    public static void main(String[] args){
        int[][] a={{1 ,2 ,3 ,6 ,5},{16,41,23,22,6},{15,17,24,21,7},{14,18,19,20,10},{13,14,11,10,9}};
        List<Integer> rs= findPeakII(a);
        for(int i:rs){
            System.out.println(i);
        }
    }
    //http://blog.csdn.net/jmspan/article/details/51724388  它的二维二分好懂一点
    //https://segmentfault.com/a/1190000003488794  它的一维二分其实就是二分了行,列是用O(n)搜索的,它的二维二分感觉不太对劲?因为它的findrowpeak是线性的
    public static List<Integer> findPeakII(int[][] a) {

        List<Integer> rs=new ArrayList<>();
        for(int i=0;i<a.length;i++){//对每一行进行二分,所以算是一维二分把
            int b=0;
            int e=a[0].length-1;
            while (b+1<e){
                int mid=b+(e-b)/2;
                if(a[i][mid]<a[i][mid+1]){
                    b=mid;
                }else{
                    e=mid;
                }
            }
            if(a[i][b]<a[i][e]){//说明第i行第e列是行上的peak,那么现在就再找这一列上它是否是peak,就是验上下两点就完了
                if(i+1<a.length&&a[i+1][e]<a[i][e]&&i-1>=0&&a[i-1][e]<a[i][e]){
                    rs.add(i);
                    rs.add(e);
                    return rs;
                }else if(i+1<a.length&&a[i+1][e]<a[i][e]&&i==0){//如果i是0行的话,只要这个元素下面的元素小于它就行了
                    rs.add(i);
                    rs.add(e);
                    return rs;
                }else if(i-1>=0&&a[i-1][e]<a[i][e]&&i+1==a.length){//如果i是最后一行的话,只要这个元素上面的元素小于它就行了
                    rs.add(i);
                    rs.add(e);
                    return rs;
                }
            }else{
                if(i+1<a.length&&a[i+1][b]<a[i][b]&&i-1>=0&&a[i-1][b]<a[i][b]){
                    rs.add(i);
                    rs.add(b);
                    return rs;
                }else if(i+1<a.length&&a[i+1][b]<a[i][b]&&i==0){
                    rs.add(i,b);
                    return rs;
                }else if(i-1>=0&&a[i-1][b]<a[i][b]&&i+1==a.length){
                    rs.add(i);
                    rs.add(b);
                    return rs;
                }
            }

        }
        return rs;
    }
}
