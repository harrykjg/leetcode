package ArrayListAndNumbers;

import java.util.Deque;
import java.util.LinkedList;

public class NumberofVisiblePeopleinaQueue {
    //10/10/2021想到肯定是单调栈但是还是不会.
    //https://www.youtube.com/watch?v=TR59srcG1Kg
    // 其实用stack就行了，维护stack，里面装的是单调上升的元素，栈顶是最小的。每次遇到一个数就看和栈顶比较，如果比栈顶小则答案是1，
    //否则一直pop出来比他小的数，记录pop出来的次数，pop完之后如果栈空了说明大胆就是pop的次数，否则是pop次数+1
    // 先看6，空栈，答案就是0，然后加入6，然后8来了，发现栈顶是6小于8，则pop出来，然后发现栈空了，只pop了一次，
    //说明8比栈里所有元素都大，即可以看到pop出来的个数的人，答案就是pop出来的次数。加入8，然后看5，发现栈顶就比5大了，说明5只能看到8。
    //加入5。然后到了1，发现栈顶大于1，即1只能看到1个。然后到3，发现栈顶1，比3小，pop出来，然后到了5，比3大了，栈不为空，说明5后面的东西啥都看
    //不见了，因此答案是pop出来的次数+1。
    /*
        [3,1,5,8,6]
          rs        0

     */
    public int[] canSeePersonsCount(int[] heights) {
        Deque<Integer> dq=new LinkedList<>();
        int[] rs=new int[heights.length];
        for (int i=heights.length;i>=0;i--){
            if (!dq.isEmpty()&&dq.peekFirst()>heights[i]){
                rs[i]=1;
                dq.offerFirst(heights[i]);
            }else if (!dq.isEmpty()){
                int count=0;
                while (!dq.isEmpty()&&dq.peekFirst()<heights[i]){
                    dq.pollFirst();
                    count++;
                }
                if (!dq.isEmpty()){
                    rs[i]=count+1;
                }else{
                    rs[i]=count;
                }
                dq.offerFirst(heights[i]);
            }else {
                rs[i]=0;
                dq.offerFirst(heights[i]);
            }
        }
        return rs;
    }
}
