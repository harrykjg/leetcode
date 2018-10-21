/**
 * Created by yufengzhu on 10/12/18.
 */
public class drawACircle {
    //pure storage面经
    public void draw(int radius){
        double y=radius;

        for(int i=0;i<=radius;i++){//公式见这里https://www.cs.uic.edu/~jbell/CourseNotes/ComputerGraphics/Circles.html
            y=Math.sqrt(Math.pow(radius,2)-Math.pow(i,2));//这里写的是分四个象限然后对称画，其实可以分成分成8块对称画，x从0到coisin45*radius即0.717R就行了，画个图理解对称性
            draw(i,y);
            draw(-i,y);
            draw(i,-y);
            draw(-i,-y);
        }

    }
    void draw(double x,double y){

    }
}
