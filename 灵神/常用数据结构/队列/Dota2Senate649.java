package 灵神.常用数据结构.队列;

import java.util.Deque;
import java.util.LinkedList;

public class Dota2Senate649 {
    static void main() {

    }
    //开始想不是谁多就谁赢么？其实不是，假如rrrdddd这个情况，前面三个r可以ban掉3个d，剩下的那个d ban掉一个r，那么第二轮就是两个r一个d
    //就是模拟就行了,ban人的话就是先ban最先的那个敌人
    public String predictPartyVictory(String senate) {
        Deque<Integer> dq1=new LinkedList<>();
        Deque<Integer> dq2=new LinkedList<>();
        char[] ch=senate.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(ch[i]=='R'){
                dq1.offer(i);
            }else{
                dq2.offer(i);
            }
        }
        while(!dq1.isEmpty()&&!dq2.isEmpty()){
            int r=dq1.pollFirst();
            int d=dq2.pollFirst();
            if(r<d) {
                dq1.offer(r+senate.length());//就是放到下一轮
            }else{
                dq2.offer(d+senate.length());
            }
        }
        if(dq1.isEmpty()){
            return "Dire";
        }
        return "Radiant";
    }
}
