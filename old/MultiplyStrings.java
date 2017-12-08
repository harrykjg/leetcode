//http://jixiangsanbao.wordpress.com/2014/05/07/multiply-strings/
//http://blog.csdn.net/linhuanmars/article/details/20967763 他的方法看不懂

public class MultiplyStrings {
	
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
		//       158                 index2指的是下面那个string，index1指的是上面这个
		//      *328
		//------------
		//      1264
		//      316
		//     424
		//基本上是看了别人的思路之后自己写出来的，就是模拟乘法，自己举个例子找找规律
		for(int index2=num2.length()-1;index2>=0;index2--){
			index=a.length-1-(num2.length()-1-index2);//想一想，每次往a上赋值其实是随着index2
			//移动的，a.length-1-(num2.length()-1-index2)就是index往前挪1步，2步，3步。。即
			//index2的初始值和当前值之差
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
					a[index-2]+=1;//注意这里容易漏掉，用999*999的例子可以判断出来，这里debug看
				}else{           //999的例子可以看到是会出现这个位置存的是10，而不是0，按理来说
					            //应该是存的是0，然后进一位，这个进的位又有可能导致下一位进一位
					            
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
		if(s.charAt(0)=='0'){//这里是因为leetcode发神经吧，前面应该已经检测了如果其中一个数字是
			return "0";    //0的话，直接返回0，但是不知道为啥不行，所以这里加了判断，如果第去了
		}                  //第一个0之后，还有0，即代表整个数都是0了
		return s;	
	}
//第三轮,我以为可以写出来，结果错的.然后调试了很久。这样写很容易出错。然后我改进了！
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
//				//我这里是设了2个k，记录两种类型的进位，第一种类型就是单纯的一位数和另一位数相乘
//				//如9*9=81那么就用k来记录8.第二种是记录入//       840 这样的两行之间
//				                                       //      150
//				                                       //     150    
//				//的加法导致的进位，用k2记录，要直接写对比较难，要调试好久。
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
				//这里只用了1个k来记录，关键是如果当前rs[index1]+temp%10>=10的话，这个进位的k
				//就要加1，否则的话，这个进位的k就是temp/10.想清楚k的意义就好了，一开始k是0，
	//代表上一个数进的位，然后算当前rs[index1]数时就加上呗，加上之后就要更新下一位进的数，即
	//更新k，而k是由2部分组成，一是两个数相乘的10位数，二是rs[index1]这个数加上
	//temp%10之后产生的进位
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
			if(k!=0){//这里就是j遍历完了之后，如果k不等于0，那么就要把k填上index1这个位置，否则
				rs[index1]=(rs[index1]+k);//就是没有进位，不用填。而吉祥那个和我第一次写的那里
				k=0;//是再j这个for循环里面，发现k不等于0的话，就把当前rs[index1]的左边那位加上
			}     //k了，也是一样
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
