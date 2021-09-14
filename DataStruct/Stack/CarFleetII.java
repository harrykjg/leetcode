package DataStruct.Stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class CarFleetII {
    public static void main(String[] args){
        int[][] cars={{3,4},{5,4},{6,3},{9,1}};
//        int[][] cars={{3,4},{5,4},{6,3},{7,1},{9,1},{10,3},{11,4}};
        CarFleetII cf=new CarFleetII();
        cf.getCollisionTimes(cars);
    }
    //9/3/2021  以为自己可以模拟出来，模拟的解法还是太难写了，因为前面的车的速度可能是分段变化的，比如当前的车想撞到前面的车，追了一段时间，这个前面的车过了n秒
    // 撞到他前面的车，则速度变了，这两个撞车之后继续往前跑，当前车还在追，而这辆车过一会又撞到前面的车，则速度又变了。然后可以一直这样变下去，导致说要算当前车
    //的话要一直这样分段用不同的前车速度算
    //https://www.youtube.com/watch?v=0tB6Ozo40Kk 看他的解释
    //https://leetcode.com/problems/car-fleet-ii/discuss/1105620/Java-oror-clean-O(n)-Monotonic-Stack-Solution-oror-Detailed-Explanations 直接stack就行了，他用的dq
    public double[] getCollisionTimes(int[][] cars) {
        Stack<Integer> st=new Stack<>();
        double[] rs=new double[cars.length];
        rs[rs.length-1]=-1;
        st.push(cars.length-1);
        for (int i=cars.length-2;i>=0;i--){
            while (!st.isEmpty()) {//这里开始写的if就不对了
                double preSpeed=cars[st.peek()][1];
                double speedDiff=cars[i][1]-preSpeed;
                double dist=cars[st.peek()][0]-cars[i][0];
                if (speedDiff>0){//这里判断也不好写，顺序写不对也错，如果说当前车速度大于前车
                    double time1=dist/speedDiff;
                    double preTime=rs[st.peek()];
                    if (preTime==-1||time1<preTime){
                        rs[i]=time1;
                        break;
                    }else{//time1==pretime的话说明还是不需要这个前车
                        st.pop();
                    }
                }else{//否则说明当前表面上车追不上前车，但不保证前车撞了之后也不能追上,但反正这个前车是不用算的，因为要算也是算前车撞的车
                    st.pop();
                }
            }
            if(rs[i]==0){//如果还没赋值，说明找不到撞的车
                rs[i]=-1;
            }
            st.push(i);
        }
        return rs;
    }
}
