import java.util.*;

/**
 * Created by 502575560 on 7/8/16.
 */
public class AlienDictionary {

    public static void main(String[] args){
//        String[] words={"wrt","wrf","er","ett","rftt"};
        String[] words={"za","zb","ca","cb"};
        System.out.print(alienOrder2(words));

    }
//别人的代码
    public static String alienOrder(String[] words) {
        if(words.length == 0)
            return "";

        HashMap<Character, List<Character>> d = new HashMap<>();
        HashMap<Character, Boolean> used = new HashMap<>();

        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                if(!used.containsKey(words[i].charAt(j)))
                    used.put(words[i].charAt(j), false);
            }
        }

        for(int i = 1; i < words.length; i++) {
            String pre = words[i - 1];
            String cur = words[i];
            int j = 0;
            while(j < Math.min(pre.length(), cur.length())) {
                if(pre.charAt(j) != cur.charAt(j)) {
                    if(!d.containsKey(pre.charAt(j))) {
                        List<Character> l = new ArrayList<>();
                        l.add(cur.charAt(j));
                        d.put(pre.charAt(j), l);
                    } else {
                        d.get(pre.charAt(j)).add(cur.charAt(j));
                    }
                    break;
                }
                j++;
            }
        }

        StringBuffer sb = new StringBuffer();
        Iterator it = d.keySet().iterator();
        while(it.hasNext()) {
            char cur = (char)it.next();
            if(!used.get(cur)) {
                Set<Character> loop = new HashSet<>();
                boolean result = topologicalSort(d, used, cur, loop, sb);
                if(result)
                    return "";
            }
        }

        it = used.keySet().iterator();
        while(it.hasNext()) {
            char cur = (char)it.next();
            if(!used.get(cur))
                sb.insert(0, cur);
        }

        return sb.toString();
    }

    static boolean topologicalSort(HashMap<Character, List<Character>> d, HashMap<Character, Boolean> used, char cur, Set<Character> loop, StringBuffer sb) {
        used.put(cur, true);
        loop.add(cur);
        if(d.containsKey(cur)) {
            for(char i : d.get(cur)) {
                if(loop.contains(i))
                    return true;
                if(!used.get(i)) {
                    boolean result = topologicalSort(d, used, i, loop, sb);
                    if(result)
                        return true;
                }
            }
        }
        sb.insert(0, cur);
        return false;
    }

    //知道是拓扑排序之后自己想的，改了一两次
    public static String alienOrder2(String[] words) {
        if(words.length==1){
            return words[0];
        }
        StringBuilder sb=new StringBuilder();
        HashMap<Character,Integer> degree=new HashMap<>();
        HashMap<Character,HashSet<Character>> map=new HashMap<>();
        for(int i=0;i<words.length-1;i++){
            char[] ch1=words[i].toCharArray();
            char[] ch2=words[i+1].toCharArray();
            int j=0;
            for(j=0;j<Math.min(ch1.length,ch2.length);j++){
                if(!map.containsKey(ch1[j])){
                    HashSet<Character> set=new HashSet<>();
                    map.put(ch1[j],set);
                }
                if(!map.containsKey(ch2[j])){
                    HashSet<Character> set=new HashSet<>();
                    map.put(ch2[j],set);
                }
                if (ch1[j]!=ch2[j]){ //map存的是从c1走向的邻居
                    if(map.get(ch1[j]).add(ch2[j])){     //这里很容易漏，比如已经知道a指向b，即b的入度算了a了，如果又来一次a指向b，则不应该重复算把b的入度加上
                        if(!degree.containsKey(ch2[j])){
                            degree.put(ch2[j],1);
                        }else{
                            degree.put(ch2[j],degree.get(ch2[j])+1);
                        }
                    }
                    break;
                }
            }
            int jj=j;//把每个word的每个字母都放到map里
            int jjj=j;
            while (jj<ch1.length){
                if(!map.containsKey(ch1[jj])){
                    HashSet<Character> set=new HashSet<>();
                    map.put(ch1[jj],set);
                }
                jj++;
            }
            while (jjj<ch2.length){
                if(!map.containsKey(ch2[jjj])){
                    HashSet<Character> set=new HashSet<>();
                    map.put(ch2[jjj],set);
                }
                jjj++;
            }
        }
        Queue<Character> q=new LinkedList<>();
        //现在words里的所有string的所有字母都在map里，但是不是所有的字母在degree里，所以说不在degree里但是在map里的就是入度为0的
        for(Character c:map.keySet()){
            if(!degree.containsKey(c)){
                q.offer(c);
            }
        }
        if(q.isEmpty()){
            return "";
        }
        while (!q.isEmpty()){
            char c=q.poll();
            sb.append(c);
            for(char nei:map.get(c)){
                int ndegree=degree.get(nei);
                if(ndegree==1){
                    q.offer(nei);
                }
                degree.put(nei,ndegree-1);

            }
        }
        if(sb.toString().length()<map.size()){
            return "";
        }
        return sb.toString();


    }
}
