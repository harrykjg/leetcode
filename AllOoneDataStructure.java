import com.sun.xml.internal.rngom.digested.DNotAllowedPattern;

import java.util.*;

public class AllOoneDataStructure {
    public static void main(String[] ar){
        AllOoneDataStructure ao=new AllOoneDataStructure();
        ao.inc("hello");
        ao.inc("hello");
//        ao.inc("a");
//        ao.inc("hello");
//        ao.dec("hello");
        ao.getMaxKey();
        ao.getMinKey();
    }
    //8/7/2021 看别人的思路写的,写的还凑活改了几次ac了，肯定有地方可以减少些代码
    //https://www.youtube.com/watch?v=wYqLisoH80w
    Dnode head;
    Dnode last;
    Map<String,Dnode> map=new HashMap<>();
    public AllOoneDataStructure() {

    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)){
            if (head==null){
                Dnode dnode=new Dnode(1);
                dnode.set.add(key);
                dnode.next=dnode;
                dnode.pre=dnode;
                head=dnode;
                last=dnode;
                map.put(key,head);
            }else if (head.count==1){
                head.set.add(key);
                map.put(key,head);
            }else {
                Dnode dnode=new Dnode(1);
                dnode.set.add(key);
                dnode.pre=head.pre;
                dnode.next=head;
                head.pre.next=dnode;
                head.pre=dnode;
                head=dnode;
                map.put(key,dnode);
            }

        }else {
            Dnode dnode=map.get(key);
            if (dnode.next.count==dnode.count+1){
                dnode.next.set.add(key);
                map.put(key,dnode.next);
            }else if (dnode==last){//dnode已经是最后一位了，则append一个node作为last
                Dnode n=new Dnode(dnode.count+1);
                n.set.add(key);
                n.pre=last;
                n.next=head;
                last.next=n;
                head.pre=n;
                last=n;
                map.put(key,n);
            }else if (dnode.next.count>dnode.count+1){                //中间得插一个node
                Dnode n=new Dnode(dnode.count+1);
                n.set.add(key);
                n.next=dnode.next;
                dnode.next.pre=n;
                dnode.next=n;
                n.pre=dnode;
                map.put(key,n);
            }
            dnode.set.remove(key);
            if (dnode.set.size()==0){
                remove(dnode);
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)){
            return;
        }
        Dnode node=map.get(key);
        if (node.count==1){
            map.remove(key);
        }else if (node.pre.count==node.count-1){
            node.pre.set.add(key);
            map.put(key,node.pre);
        }else { //先插入再考虑删除
            Dnode n=new Dnode(node.count-1);
            n.set.add(key);
            n.pre=node.pre;
            node.pre.next=n;
            n.next=node;
            node.pre=n;

            if (node==head){
                head=n;
            }
            map.put(key,n);
        }
        node.set.remove(key);
        if (node.set.size()==0){
            remove(node);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (last==null){
            return "";
        }
        Iterator<String> it=last.set.iterator();
        return it.next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head==null){
            return "";
        }
        Iterator<String> it=head.set.iterator();
        return it.next();
    }
    void remove(Dnode dnode){
        if (dnode==head){
            head=dnode.next;
        }
        if (dnode==last){
            last=dnode.pre;
        }
        if (dnode.next==dnode){
            head=null;
            last=null;
            return;
        }else {
            dnode.pre.next=dnode.next;
            dnode.next.pre=dnode.pre;
        }
    }
    class Dnode{
        int count;
        Set<String> set=new HashSet<>();
        Dnode pre;
        Dnode next;
        public Dnode(int count){
            this.count=count;
        }
    }

    /*
     这个是用treemap的可能放进map和取first/last的时候不是o（1），写起来比上面这个容易多了
    TreeMap<Integer,Set<String>> tmap=new TreeMap<>();
    Map<String,Integer> map=new HashMap<>();

    public void inc(String key) {
        if(!map.containsKey(key)){
            map.put(key,1);
            if(!tmap.containsKey(1)){
                tmap.put(1,new HashSet<String>());
            }
            tmap.get(1).add(key);
            return;
        }
        int ori=map.get(key);
        map.put(key,ori+1);
        if(!tmap.containsKey(ori+1)){
            tmap.put(ori+1,new HashSet<String>());
        }
        tmap.get(ori+1).add(key);
        tmap.get(ori).remove(key);
        if(tmap.get(ori).size()==0){
            tmap.remove(ori);
        }
    }

    public void dec(String key) {
        int ori=map.get(key);
        map.put(key,ori-1);
        if(ori-1==0){
            map.remove(key);
            tmap.get(1).remove(key);
            if(tmap.get(ori).size()==0){
                tmap.remove(ori);
            }
        }else{
            if(!tmap.containsKey(ori-1)){
                tmap.put(ori-1,new HashSet<String>());
            }
            tmap.get(ori-1).add(key);
            tmap.get(ori).remove(key);
            if(tmap.get(ori).size()==0){
                tmap.remove(ori);
            }
        }

    }

    public String getMaxKey() {
        if(tmap.lastEntry()!=null){
            Iterator<String> it= tmap.lastEntry().getValue().iterator();
            if(it.hasNext()){
                return it.next();
            }
        }
        return "";
    }

    public String getMinKey() {
        if(tmap.firstEntry()!=null){
            Iterator<String> it= tmap.firstEntry().getValue().iterator();
            if(it.hasNext()){
                return it.next();
            }
        }
        return "";
    }
**/
}
