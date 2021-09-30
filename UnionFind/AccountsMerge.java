package UnionFind;

import java.util.*;

/**
 * Created by yufengzhu on 7/15/18.
 */
public class AccountsMerge {
    //union find思路是知道的，就是写起来不好写，好题，对unionfind理解又深了一点，就是第二个for循环的解释
    // 看这个写法 https://leetcode.com/problems/accounts-merge/discuss/109157/JavaC++-Union-Find
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String,String> owner=new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        List<List<String>> rs=new ArrayList<>();

        for(List<String> ls:accounts){//先把每一个email的parent设成自己，并且记录owner的名字，巧妙再owner这个map的key是email，value才是owner
            for(int i=1;i<ls.size();i++){
                parents.put(ls.get(i),ls.get(i));
                owner.put(ls.get(i),ls.get(0));
            }
        }
        for(List<String> ls:accounts){//对于每一个account的emails，自己同组的先union,这里其实很神奇，比如第一组a，b，都指向parent a，到了第二组，是c，b，则b指向c的同时，第一组的a，b也都指向c了！神奇
            if(ls.size()<2){       //就算第二组是b，c，要把c指向b的祖先，即c和b都指向a，也对
                continue;
            }
            String first=ls.get(1);
            String firstRoot=find(parents,first);
            for(int i=2;i<ls.size();i++){
                String cur=ls.get(i);
                String curRoot=find(parents,cur);
                parents.put(curRoot,firstRoot);
            }
        }
        for(int i=0;i<accounts.size();i++){//现在再遍历accounts，把parent作为unions的key，value新建一个set，存储属于同一个parent的所有email，因为上面那个循环，所以每一组email，如果
            if(accounts.get(i).size()<2){  //他们祖先相同的话，已经都指向共同祖先了，很神奇
                continue;
            }
            String root=find(parents,accounts.get(i).get(1));
            if(!unions.containsKey(root)){
                unions.put(root,new TreeSet<>());
            }
            for(int j=1;j<accounts.get(i).size();j++){
                String cur=accounts.get(i).get(j);
                unions.get(root).add(cur);
            }

        }
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            rs.add(emails);
        }
        return rs;

    }
    String find(Map<String,String> map,String s){
        if(!map.containsKey(s)){
            return s;
        }
        if(map.get(s).equals(s)){
            return s;
        }
        String rs=find(map,map.get(s));
        map.put(map.get(s),rs);
        return rs;
    }
    //9／8／2018，不到2个月就忘了，不会
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        HashMap<String,String> ids=new HashMap<>();
        HashMap<String,String> owners=new HashMap<>();
        HashMap<String,Set<String>> map=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        for(List<String> ls:accounts){
            String ow=ls.get(0);
            for(int i=1;i<ls.size();i++){
                ids.put(ls.get(i),ls.get(i));
                owners.put(ls.get(i),ow);
            }
        }
        for(List<String> ls:accounts){
            String first=ls.get(1);
            String firstRoot=find2(ids,first);
            for(int i=2;i<ls.size();i++){
                String curRoof=find2(ids,ls.get(i));
                ids.put(curRoof,firstRoot);
            }
        }
        for(List<String> ls:accounts){//注意不是把owner作为map的key，而是把根结点作为key，value是所有指向这个根结点的email
            for(int i=1;i<ls.size();i++){
                String cur=ls.get(i);
                String curRoot=find2(ids,cur);
                if(!map.containsKey(curRoot)){
                    TreeSet<String> set=new TreeSet<>();
                    map.put(curRoot,set);
                }
                map.get(curRoot).add(cur);
            }
        }
        for(String s:map.keySet()){
            ArrayList<String> al=new ArrayList<>(map.get(s));
            al.add(0,owners.get(al.get(0)));
            rs.add(al);
        }
        return rs;
    }
    String find2(HashMap<String,String> ids,String s){
        if(ids.get(s).equals(s)){
            return s;
        }
        String root=find2(ids,ids.get(s));
        ids.put(ids.get(s),root);
        return root;
    }

    //9/16/2018,靠记的，记错了一个地方,对于HashMap<String,Set<String>> ac的理解错了，key不是owner而是email的root
    public List<List<String>> accountsMerge3(List<List<String>> accounts) {
        HashMap<String,String> owners=new HashMap<>();
        HashMap<String,String> ids=new HashMap<>();
        HashMap<String,Set<String>> ac=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        for(int i=0;i<accounts.size();i++){
            String owner=accounts.get(i).get(0);
            for(int j=1;j<accounts.get(i).size();j++){
                String email=accounts.get(i).get(j);
                owners.put(email,owner);
                ids.put(email,email);
            }
        }
        for(List<String> a:accounts){
            if(a.size()<2){
                continue;
            }
            String rootemail=find3(ids,a.get(1));
            for(int j=2;j<a.size();j++){
                String rootemail2=find3(ids,a.get(j));
                if(!rootemail.equals(rootemail2)){
                    ids.put(rootemail2,rootemail);
                }
            }
        }
        for(List<String> a:accounts){//这一步记错了，应该是在遍历account，而不是遍历ids
            if(a.size()<2){
                continue;
            }
            String emailroot=find3(ids,a.get(1));

            if(!ac.containsKey(emailroot)){
                TreeSet<String> set=new TreeSet<>();
                ac.put(emailroot,set);
            }
            for(int i=1;i<a.size();i++){
                ac.get(emailroot).add(a.get(i));
            }

        }
        for(String s:ac.keySet()){
            ArrayList<String> al=new ArrayList<>(ac.get(s));
            al.add(0,owners.get(s));
            rs.add(al);

        }
        return rs;
    }
    String find3(HashMap<String,String> ids,String email){
        if(ids.get(email).equals(email)){
            return email;
        }
        String emailroot=find3(ids,ids.get(email));
        ids.put(ids.get(email),emailroot);//这里路径压缩之前写了ids.put(email,emailroot)就错了
        return emailroot;
    }
//8/13/2021不会了，看回以前
    public List<List<String>> accountsMerge4(List<List<String>> accounts) {
        HashMap<String,String> ids=new HashMap<>();
        HashMap<String,String> owner=new HashMap<>();
        HashMap<String,Set<String>> map=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        for(List<String> ac:accounts){
            String own=ac.get(0);
            for(int i=1;i<ac.size();i++){
                if(!map.containsKey(ac.get(i))){
                    owner.put(ac.get(i),own);
                }
                ids.put(ac.get(i),ac.get(i));
            }
        }
        for (List<String> ac:accounts){
            String email1=ac.get(1);
            for(int i=2;i<ac.size();i++){
                String email=ac.get(i);
                union4(email1,email,ids);
            }
        }
        for(List<String> ac:accounts){
            String rootEmail=find5(ids,ac.get(1));
            if (!map.containsKey(rootEmail)){
                map.put(rootEmail,new TreeSet<>());
            }
            for (int i=1;i<ac.size();i++){
                map.get(rootEmail).add(ac.get(i));
            }
        }
        for (String s:map.keySet()){
            List<String> al=new ArrayList<>();
            al.add(owner.get(s));
            al.addAll(map.get(s));
            rs.add(al);
        }

        return rs;
    }
    String find4(String email,HashMap<String,String> ids){
        if(email.equals(ids.get(email))){
            return email;
        }
        ids.put(email,find4(ids.get(email),ids));
        return ids.get(email);
    }
    void union4(String e1,String e2,HashMap<String,String> ids){
            String root1=find4(e1,ids);
            String root2=find4(e2,ids);
            ids.put(root2,root1);
    }
    //9/28/2021
    public List<List<String>> accountsMerge5(List<List<String>> accounts) {
        Map<String,String> owners=new HashMap<>();
        Map<String,String> ids=new HashMap<>();
        Map<String,TreeSet<String>> map=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        for (List<String> ac:accounts){
            String name=ac.get(0);
            for (int i=1;i<ac.size();i++){
                if (!owners.containsKey(ac.get(i))){
                    owners.put(ac.get(i),name);
                }
                if (!ids.containsKey(ac.get(i))){
                    ids.put(ac.get(i),ac.get(i));
                }
            }
        }
        for (List<String> ac:accounts){
            for (int i=2;i<ac.size();i++){
                String email1=ac.get(i-1);
                String email2=ac.get(i);
                union5(ids,email1,email2);
            }
        }
        for (List<String> ac:accounts){
            String root=find5(ids,ac.get(1));
            if (!map.containsKey(root)){
                map.put(root,new TreeSet<>());
            }
            for (int i=2;i<ac.size();i++){
                map.get(root).add(ac.get(i));
            }
        }
        for (Map.Entry<String,TreeSet<String>> entry:map.entrySet()){
            List<String> al=new ArrayList<>();
            String name=entry.getKey();
            al.add(owners.get(name));
            al.addAll(entry.getValue());
            rs.add(al);
        }

        return rs;

    }
    String find5(Map<String,String> ids,String email){
        if (ids.get(email).equals(email)){
            return email;
        }
        String father=ids.get(email);
        ids.put(email,find5(ids,father));
        return ids.get(father);
    }
    void union5(Map<String,String> ids,String email1,String email2){
        String f1=find5(ids,email1);
        String f2=find5(ids,email2);
        if (!f1.equals(f2)){
            ids.put(f2,f1);
        }
    }
}
