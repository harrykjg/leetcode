package 灵神.常用数据结构.并查集;

import java.util.*;

public class AccountsMerge721 {
    static void main() {

    }
    //12/10/2025,还是写不出，看回以前的.还是容易写错
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String,String> ids=new HashMap<>();
        Map<String,String> owner=new HashMap<>();
        //比如说有js1，js2，js3属于同一组，假如选js2作为parent，即js2，js3的parent都是js1，现在来到下一组
        //js4和js1，那么联通的时候应该怎么联通？其实也是和用数组的一样，find的时候要压缩路径
        Map<String,TreeSet<String>> map=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        //初始化
        for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).size()<2){
                continue;
            }
            String name=accounts.get(i).get(0);
            for(int j=1;j<accounts.get(i).size();j++){
                ids.put(accounts.get(i).get(j),accounts.get(i).get(j));
                owner.put(accounts.get(i).get(j),name);
            }
        }
        //现在再把同一account的所有email的id指向这一组的第一个email,此时有联通的email的就会被合并
        for(int i=0;i<accounts.size();i++){
            String first=accounts.get(i).get(1);
            for(int j=2;j<accounts.get(i).size();j++){
                String cur=accounts.get(i).get(j);
                union(ids,cur,first);
            }
        }
        //但是如何知道同一个根email的所有子email呢？
        //那就便利所有email，找出每个email的根email，放进map存起来，把子email也存一起
        for(String email:ids.keySet()){
            String root=find(ids,email);
            map.putIfAbsent(root,new TreeSet<>());
            map.get(root).add(email);
        }
        for(String s:map.keySet()){
            String ow=owner.get(s);
            List<String> al=new ArrayList<>(map.get(s));
            al.add(0,ow);
            rs.add(al);
        }
        return rs;


    }
    String find(Map<String,String> map,String a){
        if(map.get(a).equals(a)){
            return a;
        }
        String p=find(map,map.get(a));
        map.put(a,p);
        return p;
    }
    void union(Map<String,String> map,String a, String b){
        String pa=find(map,a);
        String pb=find(map,b);
        if(pa.equals(pb)){
            return;
        }
        map.put(pa,pb);
    }
}

