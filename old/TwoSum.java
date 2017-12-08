//http://blog.csdn.net/linhuanmars/article/details/19711387
//http://jixiangsanbao.wordpress.com/2014/02/27/two-sum/ �������û��ô����

import java.util.Arrays;
public class TwoSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] n={5,75,25};
		TwoSum ts=new TwoSum();
		int[] i=ts.twoSum(n, 100);
		for(int k:i){
			System.out.println(k);
		}
		
		
	}
//�ڶ���д�ģ�����֮ǰ�ʺ��곽�ģ�brute force�ᳬʱ�����Ի��ǵ�sort��Ȼ���üбƣ�������sort֮��index
	//�Ͳ����ˣ����Ե��ȸ���������飬Ȼ��sort��������Ƶ����飬�ҵ��𰸣���ȥԭ���������Ҷ�Ӧ
	//���ֵ�index��������Ŀ˵�˴���Ψһ�ģ�����һ���ҵ��˾��ǶԵ�index
	public int[] twoSum(int[] numbers, int target) {
		
		int[] num=new int[numbers.length];
		for(int i=0;i<numbers.length;i++){
			num[i]=numbers[i];
		}
		int behind=num.length-1;
		int front=0;
		Arrays.sort(num);
		
		int[] ii=new int[2];
		int[] rs=new int[2];
		while(front < behind){
			
			if(num[front]+num[behind]==target){
			 ii[0]=num[front];
			 ii[1]=num[behind];
				break;
			}
			
			else if(num[front]+num[behind]>target){
				behind--;
			}
			else{
				front++;
			}
			
		}
		boolean flag=false;//Ҫ���boolean��ԭ������0,2,3,0��0������ӣ����ÿ��i���ж���
		//��ô�𰸳�������4,4����Ϊ���һ������0������ii������������Ҫ�����boolean����¼�����
		//�ҵ��˵�һ�����ֵģ���ô�Ժ�Ͳ�������
		for(int i=0;i<numbers.length;i++){
			if(numbers[i]==ii[0]&&flag==false){
				rs[0]=i+1;
				flag=true;
				continue;
			}
			if(numbers[i]==ii[1]){
				rs[1]=i+1;
			}
		}
		if(rs[0]>rs[1]){//������Ŀ˵�˴�Ӧ������������Ҫ����ж�һ�£���������Ļ��͵�ת����
			int temp=rs[0];
			rs[0]=rs[1];
			rs[1]=temp;
		}
		return rs;

	}

}
