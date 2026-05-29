package SomeInterviews.snowflake;

import java.util.*;

public class RateLimiter {
    /*
Imagine you're running a social media website and it just went viral. You've noticed that the
incoming traffic is higher than you've ever designed your
servers• for so you decide to add a rate limiter to prevent your website going out of service.
Now, you want to analyze the impact of your rate limiter• and see how many requests to your website get dropped.
# Given• an• array where each index corresponds to request id and each value is the time when the request arrived,
• return the times of dropped requests giventhe following rules:
#There • can • be max • 3• requests per second
# There can't • be more than 20• requests per 10• seconds
     */
    //gpt说应该不是基于id的，request的index就是id，value就是time。那样的话1个q能处理吗？好想不行
    public List<Integer> limit(int[] requests ){
        List<Integer> rs=new ArrayList<>();
        Arrays.sort(requests);
        Queue<Integer> q1=new LinkedList<>();//q1单独处理1秒内的情况，就是只能有三个
        Queue<Integer> q2=new LinkedList<>();
        for (int i=0;i<requests.length;i++){
            if(!q1.isEmpty()&&q1.peek()==requests[i]){//是同一秒的才看是否小于等于2
                if(q1.size()<=2){
                    //测一秒只能有三个的条件，不能现在就加入，否则犯了和roblox一样的错
                }else{
                    rs.add(requests[i]);
                    continue;
                }
            }else{
                q1.clear();
            }

            //测第二个条件
            while (!q2.isEmpty()&&requests[i]-q2.peek()>=10){
                q2.poll();
            }
            boolean good2=false;
            if(q2.size()<20){
                good2=true;
            }
            if(good2){
                q1.offer(requests[i]);
                q2.offer(requests[i]);
            }else {
                rs.add(requests[i]);
            }
        }

        return rs;

    }

}
