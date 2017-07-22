package ArrayListAndNumbers;

/**
 * Created by 502575560 on 7/10/17.
 */
public class SortColors {
    //http://www.cnblogs.com/grandyang/p/4341243.html
    //http://www.cnblogs.com/ganganloveu/p/3703746.html
    //http://www.cnblogs.com/grandyang/p/4341243.html
    public void sortColors(int[] nums) {
        // 其实这题挺难想的,几个index的意义想不清楚,不好解释
        int b=0;
        int e=nums.length-1;
        int i=0;
        while (i<=e){
            if(nums[i]==0){
                int temp=nums[i];//nums[i]和b交换,如果第一个b位置上本来就是0或者b=i那么换了也没事,否则b位置上就是1了,举个例子看看
                nums[i]=nums[b];
                nums[b]=temp;
                i++;
                b++;
            }else if(nums[i]==2){//把2换到后面去,后面的换上来,换上来的这个原素是0还是1还未知,所以i不++,下个while循环继续判断
                int temp=nums[e];
                nums[e]=nums[i];
                nums[i]=temp;
                e--;
            }else{
                i++;
            }

        }

        return;
    }
}
