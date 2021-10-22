package interval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class RangeModule {
    public static void main(String[] args){
        RangeModule rm=new RangeModule();
        rm.addRange2(6,8);
        rm.removeRange2(7,8);
        rm.removeRange2(8,9);
        rm.addRange2(8,9);
        rm.removeRange2(1,3);
        rm.addRange2(1,8);
        rm.queryRange2(2,4);
        rm.queryRange2(2,9);
        rm.queryRange2(4,6);


        /*
                10        22
                                   30   35
remove 20 33    10        20      33 35
         */
    }

    //9/8/2021 不会，看了用treemap挺巧妙的，map的key是start，value是end.用treemap其实很难写。
    //https://leetcode.com/problems/range-module/discuss/112694/Concise-Java-Solution-With-TreeMap
    //https://www.cnblogs.com/silentteller/p/12842653.html 他就是用linkedlist搞的，代码参考他的
    List<int[]> ls=new LinkedList<>();
    public RangeModule() {

    }

    public void addRange(int left, int right) {//实际上就是insert interval
        if(left>right){
            return;
        }
        List<int[]> al=new ArrayList<>();
        if (ls.size()==0){
            ls.add(new int[]{left,right});
            return;
        }
        int i=0;
        while (i<ls.size()&&left>ls.get(i)[1]){
            al.add(ls.get(i));
            i++;
        }
        while (i<ls.size()){//到了这里的话说明是ni[0]<=intervals[i][1], 然后举例子看，当ni【1】小于intervals【i】【0】的时候说明没交集，反过来
            if (ls.get(i)[0]<=right){       //就是intervals[i][0]<=ni[1]，则有交集。则不断扩大new interval
                left=Math.min(ls.get(i)[0],left);
                right=Math.max(ls.get(i)[1],right);
                i++;
            }else {
                break;
            }
        }
        al.add(new int[]{left,right});
        while (i<ls.size()){
            al.add(ls.get(i));
            i++;
        }
        ls=al;
    }

    public boolean queryRange(int left, int right) {
        if(left>right){
            return false;
        }
        if(ls.size()==0){
            return false;
        }
        int l=0;
        int e=ls.size()-1;
        while (l<e){//这个没仔细想直接写，完了判断一下，居然也对了
            int m=(l+e)/2;
            int[] cur=ls.get(m);
            if (left>cur[1]){
                l=m+1;
            }else {
                e=m;
            }
        }
        if (ls.get(l)[0]<=left&&ls.get(e)[1]>=right){
            return true;
        }
        return false;
    }

    public void removeRange(int left, int right) {//这里想写类似add 的用几个while搞，还不好写
        if(left>right){
            return;
        }
        List<int[]> al=new ArrayList<>();
        int i=0;

        while (i<ls.size()){
            if (left>ls.get(i)[1]||right<=ls.get(i)[0]){//这个<=的=号去掉也对。不知道为啥
                al.add(ls.get(i));
            }else {
                if (left>ls.get(i)[0]){//这里自己很容易想复杂。他隐含了left<=ls.get(i)[0])的情况，如果left<=ls.get(i)[0])的话则左边肯定是没了，右边
                    al.add(new int[]{ls.get(i)[0],left});//有没有就再看。同理right>=ls.get(i)[1]
                }
                if (right<ls.get(i)[1]){
                    al.add(new int[]{right,ls.get(i)[1]});
                }
            }
            i++;
        }
        ls=al;
    }

    //10//11/2021
    //https://leetcode.com/problems/range-module/discuss/108910/Java-TreeMap 参考他和下面第一个评论的
    TreeMap<Integer,Integer> map=new TreeMap<>();
    public void RangeModule2() {

    }

    public void addRange2(int left, int right) {
        Integer l=map.lowerKey(left);
        Integer r=map.floorKey(right);//很恶心，这里是lowerkey就不行
        if (l!=null){
            if (map.get(l)>=left){
               left=l;
            }
        }
        if(r!=null){
            if (right<map.get(r)){
                right=map.get(r);
            }
        }
        map.put(left,right);
        map.subMap(left,false,right,true).clear();
    }

    public boolean queryRange2(int left, int right) {
        Integer l=map.floorKey(left);
        if (l==null){
            return false;
        }
        return map.get(l)>=right;
    }

    public void removeRange2(int left, int right) {
        Integer l=map.floorKey(left);
        Integer r=map.floorKey(right);
        if (r!=null){//这里很容易错，先搞右边的，如果先搞左边的，则l的值会被更新，然后l和r相同的话，即mao。get（r）就会更新掉，会错
            if (map.get(r)>right){
                map.put(right,map.get(r));
            }
        }
        if (l!=null){
            map.put(l,Math.min(map.get(l),left));
        }

        map.subMap(left,true,right,false).clear();
    }
}
