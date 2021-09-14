import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeNeededtoInformAllEmployees {
    //9/12/2021
    //不难就是开始没想清楚，比如一个boss有2手下，boss通知他们要3分钟，这其中一个手下通知他的手下要5分钟，另一个手下通知他的手下要2分钟，则总的时间是5+3=8，
    //而不是3+5+2=10，即每一轮通知手下，只加上最长的那个时间。
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        HashMap<Integer, List<Integer>> map=new HashMap<>();
        for (int i=0;i<n;i++){
            int boss=manager[i];
            if (boss==-1){
                continue;
            }
            if (!map.containsKey(boss)){
                map.put(boss,new ArrayList<>());
            }
            map.get(boss).add(i);
        }
        return dfs(map,headID,informTime);

    }
    int dfs(HashMap<Integer,List<Integer>> map,int boss,int[] informTime){
        int rs=0;
        if (map.containsKey(boss)){
            int max=0;
            for (int e:map.get(boss)){
                int n=dfs(map,e,informTime);
                max=Math.max(max,n);
            }
            rs+=informTime[boss];
            rs+=max;
        }

        return rs;
    }
}
