package ArrayListAndNumbers;

/**
 * Created by 502575560 on 7/10/17.
 */
public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        //lintcode的卧槽瞎想的居然对了,就是两个pointer,如果第一个比k小则b++,否则把nums[b]换到后面去,e--,因为题目没要求大的数和小的数之间的降伏顺序不变所以只要扔到后面就醒了,
        // 再for循环找出第一个大于等于k的就完了,别人是可以直接return b的但是要处理特殊情况
            //http://www.cnblogs.com/EdwardLiu/p/4385823.html
        if(nums.length==0){
            return 0;
        }
        int b=0;
        int e=nums.length-1;
        while (b<e){
            if(nums[b]<k){
                b++;
                continue;
            }
            else {
                int temp=nums[e];
                nums[e]=nums[b];
                nums[b]=temp;
                e--;
            }
        }
        for (int i=0;i<nums.length;i++){
            if(nums[i]>=k){
                return i;
            }
        }
        return nums.length;
    }

    //九章第二轮，就是试试直接while循环结束之后返回，而不用再一个for循环，判断起来就要看b和e是否移动过，都已动过则再要判断
    //http://www.cnblogs.com/EdwardLiu/p/4385823.html 他就是条件是b<=e然后直接返回b就肯定对，神奇
    public int partitionArray2(int[] nums, int k) {
        if(nums.length==0){
            return 0;
        }
        int b=0;
        int e=nums.length-1;
        while (b<e){
            if(nums[b]<k){
                b++;
                continue;
            }
            else {
                int temp=nums[e];
                nums[e]=nums[b];
                nums[b]=temp;
                e--;
            }
        }
        return b;
    }
}
