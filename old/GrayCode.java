import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/24511221   这个思想就是复制镜像，只是代码比较简洁
//http://blog.csdn.net/linmiansheng/article/details/21746827 复制镜像这个想法是这个启发的
public class GrayCode {

	public ArrayList<Integer> grayCode(int n) {
//思路就是：开始看了启发镜像复制思维那个帖子，以为要先构造二进制的数字，再转成10进制，其实不用
		//因为要返回的是10进制的数，而不是2进制代码，所以在镜像复制二进制数的时候可以看到起10进制
		//的数是有规律的，就是镜像的那部分是没镜像那部分乘以2的i次方，这个i就是从1开始递增
		//
		ArrayList<Integer> al=new ArrayList<Integer>();
		if(n<=0){
			al.add(0);
			return al;
		}
		al.add(0);
		al.add(1);
		for(int i=2;i<=n;i++){
			int len=al.size();
			for(int j=len-1;j>=0;j--){
				al.add(al.get(j)+(1<<(i-1)));//1<<(i-1))代表1乘以2的i-1次方，叫左移位运算
				//就比如：把二进制的0101（即5）左移一位，变成了1010，就是10了，即5乘以2的1次方
				//这里注意如果1前面少了个括号变成1<<（i-1）则不对了
				//不知道他是怎么弄的。或者写成(int)(Math.pow(2,i-1))也行，注意pow方式是接受
				//两个double参数，要转成（int）
			}
		}
		return al;

	}

}
