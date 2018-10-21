import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by yufengzhu on 10/6/18.
 */
public class NextClosestTime {
    public static void main(String[] args){
        NextClosestTime nc=new NextClosestTime();
        nc.nextClosestTime("20:48");
    }
    int minm=Integer.MAX_VALUE;
    int minh=Integer.MAX_VALUE;

    //自己写的，改了好多次accept了，方法其实也凑活，就是写起来容易漏容易错代码量太大了
    public String nextClosestTime(String time) {
        HashSet<Integer> set=new HashSet<>();
        ArrayList<Integer> al=new ArrayList<>();
        char[] ch=time.toCharArray();
        for(char c:ch){
            if(Character.isDigit(c)){
                if(set.add(c-'0')){
                    al.add(c-'0');
                }
            }
        }
        int curMinm=Integer.parseInt(time.substring(3));
        int curHour=Integer.parseInt(time.substring(0,2));
        Collections.sort(al);
        smallestMin(0,0,al,curMinm,set);
        if(minm==Integer.MAX_VALUE){//如果没有找到比curMinm大的分钟数的话，说明得至少要找下一个小时的最小的分钟数，所以要再找一个大于-1的分钟数。
            smallestMin(0,0,al,-1,set);
        }else{//否砸就说明找到一个大于curMinm的分钟数就可以了
            String sminm=minm<10?"0"+minm:String.valueOf(minm);
            return time.substring(0,2)+":"+sminm;
        }
        smallestHour(0,0,al,curHour,set);//如果没能找到比curMinm大的分钟数的话，就要找大于curhour的最小hour了，和上面同理
        if(minh==Integer.MAX_VALUE){
            smallestHour(0,0,al,-1,set);
        }
        String sminm=minm<10?"0"+minm:String.valueOf(minm);
        String sminh=minh<10?"0"+minh:String.valueOf(minh);

        return sminh+":"+sminm;

    }
    void smallestMin(int cur,int curNum,ArrayList<Integer> al,int target,HashSet<Integer> set){
        if(curNum==2){
            if(validMin(cur,set)&&cur>target){
                minm=Math.min(minm,cur);
            }
            return;
        }

        for(int i=0;i<al.size();i++){
            if(curNum==0&&i>0&&al.get(i)==al.get(i-1)){
                continue;
            }
            smallestMin(cur*10+al.get(i),curNum+1,al,target,set);
        }
    }
    void smallestHour(int cur,int curNum,ArrayList<Integer> al,int target,HashSet<Integer> set){
        if(curNum==2){
            if(validHour(cur,set)&&cur>target){
                minh=Math.min(minh,cur);
            }
            return;
        }

        for(int i=0;i<al.size();i++){
            if(curNum==0&&i>0&&al.get(i)==al.get(i-1)){
                continue;
            }
            smallestHour(cur*10+al.get(i),curNum+1,al,target,set);
        }
    }
    boolean validHour(int a,HashSet<Integer> set){
        if(!set.contains(0)&&a<=10){
            return false;
        }
        return a>=0&&a<=23;
    }
    boolean validMin(int a,HashSet<Integer> set){
        if(!set.contains(0)&&a<=10){
            return false;
        }
        return a>=0&&a<=59;
    }
}
