package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubarraySumClosest {
  public static void main(String[] args){
    int[] n={4,5,1,1,-3};
    SubarraySumClosest ss=new SubarraySumClosest();
    ss.subarraySumClosest(n);
  }
  //leetcode没有，挺不好想的还
  //http://www.cnblogs.com/ireneyanglan/p/5995253.html   他这个没有加ls.add(new pair(0,-1))是错的，加上就会对
  //http://blog.csdn.net/willshine19/article/details/48494129
  //http://www.jiuzhang.com/solution/subarray-sum-closest/  看下面的前缀和说明
  public int[] subarraySumClosest(int[] nums) {
      //要利用前缀和，因此要计算从0到i之间数的和，由于题目要求index，因此要一个数据结构去存sum和index（否则就是一个int数组存sum就行了吧），
    List<pair> ls=new ArrayList<>();
    int sum=0;
    ls.add(new pair(0,-1));//下标问题,比较烦，不加的话，从0到0的subarray这个case就得不到了，见九章答案下面的解释
    for(int i=0;i<nums.length;i++){
      sum+=nums[i];
      pair p=new pair(sum,i);
      ls.add(p);
    }
    Collections.sort(ls, new Comparator<pair>() {
      @Override
      public int compare(pair o1, pair o2) {
        return o1.sum-o2.sum;
      }
    });
  //得到了所有的prefix后，由于我们要求的是数组中存在的subaray之和最接近0subarray，开始的想法是用i减去i-1，i-2，i-3。。。(i=ls.size...0)看
    //所有subarray的组合，谁最小就是谁，但是这样是n方的复杂度，而如果我们把前缀和按其值sort，那么值之中差值最小的肯定是相邻的两个数，
    //用一个for循环遍历就行了看相邻的数的差值谁最小就行了
    int min=Integer.MAX_VALUE;
    int[] rs=new int[2];
    for(int i=1;i<ls.size();i++){
      int diff=ls.get(i).sum-ls.get(i-1).sum;//注意这个i只是用来遍历ls的，容易和前缀的index搞混
      if(diff<min){
        min=diff;
        if(ls.get(i).index<ls.get(i-1).index){
          rs[0]=ls.get(i).index+1;//注意这里要+1，比如下标为2到4的和，则要前5个数字的和减前2个数字的和(注意前2个数字即下标是0和1)，
          rs[1]=ls.get(i-1).index;  // 而前2个数字的和的在pair里的index为1（见第一个for循环，i为2时），前5个数字的和的index为4，
           //因此想得到下标2，4则要把较小的那个index+1才行
        }else{
          rs[1]=ls.get(i).index;
          rs[0]=ls.get(i-1).index+1;
        }
      }
    }
    return rs;
  }
  class pair{
    int sum;
    int index;
    public pair(int a,int b){
      sum=a;
      index=b;
    }
  }

}
