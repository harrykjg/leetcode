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
}