package binarysearch;

/**
 * Created by 502575560 on 7/24/17.
 */
public class Sqrtx {
    //用模版,最后如果我比较b和e谁更准确一点好像不行,直接返回小的就行
    public int sqrt(int x) {
        // write your code here
        if(x<=1){
            return x;
        }
        int b=0;//注意开始这里写成b=1是错的
        int e=x;//这里开始写成x/2的话处理不了x=2的情况,其实e写成x的话也就是比写成x/2少了一次循环而已
        while (b+1<e){
            int mid=b+(e-b)/2;
            if(mid==x/mid){
                return (int)mid;
            }
            if(mid<x/mid){
                b=mid;
            }else{
                e=mid;
            }
        }

        return b;
    }
}
