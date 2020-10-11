package contest;

import java.util.HashMap;
import java.util.Map;

public class CountUnhappyFriends {
    //自己想的就是用map，不难
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<pairs.length;i++){
            int x=pairs[i][0];
            int y=pairs[i][1];
            map.putIfAbsent(x,y);
            map.putIfAbsent(y,x);
        }
        int rs=0;
        for(int x:map.keySet()){
            int y=map.get(x);
            if(preferences[x][0]==y){
                continue;
            }
            for(int i=0;i<preferences[x].length;i++){
                int u=preferences[x][i];
                if(u==y){
                    break;
                }
                boolean found=false;
                if(map.containsKey(u)){
                    int v=map.get(u);
                    for(int j=0;j<preferences[u].length;j++){
                        if(preferences[u][j]==v){
                            break;
                        }
                        if(preferences[preferences[x][i]][j]==x){
                            rs++;
                            found=true;
                            break;
                        }
                    }
                }
                if(found){
                    break;
                }
            }
        }
        return rs;
    }
}
