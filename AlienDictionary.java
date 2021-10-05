import java.util.*;

/**
 * Created by 502575560 on 7/8/16.
 */
public class AlienDictionary {

    public static void main(String[] args){
//        String[] words={"wrt","wrf","er","ett","rftt","rk"};
        String[] words={"abc","ab"};
        System.out.print(alienOrder3(words));

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

    //知道是拓扑排序之后自己想的，改了一两次,后来test case加了["abc","ab"]，是过不了的
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
//10/1/2018,写的不好，每个单词的字符的先后顺序貌似是不考虑的，考虑的是不同的单词之间的字母的顺序，还是从谁往谁走没想清楚，
    //要这样想，比较"wrt","wrf",发现t是排f前面的，那么就是t的入度为0，f入度为1，即t往f走，当把t踢掉之后f入度要减1，那么怎么存呢？想想，要么是map的key是t
      //，value是包含f的一个set，要么是反过来，再想把t踢掉之后，t走向的所有节点的入度都要减1，这样map的key存的是t这样是对的，否则好想搞不了

    //8/22/2021  写的不顺，感觉test case多了很多噁心的case。开始多用了个set存所有字符，应该用map就行了
    public static String alienOrder3(String[] words) {
        if (words.length==1){//用来对付只有一个单词aba，要输出ab
            Set<Character> set=new HashSet<>();
            for (char c:words[0].toCharArray()){
                set.add(c);
            }
            StringBuilder sb=new StringBuilder();
            for (char c:set){
                sb.append(c);
            }
            return sb.toString();
        }
        HashMap<Character,Set<Character>> map=new HashMap<>();
        HashMap<Character,Integer> degree=new HashMap<>();
        for (int i=1;i<words.length;i++){
            String s1=words[i-1];
            String s2=words[i];
            int i1=0;
            int i2=0;
            boolean found=false;
            while (i1<s1.length()&&i2<s2.length()){
                if (s1.charAt(i1)==s2.charAt(i2)){
                    if (!map.containsKey(s1.charAt(i1))){
                        map.put(s1.charAt(i1),new HashSet<>());
                    }
                    i1++;
                    i2++;
                }else {
                    if (!map.containsKey(s1.charAt(i1))){
                        map.put(s1.charAt(i1),new HashSet<>());
                    }
                    if (!map.containsKey(s2.charAt(i2))){
                        map.put(s2.charAt(i2),new HashSet<>());
                    }
                    if (!map.get(s1.charAt(i1)).contains(s2.charAt(i2))){//这个很容易漏，可能会有a先于b，后面由来a先于b，不判断的话入度就会错误的重复计算了
                        map.get(s1.charAt(i1)).add(s2.charAt(i2));
                        degree.put(s2.charAt(i2),degree.getOrDefault(s2.charAt(i2),0)+1);
                    }
                    i1++;
                    i2++;
                    found=true;
                    break;//忘了break就错了
                }
            }
            if(!found&&i2==s2.length()&&i1<s1.length()){
                return "";
            }
            while (i1<s1.length()){
                if (!map.containsKey(s1.charAt(i1))){
                    map.put(s1.charAt(i1),new HashSet<>());
                }
                i1++;
            }
            while (i2<s2.length()){
                if (!map.containsKey(s2.charAt(i2))){
                    map.put(s2.charAt(i2),new HashSet<>());
                }
                i2++;
            }
        }
        Queue<Character> q=new LinkedList<>();
        for (Character c:map.keySet()){
            if (!degree.containsKey(c)){
                q.offer(c);
            }
        }
        StringBuilder sb=new StringBuilder();
        while (!q.isEmpty()){
            char c=q.poll();
            sb.append(c);
            Set<Character> nei=map.get(c);
            if(nei!=null){
                for (char n:nei){
                    degree.put(n,degree.get(n)-1);
                    if (degree.get(n)<=0){
                        q.offer(n);
                    }
                }
            }
        }
        if (sb.length()!=map.size()){
            return "";
        }
        return sb.toString();
    }

    //10/2/2021 还凑活，改了几次，和上面不同的主要时候单独扫一遍所有words的所有字符加进map里，省的在比较两个word的时候找到不容的char之后还要继续扫完2个word
    public String alienOrder4(String[] words) {
        HashMap<Character,Set<Character>> map=new HashMap<>();
        HashMap<Character,Integer> degree=new HashMap<>();
        for(int i=0;i<words.length-1;i++){
            String word1=words[i];
            String word2=words[i+1];
            int i1=0;
            int i2=0;
            boolean found=false;
            while(i1<word1.length()&&i2<word2.length()){
                if(word1.charAt(i1)==word2.charAt(i2)){
                    map.put(word1.charAt(i1),new HashSet<Character>());
                    i1++;
                    i2++;
                }else{
                    found=true;
                    if(!map.containsKey(word1.charAt(i1))){
                        map.put(word1.charAt(i1),new HashSet<Character>());
                    }
                    if(!map.get(word1.charAt(i1)).contains(word2.charAt(i2))){
                        map.get(word1.charAt(i1)).add(word2.charAt(i2));
                        degree.put(word2.charAt(i2),degree.getOrDefault(word2.charAt(i2),0)+1);
                    }
                    break;
                }
            }
            if(!found&&i2==word2.length()&&i1<word1.length()){
                return "";
            }
        }
        for(String w:words){//
            char[] ch=w.toCharArray();
            for(char c:ch){
                if(!map.containsKey(c)){
                    map.put(c,new HashSet<Character>());
                }
            }
        }
        Queue<Character> q=new LinkedList<>();
        for(Character c:map.keySet()){
            if(!degree.containsKey(c)){
                q.offer(c);
            }
        }
        StringBuilder sb=new StringBuilder();
        int count=map.size();
        while(!q.isEmpty()){
            char c=q.poll();
            sb.append(c);
            if(map.containsKey(c)){
                for(Character n:map.get(c)){
                    degree.put(n,degree.get(n)-1);
                    if(degree.get(n)==0){
                        q.offer(n);
                        degree.remove(n);
                    }
                }
            }
        }

        if(sb.length()==count){
            return sb.toString();
        }
        return "";
    }
}
