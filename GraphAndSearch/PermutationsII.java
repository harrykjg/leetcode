package GraphAndSearch;

import java.util.*;

/**
 * Created by 502575560 on 7/13/17.
 */
public class PermutationsII {

    public static void main(String[] args){
        PermutationsII p=new PermutationsII();
        int[] a={1,1,2};
        p.permuteUnique3(a);
    }

    //居然写错了,就是略去重复那个条件,忘了原来还要memo[i-1]==false这个条件
    List<List<Integer>> rs=new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Write your code here
        if(nums.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }
        Arrays.sort(nums);
        ArrayList<Integer> al=new ArrayList<>();
        boolean[] memo=new boolean[nums.length];
        dfs(nums,al,memo);
        return rs;
    }
    void dfs(int[] nums,ArrayList<Integer> al, boolean[] memo){
        if(al.size()==nums.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]&&memo[i-1]==false){//注意这个条件别忘
                continue;
            }
            if(!memo[i]){
                al.add(nums[i]);
                memo[i]=true;
                dfs(nums,al,memo);
                memo[i]=false;
                al.remove(al.size()-1);
            }
        }
    }
//3/11/2018,九章第二轮,不顺,写的不好，我以为不用多一个memo去记录的,还必须要
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        if(nums.length==0){
            return rs;
        }
        Arrays.sort(nums);
        List<Integer> a= new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        helper(nums,a ,rs,set);
        return rs;
    }
    void helper(int[] nums, List<Integer> a, List<List<Integer> > rs,HashSet<Integer> set ){
        if(a.size()==nums.length){
            rs.add(new ArrayList<Integer>(a));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]&&!set.contains(i-1)){//注意是i-1而不是i,并且是看i-1没用过的话才continue
                continue;
            }
            //这里还要看加个if看当前i是否用过！
            if(set.add(i)){
                a.add(nums[i]);
                set.add(i);
                helper(nums,a,rs,set);
                a.remove(a.size()-1);
                set.remove(i);
            }
        }
    }
//9/13/2018,卧槽做不对了,还想复杂了，dfs以为要加begin，其实就是忘了检测set中有没有访问过这个node
    public List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(nums.length==0){
            return rs;
        }
        Arrays.sort(nums);
        HashSet<Integer> set=new HashSet<>();
        dfs3(nums,al,rs,set);

        return rs;
    }
    void dfs3(int[] nums,List<Integer> al,List<List<Integer>> rs,HashSet<Integer> set){
        if(al.size()==nums.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]&&!set.contains(i-1)){
                continue;
            }
            if(set.contains(i)){
                continue;
            }
            set.add(i);
            al.add(nums[i]);
            dfs3(nums,al,rs,set);
            set.remove(i);
            al.remove(al.size()-1);
        }
    }
//04/16/2020
    public List<List<Integer>> permuteUnique4(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(nums.length==0){
            return rs;
        }
        Arrays.sort(nums);
        boolean[] memo=new boolean[nums.length];
        dfs4(al,rs,nums,memo);
        return rs;
    }
    void dfs4(List<Integer> al,List<List<Integer>> rs,int[] nums,boolean[] memo){
        if(al.size()==nums.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i-1]==nums[i]&&memo[i-1]==false){
                continue;
            }
            if(memo[i]){//这个容易漏了
                continue;
            }
            al.add(nums[i]);
            memo[i]=true;
            dfs4(al,rs,nums,memo);
            al.remove(al.size()-1);
            memo[i]=false;
        }

    }

    //6/11/2021,一次过
    public List<List<Integer>> permuteUnique5(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if (nums.length==0){
            return rs;
        }
        Arrays.sort(nums);
        Set<Integer> set=new HashSet<>();
        dfs5(nums,al,rs,set);
        return rs;
    }
    void dfs5(int[] a,List<Integer> al,List<List<Integer>> rs,Set<Integer> set){
        if(al.size()==a.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        for (int i=0;i<a.length;i++){
            if(i>0&&a[i-1]==a[i]&&!set.contains(i-1)){
                continue;
            }
            if (!set.contains(i)){
                al.add(a[i]);
                set.add(i);
                dfs5(a,al,rs,set);
                al.remove(al.size()-1);
                set.remove(i);
            }
        }

    }

}
