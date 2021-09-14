package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfusingNumberII {
    //9/10/2021 只想到暴力法，看提示可以自己dfs产生出所有的符合条件的num.1不是confusing，因为1反过来还是1，而10就是，因为10反过来是01即1。
    int rs=0;
    public int confusingNumberII(int n) {
        List<Integer> ls=new ArrayList<>();
        ls= Arrays.asList(0,1,6,8,9);
        dfs(0,ls,n);
        return rs;
    }
    void dfs(long cur,List<Integer> ls,int n){//这里不用long的话下面cur*10可能越界导致无限循环把
        if (cur<=n&&valid(cur)){
            rs++;
        }
        for (int i=0;i<ls.size();i++){
            if (i==0&&cur==0){
                continue;
            }
            long temp=cur*10+ls.get(i);
            if (temp>n){
                return;
            }
            dfs(temp,ls,n);
        }
    }
    boolean valid(long ori){//把数字反过来看和原来是否相同
        long num=0;
        long temp=ori;
        while (temp>0){
            long t=temp%10;
            if (t==6){//只有6和9是反过来是不一样的
                num=num*10+9;
            } else if (t==9){
                num=num*10+6;
            }else {
                num=num*10+t;
            }
            temp/=10;
        }
        return ori!=num;
    }
}
