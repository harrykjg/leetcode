import java.util.*;

/**
 * Created by 502575560 on 4/24/17.
 */
public class PalindromePairs {
    public static void main(String[] args){
        String[] words={"bat", "tab", "cat"};
        palindromePairs(words);
    }
    //自己只能想暴力法的
////这题有空字符串的话很麻烦,逻辑写起来很不好看.记着吧
    //http://blog.csdn.net/z6491679/article/details/51043225
    //http://blog.csdn.net/xiexingshishu/article/details/51056177
    //http://blog.csdn.net/lyh642784803/article/details/51834086
    //https://segmentfault.com/a/1190000005605192  代码看这个的,这个还有tire的做法,没看,他的hashmap方法写的好,不像我写的这么复杂
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> rs=new ArrayList<>();
        if(words.length<=1){
            return rs;
        }
        HashMap<String,Integer> map=new HashMap<>();
        for(int i=0;i<words.length;i++){
            map.put(words[i],i);
        }
        if(map.containsKey("")){//我开始想着先处理全部空字符的情况,但是这样的话下面的逻辑会重复
            for(int i=0;i<words.length;i++){
                if(!words[i].equals("")&&isPalindorme(words[i])){
                    List<Integer> ls=new ArrayList<>();
                    ls.add(map.get(""));
                    ls.add(map.get(words[i]));
                    List<Integer> ls2=new ArrayList<>();
                    ls2.add(map.get(words[i]));
                    ls2.add(map.get(""));
                    rs.add(ls);
                    rs.add(ls2);
                }
            }
        }
        for(int i=0;i<words.length;i++){
            String w=words[i];
            if(w.equals("")){//上面处理了""的情况,这里不处理了
                continue;
            }
            for(int j=0;j<w.length();j++){//这里限制了j<w.length,所以只可能出现left是""的情况,不会出现right是""的情况,right最少也有一个字母
                String left=w.substring(0,j);
                String right=w.substring(j,w.length());
                String rleft=reverse(left);
                String rright=reverse(right);
                if(isPalindorme(left)&&map.containsKey(rright)&&map.get(rright)!=map.get(w)){//左边是对称,即rright|left|right的情
                    List<Integer> ls=new ArrayList<>();                             //况,并且rright不能是他自己,否则如果words[i]是单个字母的字符串会被加入结果
                    ls.add(map.get(rright));
                    ls.add(map.get(w));
                    rs.add(ls);
                }   //left|right|rleft的情况,这里!rleft.equals("")的条件对付了{"a",""}的情况,因为上面单独处理""的for循环已经处理了""+"a"+""的情况,所以这里要排除
                if(isPalindorme(right)&&!rleft.equals("")&&map.containsKey(rleft)&&map.get(rleft)!=map.get(w)){
                    List<Integer> ls=new ArrayList<>();
                    ls.add(map.get(w));
                    ls.add(map.get(rleft));
                    rs.add(ls);
                }
            }
        }
        return rs;
    }
    public static boolean isPalindorme(String s){
        if(s==""){
            return true;
        }
        int b=0;
        int e=s.length()-1;
        while(b<e){
            if(s.charAt(b)!=s.charAt(e)){
                return false;
            }
            b++;
            e--;
        }
        return true;

    }
    private static String reverse(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    //https://segmentfault.com/a/1190000005605192 的代码,注意见下面
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        if (words == null || words.length < 2) return ret;
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<words.length; i++) map.put(words[i], i);
        for (int i=0; i<words.length; i++) {
            for (int j=0; j<=words[i].length(); j++) { // 注意他这里 j <= words[i],而我的是j<words[i]
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.getOrDefault(str2rvs, i) != i) ret.add(Arrays.asList(map.get(str2rvs), i));
                }
                if (isPalindrome(str2) && str2.length() != 0) {//他这里加了右边的length不等于0这个条件,否则会重复,拿["bat", "tab"]想
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates
                    if (map.getOrDefault(str1rvs, i) != i) ret.add(Arrays.asList(i, map.get(str1rvs)));
                }
            }
        }
        return ret;
    }
    private boolean isPalindrome(String str) {
        for (int l = 0, r = str.length() - 1; l <= r; l ++, r --)
            if (str.charAt(l) != str.charAt(r)) return false;
        return true;
    }

//7／22/2018
    public List<List<Integer>> palindromePairs3(String[] words) {
        List<List<Integer>> rs = new ArrayList<>();
        if (words == null || words.length < 2) return rs;
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<words.length;i++){
            map.put(words[i],i);
        }
        for(int i=0;i<words.length;i++){

            for(int j=0;j<=words[i].length();j++){
                String left=words[i].substring(0,j);//有可能是""空字符
                String right=words[i].substring(j,words[i].length());//j<=words[i]。length，所以右边也会是""，这样会有一个问题，就是【"bat", "tab"】这样会导致答案是【1，0】【0，1】【0，1】【1，0】
                String reverseLeft=new StringBuilder(left).reverse().toString();
                String reverseRight=new StringBuilder(right).reverse().toString();
                if(isPalindrome(left)&&map.containsKey(reverseRight)&&map.get(reverseRight)!=i){//比如"s"，j=0时，left是""，reverseRight是s自己，那就不行
                    ArrayList<Integer> al=new ArrayList<>();
                    al.add(map.get(reverseRight));
                    al.add(i);
                    rs.add(al);
                }
                if(isPalindrome(right)&&right.length()!=0&&map.containsKey(reverseLeft)&&map.get(reverseLeft)!=i){
                    ArrayList<Integer> al=new ArrayList<>();
                    al.add(i);
                    al.add(map.get(reverseLeft));
                    rs.add(al);
                }
            }
        }

        return rs;
    }
}

