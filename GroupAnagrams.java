import com.sun.deploy.util.StringUtils;
import com.sun.tools.javac.code.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 8/12/18.
 */
public class GroupAnagrams {
    //https://leetcode.com/problems/group-anagrams/solution/  这个还有count的方案是最优解
    public  List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> rs=new ArrayList<>();
        if(strs.length==0){
            return rs;
        }
        int[] count=new int[26];
        HashMap<String,ArrayList<String>> map=new HashMap<>();
        for(String s:strs){
            char[] ch=s.toCharArray();
            Arrays.fill(count,0);
            for(int i=0;i<ch.length;i++){
                count[ch[i]-'a']++;
            }
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<26;i++){
                sb.append(count[i]);
                sb.append("#");
            }
            if(!map.containsKey(sb.toString())){
                ArrayList<String> al=new ArrayList<>();
                al.add(s);
                map.put(sb.toString(),al);
            }else{
                map.get(sb.toString()).add(s);
            }
        }
        for(ArrayList<String> al:map.values()){
            rs.add(al);
        }
        return rs;
    }

    //04/17/2020,能想到的就是sort每个string。懒得写了
    public  List<List<String>> groupAnagrams2(String[] strs) {

    }
}
