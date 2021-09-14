import java.util.*;

public class CustomSortString {
    //7/18/2021 写的不太好改了几次。主要是要用map记录字母出现的次数，要append完某个字母出现的所有次数
    public String customSortString(String order, String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        char[] ch=s.toCharArray();
        for (char c:ch){
            if (!map.containsKey(c)){
                map.put(c,1);
            }else {
                map.put(c,map.get(c)+1);
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<order.length();i++){
            while (map.containsKey(order.charAt(i))&&map.get(order.charAt(i))>0){
                sb.append(order.charAt(i));
                map.put(order.charAt(i),map.get(order.charAt(i))-1);
                if (map.get(order.charAt(i))==0){
                    map.remove(order.charAt(i));
                }
            }
        }
        Iterator<Map.Entry<Character,Integer>> it=map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Character,Integer> entry=it.next();
            for (int i=0;i<entry.getValue();i++){
                sb.append(entry.getKey());
            }
        }
        return sb.toString();

    }
}
