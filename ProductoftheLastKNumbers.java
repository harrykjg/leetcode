import java.util.*;

public class ProductoftheLastKNumbers {
    //7/30/2021 自己想的就是容arraylist装数字然后算后k个product，用个map存这k个和k-1的product来加速，每次add一个数则要清空map。看到别人用的是prefix product
    //即和prefix sum差不多的想法.还不好想，因为中间如果加进一个0来会导致全部后面的都是0。办法就是加进0就新起一个数组加入1。然而这样的话
    //会导致数组的长度可能小于k.小于k的话说这最后k个数里有一个数是0，举个例子发现只要数组长度小于等于k的话就反回0。否则按正常算。比如下面例子自己看看
    //  1 2 3 0 4 5   原数组
    //1 1 2 6 1 4 20  prefix
    //https://leetcode.com/problems/product-of-the-last-k-numbers/discuss/510260/JavaC%2B%2BPython-Prefix-Product
    List<Integer> prefix=new ArrayList<>();
    public ProductoftheLastKNumbers() {
        prefix.add(1);
    }

    public void add(int num) {
        if (num>0){
            prefix.add(prefix.get(prefix.size()-1)*num);
        }else {
            prefix=new ArrayList<>();
            prefix.add(1);
        }
    }

    public int getProduct(int k) {
        int n=prefix.size();
        if (k>=n){
            return 0;
        }
        return prefix.get(prefix.size()-1)/prefix.get(prefix.size()-1-k);
    }

    //5/19/2026，看回第一次的想法
    List<Integer> prefix2;
    public ProductoftheLastKNumbers2() {
        prefix2=new ArrayList<>();
        prefix2.add(1);
    }
    public void add2(int num) {
        if(num>0){
            prefix2.add(num*prefix2.get(prefix2.size()-1));
        }else{
            prefix2.clear();
            prefix2.add(1);
        }
    }
    public int getProduct2(int k) {
        if(k>=prefix2.size()){
            return -1;
        }
        return prefix2.get(prefix2.size()-1)/prefix2.get(prefix2.size()-1-k);
    }

}
