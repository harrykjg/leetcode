//Third. Subtraction of two arrays.
//Suppose you want to do the subtraction of two numbers. Eachdigit of the numbers is 
//divided and put in an array. Like A=[1, 2, 3, 4, 5],B=[4, 5, 3, 5]. You should output an 
//array C=[7, 8, 1, 0]. Remember that yourmachine can��t hand numbers larger than 20.


public class SubtractionOfTwoArray {
	
	public static void main(String[] args) {
		SubtractionOfTwoArray so=new SubtractionOfTwoArray();
		int[]a1={1,2,3,4,5};
		int[] a2={7,8,1,0};
		int[] rs=so.subtract(a1, a2);
		for(int a:rs){
			System.out.print(a);
		}
	}
	
	public int[] subtract(int[] a1,int[] a2){
		//����a1���ȴ��ڵ���a2,��a1����Ҫ����a2
		//�����Ȱ�2���������������
		int [] rs=new int[a1.length];
		int gap=a1.length-a2.length;
		int i2=0;
		for(int i=a1.length-1;i>=0;i--){
			i2=i-gap;//i2��Ϊ�˶�Ӧ�̵��Ǹ���������һλ
			if(i2>=0){
				if(a1[i]>=a2[i2]){
					rs[i]=a1[i]-a2[i2];
				}else{	
					rs[i]=a1[i]+10-a2[i2];
					int pre=i-1;//������һλ�Ļ�����Ҫ�п�����һλ���ý���һλ������Ҫ�ø�while
					while(pre>=0){//����pre�������һλ
						if(a1[pre]-1<0){
							a1[pre]=a1[pre]+10-1;
							
						}else{
							a1[pre]=a1[pre]-1;
							break;
						}
						pre--;
					}
				}	
			}else{
				rs[i]=a1[i];
			}
		}
		return rs;
		
		
	}

}
