package DataStruct.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by yufengzhu on 7/16/18.
 */
public class ExclusiveTimeofFunctions {
    public static void main(String[] a){
        ExclusiveTimeofFunctions et=new ExclusiveTimeofFunctions();
        List<String> ls=new ArrayList<>();
        ls.add("0:start:0");
        ls.add("0:start:2");
        ls.add("0:end:5");
        ls.add("1:start:6");
        ls.add("1:end:6");
        ls.add("0:end:7");
        int[] rs=et.exclusiveTime(2,ls);
        System.out.print("x");
    }
    //举个例子

//真tm恶心这题,改了很久都不行,关键是找不到错的例子，lc的错的例子太长了
    //https://blog.csdn.net/gqk289/article/details/76062982 看这个代码，其实不需要判断前后两个process是否相同，因为题目意思说了，
// 不可能是前面是一个process的start，后面是另一个process的end，肯定是相同的
    //https://blog.csdn.net/huanghanqian/article/details/77160234
    public int[] exclusiveTime(int n, List<String> logs) {

    }
}
