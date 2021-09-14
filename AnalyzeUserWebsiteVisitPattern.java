import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {
    //9/13/2021 很麻烦，比如["oz","wnaaxbfhxp","mryxsjc","wlarkzzqht"] 他答案是["oz","mryxsjc","wlarkzzqht"]，即把wnaaxbfhxp 丢掉了，因为他lexical order
    //比较大
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        HashMap<String, TreeMap<Integer,String>> map=new HashMap<>();
        Map<String,Integer> count=new TreeMap<>();
        for (int i=0;i<username.length;i++){
            if (!map.containsKey(username[i])){
                map.put(username[i],new TreeMap<>());
            }
            map.get(username[i]).put(timestamp[i],website[i]);
        }
        for (TreeMap<Integer,String> m:map.values()){
            StringBuilder sb=new StringBuilder();
            for (Map.Entry<Integer,String> entry:m.entrySet()){
                sb.append(entry.getValue());
                sb.append('#');
            }
            sb.deleteCharAt(sb.length()-1);
            String pattern=sb.toString();//这个pattern可能比3长。
            String[] pp=pattern.split("#");
            Arrays.sort(pp);
            for (int i=0;i+3<pp.length;i++){
                StringBuilder ss=new StringBuilder();
                for (int j=i;j+i<3;j++){
                    ss.append(pp[j]);
                    ss.append("#");
                }
                ss.deleteCharAt(ss.length()-1);
                String ppp=ss.toString();
                count.put(pattern,count.getOrDefault(ppp,0)+1);
            }
        }
        List<String> rs=new ArrayList<>();
        int max=0;
        String candidate="";
        for (Map.Entry<String,Integer> entry:count.entrySet()){
            if (entry.getValue()>max){
                max=entry.getValue();
            }
        }
        for (Map.Entry<String,Integer> entry:count.entrySet()){
            if (entry.getValue()==max){
                rs.add(entry.getKey());
            }
        }
        Collections.sort(rs);
        String[] rrs=rs.get(0).split("#");
        return Arrays.asList(rrs);

    }
}
