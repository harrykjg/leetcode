package DataStruct;

import java.util.*;

/**
 * Created by yufengzhu on 12/10/17.
 */
//http://www.lintcode.com/en/problem/anagrams/ 这个是九章上的，leetcode的anagrams好像是改名成group anagrams了，他们的返回类型不同
    //一次过
public class Anagrams {
    public List<String> anagrams(String[] strs) {
        // write your code here
        List<String> rs=new ArrayList<>();
        if(strs.length==0){
            return rs;
        }
        Map<String,ArrayList> map=new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char[] ch=strs[i].toCharArray();
            Arrays.sort(ch);
            if(!map.containsKey(new String(ch))){
                ArrayList<String> al=new ArrayList<>();
                al.add(strs[i]);
                map.put(new String(ch),al);
            }else{
                map.get(new String(ch)).add(strs[i]);
            }
        }
        for(ArrayList al:map.values()){
            if(al.size()>=2){
                rs.addAll(al);
            }
        }
        return rs;
    }

}
