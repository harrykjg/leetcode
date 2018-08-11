import java.util.*;

/**
 * Created by yufengzhu on 8/7/18.
 */
public class AddBoldTaginString {
    public static void main(String[] args){
        AddBoldTaginString ab=new AddBoldTaginString();
        String[] word={"aaa","aab","bc"};
        ab.addBoldTag("aaabbcc",word);
     }
    //自己想的是用trie,但是答案是暴力法就行。。还是不好写对
    //https://leetcode.com/problems/add-bold-tag-in-string/discuss/104263/Java-solution-Same-as-Merge-Interval. 看这个容易理解
    //https://blog.csdn.net/mtz5031/article/details/73132175

    String b1="<b>";
    String b2="</b>";
    public String addBoldTag(String s, String[] dict) {
        boolean[] b=new boolean[s.length()];
        List<Interval> ls=new ArrayList<>();

        for(int i=0;i<s.length();i++){
            for(String ss:dict){
                if(s.startsWith(ss,i)){
                    Interval in=new Interval(i,i+ss.length()-1);
                    ls.add(in);
                }
            }
        }

        Collections.sort(ls, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.b==o2.b){
                    return o1.e-o2.e;
                }
                return o1.b-o2.b;
            }
        });
        ls=merge(ls);
        if(ls.size()==0){
            return s;
        }
        StringBuilder sb=new StringBuilder();
        int index=0;
        for(Interval in:ls){
            if(index>=s.length()){
                break;
            }
            int bb=in.b;
            int ee=in.e;
            sb.append(s.substring(index,bb));
            sb.append(b1+s.substring(bb,ee+1)+b2);
            index=ee+1;
        }
        if(index<s.length()){
            sb.append(s.substring(index,s.length()));
        }

        return sb.toString();
    }
    List<Interval> merge(List<Interval> ls){
        if(ls.size()==0){
            return new ArrayList<>();
        }
        ArrayList<Interval> rs=new ArrayList<>();
        rs.add(ls.get(0));
        for(int i=1;i<ls.size();i++){
            Interval cur=ls.get(i);
            Interval pre=rs.get(rs.size()-1);
            if(cur.b<=pre.e+1){
                rs.get(rs.size()-1).e=Math.max(cur.e,pre.e);

            }else{
                rs.add(cur);
            }
        }
        return rs;
    }
    class Interval{
        int b;
        int e;
        public  Interval(int b,int e){
            this.b=b;
            this.e=e;
        }
    }

}
