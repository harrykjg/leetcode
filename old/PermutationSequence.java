//http://blog.csdn.net/linhuanmars/article/details/22028697
//http://jixiangsanbao.wordpress.com/2014/07/08/permutation-sequence/
import java.util.ArrayList;

public class PermutationSequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermutationSequence ps=new PermutationSequence();
		String s=ps.getPermutation2(8, 31492);
		System.out.println(s);
	}

	ArrayList<Integer> al=new ArrayList<Integer>();
	String s="";
	public  String getPermutation(int n, int k) {

		if(n<=0){
			return null;
		}
		int kk=k-1;
		int num=getmod(n);
		if(k>num){
			return null;
		}
		for (int i=1;i<=n;i++)
		{
			al.add(i);
		}
		int b=kk;
		for(int i=n;i>1;i--){
			b=get(i,b,al);
			
		}
		s=s+al.get(0);
		return s;
	}
	
	public int getmod(int n){
		int k=n;
		for(int i=n-1;i>0;i--){
			k=k*i;
			
		}
		return k;
	}
	public int get(int n,int k,ArrayList<Integer> a){
		int m=getmod(n-1);
		int i1=k/m;
		int i2=k%m;
		s=s+al.get(i1);
		al.remove(i1);
		return i2;
	}
	//第二次写还调试了挺久的，主要是更重数字都要再设一个复制品，去修改这个复制品而不是修改他本身
	//去确定范围。
	public  String getPermutation2(int n, int k) {
		if(n<=0){
			return null;
		}
		int[] num=new int[n];
		for(int i=0;i<num.length;i++){
			num[i]=i+1;
		}
		
		StringBuilder sb = new StringBuilder(); 
		int nn=n;
		int div=0;
		int a=k-1;//要把k-1，因为数组时从0开始，具体例子看草稿还是
		int remain=0;
		for(int i=0;i<n-1;i++){//我这个比如4位长度的话就运行3次，就确定了前3位，然后再来个for循环
			div=getmod2(nn-1);//把最后那个没有用过的数字加上
			remain=a%div;
			a=a/div;
			construct(a,num,sb);
			a=remain;
			nn--;
		}
		for(int i=0;i<num.length;i++){
			if(num[i]!=0){
				sb.append(num[i]);
			}
		}
		return sb.toString();
	}
	private int getmod2(int n){
		if(n==1){
			return 1;
		}
		int nn=n;
		int nnn=n;
		
		for(int i=0;i<n-1;i++){
			nn*=nnn-1;
			nnn--;
		}
		return nn;
	}
	//注意了，这里我原来是用的传string类型的，但是发现这个方法里的s是改了，但是回到上一层方法
	//时这个s没被影响！所以换成stringbuilder就行了
	//值传递分析见http://guhanjie.iteye.com/blog/1683637
	private void construct(int index,int[] num,StringBuilder sb){
		int count=0;
		for(int i=0;i<num.length;i++){
			if(num[i]!=0&&count==index){
				sb.append(num[i]);
				num[i]=0;
				break;
			}else if(num[i]!=0){
				count++;
			}
		}
	}
	//1/10,思想还是一样 k先减1，然后k/（n-1）！得到当前应该取的数的位置，
	//因为temp1是由kk来的，所以k=k%（n-1）！，下层的话temp1就由这个更新过的k来得出
	public  String getPermutation3(int n, int k) {
		if(n<=0){
			return null;
		}
		ArrayList<Integer> p=new ArrayList<Integer>();
		for(int i=1;i<=n;i++){
			p.add(i);
		}
		int div=0;
		int kk=k-1;
		int nn=n;
		
		int temp1=0;
		
		String rs="";
		while(nn>1){
			div=getmod2(nn-1);
		    temp1=kk/div;
			kk=kk%div;
			rs=rs+p.get(temp1);
			p.remove(temp1);
			nn--;
		}
		return rs+p.get(0);
	}

}
