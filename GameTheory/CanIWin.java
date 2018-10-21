package GameTheory;

import java.util.HashMap;

/**
 * Created by yufengzhu on 10/8/18.
 */
public class CanIWin {
    public static void main(String[] args){
        CanIWin ci=new CanIWin();
        System.out.print(ci.canIWin(10,11));
    }
    //不会
    //https://www.youtube.com/watch?v=GNZIAbf0gT0 看他分析
    //https://leetcode.com/problems/can-i-win/discuss/95277/Java-solution-using-HashMap-with-detailed-explanation 代码看他
    //我想的是player1必须会赢的情况即player2不可能赢，即如果我能找到player2可以赢的话那么player1就不是必须赢了，但是貌似题目不是这个意思，题目说返回1可以赢就完了，因为1会选择最优解。
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;//等差数列求和
        if(sum < desiredTotal) return false;
        if(desiredTotal <= 0) return true;

        HashMap<String,Boolean> map=new HashMap<>();
        boolean[] memo=new boolean[maxChoosableInteger+1];
        return dfs(0,memo,map,desiredTotal);

    }
    boolean dfs(int cur,boolean[] memo,HashMap<String,Boolean> map,int target){
//        if(cur>=target){
//            return false;
//        }
        String s=helper(memo);//得到当前已经用过的数字的状态作为key，看map中有没有，有的话说明计算过了
        if(!map.containsKey(s)){
            for(int i=1;i<memo.length;i++){
                if(memo[i]){
                    continue;
                }
                memo[i]=true;
                if(cur+i>=target){//如果当前玩家选了一个数可以赢，那么就记录下初始状态s和true就可以返回了。我之前想的还要计算下去看有没有别的选项也可以达到true，其实没必要，因为此时memo数组的状态是已经固定式那个s了
                    map.put(s,true); //把这个判断推到下一层也可以，注意下一层就是那个玩家一进来发现cur已经大于等于target的话就是他输了
                    memo[i]=false;
                    return true;
                }
                if(!dfs(cur+i,memo,map,target)){//如果当前选择不能赢的话，那么看下一个玩家能不能赢，这里不好想，开始的想法是：他能赢的话说明我肯定不能赢，那么我直接map.put(s,false);其实不是，因为当前层还有没有试完
                    map.put(s,true);    //所有可以选的值，所以应该是下一个玩家不能赢的话，说明我肯定能！因为我是先手，并且前面已经检查过这个局肯定有解，还是不好想
                    memo[i]=false;
                    return true;
                }
                memo[i]=false;
            }
            map.put(s,false);
        }
        return map.get(s);

    }

    String helper(boolean[] memo){
        String s="";
        for(int i=1;i<memo.length;i++){
            if(memo[i]){
                s+=i+",";
            }
        }
        return s;
    }

}
