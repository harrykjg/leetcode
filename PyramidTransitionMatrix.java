import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 7/21/18.
 */
public class PyramidTransitionMatrix {
    //写的很痛苦只能写出暴力法，还memory leak了
    //https://my.oschina.net/liyurong/blog/1616709
//https://leetcode.com/problems/pyramid-transition-matrix/discuss/113054/Java-solution-map-+-backtracking
    //https://blog.csdn.net/u014688145/article/details/78951010 dp没看
    //https://segmentfault.com/a/1190000012727144 dp没看
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        if(bottom.length()<2){
            return true;
        }
        HashMap<String,ArrayList<String>> map=new HashMap<>();//这里其实就是把allowed放进map了，可以更快的通过前两个字母找到第三个允许的字母
        for(String s:allowed){
            String k=s.substring(0,2);
            if(!map.containsKey(k)){
                ArrayList<String> al=new ArrayList<>();
                al.add(s.substring(2,3));
                map.put(k,al);
            }else{
                map.get(k).add(s.substring(2,3));
            }
        }
        return helper(bottom,map);
    }

    boolean helper(String bot,HashMap<String,ArrayList<String>> map){
        if(bot.length()<2){
            return true;
        }
        int len=bot.length();
        List<List<String>> nb=new ArrayList<>();//就是看着bot，每2个字母，从map里找出可以放在上面的字母们，加入nb，然后用这个nb再去constrcut出所有可能的上一层，作为bot，recursively检查上一层合法与否
        for(int i=0;i<bot.length()-1;i++){
            String s1=String.valueOf(bot.charAt(i));
            String s2=String.valueOf(bot.charAt(i+1));
            if(map.containsKey(s1+s2)){
                List<String> ls=map.get(s1+s2);
                nb.add(ls);
            }else{
                return false;
            }
        }
        List<String> candidates=new ArrayList<>();//从nb产生出所有上一层字母串的可能，有一个valid就行
        String can="";
        constructCandidates(0,can,len-1,nb,candidates);

        for(String s:candidates){
            if(helper(s,map)){
                return true;
            }
        }
        return false;
    }
    void constructCandidates(int b,String cur,int len,List<List<String>> nb,List<String> rs){
        if(cur.length()==len){
            rs.add(new String(cur));
            return;
        }
        for(int i=b;i<nb.size();i++){
            for(int j=0;j<nb.get(b).size();j++){
                String add=nb.get(b).get(j);
                constructCandidates(b+1,cur+add,len,nb,rs);
            }
        }

    }

//    boolean valid(List<List<String>> ls,List<String> allow){
//        if(ls.size()<2){
//            return true;
//        }
//        for(int i=0;i<ls.size()-1;i++){
//            List<String> l1=ls.get(i);
//            List<String> l2=ls.get(i+1);
//            for(String s1:l1){
//                for(String s2:l2){
//
//                }
//            }
//
//        }
//        return false;
//
//    }

}
