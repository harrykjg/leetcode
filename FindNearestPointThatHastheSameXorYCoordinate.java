public class FindNearestPointThatHastheSameXorYCoordinate {
    //9/13/2021
    public int nearestValidPoint(int x, int y, int[][] points) {
        int dist=0;
        int rs=-1;
        for (int i=0;i<points.length;i++){
            if (points[i][0]!=x&&points[i][1]!=y){
                continue;
            }
            int temp=Math.abs(points[i][0]-x)+Math.abs(points[i][1]-y);
            if (temp<dist){
                dist=temp;
                rs=i;
            }
        }
        return rs;
    }
}
