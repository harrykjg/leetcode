package ArrayListAndNumbers;

/**
 * Created by 502575560 on 7/10/17.
 */
public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        //lintcode的卧槽瞎想的居然对了,就是两个pointer,如果第一个比k小则b++,否则把nums[b]换到后面去,e--,因为题目没要求大的数和小的数之间的降伏顺序不变所以只要扔到后面就醒了,
        // 再for循环找出第一个大于等于k的就完了,别人是可以直接return b的但是要处理特殊情况.如果while里是b<=e那么直接返回b就行了，之前一直写的是b<e其实是漏了处理当
        //b=e时，这个nums【b】到底是大于k还是小于k，小于的话b是要++的！
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


//6/2/2021,lintcode上第二个example解释有点confusing，不要看那个。想的是用自己第一个想法但是直接返回b而不用for循环再找，居然过不了。看别人就是用的quick sort的方法
    //https://www.jianshu.com/p/973d53022242
    //https://www.cnblogs.com/zsychanpin/p/7366442.html
    public int partitionArray3(int[] nums, int k) {
        if(nums.length==0){
            return 0;
        }
        int b=0;
        for (int i=0;i<nums.length;i++){
            if(nums[i]<k){//我觉得有点反直觉，直觉上是大于k的换到后面，小于的不管
                int temp=nums[b];
                nums[b]=nums[i];
                nums[i]=temp;
                b++;
            }
        }
        return b;

    }
}
