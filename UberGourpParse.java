import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yufengzhu on 8/12/18.
 */
public class UberGourpParse {
    public static void main(String[] args){
        UberGourpParse ug=new UberGourpParse();
        HashMap<String,String[]> map=new HashMap<>();
        map.put("group0",new String[]{"group3","user0","user1"});
        map.put("group1",new String[]{"group3","user2","user3"});
        map.put("group2",new String[]{"group3","group5","user4","user5"});
        map.put("group3",new String[]{"group4","user6","user7"});
        map.put("group4",new String[]{"user8","user9"});
        map.put("group5",new String[]{"user10","user11"});
        HashMap<String,ArrayList<String>> rs=ug.parse(map);
        System.out.print("xxxx");

    }

    public HashMap<String,ArrayList<String>> parse(HashMap<String,String[]> m1){
        HashMap<String,ArrayList<String>> map=new HashMap<>();//先建一个map，key是m1里的value里的所有group和user，value是这个key所属于的group，这个key可以是user或者group，其实这里arraylist应该改成set，我就懒得改了
        for(String s:m1.keySet()){
            String[]  sa=m1.get(s);
            for(String ss:sa){
                if(!map.containsKey(ss)){
                    ArrayList<String> al=new ArrayList<>();
                    al.add(s);
                    map.put(ss,al);
                }else{
                    map.get(ss).add(s);
                }
            }
        }
        HashMap<String, ArrayList<String>> rs=new HashMap<>();
        for(String s:map.keySet()){
            if(!s.startsWith("user")){//现在对于这个map的所有key里的user，找出其所属于的group，如果他说与group1然后group1又属于group2，那么这个key属于group1和group2都要列出来
                continue;
            }
            ArrayList<String> as=map.get(s);
            for(String ss:as){
                if(!ss.startsWith("group")){
                    continue;
                }
                if(!rs.containsKey(ss)){
                    ArrayList<String> al=new ArrayList<>();
                    al.add(ss);
                    rs.put(s,al);
                }else{
                    map.get(s).add(ss);
                }
                if(map.containsKey(ss)){//map里存在ss说明这个s所属于的group 自己又属于别的group，这里要dfs找到这个ss所属于的所有group各自又属于什么group,这个dfs开始漏了
                    ArrayList<String> groupbelongsto=map.get(ss);
                    for(String sb:groupbelongsto){
                        if(!rs.get(s).contains(sb)){
                            rs.get(s).add(sb);
                            dfs(rs,s,map,sb);
                        }

                    }
                }
            }
        }
        return rs;
    }
    void dfs(HashMap<String, ArrayList<String>> rs,String user,HashMap<String, ArrayList<String>> map,String group){
        if(!map.containsKey(group)){
            return;
        }
        ArrayList<String> groupbelongsto=map.get(group);
        for(String sb:groupbelongsto){
            if(!rs.get(user).contains(sb)){
                rs.get(user).add(sb);
                dfs(rs,user,map,sb);
            }

        }
    }

}
