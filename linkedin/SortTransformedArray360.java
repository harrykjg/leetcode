package linkedin;

public class SortTransformedArray360 {
    static void main() {
        int[] a={-4,-2,2,4};
        int[] rs=sortTransformedArray(a,1,3,5);
        for (int i:rs){
            System.out.println(i);

        }

    }
    //只能想到是双曲线，那么只要找到双曲线的中点，已知双曲线开口方向，就可以知道中点两侧是上升还是下降，那么如何知道中点呢？开始以为不需要定中点
    //直接头尾pointer，谁小就取谁不就行了吗？结果不行，如[-4,-2,2,4], a = 1, b = 3, c = 5，双曲线两端都大，中间的小，因此直接头尾双指针
    //是不行的取小的。那咋办？那就先排序大的，如说a大于0即双曲线开口向上，则取大的先排，因为肯定是两端从大往小，a小于0则反过来
    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] rs=new int[nums.length];

        int begin=0;
        int end=nums.length-1;
        int index1=nums.length-1;
        int index2=0;
        while (begin<=end){
            int one=caculate(nums[begin],a,b,c);
            int two=caculate(nums[end],a,b,c);

            if(a>0){
                if(one<two){
                    rs[index1--]=two;
                    end--;
                }else{
                    rs[index1--]=one;
                    begin++;
                }
            }else{

                if(one<two){
                    rs[index2++]=one;
                    begin++;
                }else{
                    rs[index2++]=two;
                    end--;
                }
            }
        }
        return rs;
    }
    static int  caculate(int x,int a ,int b,int c){
        return x*x*a+b*x+c;
    }
}
