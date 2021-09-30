import java.util.*;

public class SubdomainVisitCount {
    //9/17/2021 就是硬写
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> rs=new ArrayList<>();
        HashMap<String,Integer> map=new HashMap<>();
        for (String s: cpdomains){
            int count=Integer.parseInt(s.substring(0,s.indexOf(' ')));
            s=s.substring(s.indexOf(' ')+1);
            String[] ss=s.split("\\.");//注意要加两个斜杠
            StringBuilder sb=new StringBuilder();

            for (int i=ss.length-1;i>=0;i--){
                sb.insert(0,ss[i]);
                String temp=sb.toString();
                if (!map.containsKey(temp)){
                    map.put(temp,count);
                }else {
                    map.put(temp,map.get(temp)+count);
                }
                sb.insert(0,'.');//刚好这样写可以不用担心多了或少了'。'
            }
        }
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            String s=entry.getValue()+" "+entry.getKey();
            rs.add(s);
        }
        return rs;
    }
    //9/29/2021
    public List<String> subdomainVisits2(String[] cpdomains) {
        List<String> rs=new ArrayList<>();
        HashMap<String,Integer> map= new HashMap<>();
        for(int i=0;i<cpdomains.length;i++){
            String[] s=cpdomains[i].split(" ");
            int count=Integer.parseInt(s[0]);
            String dom=s[1];
            while(true){
                if(!map.containsKey(dom)){
                    map.put(dom,count);
                }else{
                    map.put(dom,map.get(dom)+count);
                }
                int index=dom.indexOf(".");
                if(index!=-1){
                    dom=dom.substring(index+1);
                }else{
                    break;
                }
            }
        }
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            String s=entry.getValue()+" ";
            s+=entry.getKey();
            rs.add(s);
        }
        return rs;
    }
}
