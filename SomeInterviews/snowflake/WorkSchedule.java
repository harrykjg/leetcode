package SomeInterviews.snowflake;

import java.util.ArrayList;
import java.util.List;

public class WorkSchedule {

    //就是backtracking
    public List<String> findSchedules(int workHours, int dayHours, String pattern) {
        // TODO: Implement findSchedules logic
        List<String> rs=new ArrayList<>();
        int sum=0;
        for(int i=0;i<pattern.length();i++){
            if(Character.isDigit(pattern.charAt(i))){
                sum+=pattern.charAt(i)-'0';
            }
        }
        int gap=workHours-sum;
        dfs(0,0,gap,dayHours,pattern,rs);
        return rs;
    }
    void dfs(int b,int cur,int gap,int dayH,String pattern,List<String> rs){
        if(b==pattern.length()){
            if(cur==gap){
                rs.add(pattern);
            }
            return;
        }
        if(cur>gap){
            return;
        }
        if(pattern.charAt(b)!='?'){
            dfs(b+1,cur,gap,dayH,pattern,rs);
            return;
        }
        StringBuilder sb=new StringBuilder(pattern);
        int min=Math.min(gap,dayH);
        for(int i=0;i<=min;i++){
            char c=(char)(i+'0');
            sb.setCharAt(b,c);
            dfs(b+1,cur+i,gap,dayH,sb.toString(),rs);
        }

    }
}
