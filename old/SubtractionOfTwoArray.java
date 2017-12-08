//Third. Subtraction of two arrays.
//Suppose you want to do the subtraction of two numbers. Eachdigit of the numbers is 
//divided and put in an array. Like A=[1, 2, 3, 4, 5],B=[4, 5, 3, 5]. You should output an 
//array C=[7, 8, 1, 0]. Remember that yourmachine can’t hand numbers larger than 20.


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
		//假设a1长度大于等于a2,且a1的数要大于a2
		//可以先把2个数都算出来再做
		int [] rs=new int[a1.length];
		int gap=a1.length-a2.length;
		int i2=0;
		for(int i=a1.length-1;i>=0;i--){
			i2=i-gap;//i2是为了对应短的那个数组的最后一位
			if(i2>=0){
				if(a1[i]>=a2[i2]){
					rs[i]=a1[i]-a2[i2];
				}else{	
					rs[i]=a1[i]+10-a2[i2];
					int pre=i-1;//借了上一位的话，就要有可能上一位还得借上一位，所以要用个while
					while(pre>=0){//，用pre来标记上一位
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
