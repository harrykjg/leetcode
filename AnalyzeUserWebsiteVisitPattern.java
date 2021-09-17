import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {
    //9/13/2021 很麻烦，比如["cc","aa","bb","xx"],答案居然是["aa","bb","xx"]。尽管cc比aa的timestamp早，但是这四个string可以组成ccaabb，ccbbxx，
    //aabbxx，因此aabbxx的lexical order最先所以选他
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        HashMap<String, List<Pair>> map=new HashMap<>();
        Map<String,Integer> count=new TreeMap<>();
        for (int i=0;i<username.length;i++){
            if (!map.containsKey(username[i])){
                map.put(username[i],new ArrayList<>());
            }
            map.get(username[i]).add(new Pair(timestamp[i],website[i]));
        }
        String rs="";
        for (List<Pair> value:map.values()){
            Set<String> set = new HashSet<>();
            Collections.sort(value,(a,b)->a.time-b.time);

            for (int i=0;i<value.size();i++){
                for (int j=i+1;j<value.size();j++){
                    for (int k=j+1;k<value.size();k++){
                        String s=value.get(i).val+"#"+value.get(j).val+"#"+value.get(k).val;
                        if (!set.contains(s)){//这里也很恶心，比如某个用户他访问了a，a，a，a，那么aaa只能算一次，如果没有用set的话aaa就会算2次了。
                            count.put(s,count.getOrDefault(s,0)+1);
                            set.add(s);
                        }
                        if (rs.equals("")||count.get(s)>count.get(rs)||(count.get(s)==count.get(rs)&&s.compareTo(rs)<0)){
                            rs=s;
                        }
                    }
                }
            }
        }
        List<String> res=new ArrayList<>();
        res=Arrays.asList(rs.split("#"));
        return res;

    }
    class Pair{
        int time;
        String val;
        public Pair(int time,String val){
            this.time=time;
            this.val=val;
        }
    }
}
