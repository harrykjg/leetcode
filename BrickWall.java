import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 7/29/18.
 */
public class BrickWall {
    //不太会
    //https://www.cnblogs.com/hellowooorld/p/6684482.html
    public int leastBricks(List<List<Integer>> wall) {
        if(wall.size()==0){
            return 0;
        }
        int rs=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        int len=0;

        for(int i:wall.get(0)){
            len+=i;
        }


        int count=Integer.MIN_VALUE;
        int gap=0;
        map.put(0,0);//比如[[1]]这个例子，那就是只能穿过这一个砖了，gap就是出现0次
        for(int i=0;i<wall.size();i++){
            int sum=0;
            for(int j=0;j<wall.get(i).size();j++){
                sum+=wall.get(i).get(j);
                if(!map.containsKey(sum)){
                    map.put(sum,1);
                }else{
                    map.put(sum,map.get(sum)+1);
                }
                if(sum!=len&&map.get(sum)>count){//最后的宽度不行，否则就话最后一列的线就啥砖都不穿过了，其实也可以不算len，直接这里内层j<wall.size()-1就行了
                    count=map.get(sum);
                    gap=sum;
                }
            }
        }
        return wall.size()-map.get(gap);


    }
}
