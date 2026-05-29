package SomeInterviews.purestorage;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber202 {
    //好的方法是这样的
    //https://leetcode.com/problems/happy-number/solutions/3767573/easy-java-solution-two-pointers-floyds-t-ich2/
    //这个是基础写法，用set判断一个数是否重复出现过
    public boolean isHappy(int n) {
        Set<Long> set=new HashSet<>();
        set.add((long)n);
        long num=n;
        while (num!=0){
            long cur=0;
            while (num>0){
                cur+=(num%10)*(num%10);
                num/=10;
            }
            if(cur==1){
                return true;
            }
            if(set.contains(cur)){
                return false;
            }else {
                set.add(cur);
            }
            num=cur;
        }
        return false;
    }
    //好的方法
    public boolean isHappy2(int n) {
        long slow=n;
        long fast=n;
        do{
            slow=helper(slow);
            fast =helper(helper(fast));

        }while (slow!=fast);//有环的话肯定会相遇，然后结束while循环，没环的话最后也会在最后一个点相遇，那么就看最后的答案是不是1了
        return slow==1;//或者fast==1也是一样的
    }
    long helper(long n){
        long num=n;
        long cur=0;
        while (num>0){
            cur+=(num%10)*(num%10);
            num/=10;
        }
        return cur;
    }


}
