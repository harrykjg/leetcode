package ArrayListAndNumbers;

public class SortArrayByParity {
    //9/12/2021 想着用sortcolor来写，到284个case错了不知道为啥,而且不太好写
    //https://leetcode.com/problems/sort-array-by-parity/discuss/170734/C%2B%2BJava-In-Place-Swap 参考下面评论的写法
    public int[] sortArrayByParity(int[] nums) {
        int b=0;
        int e=nums.length-1;
        while (b<e){
            if (nums[b]%2==0){
                b++;
            }else {//说明b这个位置上是奇数，要换到e上，如果e上是偶数，则换
                if (nums[e]%2==0){
                    int temp=nums[e];
                    nums[e]=nums[b];
                    nums[b]=temp;
                    e--;
                    b++;
                }else {//如过e上面是奇数，则把e--再下一步判断,这个挺巧妙
                    e--;
                }
            }
        }
        return nums;
    }
}
