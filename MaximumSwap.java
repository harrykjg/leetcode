public class MaximumSwap {
    public static void main(String[] args){
        MaximumSwap ms=new MaximumSwap();
        System.out.println(ms.maximumSwap(873263));
    }
    //8/17/2021,自己想的居然对的，想的是维护一个数组从右向左记录该点右边最大的数和其index，然后从左边开始扫描遇到一个数比right数组的值小的话就换。
    //https://leetcode.com/problems/maximum-swap/discuss/107102/Simple-AC-O(n)-java-solution-with-ex 这个是和next permutation相似的解法没看大懂
    public int maximumSwap(int num) {
        int copy=num;
        if (num<=10){
            return num;
        }
        String s=String.valueOf(num);
        Pair[] right=new Pair[s.length()];
        int max=s.charAt(s.length()-1)-'0';
        right[right.length-1]=new Pair(max,right.length-1);
        for (int i=s.length()-2;i>=0;i--){
            if (s.charAt(i)-'0'>max){
                right[i]=new Pair(s.charAt(i)-'0',i);//注意1991这个例子，刚好看1的右边的时候最大的是第二个9，如果是第一个9的话就错了
                max=s.charAt(i)-'0';
            }else {
                right[i]=right[i+1];
            }
        }
        char[] ch=s.toCharArray();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)-'0'<right[i].value){
                int temp=ch[i]-'0';
                ch[i]=(char)(right[i].value+'0');
                ch[right[i].index]=(char)(temp+'0');
                break;
            }
        }
        String rs=new String(ch);
        return Integer.valueOf(rs);

    }
    class Pair{
        int value;
        int index;
        public Pair(int value,int index){
            this.value=value;
            this.index=index;
        }
    }
}
