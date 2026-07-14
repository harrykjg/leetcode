package SomeInterviews.moveworks;

import java.util.*;

public class ShortestUncommonSubstringinanArray3076 {

    //基本上就是暴力法
    //找到所有string的substring，然后对每一个input，那他的substring去对比别的input的substring看是否存在
    public String[] shortestSubstrings(String[] arr) {
        Map<String, Set<String>> map=new HashMap<>();
        String[] rs=new String[arr.length];
        for(String s:arr){
            map.put(s,new HashSet<>(generateSubstring(s)));
        }
        for (int i=0;i<arr.length;i++){
            String found="";
            //当前i自己的substring再建一次
            for (int x=1;x<=arr[i].length();x++){
                for(int y=0;y+x<=arr[i].length();y++){
                    boolean flag=true;
                    String sub=arr[i].substring(y,y+x);
                    for (int j=0;j<arr.length;j++){
                        if(i==j){
                            continue;
                        }
                        Set<String> set=map.get(arr[j]);
                        if(set.contains(sub)){
                            flag=false;
                            break;
                        }
                    }//内层循环结束后flag还是true，说明找到了，而且是最短的，但还要处理lexical order
                    if(flag){
                        if(found.equals("")||sub.compareTo(found)<0){
                            found=sub;
                        }
                    }
                }
                if(found.length()>0){
                    break;
                }
            }
            rs[i]=found;
        }

        return rs;

    }
    List<String> generateSubstring(String s){
        List<String> rs=new ArrayList<>();
        char[] ch=s.toCharArray();
        for (int i=1;i<=s.length();i++){
            for (int j=0;j+i<=s.length();j++){
                String sub=s.substring(j,j+i);
                rs.add(sub);
            }
        }
        return rs;
    }

}
