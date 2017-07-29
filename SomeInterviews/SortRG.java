package SomeInterviews;

/**
 * Created by 502575560 on 7/22/17.
 */
//双指针类
public class SortRG {
    //题目:How to do sort array with duplicates example: arr[]= [r g r r g g g r r r]  sort it to [r r r r r r g g g g]
    public static void main(String[] args){
        char[] a={'g','r','g','g','r','r','g','r','g','g'};
        sort(a);
    }
    static void sort(char[] a){
        int b=0;
        int e=a.length-1;
        while (b<e){
            if(a[b]=='r'){
                b++;
                continue;
            }else{
                char temp=a[e];
                a[e]='g';
                a[b]=temp;
                e--;
                continue;
            }
        }

    }
}
