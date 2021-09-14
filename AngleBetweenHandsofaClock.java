public class AngleBetweenHandsofaClock {
    //8/18/2021 没好好想，好好想想两个指针在什么位置应该怎么算大环还是小环
    public double angleClock(int hour, int minutes) {
        double h=hour*30;
        double m=minutes*6;//用int的话后面计算加减乘除都不对
        double minOffset=minutes/60d*30;
        h+=minOffset;
        if(h>=360){//这个容易漏。12点n分就会超360
            h-=360;
        }
        if (Math.abs(h-m)>180){
            return 360d-Math.abs(h-m);
        }
        return Math.abs(h-m);
    }
}
