import java.util.*;

public class StickerstoSpellWord {
    public static void main(String[] args){
        StickerstoSpellWord ss=new StickerstoSpellWord();
//        String[] words={"example","with","science"};
        String[] words={"these","guess","about","garden","him"};
        System.out.println(ss.minStickers(words,"atomher"));
    }
    //8/19/2021 自己想的greedy的暴力法是错的，统计每个sticker单词的字符出现的次数，统计target所需的字符和个数，然后greedy看每个sticker谁有最多符合target的，
    //就选它，然后target减去已经match的单词，然后继续.结果是错的，比如["these","guess","about","garden","him"]找atomher这个例子，其中有一步有两个
    //单词都和target有2个match，选择garden的话，会导致剩余的hm，导致最后只要去him就搞定，如果选择these的话回导致下一步剩下mr，而mr要2不才能搞完。
    //dp的解法非常难。不研究了
    public int minStickers(String[] stickers, String target) {//bfs应该是可行的居然可以AC
        int[][] map = new int[stickers.length][26];
        if (target.length() == 0) {
            return 0;
        }
        int[] tar = new int[26];
        for (int i = 0; i < target.length(); i++) {
            tar[target.charAt(i) - 'a']++;
        }
        Set<String> memo=new HashSet<>();
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                int index = stickers[i].charAt(j) - 'a';
                map[i][index]++;
            }
        }
        Queue<int[]> q=new LinkedList<>();
        q.offer(tar);
        char[] ch=target.toCharArray();
        Arrays.sort(ch);
        memo.add(new String(ch));//把target 字符sort了之后再放进memo，因为后面generate方法产生的string其实也是sort的了
        int rs=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                if (isEmpty(cur)){
                    return rs;
                }
                for (int j=0;j<map.length;j++){
                    String next=generate(cur,map[j]);
                    int[] nextA=new int[26];

                    if (!memo.contains(next)){
                        for (int k=0;k<next.length();k++){
                            nextA[next.charAt(k)-'a']++;
                        }
                        q.offer(nextA);
                        memo.add(next);
                    }
                }
            }
            rs++;
        }

        return -1;
    }
    boolean isEmpty(int[] cur){
        for (int i=0;i<26;i++){
            if (cur[i]>0){
                return false;
            }
        }
        return true;
    }
    String generate(int[] a,int[] b){
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<26;i++){
            if (a[i]>0&&b[i]==0){
                int n=a[i];
                for (int j=0;j<n;j++){
                    sb.append((char)(i+'a'));//这个直接写i+'a'就是append一个int了
                }
            }else if (a[i]>0&&b[i]>0){
                int needed=0;
                if (a[i]>b[i]){
                    needed=a[i]-b[i];
                }else {
                    needed=0;
                }
                for (int j=0;j<needed;j++){
                    sb.append((char)(i+'a'));
                }
            }
        }
        return sb.toString();
    }
}
