import java.util.HashMap;

/**
 * Created by 502575560 on 6/13/17.
 */
public class ConvertaNumbertoHexadecimal {
    //
    //http://blog.csdn.net/lmjq/article/details/5450292  负数的补码原理
    //https://jingyan.baidu.com/album/495ba84109665338b30ede98.html?picindex=5 10进制转16进制的方法
    //http://blog.csdn.net/blog_szhao/article/details/23997881  java 无符号右移运算符>>>和>>的区别
    //http://blog.csdn.net/camellhf/article/details/52680097  看思路
    //http://blog.csdn.net/mebiuw/article/details/52664414 思路和代码
    //http://www.cnblogs.com/y119777/p/5905564.html  思路
    //http://www.cnblogs.com/grandyang/p/5926674.html 解法二思路
    //http://blog.csdn.net/styshoo/article/details/53107560 解法二思路he代码
    //http://www.jianshu.com/p/ad4980f885ca  笨一点但直接一点的代码,主要是取最右边四位bit的那里

    public String toHex(int num) {

        String rs="";
        HashMap<Integer,String> map=new HashMap<>();
        map.put(0,"0");map.put(1,"1");map.put(2,"2");map.put(3,"3");map.put(4,"4");map.put(5,"5");
        map.put(6,"6");map.put(7,"7");map.put(8,"8");map.put(9,"9");
        map.put(10,"a");map.put(11,"b");map.put(12,"c");map.put(13,"d");map.put(14,"e");map.put(15,"f");
        while(num!=0){//可能是因为用>>>所以右移的话高位补0,而如果用>>的话且num是负数的话高位就是补1
            //这里就是如何取最右边四位二进制有点不好理解,其实num就是二进制,然后取&15即1111也可以表示位0xF(0x表示16进制),就去到了最右边四位
            String s=map.get(num&15);
            rs=s+rs;
            num=num>>>4;//这个num在计算机里本来就是带符号的表示,所以就该 用>>>而不是>>吗?不用的话num是负数的话num就一直不会为变成0
        }

        return rs.isEmpty()?"0":rs;
    }
}
