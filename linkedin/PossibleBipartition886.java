package linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PossibleBipartition886 {
    static void main() {
        int[][] dis ={{1,2},{1,3},{2,3}};
        System.out.println(possibleBipartition(3,dis));
    }
    //开始建图只建了单边就错了，这个不好想，我觉得单边就够了。这个真的不好想，背就行了
    //这题主要confusing的地方还有，如果说按这个dislike建图，那么dfs一旦错了就说明不可能分成两组人了吗？会不会有别的分法能行？答：肯定不行，dislike
    //是硬性要求，一旦违背肯定是false所以dfs一旦false就返回false，否则就refurn true
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> map=new HashMap<>();
        for (int i=0;i<dislikes.length;i++){
            if(!map.containsKey(dislikes[i][0])){
                map.put(dislikes[i][0],new ArrayList<>());
            }
            map.get(dislikes[i][0]).add(dislikes[i][1]);
            if(!map.containsKey(dislikes[i][1])){
                map.put(dislikes[i][1],new ArrayList<>());
            }
            map.get(dislikes[i][1]).add(dislikes[i][0]);
        }
        int[] memo=new int[n+1];
        for (int i=1;i<=n;i++){
            if(memo[i]==0){
                if(!dfs(i,memo,map,1)){
                    return false;
                }
            }

        }
        return true;
    }
    static boolean dfs(int i,int[] memo,Map<Integer,List<Integer>> map,int color){
        if(memo[i]==0){
            memo[i]=color;
        }
        List<Integer> neighbour=map.get(i);
        if(neighbour!=null){
            for(int nei:neighbour){
                if(memo[nei]==0){
                    boolean ok=true;
                    if(color==1){
                        ok=dfs(nei,memo,map,2);
                    }else{
                        ok=dfs(nei,memo,map,1);
                    }
                    if(!ok){
                        return false;
                    }
                }else{
                    if(color==memo[nei]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
