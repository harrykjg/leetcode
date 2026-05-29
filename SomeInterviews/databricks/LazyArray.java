package SomeInterviews.databricks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class LazyArray {

    public static void main(String[] args) {
        LazyArray arr = new LazyArray(new int[]{10, 20, 30, 40, 50});
        System.out.println(arr.map(x -> x * 2).indexOf(40)); // 1

        LazyArray doubled = arr.map(x -> x * 2);
        LazyArray chain1 = doubled.map(x -> x + 10);
        LazyArray chain2 = doubled.map(x -> x + 20);

        System.out.println(chain1.indexOf(50)); // 3, because 40*2+10=90? actually 20*2+10=50 => index 1
        System.out.println(chain2.indexOf(60)); // index 1
    }

    int[] array;
    List<Function<Integer,Integer>> funcs;
    public LazyArray(int[] array){
        this.array= Arrays.copyOf(array,array.length);
        funcs=new ArrayList<>();
    }
    public LazyArray(int[] array,List<Function<Integer,Integer>> funcs){
        this.array= Arrays.copyOf(array,array.length);
        this.funcs=funcs;
    }
    //注意这个map也需要新建一个对象，否则的话无法题目说的支持两个chain不相互影响，但是以前的functions也应该继承过来
    LazyArray map(Function<Integer,Integer> fn){
        List<Function<Integer, Integer>> functions=new ArrayList<>(this.funcs);
        functions.add(fn);
        //为啥这里又不用copy array了，因为这个array从设定开始就不会变
        return new LazyArray(this.array,functions);
    }
    //这里apply map的时候是不改变原数组的值的，也不返回新的数组，只是单纯的看看每个元素apply function之后的值是不是输入的那个值，是就返回index.
    //如果修改的话就不符合两条chain不互相影响的规则了
    int indexOf(int x){
        for (int i=0;i<array.length;i++){
            int now=0;
            for (int j=0;j<funcs.size();i++){
                now=funcs.get(i).apply(array[i]);
            }
            if(now==x){
                return i;
            }
        }
        return -1;
    }


}
