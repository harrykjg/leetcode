package SomeInterviews.verkarda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JumpingbyHeight {
    /*
    You are standing on a one-dimensional array nums at a starting position index. You also have an internal value called
    currentHeight, which is initialized to nums[index]. From there, you attempt to traverse the array by jumping between indices.
For each jump:
The first jump goes to the left. After every successful jump, the direction flips between left and right.
In the current direction, you must land on the nearest index i such that nums[i] == currentHeight + 1.
The current index itself is never a valid landing target.
After a successful jump, update currentHeight = currentHeight + x. This update is based on the previous currentHeight,
not on nums[i] at the landing position.
If no valid landing index exists in the current direction, the traversal stops.
Return the final index where the traversal stops. This may be the starting index if the first jump is not possible.
Constraints:

1 ≤ nums.length ≤ 105
0 ≤ nums[i] ≤ 104
0 ≤ index < nums.length
1 ≤ x ≤ 104
Example 1:

Input: nums = [3, 5, 1, 4, 2, 5, 6], index = 4, x = 1
Output: 6
Explanation: Starting at index 4 with currentHeight = 2 and direction = left, the traversal jumps along the sequence
4 → 0 → 3 → 1 → 6, with currentHeight growing 2 → 3 → 4 → 5 → 6. After landing at index 6, the next required target is 7,
 which no index holds, so the traversal stops at index 6.
Example 2:
Input: nums = [3, 5, 1, 4, 2, 6], index = 4, x = 2
Output: 1
Example 3:
Input: nums = [5], index = 0, x = 1
Output: 0
Hint 1
A linear scan for every jump will result in quadratic time complexity; focus on reducing the search cost per step.
Hint 2
Since the values you need to find never change, pre-grouping array indices by their numeric values creates a static lookup structure.
Hint 3
Once indices are grouped and sorted, use binary search to instantly locate the closest index strictly before or after your current position.
     */
    //暴力法不行是n方（每次走n步，扫n次），好的方法想不出
    public int finalIndex(int[] nums, int index, int x) {
        // TODO: Implement finalIndex logic
        //思路是拿一个map，key记录数组里的数值，value是一个List，记录这个数值出现过的位置index，把这个list天然就是sorted的（从左到右）
        //那么由于我们知道要找的值是什么，因此可以由这个map快速确定出现这个值的所有的位置，然后用二分法找，时间复杂度最终就是nlogn
        Map<Integer, List<Integer>> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.putIfAbsent(nums[i],new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        int target=nums[index]+1;
        boolean left=true;
        int cur=-1;
        while (map.containsKey(target)){
            List<Integer> indexes=map.get(target);
            if(left){
                cur=findleft(index,indexes);//在这一对index中找到rs左边的那一个
            }else{
                cur=findRight(index,indexes);
            }
            if(cur==-1){
                return index;
            }
            index=cur;
            target+=x;
            left=!left;
        }
        return index;
    }
    //找比target小的
    int findleft(int target,List<Integer> al){
        int b=0;
        int e=al.size()-1;
        while (b+1<e){
            int m=e-(e-b)/2;//不用检查al.get(m)是否等于target
            if(al.get(m)<target){
                b=m;
            }else{
                e=m;
            }
        }
        if(al.get(e)<target){//注意不是找等于target的，是找严格小于target的，之前写成==target了
            return al.get(e);
        }
        if(al.get(b)<target){
            return al.get(b);
        }
        return -1;
    }
    int findRight(int target,List<Integer> al){
        int b=0;
        int e=al.size()-1;
        while (b+1<e){
            int m=e-(e-b)/2;//不用检查al.get(m)是否等于target
            if(al.get(m)>target){
                e=m;
            }else{
                b=m;
            }
        }
        if(al.get(b)>target){//注意不是找等于target的，是找严格小于target的，之前写成==target了
            return al.get(b);
        }
        if(al.get(e)>target){
            return al.get(e);
        }
        return -1;
    }
}
