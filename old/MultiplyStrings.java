//http://jixiangsanbao.wordpress.com/2014/05/07/multiply-strings/
//http://blog.csdn.net/linhuanmars/article/details/20967763 ���ķ���������

public class MultiplyStringsOld {
	
	public static void main(String[] args) {
		MultiplyStrings ms=new MultiplyStrings();
		System.out.println(ms.multiply2("123", "456"));
		
	}
	
	public String multiply(String num1, String num2) {
		if(num1.length()==0||num2.length()==0){
			return "";
		}
		if(num1=="0"||num2=="0"){
			return "0";
		}
		int[] a=new int[num1.length()+num2.length()];
		int index=a.length-1;
		//       158                 index2ָ���������Ǹ�string��index1ָ�����������
		//      *328
		//------------
		//      1264
		//      316
		//     424
		//�������ǿ��˱��˵�˼·֮���Լ�д�����ģ�����ģ��˷����Լ��ٸ��������ҹ���
		for(int index2=num2.length()-1;index2>=0;index2--){
			index=a.length-1-(num2.length()-1-index2);//��һ�룬ÿ����a�ϸ�ֵ��ʵ������index2
			//�ƶ��ģ�a.length-1-(num2.length()-1-index2)����index��ǰŲ1����2����3��������
			//index2�ĳ�ʼֵ�͵�ǰֵ֮��
			for(int index1=num1.length()-1;index1>=0;index1--){
				int count=0;
				int i1=num1.charAt(index1)-'0';
				int i2=num2.charAt(index2)-'0';
				
				if(a[index]+i1*i2%10>=10){
					a[index]+=i1*i2%10-10;
					count=1;
				}else{
					a[index]+=i1*i2%10;
				}
				if(a[index-1]+count+i1*i2/10>=10){
					a[index-1]+=i1*i2/10+count-10;
					a[index-2]+=1;//ע����������©������999*999�����ӿ����жϳ���������debug��
				}else{           //999�����ӿ��Կ����ǻ�������λ�ô����10��������0��������˵
					            //Ӧ���Ǵ����0��Ȼ���һλ���������λ���п��ܵ�����һλ��һλ
					            
					a[index-1]+=i1*i2/10+count;
				}
				index--;
			}	
		}
		String s="";
		for(int i=0;i<a.length;i++){
			s=s+a[i];
		}
		if(s.charAt(0)=='0'){
			s=s.substring(1);
		}
		if(s.charAt(0)=='0'){//��������Ϊleetcode���񾭰ɣ�ǰ��Ӧ���Ѿ�������������һ��������
			return "0";    //0�Ļ���ֱ�ӷ���0�����ǲ�֪��Ϊɶ���У�������������жϣ������ȥ��
		}                  //��һ��0֮�󣬻���0������������������0��
		return s;	
	}
//������,����Ϊ����д������������.Ȼ������˺ܾá�����д�����׳���Ȼ���ҸĽ��ˣ�
	public String multiply2(String num1, String num2) {
		
		if(num1.length()==0||num2.length()==0){
			return null;
		}
		int[] rs=new int[num1.length()+num2.length()];
		int index1=rs.length-1;
		int index2=num1.length()-1;
		int index3=num2.length()-1;
		int k=0;
		int k2=0;
		for(int i=index2;i>=0;i--){
			index1=rs.length-1-(index2-i);
			for(int j=index3;j>=0;j--){
//				int temp=Integer.parseInt(num1.substring(i,i+1))*Integer.parseInt(
//						num2.substring(j,j+1));
//				int temp2=(temp+k)%10;
//				//������������2��k����¼�������͵Ľ�λ����һ�����;��ǵ�����һλ������һλ�����
//				//��9*9=81��ô����k����¼8.�ڶ����Ǽ�¼��//       840 ����������֮��
//				                                       //      150
//				                                       //     150    
//				//�ļӷ����µĽ�λ����k2��¼��Ҫֱ��д�ԱȽ��ѣ�Ҫ���Ժþá�
//				if(rs[index1]+temp2+k2>=10){
//					rs[index1]=rs[index1]+temp2+k2-10;
//					k2=1;
//				}else{
//					rs[index1]+=temp2+k2;
//					k2=0;
//				}
//				
//				k=(temp+k)/10;
//				index1--;
//				
//			}
//			if(k!=0||k2!=0){
//				rs[index1]=(rs[index1]+k+k2);
//				k=0;
//				k2=0;
//			}
				//����ֻ����1��k����¼���ؼ��������ǰrs[index1]+temp%10>=10�Ļ��������λ��k
				//��Ҫ��1������Ļ��������λ��k����temp/10.�����k������ͺ��ˣ�һ��ʼk��0��
	//������һ��������λ��Ȼ���㵱ǰrs[index1]��ʱ�ͼ����£�����֮���Ҫ������һλ����������
	//����k����k����2������ɣ�һ����������˵�10λ��������rs[index1]���������
	//temp%10֮������Ľ�λ
				int temp=(num1.charAt(i)-'0')*(num2.charAt(j)-'0')+k;
				if(rs[index1]+temp%10>=10){
					rs[index1]=rs[index1]+temp%10-10;
					k=temp/10+1;
				}else{
					rs[index1]+=temp%10;
					k=temp/10;
				}
				index1--;
			}
			if(k!=0){//�������j��������֮�����k������0����ô��Ҫ��k����index1���λ�ã�����
				rs[index1]=(rs[index1]+k);//����û�н�λ��������������Ǹ����ҵ�һ��д������
				k=0;//����j���forѭ�����棬����k������0�Ļ����Ͱѵ�ǰrs[index1]�������λ����
			}     //k�ˣ�Ҳ��һ��
		}
		
		String s="";
		int i=rs[0]==0?1:0;
		for(;i<rs.length;i++){
			s=s+rs[i];
		}
		if(s.charAt(0)=='0'){
			return "0";
		}
		return s;
	}
}
