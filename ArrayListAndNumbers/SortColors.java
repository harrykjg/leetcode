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
//九章第二轮,举个例子以为能写出来,结果还是错了,大概思路是记得的,就是一个cur一个b一个e三个pointer互相换
    public void sortColors2(int[] nums) {
        int b=0;
        int e=nums.length-1;
        int cur=0;
        while (cur<=e){//开始这里写成b<e就错了
            if(nums[cur]==1){
                cur++;
                continue;
            }
            if(nums[cur]==0){//这里开始想着nums[cur]=0的话则和b换,b上的东西换过来我不知道是1还是2,所以cur不变要继续检查,其实不是这样的,b上面只有2中可能:0
                int temp=nums[b]; //或者1,不会是2,因为如果b是2的话,一开始进来while循环就把他换到后面了.既然b上面是0或者1,两种情况都是要cur++,这里是关键点,
                nums[b]=0;    //否则无法处理原数组第一个数就是0的情况
                nums[cur]=temp;
                b++;
                cur++;//开始这里少些了cur++就错了
            }else{
                int temp=nums[e];
                nums[e]=2;
                nums[cur]=temp;
                e--;
            }
        }
    }
//11/4/2018,基本思路是对的，但是还是翻了上面注解的错误，并且while的条件也不对
    public void sortColors3(int[] nums) {
        int red=0;
        int blue=nums.length-1;
        int cur=0;
        while (cur<=blue){
            if(nums[cur]==1){
                cur++;
                continue;
            }
            if(nums[cur]==2){
                int temp=nums[blue];
                nums[blue]=2;
                blue--;
                nums[cur]=temp;
                continue;
            }
            if(nums[cur]==0){
                int temp=nums[red];
                nums[red]=0;
                red++;
                nums[cur]=temp;
                cur++;//这里开始没写，见上面以前的注解，很容易漏
                continue;
            }
        }
        return;

    }

    //04/16/2020,还是挺难，写的不好
    public void sortColors4(int[] nums) {
        if(nums.length==0){
            return;
        }
        int b=0;
        int e=nums.length-1;
        int cur=0;
        while (cur<=e){
            if(nums[cur]==1){
                cur++;
                continue;
            }
            if (nums[cur]==2){
                int temp=nums[e];
                nums[e]=2;
                nums[cur]=temp;
                e--;
                continue;
            }
            int temp=nums[b];
            nums[b]=0;
            nums[cur]=temp;
            b++;
            cur++;
        }
    }
//05/25/2020，还是写不出
    public void sortColors5(int[] nums) {
        if(nums.length==0){
            return;
        }
        int red=0;
        int blue=nums.length-1;
        int cur=0;
        while(cur<blue){//少了等号就不行
            if(nums[cur]==2){
                int temp=nums[blue];
                nums[blue]=2;
                nums[cur]=temp;
                blue--;
            }else if(nums[cur]==0){
               int temp=nums[red];
               nums[red]=0;
               nums[cur]=temp;
               cur++;
               red++;
            }else{
                cur++;
            }
        }
    }
//6/3/2021,写的一般，主要是靠例子错了再改的，思路还可以
    public void sortColors6(int[] nums) {
        if(nums.length==0){
            return;
        }
        int b=0;
        int e=nums.length-1;
        int cur=0;
        while (cur<=e){

            if(nums[cur]==0){
                int temp=nums[b];
                nums[b]=0;
                nums[cur]=temp;
                cur++;//由于是从前往后走的，所以前面换过来的东西肯定是0，或者1，两种情况都是cur++
                b++;
                continue;
            }

            if(nums[cur]==2){
                int temp=nums[e];
                nums[e]=2;
                nums[cur]=temp;
                e--;
            }else{
                cur++;
            }
        }

    }
    //8/8/2021 还是写的不好。记着找到0时i也得++
    public void sortColors7(int[] nums) {
        int b=0;
        int e=nums.length-1;
        int i=0;
        while (i<=e){
            if (nums[i]==0){
                int temp=nums[b];
                nums[b]=0;
                nums[i]=temp;
                b++;
                i++;
                continue;
            }
            if (nums[i]==2){
                int temp=nums[e];
                nums[e]=2;
                nums[i]=temp;
                e--;
                continue;
            }
            i++;
        }
    }
}
