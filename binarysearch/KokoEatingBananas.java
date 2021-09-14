package binarysearch;

public class KokoEatingBananas {
    //8/29/2021 二分法 改了几下过，主要是怎么判断能否整除一个数，我用的是一个int和一个double比较除出来的值，若double大则说明要int+1
    //其实可以这样好些
    //          countHour += pile / K;
    //            if (pile % K != 0){
    //                countHour++;
//                  }
    public int minEatingSpeed(int[] piles, int h) {
        int rs=0;
        int sum=0;
        int max=0;
        for (int i:piles){
            sum+=i;
            max=Math.max(max,i);
        }
        double num=sum/(double)h;
        int num2=sum/h;
        int min=0;
        if (num2==num){
            min=num2;
        }else {
            min=num2+1;
        }
        while (min<max){
            int m=(min+max)/2;
            if(valid(m,piles,h)){
                max=m;
            }else{
                min=m+1;
            }
        }
        if (valid(min,piles,h)){//这个直接返回min就对不用检测。
            return min;
        }
        return min+1;
    }
    boolean valid(int m,int[] piles,int h){
        for (int i:piles){
            double d1=i/(double)m;
            int d2=i/m;
            if (d1==d2){
                h-=d2;
            }else {
                h-=(d2+1);
            }
            if (h<0){
                return false;
            }
        }
        return h>=0;
    }

}
