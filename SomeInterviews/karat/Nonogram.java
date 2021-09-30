package SomeInterviews.karat;

public class Nonogram {
    //https://www.youtube.com/watch?v=_rey7d233s4 看这个视频才知道nonogram是啥意思
    //这题只需要valid nonogram是否符合其提示说的黑棋的数量和分布

    public static void main(String[] args){
        Nonogram no=new Nonogram();
        int[][] m={
                {1,1,1,1},
                {0,1,1,1},
                {0,1,0,0},
                {1,1,0,1},
                {0,0,1,1}
        };
        int[][] row={{},{1},{1,2},{1},{2}};
        int[][] col={{2,1},{1},{2},{1}};
//        int[][] m={
//                {1,1},
//                {0,0},
//                {0,0},
//                {1,0}
//        };
//        int[][] row={{},{2},{2},{1}};
//        int[][] col={{2},{3}};
        System.out.println(no.valid(m,row,col));
    }

    public boolean valid(int[][] m,int[][] rows,int[][] cols){
        for (int i=0;i<rows.length;i++){
            int[] row=rows[i];
            if (!checkRow(row,m[i])){
                return false;
            }
        }
        for (int i=0;i<cols.length;i++){
            int[] col=cols[i];
            if(!checkCol(m,i,col)){
                return false;
            }
        }
        return true;
    }
    boolean checkRow(int[] row,int[] nums){
        if (row.length==0){//如果row是空，则这一行必须都是white
            for (int i:nums){
                if (i==0){
                    return false;
                }
            }
            return true;
        }
        int index=0;
        for (int i=0;i<row.length;i++){//维护一个index作为行的pointer，然后看一个row的元素，遇到第一个black开始算有几个连续的black，大于或小于都不行，然后沿用index开始看下一个row的元素
            int count=row[i];
            while (index<nums.length&&nums[index]==1){//过掉白色的
                index++;
            }
            while (index<nums.length&&nums[index]==0){
                index++;
                count--;
            }
            if (count!=0){
                return false;
            }
        }
        while (index<nums.length){//搞完row的元素之后还要看如果index还没扫完的话，那么后面必须全是white
            if (nums[index++]==0){
                return false;
            }
        }
        return true;
    }

    boolean checkCol(int[][] m,int c,int[] cols){
        if (cols.length==0){//如果row是空，则这一行必须都是white
            for (int i=0;i<m.length;i++){
                if (m[i][c]==0){
                    return false;
                }
            }
            return true;
        }
        int index=0;
        for (int i=0;i<cols.length;i++){
            int count=cols[i];
            while (index<m.length&&m[index][c]==1){//过掉白色的
                index++;
            }
            while (index<m.length&&m[index][c]==0){
                index++;
                count--;
            }
            if (count!=0){
                return false;
            }
        }
        while (index<m.length){//搞完row的元素之后还要看如果index还没扫完的话，那么后面必须全是white
            if (m[index++][c]==0){
                return false;
            }
        }
        return true;
    }
}
