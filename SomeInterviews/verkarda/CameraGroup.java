package SomeInterviews.verkarda;

import java.util.*;

public class CameraGroup {
    /*
    Your company is a Verkada customer, and there is only one user at your company that is an Admin user, which means that they have access to every camera in your organization.
You have access to some permissions data, and your goal is to read the permissions data and return the ID of the user (i.e. user_1) who is an Admin User (which means they can access every camera).
It is guaranteed that there will be exactly one camera admin.
The permissions data contains rules that determine which users have ownership privilege over which cameras. Users can receive access to a camera either directly or through membership in a group.
For example, if group 1 is “Janitors” and group 2 is “All Building Staff”, then the Janitors will inherit the permissions of “All Building Staff.”
Groups can be nested (i.e. group_1 is a member of group_2, which is a member of group_3, etc.). But you do not need to worry about cycles (i.e. groups being members of one another).
The permissions data is a nested array that looks like this:
Each entry has the structure:
(entity_id, relationship_to_object, object_id)
[
 ("user_1", "camera_owner", "camera_1"),
 ("user_1", "group_member", "group_1"),
 ("group_1", "camera_owner", "camera_2"),
 ("group_1", "group_member", "group_2"),
 ("group_2", "camera_owner", "camera_3"),
 ("user_2", "camera_owner", "camera_3"),
 ("user_2", "group_member", "group_1"),
 ("user_3", "group_member", "group_2"),
 ("user_3", "camera_owner", "camera_1"),
]
In other words, we know the following information:
user_1 is an owner of camera_1
user_1 is a member of group_1
group_1 is an owner of camera_2
group_1 is a group member of group_2
So, in this example, user_1 has access to three cameras:
user_1 can access camera_1 directly
user_1 can access camera_2 through membership in group 1
user_1 can access camera_3 through membership in group 1 because group 1 is a member in group 2
Process the given permissions data and return the User ID of the Admin user.
Keep in mind:
Users can be members of multiple groups.
A group can have multiple levels of nesting.
Some users may not be part of any groups.
     */
//开始想着并查集，维护一个count代表拥有的camer数量，union的时候往领导那merge count，最后count=camera数量的就是admin。结果是不行的，
// gpt说是因为这个member group的方向是单向的，不是双向的。还有union的时候merge count会重复，如owner1有camer1，group1也有camera1，如果owner1和
//group1 union则会重复计算camera1
    public String findAdmin(List<Pair> input){
        //拿一个map，key作为entity，value他的所有有权限的camera，那就知道谁是admin（最大的那个）
        //好了那就是怎么取得这个map?得有一个map记录member和所属于的group
        //而我们知道group有什么camera，也知道member自己有什么camera，那么应该能找出member的所有camera。现在就是group可以是nested的
        //并且关键是单向的，如group1属于group2，则group2有的group1也有，但是group1有的group2不一定有，因此我觉得就是要维护一个map其中
        //key是group/owner，value是他所属于的所有group
        Map<String,Set<String>> entityToCam=new HashMap<>();
        Map<String, Set<String>> entityToGroups=new HashMap<>();//group 属于什么group
        //应该单独维护所有user和所有camera，这样方便比较user是否拥有所有camera，而且遍历entityToGroups的时候不会漏
        Set<String> users=new HashSet<>();
        Set<String> cams=new HashSet<>();
        for (Pair p:input){
            String entity=p.entity;
            String rela=p.relation;
            String obj=p.obj;
            if(rela.equals("camera_owner")){
                entityToCam.putIfAbsent(entity,new HashSet<>());
                entityToCam.get(entity).add(obj);
                cams.add(obj);
            }else{// group member
                entityToGroups.putIfAbsent(entity,new HashSet<>());
                entityToGroups.get(entity).add(obj);
            }
            if (entity.startsWith("user_")) {
                users.add(entity);
            }
        }
        for(String entity:entityToGroups.keySet()){
            //这里要dfs拿group和所有的祖先
            dfs(entity,entity,entityToGroups,entityToCam);
        }
        String rs="";
        for (String en:users){//不能只遍历entityToCam.keySet()，因为可能admin没有group
            if(entityToCam.get(en)!=null&&entityToCam.get(en).size()==cams.size()){
                return en;
            }
        }
        return rs;
    }
    //entity用来定位要更新的那个user，cur就是要找的当前cur的邻居，即他所属的group
    void dfs(String entity,String cur,Map<String, Set<String>> entityToGroups,Map<String,Set<String>> entityToCam){
        Set<String> neighbour=entityToGroups.get(cur);
        if(neighbour!=null){
            for(String group:neighbour){
                Set<String> cams=entityToCam.get(group);
                //entityToCam.get(entity)可能是null
                entityToCam.putIfAbsent(entity,new HashSet<>());
                if(cams!=null){
                    entityToCam.get(entity).addAll(cams);
                }
                dfs(entity,group,entityToGroups,entityToCam);//拿下一个所属的group的camera，加进entity里
            }
        }
    }

    class Pair{
        String entity;
        String relation;
        String obj;
    }
}
