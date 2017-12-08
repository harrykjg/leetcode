public class GasStation {
//http://blog.csdn.net/linhuanmars/article/details/22706553
	public static void main(String[] args) {
		
		GasStation gs=new GasStation();
		int[] a=new int[]{2,4};
		
		int[] b=new int[]{3,4};
		
		int r=gs.canCompleteCircuit3(a,b);
		System.out.println(r);
		

	}
//Ӧ�����Լ���ķ����ɣ���code ganker���ǵĲ�һ���ɣ�����ʱ�伸��һ��
	static int  flag=0;
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		
		int start=0;
		int temp=0;
		while(start<gas.length){
			boolean b=check(start,gas,cost);
			if(b==true){
				
				return start;
			}
			temp=flag+1;
			if(temp>start){//����1��2��3��4���У���5�Ͳ����ˣ���2��5,3��5,4��5�϶�Ҳ���У�
				//����2345�����ü���ˣ�����ֱ��������5��ʼ
				start=temp;
			}else{
				start++;
			}

			
		}
		return -1;
		
	}
	
	static boolean check(int s,int[] gas, int[] cost){
		int tank=0;
		
		for(int i=s;i<=gas.length-1;){
			tank+=gas[i];
			tank=tank-cost[i];
			if(tank<0){
				flag=i;//��¼�²��е������
				return false;
			}
			i=(i+1)%gas.length;
			if(i==s){
				break;
			}
		}
		return true;
		
	}
	//�ڶ��λ��ǿ��˵�һ�ε�˼·����дһ��,�е��Ť�о�

	public  int canCompleteCircuit2(int[] gas, int[] cost) {
		for(int i=0;i<gas.length;i++){//i����ӵ�i���㿪ʼ���
			
			int start=i;
			int count=0;//��¼���˵Ĳ���
			int sum=0;
			while(count<gas.length){
				count++;
				if(sum+gas[start%gas.length]-cost[start%gas.length]<0){
					i=start;//��¼��������еĵ㣬��һ�����Ǵ����start+1��ʼ����ˣ�ע�⣬����
					break;//iָ���Ǵӵ�i���㵽��i+1�ĵ�ʧ�ܣ�����˵i=4�㵽i=5ʧ�ܣ���ôӦ��
				}        //��¼����i=4����Ϊforѭ�����ټ�1����һ�ξ��Ǵ�i=5��ʼ�����
				//��ʼ�������iҪ��Ҫд��i=start%gas.length����һ��gas={2,4}��cost={3,4}��֪��
				//�����ˡ������i����gas.length��ʱ�򣬲�ȡ��Ļ������¸�forѭ���ͽ����ˣ�˵��
				//0������;�����Թ��˲��У���ȡ��Ļ��Ͱ�i�ұ�ɴӵ�һ��Ԫ�ؿ�ʼ����ˣ�����ѭ��
				//���룬������8��������6����8���У��ٵ���һ��Ҳ�У������˵ڶ��Ͳ����ˣ���
				//6,7,8,1,2������ж����У���������ôֻ�п����Ǵ�3,4,5��ʼ���п����У���������
				//�������ȼ����3,4,5�ģ�������˵Ļ�������ˣ����Բ��õ������iӦ����ô��
				if(count==gas.length){
					return i;
				}
				sum+=gas[start%gas.length]-cost[start%gas.length];
				
				start++;
			}
			
		}
		return -1;
	}
	//������,һ��tle,Ȼ������˼�����ǿ����
	 public int canCompleteCircuit3(int[] gas, int[] cost) {
	        int tank=0;
	        if(gas.length==0||cost.length==0){
	        	return -1;
	        }
	        
	        for(int i=0;i<gas.length;i++){//��Ҫ������������ѭ���е��ƣ�����յ�Ŀ������⻹�� 
	        	int count=0;   //ȡ�������
	        	int k=i;
	        	tank=gas[i];
	        	
	        	while(count<gas.length){
	        		
	        		if(tank<cost[k%gas.length]){
	        			if(k>i){
	        				i=k;//����˵���м俪ʼ���Ƶ�ǰ��Ų��У�����������еĵ㻹��С�����i
	        			}//�ģ���ô�Ͳ��ùܣ���Ϊһ��ü����ˣ�ֻ��������еĵ����i�˲���Ҫ����
	        			break;
	        		}else{
	        			tank=tank-cost[k%gas.length]+gas[(k+1)%gas.length];
	        		}
	        		count++;
	        		k++;
	        	}
	        	if(count==gas.length){
	        		return i;
	        	}
	        }
	        return -1;
	    }

}
