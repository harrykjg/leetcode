import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/24511221   ���˼����Ǹ��ƾ���ֻ�Ǵ���Ƚϼ��
//http://blog.csdn.net/linmiansheng/article/details/21746827 ���ƾ�������뷨�����������
public class GrayCode {

	public ArrayList<Integer> grayCode(int n) {
//˼·���ǣ���ʼ��������������˼ά�Ǹ����ӣ���ΪҪ�ȹ�������Ƶ����֣���ת��10���ƣ���ʵ����
		//��ΪҪ���ص���10���Ƶ�����������2���ƴ��룬�����ھ����ƶ���������ʱ����Կ�����10����
		//�������й��ɵģ����Ǿ�����ǲ�����û�����ǲ��ֳ���2��i�η������i���Ǵ�1��ʼ����
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
				al.add(al.get(j)+(1<<(i-1)));//1<<(i-1))����1����2��i-1�η���������λ����
				//�ͱ��磺�Ѷ����Ƶ�0101����5������һλ�������1010������10�ˣ���5����2��1�η�
				//����ע�����1ǰ�����˸����ű��1<<��i-1���򲻶���
				//��֪��������ôŪ�ġ�����д��(int)(Math.pow(2,i-1))Ҳ�У�ע��pow��ʽ�ǽ���
				//����double������Ҫת�ɣ�int��
			}
		}
		return al;

	}

}
