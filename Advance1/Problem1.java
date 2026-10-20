package Advance1;

import java.util.Arrays;

public class Problem1 {
    public int findJob2(int[] jobs, int[] servers){
        Arrays.sort(jobs).reverse();
        Arrays.sort(servers).reverse();

        int i=0;//job
        int j=0;//server
        int rs=0;
        while(i<jobs.length&&j<servers.length){
            if(jobs[i]<=servers[j]){
                rs++;
                i++;
                j++;
            }else{//job too big,skip it
                i++;
            }
        }
        return rs;
    }

    public int findJob3(int[] jobs, int[] servers, int k){
        Arrays.sort(jobs).reverse();
        Arrays.sort(servers).reverse();

        int i=0;//job
        int j=0;//server
        int rs=0;
        int count=0;
        while(i<jobs.length&&j<servers.length){
            if(jobs[i]<=servers[j]){
                rs++;
                i++;
                count++;
                if(count==k){
                    j++;
                    count=0;//reset count for the next server
                }
            }else{//job too big,skip it
                i++;
            }
        }
        return rs;
    }

    //job 4 7 8       find server for job 4 by binary search, pick the smallest energy
    //server 10 8,6,5 ---> sort by capacity
    //en 20 23 15 16
    //
      //array[]:


}
