//http://www.tuicool.com/articles/fAZZv2a
//http://blog.csdn.net/jiadebin890724/article/details/23306837
//http://www.tuicool.com/articles/UjQV7n
//http://www.cnblogs.com/x1957/p/3373994.html
//http://jixiangsanbao.wordpress.com/2014/06/16/single-number-ii/
//位运算，不会，见shiyan包的bit
public class SingleNumberII {
	public static void main(String[] args) {
		SingleNumberII sg=new SingleNumberII();
		int[] A={8,8,8,3,3,3,24,7,7,7};
		System.out.println(sg.singleNumber(A));
	}
	  public int singleNumber(int[] A) {
	         int[] bitnum=new int[32];
	        int res=0;
	        //位运算真是挺绕的，这里比如例子是8,8,8,3,3,3,24,7,7,7，24的二进制是11000
	        for(int i=0; i<32; i++){
	            for(int j=0; j<A.length; j++){
	                if((A[j]&(1<<i))!=0){  //或者写成  bitnum[i]+=(A[j]>>i)&1;就不要if了，也是一样的
	                    bitnum[i]++;//就是看A[j]这个数的第i位是否为0，（1<<i）就是把1左移i位，去
	                }             //与整个A[j]，比如8,即1000，与1左移0为，即1000与0，得0，若1000
	                //与1左移1位，即1000与10，那么还是得0，若1000与1左移3位即1000&1000，则得1000
	                //即8。所以说这里要是(A[j]&(1<<i))结果不为0，则说明第A[j]这个数的二进制形式的第
	                //i位是1，则把bitnum这个数组的第i位加上1。比如说24，二进制是11000，那么for循环
	                //之后再bitnum数组中24就是00011的样子了，即反过来了。现在看这个数组，把8，3,24,7
	                //都加进bitnum之后，bitnum就是6,6,3,4,1，因为3的二进制是11，反过来还是11，出现
	                //了3次，则bitnum的前2位就成立3,3。然后看7，二进制是111，反过来还是111，出现3次
	                //则bitnum的前三位都要加上3，则bitnum变成663。然后8的二进制是1000，反过来就是
	                //0001，出现三次，加进bitnum就是6633，然后24的二进制是11000，反过来就是00011，
	                //加进bitnum就是66341。这里说的反过来是指，比如24的二进制是11000，而这里用bitnum
                        //去记录他的1的位置，而bitnum这个数组是从左到右的，而11000是从右到左的数1的位置,
                        //如这里说的66341是bitnum从左到右的顺序看的
	            }
	            //每一个i都把当前i位的数都加进bitnum了，所以可以构造结果了。
	            //比如i等于1时，bitnum[0]是6,6mod3是0,0左移0位还是0，res默认是0，|=符号相当于加法
	            //所以当i等于1时，算完之后res还是0，同理i=1,2时res都是0，现在到了i=3,4mod3等于1，
	            //1左移3位等于8，res|=8即相当于0+8等于8，然后再看i等于4，1mod3等于1,1左移4位等于16，
	            //8|=16即8+16等于24.答案就是24
	            
	            res|=(bitnum[i]%3)<<i;//这里换成+=也一样
	        }
	        return res;
	    }
	  

}
