import java.util.*;

/**
 * Created by yufengzhu on 10/1/18.
 */
public class pinterestDianmian {

    public static void main(String[] args){
        pinterestDianmian pd=new pinterestDianmian();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Wel CoderPad");
        strings.add("This   pad  is running Java 8.");

       pd.function(strings.iterator());

    }


        public List<String> function(Iterator<String> iterator){
            Map<String,Integer> map=new HashMap<>();
            int max=0;
            StringBuilder sb=new StringBuilder();
            while(iterator.hasNext()){
                String s1=iterator.next();
                char[] ch=s1.toCharArray();
                int i=0;
                for(;i<ch.length;i++){
                    if(ch[i]==' '){
                        String temp=sb.toString();
                        if(sb.length()!=0){
                            if(!map.containsKey(temp)){
                                map.put(temp,1);
                            }else{
                                map.put(temp,map.get(temp)+1);
                            }
                            sb=new StringBuilder();
                            max=Math.max(max,map.get(temp));
                        }

                    }else{
                        sb.append(ch[i]);
                    }
                }
                if(i==ch.length&&sb.length()>0){
                    String temp=sb.toString();
                    if(sb.length()!=0){
                        if(!map.containsKey(temp)){
                            map.put(temp,1);
                        }else{
                            map.put(temp,map.get(temp)+1);
                        }
                        sb=new StringBuilder();
                        max=Math.max(max,map.get(temp));
                    }
                }
            }
            List<String> rs=new ArrayList<String>();
            for(String s:map.keySet()){
                if(map.get(s)==max){
                    rs.add(s);
                }
            }
            return rs;

        }




}
