//http://blog.csdn.net/linhuanmars/article/details/23162793 ��
//http://jixiangsanbao.wordpress.com/2014/05/17/best-time-to-buy-and-sell-stock/ 
public class BestTimetoBuyandSellStock {
	//��maximum subarray�Ա�ѧϰ
public int maxProfit(int[] prices) {
        
        if(prices.length==0){
            return 0;
        }
       int loc=0;
       int glo=0;
       for(int i=0;i<prices.length-1;i++){
    	   

//15��11��������̬�滮���ǲ�̫����⣬������Ǹ�������������һЩ��
//����2��3��5��1��10��4,12������ӣ�������дʵ���Ͼ��Ǵ�����ÿ�����ڵ����������Ƚϣ�������2��3������loc��1��glo��1��
//Ȼ��3��5����������2������������֮ǰ��2��3���Ĳ��1�����ˣ�����loc��1+2=3��gloҲ��3��Ȼ��5��1������ʱ�����-4���������֮ǰ��3
//���Ǹ��������loc��Ϊ0��Ȼ��1��10���������9������loc֮ǰ���0�ˣ���������loc����9��gloҲ��9������Ҳһ������֪��Ϊʲô����ô��
//�е���
    	   loc=Math.max(loc+prices[i+1]-prices[i], 0);
    	   glo=Math.max(glo, loc);
       }
        return glo;
    }

public int maxProfit2(int[] prices) { 
    
    if(prices.length==0){
        return 0;
    }
   int min=prices[0];
   int profit=0;
   for(int i=1;i<prices.length;i++){
	   if(prices[i]-min>profit){
		   profit=prices[i]-min;
		   
	   }
	   if(prices[i]<min){
		   min=prices[i];
	   }
	
   }
    return profit;
}
}
