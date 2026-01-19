package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.List;

public class twentyfourGame679 {
    static void main() {
        int[] a={1,5,9,1};
        System.out.println(judgePoint24(a));
    }
    //12/29/2025 不太能想清楚
    //https://leetcode.cn/problems/24-game/solutions/3756006/mei-ci-he-bing-liang-zhang-pai-di-gui-xi-0sdu/
    public static boolean judgePoint24(int[] cards) {
        List<Double> al=new ArrayList<>();
        for(int i:cards){
            al.add((double)i);
        }
        return dfs(al);
    }
    static boolean dfs(List<Double> al){
        if(al.size()==1){
            if(Math.abs(al.get(0)-24d)<0.001){
                return true;
            }
            return false;
        }
        for(int i=0;i<al.size();i++){
            for (int j=i+1;j<al.size();j++){
                List<Double> next=new ArrayList<>();
                for (int k = 0; k < al.size(); k++) {
                    if (k != i && k != j){
                        next.add(al.get(k));
                    }
                }
                next.add(al.get(i)+al.get(j));
                if(dfs(next)){
                    return true;
                }
                next.removeLast();

                next.add(al.get(i)-al.get(j));
                if(dfs(next)){
                    return true;
                }
                next.removeLast();

                next.add(al.get(j)-al.get(i));
                if(dfs(next)){
                    return true;
                }
                next.removeLast();

                next.add(al.get(i)*al.get(j));
                if(dfs(next)){
                    return true;
                }
                next.removeLast();

                if(al.get(j)!=0){
                    next.add(al.get(i)/al.get(j));
                    if(dfs(next)){
                        return true;
                    }
                    next.removeLast();
                }
                if(al.get(i)!=0){
                    next.add(al.get(j)/al.get(i));
                    if(dfs(next)){
                        return true;
                    }
                    next.removeLast();
                }

            }
        }
        return false;

    }
}
