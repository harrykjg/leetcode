package SomeInterviews.databricks;

import java.util.*;

public class CustomerRevenueSystem {
    int id;
    TreeSet<Customer> set;
    Map<Integer,Customer> map;
    Map<Integer,List<Integer>> referal;

    public CustomerRevenueSystem() {
        set=new TreeSet<>((a,b)->{
            if (a.total != b.total) {
                return Long.compare(b.total, a.total);
            }
            return Integer.compare(a.id, b.id);       // 注意要这样写，因为如果total一样的话就加不进set里了
        });//原来有这样的写法
        map=new HashMap<>();
        referal=new HashMap<>();
    }

    public int add(int revenue) {
        int rs=id;
        Customer cu=new Customer(rs,revenue);
        set.add(cu);
        map.put(rs,cu);
        referal.put(rs,new ArrayList<>());
        id++;
        return rs;
    }

    public int addByReferral(int revenue, int referrerId) {
        if(!map.containsKey(referrerId)){
            return -1;
        }
        int rs=id;
        Customer cu=new Customer(id++,revenue);

        map.put(rs,cu);
        Customer ref=map.get(referrerId);
        ref.total+=revenue;
        set.remove(ref);
        set.add(ref);
        referal.put(rs,new ArrayList<>());//这里也漏了
        referal.get(referrerId).add(rs);
        return rs;
    }

    public List<Integer> getTopKCustomer(int k, int minRevenue) {
        List<Integer> rs=new ArrayList<>();
        int count=0;
        for (Customer cu:set){
            if(cu.total>=minRevenue&&count<k){
                rs.add(cu.id);
                count++;
            }else {
                break;
            }
        }
        return rs;
    }
    //follow up 找这个customerId refer了多少人，然后这些人又refer了多少人
    public List<List<Integer>> getRelations(int customerId) {
        List<List<Integer>> rs=new ArrayList<>();
        if(!map.containsKey(customerId)){
            return null;
        }
        Queue<Integer> q=new LinkedList<>();
        q.offer(customerId);
        while (!q.isEmpty()){
            int size=q.size();
            List<Integer> al=new ArrayList<>();
            for (int i=0;i<size;i++){
                int cur=q.poll();
                List<Integer> neighbour=referal.get(cur);
                if(neighbour!=null){
                    for(int nei:neighbour){
                        al.add(nei);
                        q.offer(nei);
                    }
                }
            }
            if(al.size()>0){
                Collections.sort(al);
                rs.add(al);
            }
        }
        return rs;
    }
    //还有follow up，如何实现实时top k，现在用treeset就已经可以实时了
    //还有follow up 如果要nested revenue怎么写。就是说，a refer b， b refer c，现在c的revenue +10，则b也+10，a也+10，做法就是customer维护一个parent，
    //即refer他的人，然后一路线性网上找就行了
}

class Customer {
    final int id;
    long total;

    Customer(int id, long initialRevenue) {
        this.id = id;
        this.total = initialRevenue;
    }
}