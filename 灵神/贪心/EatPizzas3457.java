package 灵神.贪心;

import java.util.Arrays;
import java.util.PriorityQueue;

public class EatPizzas3457 {
    static void main() {
        int[] a={5,3,1,1,3,3,2,1,5,4,2,1};
        System.out.println(maxWeight(a));
    }

    //1/1/2026 没想对规则 比如 有12个数，其中最大的是5，5，4，3，3，3，。。。。那样说明要选3次，其中两次是基数天一次是偶数天，那么我
    //必然可以使这两次基数天选前1，2大的数，比如第一天选5xxx，第二天选33xx，第三天选5xxx得出答案13。开始想的是第一天5xxx的话，
    // 第二天肯定要贪心的选4，那么必然需要54xx这样，那么第三天只能选3了。这样只能得出12
    //规律就是设奇数天是a则可以选前a个最大的，偶数天是b，则选（n-a）之后的第二大（跳着选）选b次，对上面的例子就是（选，选，不选，选）
    //思考，会不会 第四个选的数很小，而不选的这个数很大，导致不是最优解？看例子 10，10，10，5，发现如果是（选，不选，选，选）不会得到更好答案

    //https://leetcode.cn/problems/eat-pizzas/solutions/3076629/tan-xin-pythonjavacgo-by-endlesscheng-fpjx/
    public static long maxWeight(int[] pizzas) {
        long rs=0;
        Arrays.sort(pizzas);
        int count=pizzas.length/4;
        int odd=(int)Math.ceil(count/2d);//注意这个写法，如果是3/2=1.5,ceil之后是2，所以是对的
        int even=count-odd;
        int index=pizzas.length-1;
        for(int i=0;i<odd;i++){
            rs+=pizzas[index--];
        }
        for (int i=0;i<even;i++){
            index--;
            rs+=pizzas[index--];
        }

        return rs;
    }
}
