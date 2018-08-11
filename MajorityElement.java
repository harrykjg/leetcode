import java.util.HashMap;

/**
 * Created by yufengzhu on 8/6/18.
 */
public class MajorityElement {
    //http://www.cnblogs.com/x1957/p/4177970.html
//http://blog.csdn.net/booirror/article/details/42738563
//http://www.tuicool.com/articles/EFbAnqa  https://www.cs.utexas.edu/~moore/best-ideas/mjrty/index.html
//http://www.cnblogs.com/ganganloveu/p/4177690.html
//这个应该不是他们想要的方法吧
    public int majorityElement(int[] nums) {

        int rs=0;
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
                if(map.get(nums[i])>count){
                    count=map.get(nums[i]);
                    rs=nums[i];
                }
            }else{
                map.put(nums[i],1);
                if(map.get(nums[i])>count){
                    count=map.get(nums[i]);
                    rs=nums[i];
                }
            }
        }
        return rs;
    }

}
