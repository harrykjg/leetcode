/**
 * Created by yufengzhu on 7/22/18.
 */
public class PourWater {
//不难，妈的写的不顺
    public int[] pourWater(int[] heights, int V, int K) {
        for(int i=0;i<V;i++){
            helper(heights,K);
        }
        return heights;

    }
    void helper(int[] height,int index){//这里要一直往左右延伸，直到到了边界或者高过自己的bar，但是一直延伸也不行，如果index左边全都是同样高度的，那么就不用延伸了,
        int leftlowIndex=index;
        int rightlowIndex=index;
        if(index==0){
            leftlowIndex=0;
        }else{
            int lindex=index;
            while (lindex>0){//要画图理解，，
                if(height[lindex-1]>height[lindex]){//从index往左找,遇到第一个上升的就结束了
                    break;
                }
                if(height[lindex-1]<height[leftlowIndex]){//左边的bar一定要比当前的leftlowIndex的高度小的时候才更新，否则不更新
                    leftlowIndex=lindex-1;
                }
                lindex--;
            }

        }
        if(leftlowIndex==index){//左边没有的话才去找右边
            if(index==height.length-1){
                rightlowIndex=height.length-1;
            }else{
                int rindex=index;
                while (rindex<height.length-1){

                    if(height[rindex+1]>height[rindex]){
                        break;
                    }
                    if(height[rightlowIndex]>height[rindex+1]){
                        rightlowIndex=rindex+1;
                    }
                    rindex++;
                }

            }
        }


        if(leftlowIndex<index){//左边有低点就去，没有的话再看右边有没有
            height[leftlowIndex]++;
        }else if(rightlowIndex>index){
            height[rightlowIndex]++;
        }else{
            height[index]++;
        }
    }
}
