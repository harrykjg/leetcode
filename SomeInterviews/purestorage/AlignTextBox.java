package SomeInterviews.purestorage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AlignTextBox {

    /*
    有一系列的textbox，要求bias的线齐平（如图），给一个一行的最大宽度。如果这一行放不下更多的textbox了就提行。放textbox的顺序必须是输入的textbox的顺序。
    就是说你不能更改顺序。只能调整位置。
      y=0        B(x=4,y=0)
                   ┌────┐
        y=1        │    │      C(x=8,y=1)
                   │    │      ┌────┐
y=2 A(x=0,y=2)     │    │      │    │
            ┌────┐ │    │      │    │
        y=3 ├────┤ ├────┤      ├────┤   ← bias line: y = 3
            │    │ │    │      │    │
            │    │ │    │      │    │
        y=6 └────┘ │    │      └────┘
                   └────┘
             ————
             |  |
             |  |
             ————
     x=0           x=4           x=8
     */
    //目前的理解是TextBox按左上角定位，有width和height，还有bias line（中间的某条线），每一行要按bias line最深的那个对齐。那么下一行会基于上一行的最深的那个box的
    //底边就行。这样的话还是比较直观的吧。
    public List<TextBox> align(List<TextBox> input,int pageWidth){
        int width=0;
        int preBottom=0;
        int i=0;
        while (i<input.size()){
            int start=i;
            int maxBias=0;
            while (i<input.size()&&input.get(i).width+width<=pageWidth){
                maxBias= Math.max(maxBias,input.get(i).y+input.get(i).bias);
                width+=input.get(i).width;
                i++;
            }
            preBottom=handleRow(input,start,i-1,preBottom,maxBias);
            width=0;
        }
        return input;
    }
    int handleRow(List<TextBox> al,int begin,int end, int preBottom,int maxBias){
        int curWidth=0;
        int buttom=preBottom;
        for (int i=begin;i<=end;i++){
            TextBox box = al.get(i);
            box.x=curWidth;
            box.y+=maxBias-(box.y+box.bias);
            buttom=Math.max(buttom,box.y+box.height);
        }
        return buttom;
    }
}

class TextBox{
    int width;
    int height;
    int x;
    int y;
    int bias;
    public TextBox(int height,int width,int x,int y,int bias){
        this.height=height;
        this.x=x;
        this.y=y;
        this.width=width;
        this.bias=bias;
    }
}
