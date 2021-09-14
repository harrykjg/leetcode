import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    //8/17/2021  写错了index不然一次过，画图自己看看就行。何时移动哪个pointer
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> al=new ArrayList<>();
        if (secondList.length==0||firstList.length==0){
            return new int[][]{};
        }
        int i=0;
        int j=0;
        while (i<firstList.length&&j<secondList.length){
            if (firstList[i][0]>secondList[j][1]){
                j++;
                continue;
            }
            if (firstList[i][1]<secondList[j][0]){
                i++;
                continue;
            }
            int[] a=new int[2];
            a[0]=Math.max(firstList[i][0],secondList[j][0]);
            a[1]=Math.min(firstList[i][1],secondList[j][1]);
            al.add(a);
            if (firstList[i][1]<secondList[j][1]){
                i++;
            }else {
                j++;
            }
        }
        return al.toArray(new int[al.size()][]);//记这种写法
    }
}
