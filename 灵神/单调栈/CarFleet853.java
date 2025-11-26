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
}
