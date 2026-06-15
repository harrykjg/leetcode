package SomeInterviews.snowflake;

import java.util.*;

public class ThroneInheritance1600 {
    String king;
    Map<String, List<String>> map;
    Map<String,Boolean> dead;
    //比如andy 和bob是同辈，andy比较老，那么andy生了孩子的话这个孩子的优先级会比bob高。这样就是得king->andy->andy's children->bob，就是dfs吧
    public ThroneInheritance(String kingName) {
        this.king=kingName;
        map=new HashMap<>();
        dead=new HashMap<>();
    }

    public void birth(String parentName, String childName) {
        map.putIfAbsent(parentName,new ArrayList<>());
        map.get(parentName).add(childName);
    }
//就是单纯记录谁死没死，后面dfs的时候遇到一个人就看他死没死。
    public void death(String name) {
        dead.putIfAbsent(name,true);
    }

    public List<String> getInheritanceOrder() {
        List<String> rs=new ArrayList<>();
        if(!dead.getOrDefault(king,false)){
            rs.add(king);
        }
        dfs(map.get(king),dead,rs);
        return rs;
    }
    void dfs(List<String> children,Map<String,Boolean> dead,List<String> rs){
        if(children==null||children.size()==0){
            return;
        }
        for(String child:children){
            if(!dead.getOrDefault(child,false)){
                rs.add(child);
            }
            dfs(map.get(child),dead,rs);//这里容易错，就是死了也要继续找他的children
        }
    }
}
