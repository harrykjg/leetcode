package ArrayListAndNumbers;

import java.util.Stack;

public class AsteroidCollision {
    //9/2/2021 明显是stack，但是写的很烂，主要是没判断stack里的东西的正负
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st=new Stack<>();
        for (int i=0;i<asteroids.length;i++){
            if (asteroids[i]>0){
                st.push(asteroids[i]);
            }else {
                if (st.isEmpty()||st.peek()<0){
                    st.push(asteroids[i]);
                    continue;
                }
                while (!st.isEmpty()&&st.peek()>0&&st.peek()<-asteroids[i]){
                    st.pop();
                }
                if (!st.isEmpty()&&st.peek()==-asteroids[i]){
                    st.pop();
                }else if (st.isEmpty()){
                    st.push(asteroids[i]);
                }
            }
        }
        int[] rs=new int[st.size()];
        for (int i=rs.length-1;i>=0;i--){
            rs[i]=st.pop();
        }
        return rs;
    }
}
