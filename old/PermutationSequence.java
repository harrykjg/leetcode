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
	//�ڶ���д��������ͦ�õģ���Ҫ�Ǹ������ֶ�Ҫ����һ������Ʒ��ȥ�޸��������Ʒ�������޸�������
	//ȥȷ����Χ��
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
		int a=k-1;//Ҫ��k-1����Ϊ����ʱ��0��ʼ���������ӿ��ݸ廹��
		int remain=0;
		for(int i=0;i<n-1;i++){//���������4λ���ȵĻ�������3�Σ���ȷ����ǰ3λ��Ȼ��������forѭ��
			div=getmod2(nn-1);//������Ǹ�û���ù������ּ���
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
	//ע���ˣ�������ԭ�����õĴ�string���͵ģ����Ƿ�������������s�Ǹ��ˣ����ǻص���һ�㷽��
	//ʱ���sû��Ӱ�죡���Ի���stringbuilder������
	//ֵ���ݷ�����http://guhanjie.iteye.com/blog/1683637
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
	//1/10,˼�뻹��һ�� k�ȼ�1��Ȼ��k/��n-1�����õ���ǰӦ��ȡ������λ�ã�
	//��Ϊtemp1����kk���ģ�����k=k%��n-1�������²�Ļ�temp1����������¹���k���ó�
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
