package bit;

/**
 * Created by 502575560 on 5/23/17.
 */
public class UTF8Validation {
    public static void main(String[] args){
        UTF8Validation ut=new UTF8Validation();
        int[] n=new int[]{145};
        System.out.println(ut.validUtf83(n));
//        System.out.println(0b10000000);
        //0b10000000的0b的意思是二进制,后面10000000才代表实际的二进制数
    }

//题目意思理解错了,data数组的长度不能作为一个已知条件,是要从第一个数开始看,他开头有几个连续1才能说明这个数组应该包括几个开头是10的byte,再去验证后面是否正确,
// 如[197, 130, 1] 11000101 10000010 00000001.这样的居然是对的,第一个197的二进制开头有2个1(第一个数开头是10的是不符合条件的),说明后面要跟（2-1）个开头是10的数,这里
// 后面又跟了个1,由于1小于128即其二进制开头不是10,就可以实际上跟多几个小于128的都行,如[197, 130, 1,1,3,5,6]这样的都对,https://discuss.leetcode.com/topic/78734/why-250-145-145-145-145-expected-is-false/3
//[1,2]这样的也对
//http://blog.csdn.net/MebiuW/article/details/52445248 他这个accept不了,可能是mask那错了? https://discuss.leetcode.com/topic/57178/o-n-solution-using-java/4 下面的这个可以
    //https://discuss.leetcode.com/topic/58338/bit-manipulation-java-6ms
    //http://www.cnblogs.com/grandyang/p/5847597.html
    public boolean validUtf8(int[] data) {
        int l=data.length;
        if(l==0){
            return true;
        }
        int ones=0;
        for(int i=0;i<data.length;i++){
            if(ones>0){
                if((data[i]&0b11000000)==0b10000000){//0b开头代表后面的数是二进制。
                    ones--;
                }else {
                    return false;
                }
            }else{
                ones=judge(data[i]);
                if(ones==-1){
                    return false;
                }
                if(ones==0){
                    continue;
                }
                ones--;
            }
        }

        return ones==0;

    }
    private int judge(int n){
        if((n&0b11111000)==0b11110000){//注意是前面五个1==后面是四个1
            return 4;
        }
        if((n&0b11110000)==0b11100000){
            return 3;
        }
        if((n&0b11100000)==0b11000000){
            return 2;
        }
        if((n&0b11000000)==0b10000000){
            return -1;
        }
        if(n>=248){//248二进制是11111000,就是说n大于等于248的话前面就有5个1,而utf8只能有4byte所有直接就错了
            return -1;
        }
        return 0;
    }

    public boolean validUtf82(int[] data) {
        if (data == null || data.length == 0) return false;

        int idx = 0;
        int len = data.length;
        while (idx < len) {
            int follows = getFollows(data[idx++]);
            if (follows == -1) return false;

            for (int i = 0; i < follows; i++) {
                if (idx >= len) {
                    return false;
                } else {
                    if (!isFollowed(data[idx++])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* Return the number of segment/segments that needs/need to be followed */
    private int getFollows(int num) {
        if ((num & 0b10000000) == 0) return 0;  // use parenthesis for &, due to its low priority
        else if ((num & 0b11100000) == 0b11000000) return 1;
        else if ((num & 0b11110000) == 0b11100000) return 2;
        else if ((num & 0b11111000) == 0b11110000) return 3;
        return -1;
    }

    /* to check if the num starts with "10" */
    private boolean isFollowed(int num) {
        return (num & 0b11000000) == 0b10000000;
    }

    //8/31/2021 写的不好，看回以前的，主要是理解题目，然后要知道怎么位移数字去得到前几位有几个1
    public boolean validUtf83(int[] data) {
        int i=0;
        while (i<data.length){
            int cur=data[i];
            if (((cur>>7)&1)==0){
                i++;
                continue;
            }
            if (((cur>>5)&0b111)==0b110){//开始写成((cur>>5)&0b110)==0b110是错的，假设cur>>5得出111，则他&110还是等于110
                int index=i+1;
                if (index>=data.length){
                    return false;
                }
                i++;
                while (i<=index){
                    int now=data[i];
                    if (!valid(now)){
                        return false;
                    }
                    i++;
                }
                continue;
            }
            if (((cur>>4)&0b1111)==0b1110){
                int index=i+2;
                if (index>=data.length){
                    return false;
                }
                i++;
                while (i<=index){
                    int now=data[i];
                    if (!valid(now)){
                        return false;
                    }
                    i++;
                }
                continue;
            }
            if (((cur>>3)&0b1111)==0b1110){
                int index=i+3;
                if (index>=data.length){
                    return false;
                }
                i++;
                while (i<=index){
                    int now=data[i];
                    if (!valid(now)){
                        return false;
                    }
                    i++;
                }
                continue;
            }
            if (cur>=0b11111000){//开头大于4个1的也是错的
                return false;
            }else{//比如10010001这样的，开头是1个1的，则都不符合，直接false
                return false;
            }
        }
        return true;
    }
    boolean valid(int n){
        if (((n>>6)&0b11)!=0b10){
            return false;
        }
        return true;
    }
}
