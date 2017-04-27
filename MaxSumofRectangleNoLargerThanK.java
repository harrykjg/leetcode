/**
 * Created by 502575560 on 4/25/17.
 */
public class MaxSumofRectangleNoLargerThanK {
    public static void main(String[] args){
//        int[][] m={{1,0,1},{0,-2,3}};
        int[][] m={{2,2,-1}};
        System.out.println(maxSumSubmatrix2(m,3));
    }

    //自己想了一个思路,和Largest Rectangle in Histogram类似,把matrix每一行上的某一列存储这一列上面的所有值的和,再通过几个循环得出所有可能,
    //运行了19个test case都行,后来第二十个case不知道为啥不行了
    //http://www.mamicode.com/info-detail-1410318.html
    //http://blog.csdn.net/wdlsjdl2/article/details/51907396 看不太懂treeset是怎么用的,好几个都是用了treeset的
    //http://blog.csdn.net/jmspan/article/details/51738372 和上面这个是一个思路的
    public static int maxSumSubmatrix(int[][] m, int k) {

        int rs=Integer.MIN_VALUE;
        for(int i=1;i<m.length;i++){//把matrix预处理,m[i][j]的值就是j列以上所有点的和
            for(int j=0;j<m[0].length;j++){
                m[i][j]=m[i-1][j]+m[i][j];
            }
        }
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){//到了这里才到一个具体的值,往下开始2个for循环,使得对这一行取0,1,2,3,4...然后取1,2,3,4...然后取2,3,4..
                for(int q=0;q<=j;q++){
                    int temp=0;
                    for(int o=q;o<=j;o++){
                        temp+=m[i][o];
                        if(temp<=k){
                            rs=Math.max(rs,temp);
                        }
                        for(int p=0;p<i;p++){//上面的temp是第0行到第i行的这个长方形的面积,这个for循环就是取第1,2,3,4...行到i行围城的面积
                            int temp2=temp;
                            temp2-=m[p][o];
                            if(temp2<=k){
                                rs=Math.max(rs,temp2);
                            }
                        }
                    }
                }
            }
        }
        return rs;

    }

    public static int maxSumSubmatrix2(int[][] m, int k) {
        int[][] helper=new int[m.length][m[0].length];
        int rs=Integer.MIN_VALUE;
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){//构造helper这一部分和RangeSumQuery2DImmutable的构造部分是一样的,就是求0,0点和i,j点之间
                if(i==0&&j==0){     //围城的矩形的面积
                    helper[i][j]=m[i][j];
                }
                else if(i==0){
                    helper[i][j]=helper[i][j-1]+m[i][j];
                }
                else if(j==0){
                    helper[i][j]=helper[i-1][j]+m[i][j];
                }
                else{
                    helper[i][j]=helper[i-1][j]+helper[i][j-1]-helper[i-1][j-1]+m[i][j];
                }

                for(int o=0;o<=i;o++){//这里开始要想清楚,现在有了0,0点和任意i,j点中间围城的面积,现在就要找一个任意的在,i,j与0,0点中间的一个点,即o,p点
                    for(int p=0;p<=j;p++){//算出o,p点与i,j点围城的矩形的面积,m[o][p]意义是再i,j之内的一个点,这个点和i,j之间围城的矩形的面积
                        int temp=0;
                        if(o==0&&p==0){
                            temp=helper[i][j];
                        }else if(o==0){
                            temp=helper[i][j]-helper[i][p-1];
                        }else if(p==0){
                            temp=helper[i][j]-helper[o-1][j];
                        }else{
                            temp=helper[i][j]-helper[o-1][j]-helper[i][p-1]+helper[o-1][p-1];
                        }

                        if(temp==k){
                            return k;
                        }
                        if(temp<=k){
                            rs=Math.max(rs,temp);
                        }
                    }

                }

            }
        }

        return rs;
    }
}
