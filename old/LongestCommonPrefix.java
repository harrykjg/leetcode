public class LongestCommonPrefix {
	public static void main(String[] args) {
		LongestCommonPrefix lc=new LongestCommonPrefix();
		String[] strs={"ac","ac","a","a"};
		System.out.println(lc.longestCommonPrefix(strs));
	}
	//��������Ҳ��ֱ�۵��뷨������ûʲô�ر�õķ���
	public String longestCommonPrefix(String[] strs) {
		
		if(strs.length==0){
			return "";
		}
		if(strs.length==1){
			return strs[0];
		}
		String rs="";
		int l1=strs[0].length();//����strs����������Ԫ����
		int l2=strs[1].length();
		if(l1>l2){
			rs=strs[1];
		}else{
			rs=strs[0];
		}
		
		for(int i=0;i<rs.length();i++){//���ҵ���һ��2����prefix����rs
			if(strs[0].charAt(i)!=strs[1].charAt(i)&&i==0){
				return "";
			}
			else if(strs[0].charAt(i)!=strs[1].charAt(i)){
				rs=rs.substring(0,i);
				break;
			}
		}
		//�������rs�͵�3��Ԫ�ؿ�ʼ�Ƚ�
		for(int i=2;i<strs.length;i++){
			if(strs[i]==""){
				return "";
			}
			int min=Math.min(rs.length(), strs[i].length());
			for(int j=0;j<min;j++){
				if(strs[i].charAt(j)!=rs.charAt(j)&&j==0){
					return "";
				}
				else if(strs[i].charAt(j)!=rs.charAt(j)){
					rs=rs.substring(0,j);
					break;
				}
				else if(j+1==min){//��������rs��abcd����Ҫ�Ƚϵ��Ǹ���abc������̣���ǰ�����ĸ��
					//һ���������
					rs=rs.substring(0,j+1);
				}
			}
		}
		return rs;

	}
	//�ڶ���д����������һ��
	public String longestCommonPrefix2(String[] strs) {
		if(strs.length==0){
			return "";
		}
		if(strs.length==1){
			return strs[0];
		}
		boolean flag=true;
		
		String s="";
		
		for(int i=0;i<strs[0].length();i++){
			char c=strs[0].charAt(i);//��ʵ���õ�һ��Ԫ����Ϊ����׼�����ˣ��������ĳ�����Σ�
			for(int j=0;j<strs.length;j++){//��һ�£����˶��˶�һ����
				//Ȼ���õ�һ���ַ����ĵ�i���ַ�����ʣ�µ������ַ����ĵ�i���ַ��Ƚϣ����˵Ļ�
				//�Ϳ϶�����common prefix������break�ˣ����ߺ�����ַ����Ƚ϶̣�Ҳbreak
				if(i<strs[j].length()&&c!=strs[j].charAt(i)){
					flag=false;
					break;
				}else if(i>=strs[j].length()){
					flag=false;
					break;
				}
			}
			if(flag){
				s=s+c;
			}else{//�����ǰ��i���ַ��Ѿ��������ˣ���common prefixҲ���˽�����break
				break;
			}
		}
		return s;
	}

}
