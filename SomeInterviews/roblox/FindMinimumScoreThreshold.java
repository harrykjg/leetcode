package SomeInterviews.roblox;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FindMinimumScoreThreshold {

    //我自己想的就是二分答案，枚举最大最小值，每个good方法就遍历所有game看谁是大于这个score的，记录下lose count和总count就知道这个答案是否符合。稍微提高一点
    //时间复杂度就是用map记录所有score出现过几次和是赢（或输）的count，然后就不用遍历所有games了，遍历map就行，那就是klogk，其中k是disctint的score的次数,但是disctint
    //少的话也没啥意义啊。然后看了答案说不用二分法，直接把score从小到大排序，然后从小的开始试是否能满足条件，如10不可以，20可以，那么答案就是21。但是从大到小排序也可以
    // 但是思路有点不一样，比如20不行了，但是再往下找比如15，可能又行了，比如原来是20的时候概率是4/5，现在到15，概率变成5/6即变高了。
    //还真是，这里单调性质不明显，如果分数越小得到的符合条件的game是越多，但是输赢比例倒不一定，因为新包括的game可能是赢也可能输，即total加了1，但是如果game是赢的话
    //那么输的比例是少了的。
    public int findMinScore(List<List<String>> games, double threshold) {
        Map<Integer,int[]> map=new TreeMap<>();
        int total=games.size();
        int totalLose=0;
        for (int i=0;i<games.size();i++){
            int score=Integer.valueOf(games.get(i).get(0));
            int lose=games.get(i).get(1).equals("lose")?1:0;
            int[] game=map.getOrDefault(score,new int[]{0,0});
            game[0]++;
            game[1]+=lose;
            totalLose+=lose;
        }
        double rate=totalLose/total;
        if(rate>=threshold){//如果总的失败率就符合条件，说明答案就是0.
            return 0;
        }

        int rs=-1;
        for (Map.Entry<Integer,int[]> entry:map.entrySet()){
            int[] game=entry.getValue();
            total-=game[0];
            totalLose-=game[1];
            rate=(double) totalLose/(double) total;
            if(rate>=threshold){//这里还不太好想，由于是从小到大的，那么现在对于当前game，把它排除之后剩下的所有total game和total lose是知道的，算出失败率
               return entry.getKey()+1;//如果符合条件，说明最小值设成当前score+1就可以排除当前值了，所以直接就是答案！
            }
        }
        return rs;

    }
}
