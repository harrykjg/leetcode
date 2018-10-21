import java.util.*;

/**
 * Created by yufengzhu on 9/18/18.
 */
public class twentyfourGame {
    public static void main(String[] args){
        twentyfourGame tf=new twentyfourGame();
        double[] nums=new double[]{4,2,2,5};
        System.out.print(tf.judgePoint242(nums));
    }

    //知道是回溯，但是写的不对，看别人的思路再自己写的
    //https://www.cnblogs.com/grandyang/p/8395062.html
    //https://blog.csdn.net/gangtaolun8493/article/details/78063690
    public boolean judgePoint24(int[] nums) {
        Arrays.sort(nums);
        ArrayList<Double> dou=new ArrayList<>();
        dou.add((double)nums[0]);
        dou.add((double)nums[1]);
        dou.add((double)nums[2]);
        dou.add((double)nums[3]);
        return dfs(dou);


    }
    boolean dfs(ArrayList<Double> al){
        if(al.size()==1&&Math.abs(al.get(0)-24.0)<0.001){
            return true;
        }
        for(int i=0;i<al.size();i++){
            for(int j=0;j<al.size();j++){
                if(i==j){
                    continue;
                }
                ArrayList<Double> a=new ArrayList<>();
                for(int k=0;k<al.size();k++){
                    if(k==i||k==j){
                        continue;
                    }
                    a.add(al.get(k));
                }
                a.add(al.get(i)+al.get(j));
                if(dfs(a)){
                    return true;
                }
                a.remove(a.size()-1);
                a.add(al.get(i)*al.get(j));
                if(dfs(a)){
                    return true;
                }
                a.remove(a.size()-1);
                a.add(al.get(i)-al.get(j));
                if(dfs(a)){
                    return true;
                }
                a.remove(a.size()-1);
                a.add(al.get(j)-al.get(i));
                if(dfs(a)){
                    return true;
                }
                a.remove(a.size()-1);
                if(al.get(j)!=0d){
                    a.add(al.get(i)/al.get(j));
                    if(dfs(a)){
                        return true;
                    }
                    a.remove(a.size()-1);
                }
                if(al.get(i)!=0d){
                    a.add(al.get(j)/al.get(i));
                    if(dfs(a)){
                        return true;
                    }
                    a.remove(a.size()-1);
                }
            }
        }
        return false;
    }
//pinterest 不能用括号版本的，即这四个数字不能随意组合,自己想的，貌似还是对的
    //http://scripts.cac.psu.edu/staff/r/j/rjg5/scripts/Math24.pl?a=12&b=4&c=8&d=9可以用这个验证
    public boolean judgePoint242(double[] nums) {
        Stack<Double> st=new Stack<>();
        ArrayList<Double> al=new ArrayList<>();//想法是就是类似basic calculator那样，先在stack里放第一个数，然后剩下的3个数做candidate，然后每个dfs试试加减乘除，再分别放到4个stack里，继续dfs
        for(int i=1;i<nums.length;i++){
            al.add(nums[i]);
        }
        st.push(nums[0]);
        return dfs(st,al);

    }
    boolean dfs(Stack<Double> st,ArrayList<Double> candidate){
        if(candidate.size()==0){
            int rs=0;
            while (!st.isEmpty()){
                rs+=st.pop();
            }
            if(Math.abs(rs-24)<0.001){
                return true;
            }
            return false;
        }
        Double cur=candidate.get(0);
        ArrayList<Double> temp=new ArrayList<>();//要把st里的东西都复制出来到新的stack里，stack没有那样的构造方法只能全部倒出来再加进去
        while (!st.isEmpty()){
            temp.add(st.pop());
        }

        Stack<Double> st2=new Stack<>();
        Stack<Double> st3=new Stack<>();
        Stack<Double> st4=new Stack<>();
        Stack<Double> st5=new Stack<>();
        for(int i=temp.size()-1;i>=0;i--){
            st2.push(temp.get(i));
            st3.push(temp.get(i));
            st4.push(temp.get(i));
            st5.push(temp.get(i));
        }

        st2.push(st2.pop()*cur);
        candidate.remove(0);
        if(dfs(st2,candidate)){
            return true;
        }
        candidate.add(0,cur);
        if(cur!=0){
            st3.push(st3.pop()/cur);
            candidate.remove(0);
            if(dfs(st3,candidate)){
                return true;
            }
            candidate.add(0,cur);
        }
        st4.push(cur);
        candidate.remove(0);
        if(dfs(st4,candidate)){
            return true;
        }
        candidate.add(0,cur);

        st5.push(-cur);
        candidate.remove(0);
        if(dfs(st5,candidate)){
            return true;
        }
        candidate.add(0,cur);

        return false;

    }
}
