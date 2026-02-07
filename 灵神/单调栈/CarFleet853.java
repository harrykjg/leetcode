package 灵神.单调栈;

import java.util.*;

public class CarFleet853 {
    public static void main(String[] args) {
        int[] pos={0,4,2};
        int[] speed={2,1,3};
        System.out.println(carFleet(10,pos,speed));
    }
    public static int carFleet(int target, int[] position, int[] speed) {
        int rs=0;
        List<Car> al=new ArrayList<>();
        for(int i=0;i<position.length;i++){
            al.add(new Car(position[i],speed[i]));
        }
        Collections.sort(al,(o1, o2) -> o1.pos-o2.pos);
        Deque<Double> st=new LinkedList<>();
        for (int i=al.size()-1;i>=0;i--){
            double t=(target-al.get(i).pos)/(double)al.get(i).speed;
            if(st.isEmpty()){
                st.push(t);
                continue;
            }
            boolean flag=false;
            while (!st.isEmpty()&&st.peekLast()<t){
                st.pop();
                flag=true;
            }
            if(flag){
                rs++;
            }
            st.push(t);

        }
        return rs+1;
    }
    static class Car {
        int pos;
        int speed;

        public Car(int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }
    }
    //1/20/2026，想到是按pos sort,但是还是没想清楚这种情况，pos=【5，9，10】，speed=【3，1，1】，可知第一个和第二个车在11才相遇，
    //那么他和第三个车的关系怎么算呢？是把前两个车整合到11这个car放进stack吗？然后再扫到10？结果不是,stack里放的是时间！
    //下面这种写法顺，巧妙在用的是时间，而从小的pos开始算的时间是按他不受任何影响到达target的时间，因此看下一pos的车时就可以比较他的时间和
    //上一个车的时间，如果上一个车的时间小于等于当前的话说明必然会回合成一个车队，因此把上一个车pop掉，最后stack的size就是车队的数量
    //https://leetcode.cn/problems/car-fleet/solutions/3757845/dan-diao-zhan-cong-zhui-ji-wen-ti-dao-me-393t/
    public  int carFleet2(int target, int[] position, int[] speed) {
        List<int[]> al=new ArrayList<>();
        for (int i=0;i<position.length;i++){
            al.add(new int[]{position[i],speed[i]});
        }
        Collections.sort(al,(a,b)->a[0]-b[0]);
        Stack<Double> st=new Stack<>();
        for(int i=0;i<al.size();i++){
            double time=(target-al.get(i)[0])/(double)al.get(i)[0];
            if(st.isEmpty()){
                st.push(time);
                continue;
            }
            while (!st.isEmpty()&&time>=st.peek()){
                st.pop();
            }
            st.push(time);
        }
        return st.size();
    }
}
