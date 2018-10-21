import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 9/25/18.
 */
public class pinterestScreenFit {
    public static void main(String[] args){
        pinterestScreenFit ps=new pinterestScreenFit();
        int[] hei={100,300,400,250};

        List<Point> rs=ps.function(hei,200,2,0);

    }
    /*
点面，一道题目, 类似于安排images在pinterest的app版本上，给出屏幕的宽度，给定可以有多少column，padding，问怎么按顺序的插入heights array里的pins(images)，
返回插入位置top left corner的坐标array. 想法是用一个PriorityQueue keep track 哪一列是目前最短的，然后根据column找到x，根据当前列的height找到y. PriorityQueue里的node是一个 (currentHeight, column) pair。

/*
heights = [100, 300, 400]
width = 200
numColumns = 2;
padding = 0;

return: [{x: 0, y: 0 }, {x: 100, y: 0}, {x:0, y: 100}]


ArrayList<Point> function(int[] heights, int width, int numColumns, int padding) {
}

     */
//我觉得应该是y轴向下为正
    public List<Point> function(int[] heights, int width, int numColumns, int padding) {
        List<Point> rs=new ArrayList<>();
        PriorityQueue<Column> pq=new PriorityQueue<>(new Comparator<Column>() {
            @Override
            public int compare(Column o1, Column o2) {
                if(o1.height==o2.height){
                    return o1.x -o2.x;
                }
                return o1.height-o2.height;
            }
        });
        int wid=width/numColumns;
        for(int i=0;i<numColumns;i++){
            pq.offer(new Column(i*wid,0));
        }
        for(int i=0;i<heights.length;i++){
            Column curc=pq.poll();
            rs.add(new Point(curc.x,curc.height));
            curc.height+=heights[i]+padding;

            pq.offer(curc);
        }

        return rs;
    }
    class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    class Column{
        int x;
        int height;
        public Column(int id,int height){
            this.x =id;
            this.height=height;
        }
    }
}
