
//http://jixiangsanbao.wordpress.com/2014/06/23/next-permutation/
//http://blog.csdn.net/linhuanmars/article/details/20434115
//https://leetcode.com/problems/next-permutation/solution/
//https://blog.csdn.net/c18219227162/article/details/50301513  ������м������ѵ���չ

public class NextPermutationOld {
	//֪��˼·֮��һ��accept
	public void nextPermutation(int[] num) {
		if(num.length==0){
			return ;
		}
		int index1=-1;
		for(int i=num.length-1;i>0;i--){
			if(num[i]>num[i-1]){
				index1=i;
				break;
			}
		}
		if(index1==-1){
			int i=0;
			int j=num.length-1;
			while(i<j){
				int temp=num[j];
				num[j]=num[i];
				num[i]=temp;
				i++;
				j--;
			}
		}else{
			for(int i=num.length-1;i>=0;i--){
				if(num[i]>num[index1-1]){//�ҵ�һ�����num[index1-1]�Ǹ���������
					int temp=num[index1-1];
					num[index1-1]=num[i];
					num[i]=temp;
					break;
				}
			}
			int i=index1;
			int j=num.length-1;
			while(i<j){
				int temp=num[j];
				num[j]=num[i];
				num[i]=temp;
				i++;
				j--;
			}
		}
	}
}
