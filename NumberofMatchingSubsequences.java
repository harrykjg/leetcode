import java.util.*;

/**
 * Created by yufengzhu on 7/2/18.
 */
public class NumberofMatchingSubsequences {
    public static void main(String[] args){
        NumberofMatchingSubsequences nm=new NumberofMatchingSubsequences();
//        String s="btovxbkumc";
//        String[] w={"btovxbku","to","zueoxxxjme","yjkclbkbtl"};
        String s="abcbde";
        String[] w={"a","bb","acd","ace"};
        System.out.println(nm.numMatchingSubseq3(s,w));
    }
    //不会，https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation/ 真是吊
    //自己写debug了很久才高对，是关于concurrentModification的东西，
    public int numMatchingSubseq(String S, String[] words) {
        HashMap<Character,List<String>> map=new HashMap<>();
        for(int i=0;i<words.length;i++){
            char[] ch=words[i].toCharArray();
            if(!map.containsKey(ch[0])){
                List<String> ls=new ArrayList<>();
                ls.add(new String(ch,1,ch.length-1));
                map.put(ch[0],ls);
            }else{
                map.get(ch[0]).add(new String(ch,1,ch.length-1));
            }
        }
        int rs=0;
        char[] s=S.toCharArray();
        for(char c:s){
            if(map.containsKey(c)){
                List<String> ls=map.get(c);//map里的list会在遍历的过程中需要删除，那样会错，所以要建一个copy，单纯来遍历，然后把原来的list清除掉，再加或者不加如子串
                List<String> copy=new ArrayList<>(ls);
                ls.clear();
                for(int i=0;i<copy.size();i++){
                    char[] chss=copy.get(i).toCharArray();
                    if(chss.length==0){
                        rs++;//已经是最后一个了，不需要加到map的list里

                    }
                    else{
                        char newc=chss[0];
                        if(map.containsKey(newc)){
                            map.get(newc).add(new String(chss,1,chss.length-1));//这里加到原来那个list里，由于list己经被clear了，所以没事，否则会错！
                        }else{
                            List<String> newls=new ArrayList<>();
                            newls.add(new String(chss,1,chss.length-1));
                            map.put(newc,newls);
                        }
                    }
                }
            }
        }
        return rs;
    }

    //10/17/2018,还是不知道好的方法
    public int numMatchingSubseq2(String S, String[] words) {
        HashMap<Character,List<String>> map=new HashMap<>();
        char[] ch=S.toCharArray();
        int rs=0;
        for(String s:words){
            if(!map.containsKey(s.charAt(0))){
                map.put(s.charAt(0),new ArrayList<>());
            }
            map.get(s.charAt(0)).add(s.substring(1));
        }
        for(int i=0;i<ch.length;i++){
            if(map.containsKey(ch[i])){
                ArrayList<String> al=(ArrayList<String>) map.get(ch[i]);
                map.remove(ch[i]);//这里我直接把al删掉了，代码就比之前简洁了，反正里面的字符串再加的话就加到别的key下的，避免了对al的concurrent modification
                for(String s:al){
                    if(s.length()==0){
                        rs++;
                    }else{
                        Character first=s.charAt(0);
                        String s2=s.substring(1);
                        if(map.containsKey(first)){
                            map.get(first).add(s2);
                        }else{
                            map.put(first,new ArrayList<>());
                            map.get(first).add(s2);
                        }
                    }
                }
            }
        }
        return rs;

    }

    //10/21/2021 自己想的，用一个map里面装s里的所有char出现的index，HashMap<Character,TreeSet<Integer>>。这样遍历每个word，比如输入s=abcbde，
    //则其map是 a0 b13 c2 d4 e5 。然后看adb，扫描第一个a，发现map有a，并且a出现在0位置上，此时index初始化为0，没毛病，取了第一a之后update一下index=0+1，说明
    // 下一个出现的字符必须在1或者1后面。然后扫描到d，发现有d并且出现的位置是4，大于1，然后把index=4+1，然后扫描到b，发现有b，但是b的出现位置是1，3，没有大于等于5的
    //因此不行，
    public int numMatchingSubseq3(String s, String[] words) {
        HashMap<Character,TreeSet<Integer>> map=new HashMap<>();
        int rs=0;
        for (int i=0;i<s.length();i++){
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i),new TreeSet<>());
            }
            map.get(s.charAt(i)).add(i);
        }
        for (String w:words){
            char[] ch=w.toCharArray();
            int i=0;
            int index=0;//代表现在char出现的位置必须在大于等于index的位置上
            for (;i<ch.length;i++){
                if (!map.containsKey(ch[i])){
                    break;
                }else{
                    if (map.get(ch[i]).ceiling(index)!=null){
                        index=map.get(ch[i]).ceiling(index)+1;
                    }else{
                        break;
                    }
                }
            }
            if (i==ch.length){//到达最后的说明都找到了，只要有一个没找到都会提前break
                rs++;
            }
        }
        return rs;
    }

}
