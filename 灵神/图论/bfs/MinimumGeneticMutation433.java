package 灵神.图论.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumGeneticMutation433 {
    public static void main(String[] args) {
        String[] bank={"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
        System.out.println(minMutation("AACCTTGG","AATTCCGG",bank));
    }
    public static int minMutation(String startGene, String endGene, String[] bank) {
        //gene有8位，每个位可以变成ACTG，所以最多也就是32种变化。
        Set<String> set=new HashSet<>();

        for(String s:bank){
            set.add(s);
        }
        if(!set.contains(endGene)){
            return -1;
        }
        Set<String> memo=new HashSet<>();
        memo.add(startGene);
        Queue<String> q=new LinkedList<>();
        q.offer(startGene);
        int dist=0;
        char[] atcg={'A','C','T','G'};
        while (!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                String cur=q.poll();
                memo.add(cur);
                if(cur.equals(endGene)){
                    return dist;
                }
                StringBuilder sb=new StringBuilder(cur);
                for(int j=0;j<8;j++){
                    for (int k=0;k<atcg.length;k++){
                        char temp=sb.charAt(j);
                        if(temp== atcg[k]){
                            continue;
                        }
                        sb.setCharAt(j,atcg[k]);
                        String next= sb.toString();
                        if(set.contains(next)&&!memo.contains(next)){
                            q.offer(next);
                            memo.add(next);
                        }
                        sb.setCharAt(j,temp);
                    }
                }
            }
            dist++;
        }
        return -1;
    }
}
