package SomeInterviews.doordash;

import sun.plugin2.ipc.windows.WindowsIPCFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class anagram {
    public static void main(String[] args){
        anagram anagram=new anagram();
        List<String> rs=anagram.isSimilar("omega grill",Arrays.asList("omeag grill", "omeea grill", "omega gril", "omegla gril"));
//        for (String s:rs){
//            System.out.println(s);
//        }
        List<String> rs1=anagram.kSimilar("omexyb grillg", Arrays.asList("omgxca grille"),2);
        for (String s:rs1){
            System.out.println(s);
        }
    }
    //https://leetcode.com/discuss/interview-question/1505566/doordash-coding-new-grad
    public List<String> isSimilar(String s1,List<String> words){
        List<String> rs=new ArrayList<>();
        for (int i=0;i<words.size();i++){
            String s2=words.get(i);
            int p1=-1;
            int p2=-1;
            if (s1.equals(s2)){
                rs.add(s2);
                continue;
            }
            if (s1.length()!=s2.length()){
                continue;
            }
            int count=0;
            for (int j=0;j<s1.length();j++){
                if (s1.charAt(j)!=s2.charAt(j)){
                    count++;
                    if (p1==-1){
                        p1=j;
                    }else if(p2==-1){
                        p2=j;
                    }else {
                        continue;
                    }
                }
            }
            if (count==2&&p1!=-1&&p2!=-1){
                if (s2.charAt(p1)==s1.charAt(p2)&&s2.charAt(p2)==s1.charAt(p1)){
                    rs.add(s2);
                }
            }
        }
        return rs;
    }
    public List<String> kSimilar(String s,List<String> ls,int k){
        List<String> rs=new ArrayList<>();
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i=0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for (int i=0;i<ls.size();i++){
            HashMap<Character,Integer> map2=new HashMap<>(map);
            if (s.length()!=ls.get(i).length()){
                continue;
            }
            int count=0;
            char[] ch=ls.get(i).toCharArray();
            for (int j=0;j<ch.length;j++){
                if (map2.containsKey(ch[j])&&map2.get(ch[j])>0){
                    map2.put(ch[j],map2.get(ch[j])-1);
                }else{
                    count++;
                }
            }
            if (count<=k){
                rs.add(ls.get(i));
            }
        }
        return rs;
    }
}
