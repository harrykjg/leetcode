package GraphAndSearch;

import java.util.*;

/**
 * Created by 502575560 on 7/14/17.
 */
public class CombinationSumII {
    //还是没有一次写对,
    public List<List<Integer>> combinationSum2(int[] can, int tar) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(can.length==0){

            rs.add(al);
            return rs;
        }
        Arrays.sort(can);
        helper(0,0,can,tar,al,rs);
        return rs;

    }
    public void helper(int cur,int b,int[] can,int tar,List<Integer> al,List<List<Integer>> rs){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<can.length;i++){
            if(i>b&&can[i]==can[i-1]){//这里之前写成i>0就不行了,这里注意要想清楚,和permutation2那里的区别,那里多了个memo
                continue;
            }
            if(cur+can[i]>tar){
                return;
            }
            al.add(can[i]);
            helper(can[i]+cur,i+1,can,tar,al,rs);
            al.remove(al.size()-1);

        }
    }
    //3/17/2018 九章第二轮,改了一次对了
    public List<List<Integer>> combinationSum22(int[] can, int tar) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(can.length==0){

            rs.add(al);
            return rs;
        }
        Arrays.sort(can);
        boolean[] memo=new boolean[can.length];
        helper2(0,0,al,can,tar,rs,memo);
        return rs;
    }
    void helper2(int cur,int b,List<Integer> al, int[] can,int tar,List<List<Integer>> rs,boolean[] memo){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<can.length;i++){
            if(i>0&&can[i-1]==can[i]&&!memo[i-1]){//看回以前的old code发现，这里就是permutation2的去重的方法，多用了个boolean数组，而上面这个code用的i>b就不用booleam数组了
                continue;
            }
            int now=cur+can[i];
            if(now>tar){
                return;
            }
            memo[i]=true;
            al.add(can[i]);


            helper2(now,i+1,al,can,tar,rs,memo);
            al.remove(al.size()-1);
            memo[i]=false;

        }
    }
    //9/13/2018,妈的忘了去重复
    public List<List<Integer>> combinationSum23(int[] can, int tar) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(can.length==0){
            return rs;
        }
        HashSet<Integer> set=new HashSet<>();
        Arrays.sort(can);
        dfs(0,0,can,tar,al,rs,set);
        return rs;
    }
    void dfs(int b,int cur,int[] nums,int tar,List<Integer> al,List<List<Integer>> rs,HashSet<Integer> set){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<nums.length;i++){
            if(i>0&&nums[i-1]==nums[i]&&!set.contains(i-1)){
                continue;
            }
            if(cur+nums[i]>tar){
                return;
            }
            al.add(nums[i]);
            set.add(i);
            dfs(i+1,cur+nums[i],nums,tar,al,rs,set);
            al.remove(al.size()-1);
            set.remove(i);
        }
    }
//04/13/2020,基本一次过
    public List<List<Integer>> combinationSum24(int[] can, int tar) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        Arrays.sort(can);
        boolean[] memo=new boolean[can.length];
        helper4(0,0,tar,can,al,rs,memo);
        return rs;

    }
    void helper4(int b,int cur,int tar,int[] can,List<Integer> al,List<List<Integer>> rs,boolean[] memo){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
        }
        for(int i=b;i<can.length;i++){
            if(i>0&&can[i-1]==can[i]&&memo[i-1]==false){
                continue;
            }
            if(cur+can[i]<=tar&&memo[i]==false){
                al.add(can[i]);
                memo[i]=true;
                helper4(i,cur+can[i],tar,can,al,rs,memo);//b设成i+1应该更好一点点
                al.remove(al.size()-1);
                memo[i]=false;
            }
        }
    }
}
