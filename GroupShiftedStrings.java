import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yufengzhu on 8/6/18.
 */
public class GroupShiftedStrings {
    public static void main(String[] args){
        GroupShiftedStrings gs=new GroupShiftedStrings();
        String[] ss={"abc","bcd","acef","xyz","az","ba","a","z"};
        gs.groupStrings(ss);
    }

    //这个代码是自己想的，就是遍历每个字符串，对于当前字符串，算出他的26个shift的形式，如果其中的几个形式在map中存在，就加入结果集,run到倒数第二个test case就错了，不知道为啥
    //还是看别人的代码把
    //https://www.cnblogs.com/Dylan-Java-NYC/p/5211776.html
    //http://www.cnblogs.com/beiyeqingteng/p/5717873.html
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> rs=new ArrayList<>();
        HashMap<String,Integer> map=new HashMap<>();
        for(String s:strings){
//            map.put(s,map.getOrDefault(s,0)+1);
            if(!map.containsKey(s)){
                map.put(s,1);
            }else{
                map.put(s,map.get(s)+1);
            }

        }

        for(int i=0;i<strings.length;i++){
            String cur=strings[i];

            ArrayList<String> al=new ArrayList<>();
            if(map.containsKey(cur)&&map.get(cur)>=1){
                al.add(cur);
                map.put(cur,map.get(cur)-1);
            }
            char[] ch=cur.toCharArray();
            for(int j=0;j<=25;j++){//
                char[] ch2=ch;
                for(int k=0;k<ch.length;k++){//这个shift的过程其实改了很久，不好想
                    if(ch[k]+1>'z'){
                        int gap=ch[k]+1-'z';
                        ch2[k]=(char)('a'+gap-1);
                    }else{
                        ch2[k]=(char)(ch[k]+1);
                    }

                }
                String ns=new String(ch2);
                if(map.containsKey(ns)&&map.get(ns)>=1){
                    al.add(ns);
                    map.put(ns,map.get(ns)-1);
                }

            }
            if(al.size()!=0){
                rs.add(al);

            }
        }
        return rs;

    }

}
