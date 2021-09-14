package DataStruct;

/**
 * Created by yufengzhu on 12/14/17.
 */
public class UglyNumber {
    //还做不出来,
    //http://my.oschina.net/Tsybius2014/blog/495953
//http://www.nowamagic.net/algorithm/algorithm_FindUglyNumber.php
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num%2==0){
            num/=2;
        }
        while (num%3==0){
            num/=3;
        }
        while (num%5==0){
            num/=5;
        }
        return num==1;
    }

    //05/27/2020,改了一次对了
    public boolean isUgly2(int num) {
        if (num <= 0) {
            return false;
        }
        while(num%5==0){
            num/=5;
        }
        while(num%3==0){
            num/=3;
        }
        while(num%2==0){
            num/=2;
        }

        return num==1;
    }
//6/8/2021
    public boolean isUgly3(int num) {
        if(num<=0){
            return false;
        }
        int temp=num;
        while (temp%2==0){
            temp/=2;
        }
        while (temp%3==0){
            temp/=3;
        }
        while (temp%5==0){
            temp/=5;
        }
        return temp==1;
    }
}
