package SomeInterviews.moveworks;

import java.util.ArrayList;
import java.util.List;

public class ParsingCitation {
    /*
    1. string parsing.  Validated  brackets，不准用regex. valid “xxx xxx [1],  xxxxx[2-5]”  return all valid ranges [1,1][2,5]，
     注意，只有这两种情况是符合要求的，所以实际上只需要几个（java的话）indexof和split就可以筛出来。可惜意识过来的时候已经35+分钟了。
      2. merge intervals from 1就是把第一题的输出给merge了。lc原题级别的。
     */
    public List<int[]> parseCitations(String s) {//这里假设s是“xxx xxx [1],  xxxxx[2-5]” 这样的，不一定有逗号
            char[] ch=s.toCharArray();
            List<int[]> rs=new ArrayList<>();
            int i=0;
            while (i<s.length()){
                int left=s.indexOf('[',i);//原来有这种，第二个参数是from index
                if(left==-1){
                    break;
                }
                int right=s.indexOf(']',left+1);
                if(right==-1){
                    break;
                }
                String value=s.substring(left+1,right);
                value=value.replace(" ","");//删除空格
                int[] range=helper(value);
                if(range!=null){
                    rs.add(range);
                }
                i=right+1;

            }
            return rs;

    }
    int[] helper(String s){
        if(s==null||s.length()==0){
            return null;
        }
        int dash=s.indexOf('-');
        if(dash==-1){
            if(!isNumber(s)){
                return null;
            }
            int num = Integer.parseInt(s);
            return new int[]{num,num};
        }
        // 如果有多个 '-'，不合法
        if (s.indexOf('-', dash + 1) != -1) {
            return null;
        }
        String left = s.substring(0, dash);
        String right = s.substring(dash + 1);
        if (!isNumber(left) || !isNumber(right)) {
            return null;
        }
        int start = Integer.parseInt(left);
        int end = Integer.parseInt(right);
        if (start > end) {
            return null;
        }
        return new int[]{start, end};
    }
    boolean isNumber(String s){
        if(s==null||s.length()==0){
            return false;
        }
        for(char c:s.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
    //part 2，gpt的代码
    public List<int[]> mergeCitationRanges(String s1, String s2) {
        List<int[]> intervals = new ArrayList<>();
        intervals.addAll(parseCitations(s1));
        intervals.addAll(parseCitations(s2));
        intervals.sort((a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        for (int[] cur : intervals) {
            if (res.isEmpty()) {
                res.add(cur);
            } else {
                int[] last = res.get(res.size() - 1);

                if (cur[0] <= last[1]) {
                    last[1] = Math.max(last[1], cur[1]);
                } else {
                    res.add(cur);
                }
            }
        }
        return res;
    }
}
