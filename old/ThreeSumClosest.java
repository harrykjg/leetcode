import java.util.Arrays;
//��3sum����һ����ֻ�ǼӶ��gap���Ƚ϶��ѣ�brute force��n3���Ӷȣ������ȹ̶���һ������������
//���üб�ȷ��

public class ThreeSumClosest {
	public static void main(String[] args) {
		ThreeSumClosest ts=new ThreeSumClosest();
		int[] num={1,2,-1,-4};
		System.out.println(ts.threeSumClosest2(num, 1));
	}
	
	int rs=0;
	int gap=Integer.MAX_VALUE;
	public int threeSumClosest(int[] num, int target) {
		
		if(num.length<=3){
			for(int i=0;i<num.length;i++){
				rs+=num[i];
				
			}
			return rs;
		}
		Arrays.sort(num);
		for(int i=0;i<num.length;i++){
			if(i>=1&&num[i]==num[i-1]){
				continue;
			}
			find(num,num[i],i,target);
			if(gap==0){
				return target;
			}
		}
		return rs;
	}
	public void find(int[] num,int cursum,int index,int target){
		int temp=cursum;
		int end=num.length-1;
		int begin=index+1;
		while(begin<end){
			temp=cursum+num[begin]+num[end];
			if(temp==target){
				gap=0;
				return;
				
			}
			else if(Math.abs(temp-target)<gap){
				gap=Math.abs(temp-target);
				rs=temp;
			}
			if(temp>target){
				end--;
				while(end>=0&&num[end+1]==num[end]){
					end--;
				}
			}
			else{
				begin++;
				while(begin<=num.length-1&&num[begin-1]==num[begin]){
					begin++;
				}
			}
		}
	}
	//�ڶ���д,rs��Ϊһ��int���Ļ������õ�go�������²���threeSumClosest2���rs
	public int threeSumClosest2(int[] num, int target) {
		
		if(num.length<=3){
			for(int i=0;i<num.length;i++){
				rs+=num[i];
				
			}
			return rs;
		}
		Arrays.sort(num);
		for(int i=0;i<num.length-2;i++){
			go(num[i],i+1,num.length-1,target,num);
		}
		return rs;
	}
	private void go(int first,int b,int e,int target,int[] num){
		if(b>=e){//���￪ʼд��b>e�ˣ�����b=3���ԣ�����
			return;
		}
		int sum=first+num[b]+num[e];
		if(Math.abs(target-sum)<gap){
			gap=Math.abs(target-sum);
			rs=sum;
		}
		if(sum>target){
			go(first,b,e-1,target,num);
		}else{
			go(first,b+1,e,target,num);
		}
	}

}
