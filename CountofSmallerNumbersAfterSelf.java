import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class CountofSmallerNumbersAfterSelf {

//和countof
    //不会，看的是mergesort的思路
    //https://leetcode.com/discuss/73256/mergesort-solution
    //https://leetcode.com/discuss/74110/11ms-java-solution-using-merge-sort-with-explanation

    public List<Integer> countSmaller(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        numIndex[] indexs = new numIndex[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexs[i] = new numIndex(nums[i], i);
        }
        int[] space = new int[nums.length];
        indexs = mergesort(indexs, space);
        List<Integer> rs = new ArrayList<Integer>();
        for (int i = 0; i < space.length; i++) {
            rs.add(space[i]);
        }
        return rs;
    }

    public numIndex[] mergesort(numIndex[] indexs, int[] count) {
        int len = indexs.length;
        if (len / 2 > 0) {
            numIndex[] left = new numIndex[len / 2];
            for (int i = 0; i < len / 2; i++) {
                left[i] = indexs[i];
            }
            numIndex[] right = new numIndex[len - len / 2];
            for (int i = 0; i < right.length; i++) {
                right[i] = indexs[i + len / 2];
            }
            left = mergesort(left, count);
            right = mergesort(right, count);

            int i = 0;
            int j = 0;
            while (i < left.length && j < right.length) {//第一个链接那个代码这里写的精妙，自己很难想
                if (left[i].num <= right[j].num) {//这里容易少了等于号，少了的话就不是稳定排序了
                    indexs[i + j] = left[i];
                    count[left[i].index] += j; //这里难想，开始写成count[right[j].index]++;是不对的，比如比较1和2，
                    i++;                  //那么实际上2的count是不用++的因为1本来就在2的左边
                } else {
                    indexs[i + j] = right[j];
                    //count[left[i].index]++;//注意这里只要记录从右边跳到左边的，因为题目要的是这个数的右边比他小的数，
                    j++;                //不需要算本来在他左边且小于他的数,因此不能写count[left[i].index]++;这句
                }
            }
            while (i < left.length) {
                indexs[i + j] = left[i];
                count[left[i].index] += j;//这里这个while循环实际上可以融合到上面的while循环里，参考第一个链接的代码
                i++;
            }
            while (j < right.length) {
                indexs[i + j] = right[j];
                j++;
            }
        }
        return indexs;
    }

    class numIndex {//开始想的是不用这个class，直接用个hashmap来记录index，后来想想不行，因为merge的时候两个数组分别试新的index，
        int index; //即不是原来的数组的index了，所以没法记录。因此需要一个数据结构来记录他的值和index
        int num;

        public numIndex(int n, int i) {
            num = n;
            index = i;

        }

        public numIndex(numIndex ni) {
            num = ni.num;
            index = ni.index;

        }
    }
}