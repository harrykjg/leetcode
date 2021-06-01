package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class offerTreeFilter {

    public static void main(String[] args){
        Inclusion root=new Inclusion("root","targetx");
        Inclusion root2=new Inclusion("root2","rootvalue2");
        Inclusion child1=new Inclusion("child1","v1");
        Inclusion child2=new Inclusion("currency","value2");
        Inclusion child3=new Inclusion("currency","target" );
        Inclusion child4=new Inclusion("child4","value4");
        Inclusion child5=new Inclusion("child5","value5");
        Inclusion child6=new Inclusion("currency","target");
        Inclusion child7=new Inclusion("child3","value3" );
        Inclusion child8=new Inclusion("child4","value4");

        Inclusion child10=new Inclusion("currency","target");
//        Inclusion child11=new Inclusion("currency","target");
        child2.next.add(child10);
//        child3.next.add(child11);
        child1.next.add(child3);
        child1.next.add(child4);
        root.next.add(child1);
        root.next.add(child2);

        child6.next.add(child7);
        child6.next.add(child8);
        root2.next.add(child5);
        root2.next.add(child6);
        List<Inclusion> ls=new ArrayList<>();
        ls.add(root);
        ls.add(root2);
        offerTreeFilter ot=new offerTreeFilter();
        List<Inclusion> rs=ot.treeFilter("currency","target",ls);
        for(Inclusion in:rs){
            System.out.println(in.name);
        }


    }

    public List<Inclusion> treeFilter(String name,String value,List<Inclusion> list){
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        List<Inclusion> rs= new ArrayList<>();
        boolean found=false;

        for(int i=0;i<list.size();i++){
            if(list.get(i).name.equalsIgnoreCase(name)&&list.get(i).value.equalsIgnoreCase(value)){
                found=true;
                break;
            }
        }
        if(found){
            rs=list.stream().filter(in->(in.name.equalsIgnoreCase(name)&&in.value.equalsIgnoreCase(value))).collect(Collectors.toList());
            return rs;
        }else{
            Iterator<Inclusion> it=list.iterator();
            while (it.hasNext()){
                if(!dfs(name,value,it.next())){
                    it.remove();
                }
            }
            return list;
        }

    }
    boolean dfs(String name,String value,Inclusion root){
        if(root==null||root.next==null||root.next.size()==0){
            return false;
        }
        List<Inclusion> rs= new ArrayList<>();
        boolean found=false;
        for(Inclusion in:root.next){
            if(in.name.equalsIgnoreCase(name)&&in.value.equalsIgnoreCase(value)){
                found=true;
                break;
            }

        }
        if(found){
            root.next=root.next.stream().filter(in->(in.name.equalsIgnoreCase(name)&&in.value.equalsIgnoreCase(value))).collect(Collectors.toList());
            return true;
        }else{

            boolean childFound=false;
            List<Inclusion> copyNext=new ArrayList<>();
            for(Inclusion in:root.next){
                if(dfs(name,value,in)){
                    childFound=true;
                    copyNext.add(in);
                }
            }
            root.next=copyNext;
            if(childFound){
                return true;
            }else{
                return false;
            }
        }

    }

}

class Inclusion{
    String name;
    String value;
    List<Inclusion> next;
    public Inclusion(String name,String value){
        this.name=name;
        this.value=value;
        next=new ArrayList<>();
    }
}
