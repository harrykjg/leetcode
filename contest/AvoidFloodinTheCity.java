package contest;

import java.util.*;

public class AvoidFloodinTheCity {
    public static void main(String[] a){
        AvoidFloodinTheCity af=new AvoidFloodinTheCity();
//        System.out.println(af.avoidFlood(new int[]{3,5,4,0,1,0,1,5,2,8,9}));
        af.avoidFlood2(new int[]{2,3,0,0,3,1,0,1,0,2,2});
    }
    //自己写的dfs超时
    boolean found=false;
    public int[] avoidFlood(int[] rains) {
        int[] rs=new int[rains.length];
        List<Integer> al=new ArrayList<>();
        dfs(0,al,rains,rs);
        if(found){
            return rs;
        }
        return new int[0];

    }
    void dfs(int b,List<Integer> full,int[] rains, int[] rs){
        if(found){
            return;
        }
        if(b>=rains.length){
            found=true;
            return;
        }
        for(int i=b;i<rains.length;i++){
            if(rains[i]>0){
                if(!full.contains(rains[i])){
                    rs[i]=-1;
                    full.add(rains[i]);
                    if(i==rains.length-1){
                        found=true;
                        break;
                    }
                }else{
                    //dead;
                    return;
                }
            }else if(rains[i]==0){
                check(i,full,rs,rains);
            }
        }


    }
    void check(int index,List<Integer> full,int[] rs, int[] rains){
        if(full.size()==0){
            rs[index]=1;
        }
        for(int i=0;i<full.size();i++){
            if(found){
                break;
            }
            int cur=full.get(i);
            int curindex=i;
            full.remove(new Integer(cur));
            rs[index]=cur;
            dfs(index+1,new ArrayList<>(full),rains,rs);//这里不写new ArrayList<>(full)的话就错了，因为dfs途中会改变full里的内容
            full.add(curindex,cur);

        }
    }
    //https://leetcode.com/problems/avoid-flood-in-the-city/discuss/697844/Java-The-method-of-post-selection-detailed-explanation
    //好的方法,操作上要想清楚，不然map，set，rs操作容易错
    public int[] avoidFlood2(int[] rains) {
        Map<Integer,Integer> map=new HashMap<>();
        TreeSet<Integer> set=new TreeSet<>();
        int[] rs=new int[rains.length];
        for(int i=0;i<rains.length;i++){
            if(rains[i]==0){
                set.add(i);
                continue;
            }
            if(map.containsKey(rains[i])){//实际上这个条件下是，做了两步，1，填湖，2，再把所填的湖设成满的，容易漏
                Integer next=set.higher(map.get(rains[i]));
                if(next==null){
                    return new int[0];
                }
                set.remove(next);
                rs[next]=rains[i];
                rs[i]=-1;
                map.put(rains[i],i);//容易忘更新这个满的湖的位置
            }else{
                map.put(rains[i],i);
                rs[i]=-1;
            }
        }
        for(Integer i:set){
            rs[i]=1;
        }
        return rs;
    }
}
