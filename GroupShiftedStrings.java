import java.util.*;

/**
 * Created by yufengzhu on 8/6/18.
 */
public class GroupShiftedStrings {
    public static void main(String[] args){
        GroupShiftedStrings gs=new GroupShiftedStrings();
        String[] ss={"abc","bcd","acef","xyz","az","ba","a","z"};
        gs.groupStrings(ss);
    }

    //这个代码是自己想的，就是遍历每个字符串，对于当前字符串，算出他的26个shift的形式，如果其中的几个形式在map中存在，就加入结果集,run到倒数第二个test case就错了，不知道为啥
    //还是看别人的代码把
    //https://www.cnblogs.com/Dylan-Java-NYC/p/5211776.html
    //http://www.cnblogs.com/beiyeqingteng/p/5717873.html
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> rs=new ArrayList<>();
        HashMap<String,Integer> map=new HashMap<>();
        for(String s:strings){
//            map.put(s,map.getOrDefault(s,0)+1);
            if(!map.containsKey(s)){
                map.put(s,1);
            }else{
                map.put(s,map.get(s)+1);
            }

        }

        for(int i=0;i<strings.length;i++){
            String cur=strings[i];

            ArrayList<String> al=new ArrayList<>();
            if(map.containsKey(cur)&&map.get(cur)>=1){
                al.add(cur);
                map.put(cur,map.get(cur)-1);
            }
            char[] ch=cur.toCharArray();
            for(int j=0;j<=25;j++){//
                char[] ch2=ch;
                for(int k=0;k<ch.length;k++){//这个shift的过程其实改了很久，不好想
                    if(ch[k]+1>'z'){
                        int gap=ch[k]+1-'z';
                        ch2[k]=(char)('a'+gap-1);
                    }else{
                        ch2[k]=(char)(ch[k]+1);
                    }

                }
                String ns=new String(ch2);
                if(map.containsKey(ns)&&map.get(ns)>=1){
                    al.add(ns);
                    map.put(ns,map.get(ns)-1);
                }

            }
            if(al.size()!=0){
                rs.add(al);

            }
        }
        return rs;

    }
//8/17/2021 开始不太理解怎么样的string算同一类，比如abc和bcd是同一类因为每个字符是相邻的，比如az和ba也是同一类因为a和z相隔了25个字符，b和a也是。
    //但是acef就有点奇怪了，a和c隔了一个，c和e隔了一个，e和f确是相邻的。要和他同类的话比如bdfg就算dfhi也算
    //上面那两个链接其实也是一样的，就是对每一个字符串，算出他的base，base相同自然是同一类了，那么怎么算base呢？就是拿自己的第0，1，2，3。。n位
    //去减自己的第一位，得到的int加上'a'，如果是负数，则加上26，如ba和az
    public List<List<String>> groupStrings2(String[] strings) {
        List<List<String>> rs=new ArrayList<>();
        HashMap<String,List<String>> map=new HashMap<>();
        for (int i=0;i<strings.length;i++){
            String base=getBase(strings[i]);
            if (!map.containsKey(base)){
                map.put(base,new ArrayList<String>());
            }
            map.get(base).add(strings[i]);
        }
        for (List<String> al:map.values()){
            rs.add(al);
        }
        return rs;
    }
    String getBase(String s){
        StringBuilder sb=new StringBuilder();
        char[] ch=s.toCharArray();
        for (int i=0;i<s.length();i++){
            int c=ch[i]-ch[0]+'a';//
            if (c<'a'){//居然可以直接加int
                c+=26;
            }
            sb.append(c);
        }
        return sb.toString();
    }
    //8/23/2021 写的getBase是这样的，是相邻的两个char相减，居然也行
    String getBase2(String s){
        char[] ch=s.toCharArray();
        if(s.length()==1){
            return "a";
        }
        StringBuilder sb=new StringBuilder();
        sb.append('a');
        for(int i=1;i<ch.length;i++){
            int gap=ch[i]-ch[i-1];
            if(gap<0){
                gap+=26;
            }
            sb.append(gap+'a');
        }
        return sb.toString();
    }

}
