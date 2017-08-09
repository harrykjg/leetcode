package Advance2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/16/17.
 */
public class NumberofIslandsII {
    //http://blog.csdn.net/dm_vincent/article/details/7655764 将union find原理
    //http://blog.csdn.net/jmspan/article/details/51189502  题目在这
    // https://discuss.leetcode.com/topic/29518/java-python-clear-solution-with-unionfind-class-weighting-and-path-compression
    //https://discuss.leetcode.com/topic/29613/easiest-java-solution-with-explanations/2  这个值
    // 其实就是往一个空的图里的m,n这个位置块陆地,求加完后有几个岛,
    //这个positions是a list of operations如 [[0,0], [0,1], [1,2], [2,1]]代表先加0,0这个位置再加0,1.....
    //主要是按着链接的思路自己想的
    int[] ids;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        ids=new int[m*n];//它的意义就是记录这个m*n矩阵上每个点的identifier
        int[][] grid=new int[m][n];
        for(int i=0;i<ids.length;i++){//一开始都是一片海,那么要初始化id吗,应该要初始化为-1,代表都是海,否则就代表其真实的id,不能让他默认为0,因为
            ids[i]=-1;            //0,0的id就是0,所以找-1来初始化
        }
        List<Integer> rs=new ArrayList<>();
        int num=0;
        int[] indenty=new int[]{-1,0,1,0};//构造方法,先找上,所以行数i-1,y坐标不变,然后右,横行数不变,列坐标+1,类似的见TrappingRainWaterII
        int[] indentx=new int[]{0,1,0,-1};
        //每个操作其实就是包括这几步:找到(计算)这个点的id,然后找这个点的四周的点的id,发现有相同的就把两者的id变成一个
        for(int i=0;i<positions.length;i++){
            int x=positions[i][1];//就是把二维数组看成一个坐标,x就是列数,然后把x,y转化成一个数字来表示其id,方法就是行坐标乘以列数再加横坐标
            int y=positions[i][0];
            int id=y*n+x;
            ids[id]=id;
            num++;
            //现在要看这个点的上下左右四个点的id值,现在要表示上下左右这四个点了
            for(int j=0;j<indentx.length;j++){
                int ix=x+indentx[j];
                int iy=y+indenty[j];
                int iid=iy*n+ix;
                //现在得到了上下左右这个点的坐标,就要看这个坐标的上的id值到底是多少
                if(ix>=0&&ix<n&&iy>=0&&iy<m&&ids[iid]!=-1){//如果这个上下左右点的id不是-1的话,说明它是陆地,则当前x,y点就应该和
                                                        //这个上下左右点union join
                    int root=find(iid);//先找到这个上下左右点的跟节点的id,再来和x,y点的id做比较,如果不同,则把他们变成一样,其实就是union了
                    if(id!=root){
                        ids[root]=id;
                        num--;
                    }
                }
            }
            rs.add(num);
        }
        return rs;
    }
    int find(int p){
        while (ids[p]!=p){//"寻找p节点所在组的根节点，根节点具有性质id[root] = root",这一条性质不太好懂,见union find原理那个链接
                        //的树的图,6-5-0-1那个树,这里比如p是6,则6的根是5(不是6),再发现5的根是0(不是5),再..发现1的根是1,所以最终返回1
            ids[p]=ids[ids[p]];            //这里顺便再做一步压缩路径,也不太好理解,貌似就是6直接找到0作为根,0再找到1作为根,而不是6-5-0-1这样找四次了
            p=ids[p];
        }
        return p;
    }
}