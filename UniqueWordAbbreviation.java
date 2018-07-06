import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yufengzhu on 7/4/18.
 */
public class UniqueWordAbbreviation {
    //这题不难，就是麻烦，题目条件容易误解，见 https://leetcode.com/problems/unique-word-abbreviation/solution/
    public static void main(String[] a){
        String[] ss={"deer","door","cake","card","hello"};
        UniqueWordAbbreviation uw=new UniqueWordAbbreviation(ss);
//        System.out.print(uw.isUnique("dear"));
//        System.out.print(uw.isUnique("cart"));
        System.out.print(uw.isUnique("hello"));
    }
    HashMap<String,Integer> map;
    HashSet<String> set;
    public UniqueWordAbbreviation(String[] dictionary) {
        map=new HashMap<>();
        set=new HashSet<>();

        for(String s:dictionary){//真恶心这题，题目的dictionary还会有重复的，我先去重之后就对了
            set.add(s);
        }
        Iterator<String> it=set.iterator();
        while (it.hasNext()){
            helper(it.next());
        }
    }
    void helper(String s){
        String temp=s;
        if(s.length()<=2){

        }else{
            StringBuilder sb=new StringBuilder();
            sb.append(s.charAt(0));
            int mid=s.length()-2;
            sb.append(mid);
            sb.append(s.charAt(s.length()-1));
            temp=sb.toString();
        }
        if(!map.containsKey(temp)){
            map.put(temp,1);
        }else{
            map.put(temp,map.get(temp)+1);
        }

    }

    public boolean isUnique(String word) {
        String temp=word;
        if(word.length()<=2){

        }else{
            StringBuilder sb=new StringBuilder();
            sb.append(word.charAt(0));
            int mid=word.length()-2;
            sb.append(mid);
            sb.append(word.charAt(word.length()-1));
            temp=sb.toString();

        }
        if (!map.containsKey(temp)||(set.contains(word)&&map.get(temp)==1)){//这里也很恶心，这个temp没出现过的话就直接对了，要么就是dictional里有1个和这个word同样的单词，并且dictionry里
            return true;       //没有别的单词的缩写和word的缩写一样的
        }else{
            return false;
        }
    }
}
//class abbre{
//    Character begin;
//    Character end;
//    Integer mid;
//    public abbre(Character begin, Character end, int mid){
//        this.begin=begin;
//        this.end =end;
//        this.mid=mid;
//    }
//}
