import java.util.HashMap;
import java.util.Map;
//http://www.2cto.com/kf/201403/286818.html
//

public class MaxPointsonaLine {
	public static void main(String[] args) {
		MaxPointsonaLine mp=new MaxPointsonaLine();
		Point p1=new Point(84,250);
		Point p2=new Point(0,0);
		Point p3=new Point(1,0);
		Point p4=new Point(0,-70);
		Point p5=new Point(0,-70);
		Point p6=new Point(1,-1);
		Point p7=new Point(21,10);
		Point p8=new Point(42,90);
		Point p9=new Point(-42,-230);
		
		
		
		Point[] p={p1,p2,p3,p4,p5,p6,p7,p8,p9};
//		Point p1=new Point(0,0);
//		Point p2=new Point(1,0);
//		Point[] p={p1,p2};
		System.out.println(mp.maxPoints(p));
	}
	
	
	    public int maxPoints(Point[] points) {
	        if(points.length==0){
	        	return 0;
	        }
	        int max=1;
	        for(int i=0;i<points.length;i++){
	        	if(i>0&&points[i].x==points[i-1].x&&points[i-1].x==points[i-1].y){
	        		continue;//这里不加这个省去相同点的速度还是差不多
	        	}
	        	HashMap<Double,Integer> hm=new HashMap<Double,Integer>();
	        	int locol=1;
	        	int same=0;
	        	for(int j=0;j<points.length;j++){
	        		if(i==j){
	        			continue;
	        		}
	        		if(points[i].x==points[j].x&&points[i].y==points[j].y){
	        			same++;
	        			continue;
	        		}
	        		double k=0;
	        		if(points[i].x-points[j].x!=0){
	        			//注意这里，不强转成double的话这个k算出来的值是int
	        			 k=(double)(points[j].y-points[i].y)/(points[j].x-points[i].x);
	        		}else{
	        			 k=Integer.MAX_VALUE;
	        		}
	        		if(!hm.containsKey(k)){
	        			hm.put(k, 2);//最少是2个点
	        		}else{
	        			hm.put(k, hm.get(k)+1);
	        		}
	        		locol=Math.max(hm.get(k), locol);	        		
	        	}
	        	locol+=same;
	        	max=Math.max(locol, max);
	        }
	        return max;
	    }
	    
	}


class Point {
     int x;
     int y;
     Point() { x = 0; y = 0; }
     Point(int a, int b) { x = a; y = b; }
 }
