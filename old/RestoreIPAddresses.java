import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/24683699
//����뵽�Ǹ�1,2,3������һ���㣬ʣ�µļ����ӵ����ӣ���������dfs���е���Palindrome Partitioning
//�����ǰ��Լ����뷨д�ģ�һЩϸ�ڣ�����000��00��Ӧ��������ֻ��0����������ж�������code ganker��
//������д���������ǵĴ��붼��
public class RestoreIPAddresses {
	public static void main(String[] args) {
		RestoreIPAddresses ri=new RestoreIPAddresses();
		ri.restoreIpAddresses2("010010");
		System.out.println();
		
	}

	ArrayList<String> al=new ArrayList<String>();
	public ArrayList<String> restoreIpAddresses(String s) {

		if(s.length()<4||s.length()>12){
			return al;
		}
		int len=s.length();
		dfs(s,len,4,"");
		return al;
		
	}
	public void dfs(String s,int left1,int left2,String cur){
		if(left1<=0&&left2<=0){
			cur=cur.substring(0,cur.length()-1);//���һ���ǵ㣬����ȥ��
			al.add(cur);
		}
		int b=s.length()-left1;
		for(int i=1;i<=3&&i<=left1;i++){
			String s1=s.substring(b,i+b);
			if(left1-i>(left2-1)*3||(s1.length()>1&&s1.charAt(0)=='0')){//���ȥ��i������Ϊ
				//��һ��ip�����ʣ�µ����ֱ�ʣ�µĶ�������3��Ҫ��Ļ������С��������s1�ĳ���
				//�������1�Ļ������ҵ�һ��ֵ��0������ȥ����Ϊ000��00����0�������ˣ������001����
		//01������Ҳ���У�ӦΪip�Ͳ���������ʾ.����Ӧ�û���д��left1<��left2-1��*1�������
				continue;
			}
			if(Integer.parseInt(s1)<=255){
				dfs(s,left1-i,left2-1,cur+s1+".");
			}
		}
	}
//�ڶ���д�����Լ��������,˼·ok������ʵ������Щϸ�����⣬��һ������Ԫ�ַ���ʣ�¶���λ�ͻ�Ҫ�ּ���
	//������Ϊ�����ģ�������������һ��rs�ַ�������¼��ǰ�õ����ֽ׶εĽ���������Ƕ��ˡ�.���ģ����
	//�жϳ����ϵĺܶ��鷳
	public ArrayList<String> restoreIpAddresses2(String s) {
		ArrayList<String> al=new ArrayList<String>();
		if(s.length()<4||s.length()>12){
			return al;
		}
		go(1,s,0,"",al);
		return al;
	}
	private void go(int cur,String s,int begin,String rs,ArrayList<String> al){
		if(begin==s.length()&&cur==5){
			al.add(rs.substring(0,rs.length()-1));
			return;
		}
		for(int i=1;i<=3&&i+begin<=s.length();i++){//���i+begin<s.length©��
			if(i>1&&s.charAt(begin)=='0'){
				continue;
			}
			String temp=s.substring(begin,begin+i);
			rs=rs+temp+".";//��������rs�Ǽ���cur����ģ����������漰rs�ĳ��ȵĶ�Ҫ��ȥcur
			if(Integer.parseInt(temp)<=255&&(s.length()-(rs.length()-cur))>=(4-cur)&&
					(s.length()-rs.length()-cur)<=(4-cur)*3&&cur<5){
				go(cur+1,s,rs.length()-cur,rs,al);
			}
			rs=rs.substring(0,rs.length()-i-1);//����rs.length()-i-1����˼�Ǳ��統ǰ����
			//2λ����������2λ�Ҽ�ȥ1����
		}
	}
}
